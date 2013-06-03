/*******************************************************************************
 * @author Reika Kalseki
 * 
 * Copyright 2013
 * 
 * All rights reserved.
 * 
 * Distribution of the software in any form is only allowed
 * with explicit, prior permission from the owner.
 ******************************************************************************/
package Reika.RotaryCraft.TileEntities;

import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;
import Reika.DragonAPI.Interfaces.GuiController;
import Reika.DragonAPI.Libraries.ReikaBlockHelper;
import Reika.DragonAPI.Libraries.ReikaEnchantmentHelper;
import Reika.DragonAPI.Libraries.ReikaInventoryHelper;
import Reika.DragonAPI.Libraries.ReikaItemHelper;
import Reika.DragonAPI.Libraries.ReikaMathLibrary;
import Reika.DragonAPI.Libraries.ReikaWorldHelper;
import Reika.RotaryCraft.MachineRegistry;
import Reika.RotaryCraft.RotaryCraft;
import Reika.RotaryCraft.Auxiliary.EnchantableMachine;
import Reika.RotaryCraft.Base.RotaryModelBase;
import Reika.RotaryCraft.Base.TileEntityBeamMachine;

public class TileEntityBorer extends TileEntityBeamMachine implements EnchantableMachine, GuiController {

	private HashMap<Enchantment,Integer> enchantments = new HashMap<Enchantment,Integer>();

	private int pipemeta2 = 0;

	public boolean drops = true;

	public int reqpow;
	public int mintorque;

	/** Power required to break a block, per 0.1F hardness */
	public static final int DIGPOWER = 64;
	public static final int OBSIDIANTORQUE = 512;

	/** Rate of conversion - one power++ = 1/falloff ++ range - not used by this machine*/
	public static final int FALLOFF = 1024; //1kW per meter right now

	public int step = 1;


	/** Digging Profile */
	public int mode;

	public boolean[][] cutShape = new boolean[7][5]; // 7 cols, 5 rows

	@Override
	public void updateEntity(World world, int x, int y, int z, int meta) {
		super.updateTileEntity();
		//ReikaJavaLibrary.pConsole(this.hasEnchantments());
		tickcount++;
		this.getIOSides(world, x, y, z, meta);
		this.getPower(false, false);
		power = omega*torque;
		boolean nodig = true;
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 5; j++) {
				if (cutShape[i][j]) {
					nodig = false;
					i = j = 7;
				}
			}
		}
		if (this.hasEnchantments()) {
			for (int i = 0; i < 8; i++) {
				world.spawnParticle("portal", -0.5+x+2*par5Random.nextDouble(), y+par5Random.nextDouble(), -0.5+z+2*par5Random.nextDouble(), 0, 0, 0);
			}
		}
		if (nodig)
			return;
		//ModLoader.getMinecraftInstance().thePlayer.addChatMessage(String.valueOf(cutShape[0][0]));
		if (omega <= 0)
			return;
		if (this.operationComplete((int)(tickcount*ReikaEnchantmentHelper.getEfficiencyMultiplier(this.getEnchantment(Enchantment.efficiency))), 0)) {
			this.calcReqPower(world, x, y, z, meta);
			//ModLoader.getMinecraftInstance().ingameGUI.addChatMessage(String.format("%d", this.reqpow));
			if (power > reqpow && reqpow != -1)
				this.dig(world, x, y, z, meta);
			tickcount = 0;
			if (reqpow == -1)
				step = ReikaMathLibrary.extrema(step-1, 1, "max");
		}
	}

	public void reqPowAdd(World world, int xread, int yread, int zread, int metadata) {
		if (world.getBlockId(xread, yread, zread) != 0 && !ReikaMathLibrary.isValueInsideBoundsIncl(8, 11, world.getBlockId(xread, yread, zread))) {
			reqpow += (int)(DIGPOWER*10*Block.blocksList[world.getBlockId(xread, yread, zread)].getBlockHardness(world, xread, yread, zread));
			if (ReikaMathLibrary.ceil2exp((int)(16*10*Block.blocksList[world.getBlockId(xread, yread, zread)].getBlockHardness(world, xread, yread, zread))) > mintorque)
				mintorque = ReikaMathLibrary.ceil2exp((int)(16*10*Block.blocksList[world.getBlockId(xread, yread, zread)].getBlockHardness(world, xread, yread, zread)));
			if (world.getBlockId(xread, yread, zread) == 7)
				reqpow = -1;
		}
	}

	public void calcReqPower(World world, int x, int y, int z, int metadata) {
		reqpow = 0;
		int lowtorque = -1;
		int a = 0;
		if (metadata > 1)
			a = 1;
		int b = 1-a;
		int xread;
		int yread;
		int zread;
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 5; j++) {
				if (cutShape[i][j] || step == 1) {
					xread = x+step*xstep+a*(i-3); yread = y+step*ystep+(4-j); zread = z+step*zstep+b*(i-3);
					this.reqPowAdd(world, xread, yread, zread, metadata);
				}
			}
		}

		if (torque < lowtorque)
			reqpow = -1;
		//ModLoader.getMinecraftInstance().ingameGUI.addChatMessage(String.format("%d", lowtorque));
	}



	public void support(World world, int x, int y, int z, int metadata) {
		int a = 0;
		if (metadata > 1)
			a = 1;
		int b = 1-a;
		int xread;
		int yread;
		int zread;
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 5; j++) {
				if (cutShape[i][j] || step == 1) {
					xread = x+step*xstep+a*(i-3); yread = y+step*ystep+(4-j); zread = z+step*zstep+b*(i-3);
					int id = world.getBlockId(xread, yread+1, zread);
					if (id == Block.sand.blockID || id == Block.gravel.blockID)
						if (this.checkTop(i, j)) {
							if (id == Block.sand.blockID)
								ReikaWorldHelper.legacySetBlockWithNotify(world, xread, yread+1, zread, Block.sandStone.blockID);
							else
								ReikaWorldHelper.legacySetBlockWithNotify(world, xread, yread+1, zread, Block.stone.blockID);
						}
				}
			}
		}
	}

	private boolean checkTop(int i, int j) {
		while (j > 0) {
			j--;
			if (cutShape[i][j])
				return false;
		}
		return true;
	}

	public void dropBlocks(int xread, int yread, int zread, World world, int x, int y, int z, int id, int meta) {
		if (drops && id != 0) {
			if (id == Block.mobSpawner.blockID) {
				TileEntityMobSpawner spw = (TileEntityMobSpawner)world.getBlockTileEntity(xread, yread, zread);
				if (spw != null) {
					if (world.isRemote)
						return;
					MobSpawnerBaseLogic lgc = spw.func_98049_a();
					String mob = lgc.getEntityNameToSpawn();
					//ModLoader.getMinecraftInstance().thePlayer.addChatMessage(String.format("%s", mob));
					int dmg = -1;
					if (mob == "Zombie")
						dmg = 0;
					if (mob == "Spider")
						dmg = 1;
					if (mob == "CaveSpider")
						dmg = 2;
					if (mob == "Skeleton")
						dmg = 3;
					if (mob == "Silverfish")
						dmg = 4;
					if (mob == "Blaze")
						dmg = 5;

					if (dmg == -1)
						return;
					ItemStack is = new ItemStack(RotaryCraft.spawner.itemID, 1, dmg);
					EntityItem ent = new EntityItem(world, x, y, z, is);
					ent.delayBeforeCanPickup = 10;
					if (!this.chestCheck(world, x, y, z, is))
						world.spawnEntityInWorld(ent);
					return;
				}
			}
			int metaread = world.getBlockMetadata(xread, yread, zread);
			int dropid = Block.blocksList[id].idDropped(id, par5Random, this.getEnchantment(Enchantment.fortune));
			int dropmeta = Block.blocksList[id].damageDropped(metaread);
			int dropsize = Block.blocksList[id].quantityDropped(par5Random);
			if (this.getEnchantment(Enchantment.silkTouch) > 0 && ReikaBlockHelper.canSilkTouch(id, metaread)) {
				dropid = id;
				dropmeta = metaread;
			}
			ItemStack is = new ItemStack(dropid, dropsize, dropmeta);
			if (dropid != 0) {
				if (!this.chestCheck(world, x, y, z, is)) {
					if (this.getEnchantment(Enchantment.silkTouch) <= 0)
						Block.blocksList[id].dropBlockAsItem(world, x, y+1, z, metaread, this.getEnchantment(Enchantment.fortune));
					else {
						ReikaItemHelper.dropItem(world, x+0.5, y+1, z+0.5, is);
					}
				}
			}
		}
	}

	private boolean chestCheck(World world, int x, int y, int z, ItemStack is) {
		TileEntity te = world.getBlockTileEntity(x+1, y, z);
		IInventory ii;
		if (te instanceof IInventory) {
			ii = (IInventory)te;
			if (ReikaInventoryHelper.addToIInv(is, ii))
				return true;
		}
		te = world.getBlockTileEntity(x-1, y, z);
		if (te instanceof IInventory) {
			ii = (IInventory)te;
			if (ReikaInventoryHelper.addToIInv(is, ii))
				return true;
		}
		te = world.getBlockTileEntity(x, y+1, z);
		if (te instanceof IInventory) {
			ii = (IInventory)te;
			if (ReikaInventoryHelper.addToIInv(is, ii))
				return true;
		}
		te = world.getBlockTileEntity(x, y-1, z);
		if (te instanceof IInventory) {
			ii = (IInventory)te;
			if (ReikaInventoryHelper.addToIInv(is, ii))
				return true;
		}
		te = world.getBlockTileEntity(x, y, z+1);
		if (te instanceof IInventory) {
			ii = (IInventory)te;
			if (ReikaInventoryHelper.addToIInv(is, ii))
				return true;
		}
		te = world.getBlockTileEntity(x, y, z-1);
		if (te instanceof IInventory) {
			ii = (IInventory)te;
			if (ReikaInventoryHelper.addToIInv(is, ii))
				return true;
		}
		return false;
	}

	public void dig(World world, int x, int y, int z, int metadata) {
		this.support(world, x, y, z, metadata);
		int a = 0;
		if (metadata > 1)
			a = 1;
		int b = 1-a;
		int xread;
		int yread;
		int zread;

		if (step == 1) {
			pipemeta2 = pipemeta;
			pipemeta = 3;
		}
		else if (pipemeta > 2 && pipemeta2 != 3)
			pipemeta = pipemeta2;

		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 5; j++) {
				if (cutShape[i][j] || step == 1) {
					xread = x+step*xstep+a*(i-3); yread = y+step*ystep+(4-j); zread = z+step*zstep+b*(i-3);
					this.dropBlocks(xread, yread, zread, world, x, y, z, world.getBlockId(xread, yread, zread), world.getBlockMetadata(xread, yread, zread));
					ReikaWorldHelper.legacySetBlockAndMetadataWithNotify(world, xread, yread, zread, RotaryCraft.miningpipe.blockID, pipemeta);
				}
			}
		}
		step++;
	}

	public boolean applyEnchants(ItemStack is) {
		if (ReikaEnchantmentHelper.hasEnchantment(Enchantment.fortune, is)) {
			enchantments.put(Enchantment.fortune, ReikaEnchantmentHelper.getEnchantmentLevel(Enchantment.fortune, is));
			return true;
		}
		if (ReikaEnchantmentHelper.hasEnchantment(Enchantment.silkTouch, is)) {
			enchantments.put(Enchantment.silkTouch, ReikaEnchantmentHelper.getEnchantmentLevel(Enchantment.silkTouch, is));
			return true;
		}
		if (ReikaEnchantmentHelper.hasEnchantment(Enchantment.efficiency, is))	 {
			enchantments.put(Enchantment.efficiency, ReikaEnchantmentHelper.getEnchantmentLevel(Enchantment.efficiency, is));
			return true;
		}
		return false;
	}

	public HashMap<Enchantment,Integer> getEnchantments() {
		return enchantments;
	}

	/**
	 * Writes a tile entity to NBT.
	 */
	@Override
	public void writeToNBT(NBTTagCompound NBT)
	{
		super.writeToNBT(NBT);
		NBT.setInteger("mode", mode);
		NBT.setBoolean("drops", drops);
		NBT.setInteger("step", step);
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 5; j++)
				NBT.setBoolean("cut"+String.valueOf(i*7+j), cutShape[i][j]);
		}
		for (int i = 0; i < Enchantment.enchantmentsList.length; i++) {
			if (Enchantment.enchantmentsList[i] != null) {
				int lvl = this.getEnchantment(Enchantment.enchantmentsList[i]);
				NBT.setInteger(Enchantment.enchantmentsList[i].getName(), lvl);
			}
		}
	}

	/**
	 * Reads a tile entity from NBT.
	 */
	@Override
	public void readFromNBT(NBTTagCompound NBT)
	{
		super.readFromNBT(NBT);
		mode = NBT.getInteger("mode");
		drops = NBT.getBoolean("drops");
		step = NBT.getInteger("step");
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 5; j++)
				cutShape[i][j] = NBT.getBoolean("cut"+String.valueOf(i*7+j));
		}
		enchantments = new HashMap<Enchantment,Integer>();
		for (int i = 0; i < Enchantment.enchantmentsList.length; i++) {
			if (Enchantment.enchantmentsList[i] != null) {
				int lvl = NBT.getInteger(Enchantment.enchantmentsList[i].getName());
				enchantments.put(Enchantment.enchantmentsList[i], lvl);
			}
		}
	}

	@Override
	public void makeBeam(World world, int x, int y, int z, int meta) {}

	@Override
	public boolean hasModelTransparency() {
		return false;
	}

	@Override
	public RotaryModelBase getTEModel(World world, int x, int y, int z) {
		return null;
	}

	@Override
	public void animateWithTick(World world, int x, int y, int z) {

	}

	@Override
	public int getMachineIndex() {
		return MachineRegistry.BORER.ordinal();
	}

	@Override
	public boolean hasEnchantment(Enchantment e) {
		return this.getEnchantments().containsKey(e);
	}

	@Override
	public int getEnchantment(Enchantment e) {
		if (!this.hasEnchantment(e))
			return 0;
		else
			return this.getEnchantments().get(e);
	}

	@Override
	public boolean hasEnchantments() {
		for (int i = 0; i < Enchantment.enchantmentsList.length; i++) {
			if (Enchantment.enchantmentsList[i] != null) {
				if (this.getEnchantment(Enchantment.enchantmentsList[i]) > 0)
					return true;
			}
		}
		return false;
	}

	@Override
	public int getRedstoneOverride() {
		if (torque < mintorque)
			return 15;
		if (power < reqpow)
			return 15;
		return 0;
	}
}
