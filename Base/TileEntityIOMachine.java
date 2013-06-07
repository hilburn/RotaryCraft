/*******************************************************************************
 * @author Reika Kalseki
 * 
 * Copyright 2013
 * 
 * All rights reserved.
 * Distribution of the software in any form is only allowed with
 * explicit, prior permission from the owner.
 ******************************************************************************/
package Reika.RotaryCraft.Base;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import Reika.DragonAPI.Libraries.ReikaChatHelper;
import Reika.DragonAPI.Libraries.ReikaJavaLibrary;
import Reika.RotaryCraft.TileEntities.TileEntityGearBevel;
import Reika.RotaryCraft.TileEntities.TileEntityShaft;

public abstract class TileEntityIOMachine extends RotaryCraftTileEntity {

	public int iotick = 512;

	public int writex = Integer.MIN_VALUE;
	public int writey = Integer.MIN_VALUE;
	public int writez = Integer.MIN_VALUE;
	public int writex2 = Integer.MIN_VALUE;
	public int writey2 = Integer.MIN_VALUE;
	public int writez2 = Integer.MIN_VALUE;

	public long power = 0;
	public int torque = 0;
	public int omega = 0;
	public int readx = Integer.MIN_VALUE;
	public int ready = Integer.MIN_VALUE;
	public int readz = Integer.MIN_VALUE;
	public int readx2 = Integer.MIN_VALUE;
	public int ready2 = Integer.MIN_VALUE;
	public int readz2 = Integer.MIN_VALUE;
	public int readx3 = Integer.MIN_VALUE;
	public int ready3 = Integer.MIN_VALUE;
	public int readz3 = Integer.MIN_VALUE;
	public int readx4 = Integer.MIN_VALUE;
	public int ready4 = Integer.MIN_VALUE;
	public int readz4 = Integer.MIN_VALUE;

	public boolean isOmniSided = false;

	protected int torquein;
	protected int omegain;

	private boolean superCalled = false;
	private int superTick = 0;

	public void updateTileEntity() {
		if (iotick > 0)
			iotick -= 8;
		superCalled = true;
	}

	//public abstract void getIOSides();

	/**
	 * Writes a tile entity to NBT.
	 */
	@Override
	public void writeToNBT(NBTTagCompound NBT)
	{
		super.writeToNBT(NBT);
		NBT.setInteger("torque", torque);
		NBT.setInteger("omega", omega);
		NBT.setInteger("io", iotick);
		NBT.setInteger("rx", readx);
		NBT.setInteger("ry", ready);
		NBT.setInteger("rz", readz);
		NBT.setInteger("rx2", readx2);
		NBT.setInteger("ry2", ready2);
		NBT.setInteger("rz2", readz2);
		NBT.setInteger("rx3", readx3);
		NBT.setInteger("ry3", ready3);
		NBT.setInteger("rz3", readz3);
		NBT.setInteger("rx4", readx4);
		NBT.setInteger("ry4", ready4);
		NBT.setInteger("rz4", readz4);
	}

	/**
	 * Reads a tile entity from NBT.
	 */
	@Override
	public void readFromNBT(NBTTagCompound NBT)
	{
		super.readFromNBT(NBT);
		torque = NBT.getInteger("torque");
		omega = NBT.getInteger("omega");
		iotick = NBT.getInteger("io");
		readx = NBT.getInteger("rx");
		ready = NBT.getInteger("ry");
		readz = NBT.getInteger("rz");
		readx2 = NBT.getInteger("rx2");
		ready2 = NBT.getInteger("ry2");
		readz2 = NBT.getInteger("rz2");
		readx3 = NBT.getInteger("rx3");
		ready3 = NBT.getInteger("ry3");
		readz3 = NBT.getInteger("rz3");
		readx4 = NBT.getInteger("rx4");
		ready4 = NBT.getInteger("ry4");
		readz4 = NBT.getInteger("rz4");

		if (torque < 0)
		{
			torque = 0;
		}
		if (omega < 0)
		{
			omega = 0;
		}

		if (!superCalled && this.isInWorld()) {
			superTick++;
			EntityPlayer ep = worldObj.getClosestPlayer(xCoord, yCoord, zCoord, -1);
			if (ep != null && this.isSelfBlock() && superTick > 40) {
				ReikaJavaLibrary.pConsole("Super() not called for "+this.getName()+"! Contact Reika immediately!");
				ReikaChatHelper.write("Super() not called for "+this.getName()+"! Contact Reika immediately!");
			}
		}
	}

	protected boolean isProvider(TileEntity te) {
		if (!(te instanceof TileEntityIOMachine))
			return false;
		return ((TileEntityIOMachine)te).canProvidePower();
	}

	public abstract boolean canProvidePower();

	protected void copyStandardPower(World world, int x, int y, int z) {
		TileEntityIOMachine devicein = (TileEntityIOMachine)worldObj.getBlockTileEntity(x, y, z);
		if (devicein instanceof TileEntityShaft)
			return;
		if (!this.isPointingAt(world, x, y, z))
			return;
		torquein = devicein.torque;
		omegain = devicein.omega;
	}

	private boolean isPointingAt(World world, int x, int y, int z) {
		boolean cy = false;
		TileEntityIOMachine devicein = (TileEntityIOMachine)worldObj.getBlockTileEntity(x, y, z);
		if (devicein instanceof TileEntityGearBevel)
			cy = true;
		if (devicein.writex == xCoord && devicein.writez == zCoord) {
			if (!cy || devicein.writey == yCoord) {
				return true;
			}
		}
		return false;
	}

	public TileEntityIOMachine getInput() {
		if (ready == Integer.MIN_VALUE)
			ready = yCoord;
		TileEntity te = worldObj.getBlockTileEntity(readx, ready, readz);
		if (te instanceof TileEntityIOMachine)
			return (TileEntityIOMachine)te;
		return null;
	}

	public TileEntityIOMachine getOutput() {
		if (writey == Integer.MIN_VALUE)
			writey = yCoord;
		TileEntity te = worldObj.getBlockTileEntity(writex, writey, writez);
		if (te instanceof TileEntityIOMachine)
			return (TileEntityIOMachine)te;
		return null;
	}
}
