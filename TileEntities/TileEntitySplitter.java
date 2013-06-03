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

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import Reika.DragonAPI.Interfaces.GuiController;
import Reika.DragonAPI.Libraries.ReikaMathLibrary;
import Reika.DragonAPI.Libraries.ReikaWorldHelper;
import Reika.RotaryCraft.MachineRegistry;
import Reika.RotaryCraft.Base.RotaryModelBase;
import Reika.RotaryCraft.Base.TileEntityIOMachine;
import Reika.RotaryCraft.Models.ModelSplitter;
import Reika.RotaryCraft.Models.ModelSplitter2;

public class TileEntitySplitter extends TileEntityIOMachine implements GuiController {

	public int[] writeinline = new int[2]; //xz coords
	public int[] writebend = new int[2]; //xz coords

	private int torquein2;
	private int omegain2;
	private boolean split = false;
	public int splitmode = 0;


	private int cheatCount = 0;
	private int cheatTick = 0;
	private int[] lastTorque = new int[7]; //torque 1-7 ticks ago

	public int getRatioFromMode() {
		switch(splitmode) {
		case -1:
			return 0; //if merge, return 0
		case 0:
			return 32; //31:1
		case 4:
			return -32; //31:1, favor bent
		case 1:
			return 16; //15:1
		case 5:
			return -16; //15:1, favor bent
		case 2:
			return 8; //7:1
		case 6:
			return -8; //7:1, favor bent
		case 3:
			return 4; //3:1
		case 7:
			return -4; //3:1, favor bent
		case 8:
			return 1; //1:1
		default:
			return 9999999; //will know something is wrong
		}
	}

	public boolean isUseableByPlayer(EntityPlayer ep) {
		return (ReikaMathLibrary.py3d(ep.posX-xCoord, ep.posY-yCoord, ep.posZ-zCoord) <= 8);
	}

	/**
	 * Returns block data at the location of this entity (client-only). Call parent one.
	 *//*
    public int getBlockMetadata()
    {
        if (this.blockMetadata == -1)
        {
            this.blockMetadata = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord);
        }

        return this.blockMetadata;
    }*/

	public boolean testForLoopCheat() { //logic: if for last 5 cycles lasttorque+torque2 = this.torque(now)
		boolean allstep = true;
		boolean allstep2 = true;

		if (torque != lastTorque[0]) {
			for (int i = 6; i > 0; i--) {
				lastTorque[i] = lastTorque[i-1]; //array shift
			}
			lastTorque[0] = torque;
		}

		for (int i = 0; i < 6; i++) {
			if (lastTorque[i+1]+torquein != lastTorque[i] || lastTorque[i] == 0)
				allstep = false;
		}
		for (int i = 0; i < 6; i++) {
			if (lastTorque[i+1]+torquein2 != lastTorque[i] || lastTorque[i] == 0)
				allstep2 = false;
		}

		return (allstep || allstep2);
	}

	@Override
	public void updateEntity(World world, int x, int y, int z, int meta) {
		super.updateTileEntity();
		power = omega*torque;

		this.getIOSides(world, x, y, z, meta);
		//ModLoader.getMinecraftInstance().thePlayer.addChatMessage(String.format("%d", this.splitmode));
		this.transferPower(world, x, y, z, meta);
		//if (!this.worldObj.isRemote)
		//ModLoader.getMinecraftInstance().thePlayer.addChatMessage(String.format("%d  %d  %d", torquein, torquein2, this.torque));

		if (this.testForLoopCheat()) {
			cheatCount++;
			if (cheatCount > 20) {
				//ModLoader.getMinecraftInstance().thePlayer.addChatMessage(String.format("%d", cheatCount));
				torque = ReikaMathLibrary.extrema(torquein, torquein2, "absmin");
				ReikaWorldHelper.legacySetBlockWithNotify(worldObj, x, y, z, 0); //cause loss of machine
				worldObj.createExplosion(null, x, y, z, 2F+par5Random.nextFloat(), true);
			}
		}
		else {
			cheatTick++;
		}
		if (cheatTick > 20) {
			cheatTick = 0;
			cheatCount = 0;
		}
	}

	public void getIOSides(World world, int x, int y, int z, int metadata) {
		switch(metadata) {
		case 0://-z and +x -> -x
			readx = xCoord+1;
			readz2 = zCoord-1;
			writex = xCoord-1;
			readx2 = xCoord;
			readz = writez = zCoord;
			break;
		case 1: //+z and +x -> -z
			readx = xCoord+1;
			readz2 = zCoord+1;
			writex = readx2 = xCoord;
			readz = zCoord;
			writez = zCoord-1;
			break;
		case 2: //+z and -x -> +x
			readz = zCoord+1;
			readz2 = zCoord;
			readx2 = xCoord-1;
			writez = zCoord;
			readx = xCoord;
			writex = xCoord+1;
			break;
		case 3: //-z and -x -> +z
			writez = zCoord+1;
			readz2 = zCoord;
			readx2 = xCoord-1;
			readz = zCoord-1;
			writex = readx = xCoord;
			break;
		case 4://+z and +x -> -x
			readx = xCoord+1;
			readz2 = zCoord+1;
			writex = xCoord-1;
			readx2 = xCoord;
			readz = writez = zCoord;
			break;
		case 5: //+z and -x -> -z
			readx = xCoord-1;
			readz2 = zCoord+1;
			writex = readx2 = xCoord;
			readz = zCoord;
			writez = zCoord-1;
			break;
		case 6: //-z and -x -> +x
			readz = zCoord-1;
			readz2 = zCoord;
			readx2 = xCoord-1;
			writez = zCoord;
			readx = xCoord;
			writex = xCoord+1;
			break;
		case 7: //-z and +x -> +z
			writez = zCoord+1;
			readz2 = zCoord;
			readx2 = xCoord+1;
			readz = zCoord-1;
			writex = readx = xCoord;
			break;
			//---------------------------SPLITTER-----------------------------------
		case 8://-z and +x <- -x
			readx = xCoord-1;
			readz = zCoord;
			readx2 = Integer.MIN_VALUE;
			readz2 = Integer.MIN_VALUE;
			writex = Integer.MIN_VALUE;
			writez = Integer.MIN_VALUE;
			writeinline[0] = xCoord+1;
			writeinline[1] = zCoord;
			writebend[0] = xCoord;
			writebend[1] = zCoord-1;
			break;
		case 9: //+z and +x <- -z
			readx = xCoord;
			readz = zCoord-1;
			readx2 = Integer.MIN_VALUE;
			readz2 = Integer.MIN_VALUE;
			writex = Integer.MIN_VALUE;
			writez = Integer.MIN_VALUE;
			writeinline[0] = xCoord;
			writeinline[1] = zCoord+1;
			writebend[0] = xCoord+1;
			writebend[1] = zCoord;
			break;
		case 10: //+z and -x <- +x
			readz = zCoord;
			readx = xCoord+1;
			readx2 = Integer.MIN_VALUE;
			readz2 = Integer.MIN_VALUE;
			writex = Integer.MIN_VALUE;
			writez = Integer.MIN_VALUE;
			writeinline[0] = xCoord-1;
			writeinline[1] = zCoord;
			writebend[0] = xCoord;
			writebend[1] = zCoord+1;
			break;
		case 11: //-z and -x <- +z
			readz = zCoord+1;
			readx = xCoord;
			readx2 = Integer.MIN_VALUE;
			readz2 = Integer.MIN_VALUE;
			writex = Integer.MIN_VALUE;
			writez = Integer.MIN_VALUE;
			writeinline[0] = xCoord;
			writeinline[1] = zCoord-1;
			writebend[0] = xCoord-1;
			writebend[1] = zCoord;
			break;
		case 12://+z and +x <- -x
			readx = xCoord-1;
			readz = zCoord;
			readx2 = Integer.MIN_VALUE;
			readz2 = Integer.MIN_VALUE;
			writex = Integer.MIN_VALUE;
			writez = Integer.MIN_VALUE;
			writeinline[0] = xCoord+1;
			writeinline[1] = zCoord;
			writebend[0] = xCoord;
			writebend[1] = zCoord+1;
			break;
		case 13: //+z and -x <- -z
			readx = xCoord;
			readz = zCoord-1;
			readx2 = Integer.MIN_VALUE;
			readz2 = Integer.MIN_VALUE;
			writex = Integer.MIN_VALUE;
			writez = Integer.MIN_VALUE;
			writeinline[0] = xCoord;
			writeinline[1] = zCoord+1;
			writebend[0] = xCoord-1;
			writebend[1] = zCoord;
			break;
		case 14: //-z and -x <- +x
			readz = zCoord;
			readx = xCoord+1;
			readx2 = Integer.MIN_VALUE;
			readz2 = Integer.MIN_VALUE;
			writex = Integer.MIN_VALUE;
			writez = Integer.MIN_VALUE;
			writeinline[0] = xCoord-1;
			writeinline[1] = zCoord;
			writebend[0] = xCoord;
			writebend[1] = zCoord-1;
			break;
		case 15: //-z and +x <- +z
			readz = zCoord+1;
			readx = xCoord;
			readx2 = Integer.MIN_VALUE;
			readz2 = Integer.MIN_VALUE;
			writex = Integer.MIN_VALUE;
			writez = Integer.MIN_VALUE;
			writeinline[0] = xCoord;
			writeinline[1] = zCoord-1;
			writebend[0] = xCoord+1;
			writebend[1] = zCoord;
			break;
		}
		//ReikaWorldHelper.legacySetBlockWithNotify(this.worldObj, this.writex, this.yCoord, this.writez, 44);
		//ReikaWorldHelper.legacySetBlockWithNotify(this.worldObj, this.writex2, this.yCoord, this.writez2, 79);
		//ReikaWorldHelper.legacySetBlockWithNotify(this.worldObj, this.readx, this.yCoord, this.readz, 20);
	}

	public void readFromCross(TileEntityShaft cross) {
		if (xCoord == cross.writex && zCoord == cross.writez) {
			omega = cross.readomega[0];
			torque = cross.readtorque[0];
		}
		else if (xCoord == cross.writex2 && zCoord == cross.writez2) {
			omega = cross.readomega[1];
			torque = cross.readtorque[1];
		}
		else
			return; //not its output
	}

	public void mergeReadFromCross(TileEntityShaft cross, int side) {
		if (xCoord == cross.writex && zCoord == cross.writez) {
			if (side == 0) {
				omegain = cross.readomega[0];
				torquein = cross.readtorque[0];
			}
			if (side == 1) {
				omegain2 = cross.readomega[0];
				torquein2 = cross.readtorque[0];
			}
		}
		else if (xCoord == cross.writex2 && zCoord == cross.writez2) {
			if (side == 0) {
				omegain = cross.readomega[1];
				torquein = cross.readtorque[1];
			}
			if (side == 1) {
				omegain2 = cross.readomega[1];
				torquein2 = cross.readtorque[1];
			}
		}
		else
			return; //not its output
	}

	public void transferPower(World world, int x, int y, int z, int meta) {
		ready = y;
		ready2 = y;
		omegain = torquein = 0;
		if (meta < 8) {
			TileEntity te = world.getBlockTileEntity(readx, ready, readz);
			TileEntity te2 = world.getBlockTileEntity(readx2, ready2, readz2);
			MachineRegistry m = MachineRegistry.getMachine(world, readx, y, readz);
			MachineRegistry m2 = MachineRegistry.getMachine(world, readx2, y, readz2);
			if (this.isProvider(te) && this.isIDTEMatch(world, readx, ready, readz)) {
				if (m == MachineRegistry.SHAFT) {
					TileEntityShaft devicein = (TileEntityShaft)world.getBlockTileEntity(readx, y, readz);
					if (devicein.getBlockMetadata() >= 6) {
						this.mergeReadFromCross(devicein, 0);
						//  return;
					}
					else if (devicein.writex == x && devicein.writez == z) {
						torquein = devicein.torque;
						omegain = devicein.omega;
					}
				}
				if (m == MachineRegistry.ENGINE) {
					TileEntityEngine devicein = (TileEntityEngine)world.getBlockTileEntity(readx, y, readz);
					if ((devicein.writex == x && devicein.writez == z)) {
						torquein = devicein.torque;
						omegain = devicein.omega;
					}
				}
				if (m == MachineRegistry.ADVANCEDGEARS) {
					TileEntityAdvancedGear devicein = (TileEntityAdvancedGear)world.getBlockTileEntity(readx, y, readz);
					if ((devicein.writex == x && devicein.writez == z)) {
						torquein = devicein.torque;
						omegain = devicein.omega;
					}
				}
				if (m == MachineRegistry.WINDER) {
					TileEntityWinder devicein = (TileEntityWinder)world.getBlockTileEntity(readx, y, readz);
					if (!devicein.winding && (devicein.readx == x && devicein.readz == z)) {
						torquein = devicein.torque;
						omegain = devicein.omega;
					}
				}
				if (m == MachineRegistry.FLYWHEEL) {
					TileEntityFlywheel devicein = (TileEntityFlywheel)world.getBlockTileEntity(readx, y, readz);
					if (devicein.writex == x && devicein.writez == z) {
						torquein = devicein.torque;
						omegain = devicein.omega;
					}
				}
				if (m == MachineRegistry.CLUTCH) {
					TileEntityClutch devicein = (TileEntityClutch)world.getBlockTileEntity(readx, y, readz);
					if (devicein.writex == x && devicein.writez == z) {
						torquein = devicein.torque;
						omegain = devicein.omega;
					}
				}
				if (m == MachineRegistry.BEVELGEARS) {
					TileEntityGearBevel devicein = (TileEntityGearBevel)world.getBlockTileEntity(readx, y, readz);
					if (devicein.writex == x && devicein.writez == z) {
						torquein = devicein.torque;
						omegain = devicein.omega;
					}
				}
				if (m == MachineRegistry.DYNAMOMETER) {
					TileEntityMonitor devicein = (TileEntityMonitor)world.getBlockTileEntity(readx, y, readz);
					if (devicein.writex == x && devicein.writez == z) {
						torquein = devicein.torque;
						omegain = devicein.omega;
					}
				}
				if (m == MachineRegistry.GEARBOX) {
					TileEntityGearbox devicein = (TileEntityGearbox)world.getBlockTileEntity(readx, y, readz);
					if (devicein.writex == x && devicein.writez == z) {
						torquein = devicein.torque;
						omegain = devicein.omega;
					}
				}
				if (m == MachineRegistry.SPLITTER) {
					TileEntitySplitter devicein = (TileEntitySplitter)world.getBlockTileEntity(readx, y, readz);
					if (devicein.getBlockMetadata() >= 8) {
						this.readFromSplitter(devicein);
						return;
					}
					else if (devicein.writex == x && devicein.writez == z) {
						torquein = devicein.torque;
						omegain = devicein.omega;
					}
				}
			}
			else {
				torquein = omegain = 0;
			}
			if (this.isProvider(te2) && this.isIDTEMatch(world, readx2, y, readz2)) {
				if (m2 == MachineRegistry.ADVANCEDGEARS) {
					TileEntityAdvancedGear devicein2 = (TileEntityAdvancedGear)world.getBlockTileEntity(readx2, y, readz2);
					if ((devicein2.writex == x && devicein2.writez == z)) {
						torquein2 = devicein2.torque;
						omegain2 = devicein2.omega;
					}
				}
				if (m2 == MachineRegistry.WINDER) {
					TileEntityWinder devicein2 = (TileEntityWinder)world.getBlockTileEntity(readx2, y, readz2);
					if (!devicein2.winding && (devicein2.readx == x && devicein2.readz == z)) {
						torquein2 = devicein2.torque;
						omegain2 = devicein2.omega;
					}
				}
				if (m2 == MachineRegistry.SPLITTER) {
					TileEntitySplitter devicein2 = (TileEntitySplitter)world.getBlockTileEntity(readx2, y, readz2);
					if (devicein2.getBlockMetadata() >= 8) {
						this.readFromSplitter(devicein2);
						return;
					}
					else if (devicein2.writex == x && devicein2.writez == z) {
						torquein2 = devicein2.torque;
						omegain2 = devicein2.omega;
					}
				}
				if (m2 == MachineRegistry.SHAFT) {
					TileEntityShaft devicein2 = (TileEntityShaft)world.getBlockTileEntity(readx2, y, readz2);
					if (devicein2.getBlockMetadata() >= 6) {
						this.mergeReadFromCross(devicein2, 1);
						// ReikaChatHelper.writeInt(this.omegain2);
						// return;
					}
					else if (devicein2.writex == x && devicein2.writez == z) {
						torquein2 = devicein2.torque;
						omegain2 = devicein2.omega;
					}
				}
				if (m2 == MachineRegistry.ENGINE) {
					TileEntityEngine devicein2 = (TileEntityEngine)world.getBlockTileEntity(readx2, y, readz2);
					if ((devicein2.writex == x && devicein2.writez == z)) {
						torquein2 = devicein2.torque;
						omegain2 = devicein2.omega;
					}
				}
				if (m2 == MachineRegistry.CLUTCH) {
					TileEntityClutch devicein2 = (TileEntityClutch)world.getBlockTileEntity(readx2, y, readz2);
					if (devicein2.writex == x && devicein2.writez == z) {
						torquein2 = devicein2.torque;
						omegain2 = devicein2.omega;
					}
				}
				if (m == MachineRegistry.FLYWHEEL) {
					TileEntityFlywheel devicein2 = (TileEntityFlywheel)world.getBlockTileEntity(readx, y, readz);
					if (devicein2.writex == x && devicein2.writez == z) {
						torquein2 = devicein2.torque;
						omegain2 = devicein2.omega;
					}
				}
				if (m2 == MachineRegistry.BEVELGEARS) {
					TileEntityGearBevel devicein2 = (TileEntityGearBevel)world.getBlockTileEntity(readx2, y, readz2);
					if (devicein2.writex == x && devicein2.writez == z) {
						torquein2 = devicein2.torque;
						omegain2 = devicein2.omega;
					}
				}
				if (m2 == MachineRegistry.GEARBOX) {
					TileEntityGearbox devicein2 = (TileEntityGearbox)world.getBlockTileEntity(readx2, y, readz2);
					if (devicein2.writex == x && devicein2.writez == z) {
						torquein2 = devicein2.torque;
						omegain2 = devicein2.omega;
					}
				}
				if (m2 == MachineRegistry.DYNAMOMETER) {
					TileEntityMonitor devicein2 = (TileEntityMonitor)world.getBlockTileEntity(readx2, y, readz2);
					if (devicein2.writex == x && devicein2.writez == z) {
						torquein2 = devicein2.torque;
						omegain2 = devicein2.omega;
					}
				}
			}
			else {
				torquein2 = omegain2 = 0;
			}

			//ModLoader.getMinecraftInstance().ingameGUI.addChatMessage(String.format("%d, %d", omegain, omegain2));

			if (omegain == omegain2) {
				omega = omegain;
				torque = torquein+torquein2;
				//ModLoader.getMinecraftInstance().ingameGUI.addChatMessage(String.format("%d, %d", this.omega, this.torque));
			}
			else {
				omega = ReikaMathLibrary.extrema(omegain, omegain2, "max");
				if (omega == omegain)
					torque = torquein;
				else
					torque = torquein2;
				if (omegain != 0 && omegain2 != 0) {
					world.spawnParticle("crit", x+par5Random.nextFloat(), y+par5Random.nextFloat(), z+par5Random.nextFloat(), -0.5+par5Random.nextFloat(), par5Random.nextFloat(), -0.5+par5Random.nextFloat());
					world.playSoundEffect(x+0.5, y+0.5, z+0.5, "mob.blaze.hit", 0.1F, 1F);
				}
			}
		}			//--------------------------------------------------------------------------------------------
		else {		//------################-------Splitter mode (dmg >= 8)-------##########################------
			//--------------------------------------------------------------------------------------------
			MachineRegistry m = MachineRegistry.getMachine(world, readx, y, readz);
			//ReikaJavaLibrary.pConsole(readx+"  "+readz+"  "+m);
			TileEntity te = world.getBlockTileEntity(readx, y, readz);
			if (te == null || !(te instanceof TileEntityIOMachine)) {
				torque = omega = 0;
				return;
			}
			if (m == MachineRegistry.SHAFT) {
				TileEntityShaft devicein = (TileEntityShaft)world.getBlockTileEntity(readx, y, readz);
				if (devicein.getBlockMetadata() >= 6) {
					this.readFromCross(devicein);
					//ReikaChatHelper.writeInt(this.torque);
					return;
				}
				if (devicein.writex == x && devicein.writey == y && devicein.writez == z) {
					torque = devicein.torque;
					omega = devicein.omega;
				}
				else
					torque = omega = 0;
			}
			if (m == MachineRegistry.ENGINE) {
				TileEntityEngine devicein = (TileEntityEngine)world.getBlockTileEntity(readx, y, readz);
				if ((devicein.writex == x && devicein.writez == z)) {
					torque = devicein.torque;
					omega = devicein.omega;
				}
				else
					torque = omega = 0;
			}
			if (m == MachineRegistry.ADVANCEDGEARS) {
				TileEntityAdvancedGear devicein = (TileEntityAdvancedGear)world.getBlockTileEntity(readx, y, readz);
				if ((devicein.writex == x && devicein.writez == z)) {
					torque = devicein.torque;
					omega = devicein.omega;
				}
				else
					torque = omega = 0;
			}
			if (m == MachineRegistry.WINDER) {
				TileEntityWinder devicein = (TileEntityWinder)world.getBlockTileEntity(readx, y, readz);
				if ((devicein.writex == x && devicein.writez == z)) {
					torque = devicein.torque;
					omega = devicein.omega;
				}
				else
					torque = omega = 0;
			}
			if (m == MachineRegistry.SPLITTER) {
				TileEntitySplitter devicein = (TileEntitySplitter)world.getBlockTileEntity(readx, y, readz);
				if (devicein.getBlockMetadata() >= 8) {
					this.readFromSplitter(devicein);
					return;
				}
				else if (devicein.writex == x && devicein.writez == z) {
					torque = devicein.torque;
					omega = devicein.omega;
				}
				else
					torque = omega = 0;
			}
			if (m == MachineRegistry.CLUTCH) {
				TileEntityClutch devicein = (TileEntityClutch)world.getBlockTileEntity(readx, y, readz);
				if (devicein.writex == x && devicein.writez == z) {
					torque = devicein.torque;
					omega = devicein.omega;
				}
				else
					torque = omega = 0;
			}
			if (m == MachineRegistry.FLYWHEEL) {
				TileEntityFlywheel devicein = (TileEntityFlywheel)world.getBlockTileEntity(readx, y, readz);
				if (devicein.writex == x && devicein.writez == z) {
					torque = devicein.torque;
					omega = devicein.omega;
				}
				else
					torque = omega = 0;
			}
			if (m == MachineRegistry.DYNAMOMETER) {
				TileEntityMonitor devicein = (TileEntityMonitor)world.getBlockTileEntity(readx, y, readz);
				if (devicein.writex == x && devicein.writez == z) {
					torque = devicein.torque;
					omega = devicein.omega;
				}
				else
					torque = omega = 0;
			}
			if (m == MachineRegistry.GEARBOX) {
				TileEntityGearbox devicein = (TileEntityGearbox)world.getBlockTileEntity(readx, y, readz);
				if (devicein.writex == x && devicein.writez == z) {
					torque = devicein.torque;
					omega = devicein.omega;
				}
				else
					torque = omega = 0;
			}
			if (m == MachineRegistry.BEVELGEARS) {
				TileEntityGearBevel devicein = (TileEntityGearBevel)world.getBlockTileEntity(readx, y, readz);
				if (devicein.writex == x && devicein.writey == y && devicein.writez == z) {
					torque = devicein.torque;
					omega = devicein.omega;
				}
				else
					torque = omega = 0;
			}
			power = torque*omega;
			//ModLoader.getMinecraftInstance().thePlayer.addChatMessage(String.format("%d * %d = %d", this.omega, this.torque, this.power));
		}
	}

	public void readFromSplitter(TileEntitySplitter spl) { //Complex enough to deserve its own function
		omega = spl.omega; //omega always constant
		int ratio = spl.getRatioFromMode();
		if (ratio == 0)
			return;
		boolean favorbent = false;
		if (ratio < 0) {
			favorbent = true;
			ratio = -ratio;
		}
		if (xCoord == spl.writeinline[0] && zCoord == spl.writeinline[1]) { //We are the inline
			if (ratio == 1) { //Even split, favorbent irrelevant
				torque = spl.torque/2;
				return;
			}
			if (favorbent) {
				torque = spl.torque/ratio;
			}
			else {
				torque = (int)(spl.torque*((ratio-1D)/(ratio)));
			}
		}
		else if (xCoord == spl.writebend[0] && zCoord == spl.writebend[1]) { //We are the bend
			omega = spl.omega; //omega always constant
			if (ratio == 1) { //Even split, favorbent irrelevant
				torque = spl.torque/2;
				return;
			}
			if (favorbent) {
				torque = (int)(spl.torque*((ratio-1D)/(ratio)));
			}
			else {
				torque = spl.torque/ratio;
			}
		}
		else //We are not one of its write-to blocks
			return;
	}

	/**
	 * Writes a tile entity to NBT.
	 */
	@Override
	public void writeToNBT(NBTTagCompound NBT)
	{
		super.writeToNBT(NBT);
		NBT.setInteger("mode", splitmode);
		NBT.setBoolean("split", split);
	}

	/**
	 * Reads a tile entity from NBT.
	 */
	@Override
	public void readFromNBT(NBTTagCompound NBT)
	{
		super.readFromNBT(NBT);
		splitmode = NBT.getInteger("mode");
		split = NBT.getBoolean("split");
	}

	@Override
	public boolean hasModelTransparency() {
		return false;
	}

	@Override
	public RotaryModelBase getTEModel(World world, int x, int y, int z) {
		int dmg = world.getBlockMetadata(x, y, z);
		if (dmg < 4 || (dmg >= 8 && dmg < 12))
			return new ModelSplitter();
		else
			return new ModelSplitter2();
	}

	@Override
	public void animateWithTick(World world, int x, int y, int z) {
		if (!this.isInWorld()) {
			phi = 0;
			return;
		}
		phi += ReikaMathLibrary.doubpow(ReikaMathLibrary.logbase(omega+1, 2), 1.05);
	}

	@Override
	public boolean canProvidePower() {
		return true;
	}

	@Override
	public int getMachineIndex() {
		return MachineRegistry.SPLITTER.ordinal();
	}

	@Override
	public int getRedstoneOverride() {
		return 0;
	}
}
