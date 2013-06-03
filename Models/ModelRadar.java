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
// Date: 30/04/2013 7:23:08 PM
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX

package Reika.RotaryCraft.Models;

import java.util.List;

import net.minecraft.client.model.ModelRenderer;

import org.lwjgl.opengl.GL11;

import Reika.RotaryCraft.Base.RotaryModelBase;


public class ModelRadar extends RotaryModelBase
{
	//fields
	ModelRenderer Shape1;
	ModelRenderer Shape2a;
	ModelRenderer Shape2b;
	ModelRenderer Shape3;
	ModelRenderer Shape3a;
	ModelRenderer Shape3b;
	ModelRenderer Shape2c;
	ModelRenderer Shape2d;
	ModelRenderer Shape2e;
	ModelRenderer Shape2f;
	ModelRenderer Shape4;
	ModelRenderer Shape5;
	ModelRenderer Shape4a;
	ModelRenderer Shape4b;
	ModelRenderer Shape4c;

	public ModelRadar()
	{
		textureWidth = 128;
		textureHeight = 128;

		Shape1 = new ModelRenderer(this, 0, 0);
		Shape1.addBox(0F, 0F, 0F, 16, 1, 16);
		Shape1.setRotationPoint(-8F, 23F, -8F);
		Shape1.setTextureSize(128, 128);
		Shape1.mirror = true;
		this.setRotation(Shape1, 0F, 0F, 0F);
		Shape2a = new ModelRenderer(this, 0, 54);
		Shape2a.addBox(-2F, 0F, -2F, 4, 1, 4);
		Shape2a.setRotationPoint(0F, 22F, 0F);
		Shape2a.setTextureSize(128, 128);
		Shape2a.mirror = true;
		this.setRotation(Shape2a, 0F, 0.7853982F, 0F);
		Shape2b = new ModelRenderer(this, 64, 33);
		Shape2b.addBox(-5F, 0F, -5F, 10, 1, 10);
		Shape2b.setRotationPoint(0F, 21F, 0F);
		Shape2b.setTextureSize(128, 128);
		Shape2b.mirror = true;
		this.setRotation(Shape2b, 0F, 0.7853982F, 0F);
		Shape3 = new ModelRenderer(this, 16, 17);
		Shape3.addBox(-6.5F, 0F, -3.5F, 1, 8, 5);
		Shape3.setRotationPoint(0F, 12F, 0F);
		Shape3.setTextureSize(128, 128);
		Shape3.mirror = true;
		this.setRotation(Shape3, 0F, -0.7853982F, 0F);
		Shape3a = new ModelRenderer(this, 0, 17);
		Shape3a.addBox(-5.7F, 0F, -3.5F, 1, 8, 7);
		Shape3a.setRotationPoint(0F, 12F, 0F);
		Shape3a.setTextureSize(128, 128);
		Shape3a.mirror = true;
		this.setRotation(Shape3a, 0F, 0F, 0F);
		Shape3b = new ModelRenderer(this, 28, 17);
		Shape3b.addBox(-6.5F, 0F, -1.5F, 1, 8, 5);
		Shape3b.setRotationPoint(0F, 12F, 0F);
		Shape3b.setTextureSize(128, 128);
		Shape3b.mirror = true;
		this.setRotation(Shape3b, 0F, 0.7853982F, 0F);
		Shape2c = new ModelRenderer(this, 0, 54);
		Shape2c.addBox(-2F, 0F, -2F, 4, 1, 4);
		Shape2c.setRotationPoint(0F, 22F, 0F);
		Shape2c.setTextureSize(128, 128);
		Shape2c.mirror = true;
		this.setRotation(Shape2c, 0F, 0F, 0F);
		Shape2d = new ModelRenderer(this, 64, 22);
		Shape2d.addBox(-5F, 0F, -5F, 10, 1, 10);
		Shape2d.setRotationPoint(0F, 21F, 0F);
		Shape2d.setTextureSize(128, 128);
		Shape2d.mirror = true;
		this.setRotation(Shape2d, 0F, 1.178097F, 0F);
		Shape2e = new ModelRenderer(this, 64, 11);
		Shape2e.addBox(-5F, 0F, -5F, 10, 1, 10);
		Shape2e.setRotationPoint(0F, 21F, 0F);
		Shape2e.setTextureSize(128, 128);
		Shape2e.mirror = true;
		this.setRotation(Shape2e, 0F, 0F, 0F);
		Shape2f = new ModelRenderer(this, 64, 0);
		Shape2f.addBox(-5F, 0F, -5F, 10, 1, 10);
		Shape2f.setRotationPoint(0F, 21F, 0F);
		Shape2f.setTextureSize(128, 128);
		Shape2f.mirror = true;
		this.setRotation(Shape2f, 0F, 0.3926991F, 0F);
		Shape4 = new ModelRenderer(this, 14, 32);
		Shape4.addBox(7F, -1F, -2F, 1, 3, 2);
		Shape4.setRotationPoint(0F, 15F, 0F);
		Shape4.setTextureSize(128, 128);
		Shape4.mirror = true;
		this.setRotation(Shape4, 0F, -2.356194F, 0.7853982F);
		Shape5 = new ModelRenderer(this, 0, 44);
		Shape5.addBox(4.6F, 0F, -2F, 1, 4, 4);
		Shape5.setRotationPoint(0F, 15.4F, 0F);
		Shape5.setTextureSize(128, 128);
		Shape5.mirror = true;
		this.setRotation(Shape5, 0F, 0F, 0F);
		Shape4a = new ModelRenderer(this, 0, 38);
		Shape4a.addBox(6F, 1F, -1F, 1, 3, 2);
		Shape4a.setRotationPoint(0F, 15F, 0F);
		Shape4a.setTextureSize(128, 128);
		Shape4a.mirror = true;
		this.setRotation(Shape4a, 0F, 0F, 0.5235988F);
		Shape4b = new ModelRenderer(this, 0, 32);
		Shape4b.addBox(6.5F, -0.5F, -1F, 1, 3, 2);
		Shape4b.setRotationPoint(0F, 15F, 0F);
		Shape4b.setTextureSize(128, 128);
		Shape4b.mirror = true;
		this.setRotation(Shape4b, 0F, 3.141593F, -0.7853982F);
		Shape4c = new ModelRenderer(this, 7, 32);
		Shape4c.addBox(7F, -1F, 0F, 1, 3, 2);
		Shape4c.setRotationPoint(0F, 15F, 0F);
		Shape4c.setTextureSize(128, 128);
		Shape4c.mirror = true;
		this.setRotation(Shape4c, 0F, 2.356194F, 0.7853982F);
	}

	@Override
	public void renderAll(List li, float phi)
	{
		Shape1.render(f5);
		GL11.glTranslated(0, 1, 0);
		GL11.glRotatef(phi, 0, 1, 0);
		GL11.glTranslated(0, -1, 0);
		Shape2a.render(f5);
		Shape2b.render(f5);
		Shape3.render(f5);
		Shape3a.render(f5);
		Shape3b.render(f5);
		Shape2c.render(f5);
		Shape2d.render(f5);
		Shape2e.render(f5);
		Shape2f.render(f5);
		Shape5.render(f5);
		Shape4a.render(f5);
		//Shape4.render(f5);
		Shape4b.render(f5);
		//Shape4c.render(f5);
		GL11.glTranslated(0, 1, 0);
		GL11.glRotatef(-phi, 0, 1, 0);
		GL11.glTranslated(0, -1, 0);
	}

}
