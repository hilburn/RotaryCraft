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
package Reika.RotaryCraft;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import Reika.DragonAPI.Libraries.ReikaInventoryHelper;
import Reika.DragonAPI.Libraries.ReikaItemHelper;
import Reika.RotaryCraft.Base.ItemChargedTool;
import cpw.mods.fml.common.ICraftingHandler;

public class ItemChargingRecipeHandler implements ICraftingHandler {

	private boolean checkHasTool(IInventory ic) {
		for (int i = 0; i < ic.getSizeInventory(); i++) {
			ItemStack is = ic.getStackInSlot(i);
			if (is != null) {
				if (is.getItem() instanceof ItemChargedTool || is.itemID == RotaryCraft.nvg.itemID)
					return true;
			}
		}
		return false;
	}

	private int getTool(IInventory ic) {
		for (int i = 0; i < ic.getSizeInventory(); i++) {
			ItemStack is = ic.getStackInSlot(i);
			if (is != null) {
				if (is.getItem() instanceof ItemChargedTool || is.itemID == RotaryCraft.nvg.itemID)
					return ic.getStackInSlot(i).itemID;
			}
		}
		return -1;
	}

	@Override
	public void onCrafting(EntityPlayer player, ItemStack item,	IInventory ii) {
		boolean hasSpring = ReikaInventoryHelper.hasItem(RotaryCraft.wind.itemID, ii);
		boolean hasTool = this.checkHasTool(ii);
		boolean onlyThose = ReikaInventoryHelper.hasNEmptyStacks(ii, 7);

		if (hasSpring && hasTool && onlyThose) {
			int toolid = this.getTool(ii);
			int toolslot = ReikaInventoryHelper.locateIDInInventory(toolid, ii);
			int springslot = ReikaInventoryHelper.locateIDInInventory(RotaryCraft.wind.itemID, ii);
			int toolmeta = ii.getStackInSlot(toolslot).getItemDamage();
			int springmeta = ii.getStackInSlot(springslot).getItemDamage();
			//ItemStack newtool = new ItemStack(toolid, 1, springmeta);
			ItemStack newspring = new ItemStack(RotaryCraft.wind.itemID, 1, toolmeta);
			item.setItemDamage(springmeta);
			if (!player.inventory.addItemStackToInventory(newspring))
				ReikaItemHelper.dropItem(player.worldObj, player.posX, player.posY, player.posZ, newspring);
		}
	}

	@Override
	public void onSmelting(EntityPlayer player, ItemStack item) {}
}
