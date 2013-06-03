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
// Date: 20/02/2013 7:13:37 PM
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX

package Reika.RotaryCraft.Models;

import java.util.List;

import net.minecraft.client.model.ModelRenderer;

import org.lwjgl.opengl.GL11;

import Reika.RotaryCraft.Base.RotaryModelBase;


public class ModelCombustion extends RotaryModelBase
{
	//fields
	ModelRenderer Shape1;
	ModelRenderer Shape12;
	ModelRenderer Shape13;
	ModelRenderer Shape5;
	ModelRenderer Shape6;
	ModelRenderer Shape7;
	ModelRenderer Shape3;
	ModelRenderer Shape8;
	ModelRenderer Shape4;

	public ModelCombustion()
	{
		textureWidth = 128;
		textureHeight = 128;

		Shape1 = new ModelRenderer(this, 64, 0);
		Shape1.addBox(0F, 0F, 0F, 15, 1, 15);
		Shape1.setRotationPoint(-7.5F, 22F, -7.5F);
		Shape1.setTextureSize(128, 128);
		Shape1.mirror = true;
		this.setRotation(Shape1, 0F, 0F, 0F);
		Shape12 = new ModelRenderer(this, 0, 80);
		Shape12.addBox(0F, 0F, 0F, 11, 2, 2);
		Shape12.setRotationPoint(-8.5F, 15F, -1F);
		Shape12.setTextureSize(128, 128);
		Shape12.mirror = true;
		this.setRotation(Shape12, 0F, 0F, 0F);
		Shape13 = new ModelRenderer(this, 0, 80);
		Shape13.addBox(0F, 0F, 0F, 11, 2, 2);
		Shape13.setRotationPoint(-8.5F, 16F, -1.4F);
		Shape13.setTextureSize(128, 128);
		Shape13.mirror = true;
		this.setRotation(Shape13, 0.7853982F, 0F, 0F);
		Shape5 = new ModelRenderer(this, 0, 33);
		Shape5.addBox(0F, 0F, 0F, 12, 11, 6);
		Shape5.setRotationPoint(-6F, 11F, 3F);
		Shape5.setTextureSize(128, 128);
		Shape5.mirror = true;
		this.setRotation(Shape5, -0.5235988F, 0F, 0F);
		Shape6 = new ModelRenderer(this, 56, 17);
		Shape6.addBox(0F, 0F, 0F, 13, 12, 6);
		Shape6.setRotationPoint(-6.5F, 11F, -3F);
		Shape6.setTextureSize(128, 128);
		Shape6.mirror = true;
		this.setRotation(Shape6, 0F, 0F, 0F);
		Shape7 = new ModelRenderer(this, 0, 33);
		Shape7.addBox(0F, 0F, 0F, 12, 11, 6);
		Shape7.setRotationPoint(-6F, 14F, -8F);
		Shape7.setTextureSize(128, 128);
		Shape7.mirror = true;
		this.setRotation(Shape7, 0.5235988F, 0F, 0F);
		Shape3 = new ModelRenderer(this, 0, 17);
		Shape3.addBox(0F, 0F, 0F, 14, 2, 14);
		Shape3.setRotationPoint(-7F, 21F, -7F);
		Shape3.setTextureSize(128, 128);
		Shape3.mirror = true;
		this.setRotation(Shape3, 0F, 0F, 0F);
		Shape8 = new ModelRenderer(this, 0, 0);
		Shape8.addBox(0F, 0F, 0F, 16, 1, 16);
		Shape8.setRotationPoint(-8F, 23F, -8F);
		Shape8.setTextureSize(128, 128);
		Shape8.mirror = true;
		this.setRotation(Shape8, 0F, 0F, 0F);
		Shape4 = new ModelRenderer(this, 0, 53);
		Shape4.addBox(0F, 0F, 0F, 12, 3, 3);
		Shape4.setRotationPoint(-5.9F, 11F, -2F);
		Shape4.setTextureSize(128, 128);
		Shape4.mirror = true;
		this.setRotation(Shape4, 0.7853982F, 0F, 0F);
	}

	@Override
	public void renderAll(List li, float phi) {
		Shape1.render(f5);

		GL11.glTranslated(0, 1, 0);
		GL11.glRotatef(phi, 1, 0, 0);
		GL11.glTranslated(0, -1, 0);
		Shape12.render(f5);
		Shape13.render(f5);
		GL11.glTranslated(0, 1, 0);
		GL11.glRotatef(-phi, 1, 0, 0);
		GL11.glTranslated(0, -1, 0);

		Shape5.render(f5);
		Shape6.render(f5);
		Shape7.render(f5);
		Shape3.render(f5);
		Shape8.render(f5);
		Shape4.render(f5);
	}
}
