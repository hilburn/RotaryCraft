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

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import Reika.DragonAPI.Interfaces.GuiController;
import Reika.DragonAPI.Libraries.ReikaWorldHelper;
import Reika.RotaryCraft.MachineRegistry;
import Reika.RotaryCraft.RotaryConfig;
import Reika.RotaryCraft.Auxiliary.RangedEffect;
import Reika.RotaryCraft.Base.RotaryModelBase;
import Reika.RotaryCraft.Base.TileEntityPowerReceiver;
import Reika.RotaryCraft.Models.ModelDetector;

public class TileEntityPlayerDetector extends TileEntityPowerReceiver implements GuiController, RangedEffect {
	public static final int FALLOFF = 1024; // 1kW per meter range
	public static final int SPEEDFACTOR = 32; //32 rad/s per -tick
	public static final int BASESPEED = 100; //5s reaction time by default

	public boolean analog = false;
	public boolean isActive = false;
	public int powerLevel = 0;
	public int selectedrange;

	/** Used to determine reaction time */
	private int ticksdetected = 0;

	@Override
	public void updateEntity(World world, int x, int y, int z, int meta) {
		super.updateTileEntity();
		//ModLoader.getMinecraftInstance().thePlayer.addChatMessage("Run");

		//ModLoader.getMinecraftInstance().thePlayer.addChatMessage(String.format("%d", this.selectedrange));
		this.getPowerBelow();
		ReikaWorldHelper.causeAdjacentUpdates(world, x, y, z);
		if (power < MINPOWER) {
			isActive = false;
			ticksdetected = 0;
			return;
		}
		if (!analog) {
			powerLevel = 0;
			if (this.checkForPlayers(world, x, y, z)) {
				ticksdetected++;
				if (ticksdetected >= this.getReactionTime())
					isActive = true;
			}
			else {
				isActive = false;
				ticksdetected = 0;
			}
		}
		else {
			isActive = false;
			int level = this.countPlayers(world, x, y, z);
			if (level > 0) {
				ticksdetected++;
				if (ticksdetected >= this.getReactionTime())
					powerLevel = level;
			}
			else {
				powerLevel = 0;
				ticksdetected = 0;
			}
		}
	}

	public int getReactionTime() { //with current numbers maxes to instant raction at 3200 rad/s
		int time = BASESPEED - (omega/SPEEDFACTOR);
		if (time < 1)
			time = 1;
		return time;
	}

	private int countPlayers(World world, int x, int y, int z) {
		int range = this.getRange();
		AxisAlignedBB box = AxisAlignedBB.getBoundingBox(x, y, z, x+1, y+1, z+1).expand(range, range, range);
		List inbox;
		//if (world.isRemote)
		//inbox = world.getEntitiesWithinAABB(EntityPlayerMP.class, box);
		//else
		inbox = world.getEntitiesWithinAABB(EntityPlayer.class, box);
		int count = inbox.size();
		if (count > 15)
			count = 15; //15 is max redstone
		return count;
	}

	private boolean checkForPlayers(World world, int x, int y, int z) {
		int range = this.getRange();
		AxisAlignedBB box = AxisAlignedBB.getBoundingBox(x, y, z, x+1, y+1, z+1).expand(range, range, range);
		List inbox;
		//if (world.isRemote)
		//inbox = world.getEntitiesWithinAABB(EntityPlayerMP.class, box);
		//else
		inbox = world.getEntitiesWithinAABB(EntityPlayer.class, box);
		//ModLoader.getMinecraftInstance().thePlayer.addChatMessage(String.format("%d", inbox.size()));
		return (inbox.size() > 0);
	}

	public int getRange() {
		int range = (int)(power/FALLOFF);
		if (range > RotaryConfig.maxdetectorrange)
			range = RotaryConfig.maxdetectorrange;
		if (range > selectedrange)
			return selectedrange;
		return range;
	}

	public int getMaxRange() {
		int range = (int)(power/FALLOFF);
		if (range > RotaryConfig.maxdetectorrange)
			range = RotaryConfig.maxdetectorrange;
		return range;
	}

	/**
	 * Writes a tile entity to NBT.
	 */
	@Override
	public void writeToNBT(NBTTagCompound NBT)
	{
		super.writeToNBT(NBT);
		NBT.setInteger("range", selectedrange);
	}

	/**
	 * Reads a tile entity from NBT.
	 */
	@Override
	public void readFromNBT(NBTTagCompound NBT)
	{
		super.readFromNBT(NBT);
		selectedrange = NBT.getInteger("range");
	}

	@Override
	public boolean hasModelTransparency() {
		return false;
	}

	@Override
	public RotaryModelBase getTEModel(World world, int x, int y, int z) {
		return new ModelDetector();
	}

	@Override
	public void animateWithTick(World world, int x, int y, int z) {

	}

	@Override
	public int getMachineIndex() {
		return MachineRegistry.PLAYERDETECTOR.ordinal();
	}

	@Override
	public int getRedstoneOverride() {
		if (isActive)
			return 15;
		return 0;
	}
}
