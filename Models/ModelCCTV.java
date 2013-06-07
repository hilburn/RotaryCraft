/*******************************************************************************
 * @author Reika Kalseki
 * 
 * Copyright 2013
 * 
 * All rights reserved.
 * Distribution of the software in any form is only allowed with
 * explicit, prior permission from the owner.
 ******************************************************************************/
// Date: 11/05/2013 6:19:06 PM
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX

package Reika.RotaryCraft.Models;

import java.util.List;

import net.minecraft.client.model.ModelRenderer;

import org.lwjgl.opengl.GL11;

import Reika.RotaryCraft.Base.RotaryModelBase;

public class ModelCCTV extends RotaryModelBase
{
	//fields
	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer Shape3;
	ModelRenderer Shape3a;
	ModelRenderer Shape4a;
	ModelRenderer Shape4;
	ModelRenderer Shape5a;
	ModelRenderer Shape5;
	ModelRenderer Shape6;
	ModelRenderer Shape7;

	public ModelCCTV()
	{
		textureWidth = 128;
		textureHeight = 128;

		Shape1 = new ModelRenderer(this, 28, 0);
		Shape1.addBox(0F, 0F, 0F, 8, 1, 8);
		Shape1.setRotationPoint(-4F, 23F, -4F);
		Shape1.setTextureSize(128, 128);
		Shape1.mirror = true;
		this.setRotation(Shape1, 0F, 0F, 0F);
		Shape2 = new ModelRenderer(this, 62, 0);
		Shape2.addBox(-0.5F, 0F, 0F, 1, 5, 3);
		Shape2.setRotationPoint(0F, 18F, 0F);
		Shape2.setTextureSize(128, 128);
		Shape2.mirror = true;
		this.setRotation(Shape2, 0F, 0F, 0F);
		Shape3 = new ModelRenderer(this, 0, 26);
		Shape3.addBox(-2.5F, -1.5F, -5.5F, 5, 3, 8);
		Shape3.setRotationPoint(0F, 18F, 1.5F);
		Shape3.setTextureSize(128, 128);
		Shape3.mirror = true;
		this.setRotation(Shape3, 0F, 0F, 0F);
		Shape3a = new ModelRenderer(this, 0, 13);
		Shape3a.addBox(-2F, -2F, -5.5F, 4, 4, 8);
		Shape3a.setRotationPoint(0F, 18F, 1.5F);
		Shape3a.setTextureSize(128, 128);
		Shape3a.mirror = true;
		this.setRotation(Shape3a, 0F, 0F, 0F);
		Shape4a = new ModelRenderer(this, 5, 0);
		Shape4a.addBox(-2F, 1F, -6.5F, 4, 1, 1);
		Shape4a.setRotationPoint(0F, 18F, 1.5F);
		Shape4a.setTextureSize(128, 128);
		Shape4a.mirror = true;
		this.setRotation(Shape4a, 0F, 0F, 0F);
		Shape4 = new ModelRenderer(this, 5, 0);
		Shape4.addBox(-2F, -2F, -6.5F, 4, 1, 1);
		Shape4.setRotationPoint(0F, 18F, 1.5F);
		Shape4.setTextureSize(128, 128);
		Shape4.mirror = true;
		this.setRotation(Shape4, 0F, 0F, 0F);
		Shape5a = new ModelRenderer(this, 0, 0);
		Shape5a.addBox(-2.5F, -1.5F, -6.5F, 1, 3, 1);
		Shape5a.setRotationPoint(0F, 18F, 1.5F);
		Shape5a.setTextureSize(128, 128);
		Shape5a.mirror = true;
		this.setRotation(Shape5a, 0F, 0F, 0F);
		Shape5 = new ModelRenderer(this, 0, 0);
		Shape5.addBox(1.5F, -1.5F, -6.5F, 1, 3, 1);
		Shape5.setRotationPoint(0F, 18F, 1.5F);
		Shape5.setTextureSize(128, 128);
		Shape5.mirror = true;
		this.setRotation(Shape5, 0F, 0F, 0F);
		Shape6 = new ModelRenderer(this, 0, 39);
		Shape6.addBox(-1.5F, -1F, -6F, 3, 2, 1);
		Shape6.setRotationPoint(0F, 18F, 1.5F);
		Shape6.setTextureSize(128, 128);
		Shape6.mirror = true;
		this.setRotation(Shape6, 0F, 0F, 0F);
		Shape7 = new ModelRenderer(this, 0, 5);
		Shape7.addBox(-2F, -1.5F, 2F, 4, 3, 1);
		Shape7.setRotationPoint(0F, 18F, 1.5F);
		Shape7.setTextureSize(128, 128);
		Shape7.mirror = true;
		this.setRotation(Shape7, 0F, 0F, 0F);
	}

	public void renderAll(List li, float phi, float theta)
	{
		double d = 0.175;
		double d1 = 0.0625;
		Shape1.render(f5);
		Shape2.render(f5);
		GL11.glTranslated(0, 0, d1);
		GL11.glRotatef(phi, 0, 1, 0);
		GL11.glTranslated(0, 0, -d1);
		GL11.glTranslated(0, 1, 0);
		GL11.glTranslated(0, 0, d);
		GL11.glRotatef(theta, 1, 0, 0);
		GL11.glTranslated(0, -1, 0);
		GL11.glTranslated(0, 0, -d);
		Shape3.render(f5);
		Shape3a.render(f5);
		Shape4a.render(f5);
		Shape4.render(f5);
		Shape5a.render(f5);
		Shape5.render(f5);
		Shape6.render(f5);
		Shape7.render(f5);
		GL11.glTranslated(0, 1, 0);
		GL11.glRotatef(-theta, 1, 0, 0);
		GL11.glTranslated(0, -1, 0);
		GL11.glTranslated(0, 0, d1);
		GL11.glRotatef(-phi, 0, 1, 0);
		GL11.glTranslated(0, 0, -d1);
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f6)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5);
	}

	@Override
	public void renderAll(List conditions, float phi) {
		this.renderAll(conditions, phi, 0);
	}

}
