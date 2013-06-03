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
package Reika.RotaryCraft.GUIs;

import net.minecraft.entity.player.EntityPlayer;

import org.lwjgl.opengl.GL11;

import Reika.DragonAPI.Libraries.ReikaGuiAPI;
import Reika.RotaryCraft.Base.GuiMachine;
import Reika.RotaryCraft.Containers.ContainerObsidian;
import Reika.RotaryCraft.TileEntities.TileEntityObsidianMaker;

public class GuiObsidian extends GuiMachine
{
	private TileEntityObsidianMaker obs;

	public GuiObsidian(EntityPlayer p5ep, TileEntityObsidianMaker Obsidian)
	{
		super(new ContainerObsidian(p5ep, Obsidian), Obsidian);
		obs = Obsidian;
	}

	/**
	 * Draw the background layer for the GuiContainer (everything behind the items)
	 */
	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
	{
		super.drawGuiContainerBackgroundLayer(par1, par2, par3);

		int j = (width - xSize) / 2;
		int k = (height - ySize) / 2;

		int i1 = obs.getWaterScaled(54);
		int i2 = obs.getLavaScaled(54);
		this.drawTexturedModalRect(j+48, k+71-i1, 193, 55-i1, 7, i1);
		this.drawTexturedModalRect(j+120, k+71-i2, 202, 55-i2, 7, i2);
	}

	@Override
	protected void drawPowerTab(int var5, int var6) {
		String var4 = "/Reika/RotaryCraft/Textures/GUI/powertab.png";
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(var4);
		this.drawTexturedModalRect(xSize+var5, var6+4, 0, 4, 42, ySize-4);

		long frac = (obs.power*29L)/obs.MINPOWER;
		if (frac > 29)
			frac = 29;
		this.drawTexturedModalRect(xSize+var5+5, ySize+var6-144, 0, 0, (int)frac, 4);

		frac = (int)(obs.omega*29L)/obs.MINSPEED;
		if (frac > 29)
			frac = 29;
		this.drawTexturedModalRect(xSize+var5+5, ySize+var6-84, 0, 0, (int)frac, 4);

		frac = (int)(obs.torque*29L)/obs.MINTORQUE;
		if (frac > 29)
			frac = 29;
		this.drawTexturedModalRect(xSize+var5+5, ySize+var6-24, 0, 0, (int)frac, 4);

		ReikaGuiAPI.instance.drawCenteredStringNoShadow(fontRenderer, "Power:", xSize+var5+20, var6+9, 0xff000000);
		ReikaGuiAPI.instance.drawCenteredStringNoShadow(fontRenderer, "Speed:", xSize+var5+20, var6+69, 0xff000000);
		ReikaGuiAPI.instance.drawCenteredStringNoShadow(fontRenderer, "Torque:", xSize+var5+20, var6+129, 0xff000000);
		//this.drawCenteredStringNoShadow(fontRenderer, String.format("%d/%d", obs.power, obs.MINPOWER), xSize+var5+16, var6+16, 0xff000000);
	}

	@Override
	public String getGuiTexture() {
		return "obsidiangui";
	}
}
