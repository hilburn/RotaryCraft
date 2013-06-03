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
// Date: 23/05/2013 5:12:41 PM
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX

package Reika.RotaryCraft.Models;

import java.util.List;

import net.minecraft.client.model.ModelRenderer;

import org.lwjgl.opengl.GL11;

import Reika.RotaryCraft.Base.RotaryModelBase;

public class ModelFreezeGun extends RotaryModelBase
{
	//fields
	ModelRenderer Shape1;
	ModelRenderer Shape5;
	ModelRenderer Shape6;
	ModelRenderer Shape7;
	ModelRenderer Shape7a;
	ModelRenderer Shape7b;
	ModelRenderer Shape7c;
	ModelRenderer Shape7d;
	ModelRenderer Shape7e;
	ModelRenderer Shape7f;
	ModelRenderer Shape7g;
	ModelRenderer Shape8;
	ModelRenderer Shape8a;
	ModelRenderer Shape9;
	ModelRenderer Shape3d;
	ModelRenderer Shape1a;
	ModelRenderer Shape1b;
	ModelRenderer Shape3da;
	ModelRenderer Shape2;
	ModelRenderer Shape2a;
	ModelRenderer Shape2b;
	ModelRenderer Shape2c;
	ModelRenderer Shape2d;
	ModelRenderer Shape2e;

	public ModelFreezeGun()
	{
		textureWidth = 128;
		textureHeight = 128;

		Shape1 = new ModelRenderer(this, 35, 81);
		Shape1.addBox(-2.8F, 2F, -13F, 3, 1, 13);
		Shape1.setRotationPoint(0F, 11.5F, 6F);
		Shape1.setTextureSize(128, 128);
		Shape1.mirror = true;
		this.setRotation(Shape1, 0F, 0F, 0.5235988F);
		Shape5 = new ModelRenderer(this, 0, 16);
		Shape5.addBox(0F, 0F, 0F, 16, 1, 16);
		Shape5.setRotationPoint(-8F, 23F, -8F);
		Shape5.setTextureSize(128, 128);
		Shape5.mirror = true;
		this.setRotation(Shape5, 0F, 0F, 0F);
		Shape6 = new ModelRenderer(this, 0, 34);
		Shape6.addBox(-5F, 0F, -5F, 10, 2, 10);
		Shape6.setRotationPoint(0F, 20F, 0F);
		Shape6.setTextureSize(128, 128);
		Shape6.mirror = true;
		this.setRotation(Shape6, 0F, 0F, 0F);
		Shape7 = new ModelRenderer(this, 0, 0);
		Shape7.addBox(-6F, 0F, -6F, 12, 1, 12);
		Shape7.setRotationPoint(0F, 22F, 0F);
		Shape7.setTextureSize(128, 128);
		Shape7.mirror = true;
		this.setRotation(Shape7, 0F, 0.7853982F, 0F);
		Shape7a = new ModelRenderer(this, 0, 0);
		Shape7a.addBox(-6F, 0F, -6F, 12, 1, 12);
		Shape7a.setRotationPoint(0F, 22F, 0F);
		Shape7a.setTextureSize(128, 128);
		Shape7a.mirror = true;
		this.setRotation(Shape7a, 0F, 0.3926991F, 0F);
		Shape7b = new ModelRenderer(this, 0, 0);
		Shape7b.addBox(-6F, 0F, -6F, 12, 1, 12);
		Shape7b.setRotationPoint(0F, 22F, 0F);
		Shape7b.setTextureSize(128, 128);
		Shape7b.mirror = true;
		this.setRotation(Shape7b, 0F, 1.178097F, 0F);
		Shape7c = new ModelRenderer(this, 0, 0);
		Shape7c.addBox(-6F, 0F, -6F, 12, 1, 12);
		Shape7c.setRotationPoint(0F, 22F, 0F);
		Shape7c.setTextureSize(128, 128);
		Shape7c.mirror = true;
		this.setRotation(Shape7c, 0F, 0.1963495F, 0F);
		Shape7d = new ModelRenderer(this, 0, 0);
		Shape7d.addBox(-6F, 0F, -6F, 12, 1, 12);
		Shape7d.setRotationPoint(0F, 22F, 0F);
		Shape7d.setTextureSize(128, 128);
		Shape7d.mirror = true;
		this.setRotation(Shape7d, 0F, -0.1963495F, 0F);
		Shape7e = new ModelRenderer(this, 0, 0);
		Shape7e.addBox(-6F, 0F, -6F, 12, 1, 12);
		Shape7e.setRotationPoint(0F, 22F, 0F);
		Shape7e.setTextureSize(128, 128);
		Shape7e.mirror = true;
		this.setRotation(Shape7e, 0F, 0.9817477F, 0F);
		Shape7f = new ModelRenderer(this, 0, 0);
		Shape7f.addBox(-6F, 0F, -6F, 12, 1, 12);
		Shape7f.setRotationPoint(0F, 22F, 0F);
		Shape7f.setTextureSize(128, 128);
		Shape7f.mirror = true;
		this.setRotation(Shape7f, 0F, 0.5890486F, 0F);
		Shape7g = new ModelRenderer(this, 0, 0);
		Shape7g.addBox(-6F, 0F, -6F, 12, 1, 12);
		Shape7g.setRotationPoint(0F, 22F, 0F);
		Shape7g.setTextureSize(128, 128);
		Shape7g.mirror = true;
		this.setRotation(Shape7g, 0F, 0F, 0F);
		Shape8 = new ModelRenderer(this, 0, 98);
		Shape8.addBox(-3F, 0F, 0F, 1, 1, 9);
		Shape8.setRotationPoint(0F, 20F, 0F);
		Shape8.setTextureSize(128, 128);
		Shape8.mirror = true;
		this.setRotation(Shape8, 0.7853982F, 0F, 0F);
		Shape8a = new ModelRenderer(this, 0, 98);
		Shape8a.addBox(2F, 0F, 0F, 1, 1, 9);
		Shape8a.setRotationPoint(0F, 20F, 0F);
		Shape8a.setTextureSize(128, 128);
		Shape8a.mirror = true;
		this.setRotation(Shape8a, 0.7853982F, 0F, 0F);
		Shape9 = new ModelRenderer(this, 0, 109);
		Shape9.addBox(-1F, 0F, 0F, 2, 1, 7);
		Shape9.setRotationPoint(0F, 21F, 4F);
		Shape9.setTextureSize(128, 128);
		Shape9.mirror = true;
		this.setRotation(Shape9, 1.186824F, 0F, 0F);
		Shape3d = new ModelRenderer(this, 34, 47);
		Shape3d.addBox(-4F, -3.5F, -9F, 8, 1, 11);
		Shape3d.setRotationPoint(0F, 11.5F, 6F);
		Shape3d.setTextureSize(128, 128);
		Shape3d.mirror = true;
		this.setRotation(Shape3d, 0F, 0F, 0F);
		Shape1a = new ModelRenderer(this, 70, 81);
		Shape1a.addBox(-0.1F, 2F, -13F, 3, 1, 13);
		Shape1a.setRotationPoint(0F, 11.5F, 6F);
		Shape1a.setTextureSize(128, 128);
		Shape1a.mirror = true;
		this.setRotation(Shape1a, 0F, 0F, -0.5235988F);
		Shape1b = new ModelRenderer(this, 0, 81);
		Shape1b.addBox(-1.5F, 1.65F, -13.1F, 3, 1, 14);
		Shape1b.setRotationPoint(0F, 11.5F, 6F);
		Shape1b.setTextureSize(128, 128);
		Shape1b.mirror = true;
		this.setRotation(Shape1b, 0F, 0F, 0F);
		Shape3da = new ModelRenderer(this, 0, 58);
		Shape3da.addBox(-4F, -2.5F, 0F, 8, 6, 3);
		Shape3da.setRotationPoint(0F, 11.5F, 6F);
		Shape3da.setTextureSize(128, 128);
		Shape3da.mirror = true;
		this.setRotation(Shape3da, 0F, 0F, 0F);
		Shape2 = new ModelRenderer(this, 80, 0);
		Shape2.addBox(3F, -2.5F, -9F, 1, 3, 4);
		Shape2.setRotationPoint(0F, 11.5F, 6F);
		Shape2.setTextureSize(128, 128);
		Shape2.mirror = true;
		this.setRotation(Shape2, 0F, 0F, 0F);
		Shape2a = new ModelRenderer(this, 100, 0);
		Shape2a.addBox(3F, -2.5F, -3F, 1, 5, 3);
		Shape2a.setRotationPoint(0F, 11.5F, 6F);
		Shape2a.setTextureSize(128, 128);
		Shape2a.mirror = true;
		this.setRotation(Shape2a, 0F, 0F, 0F);
		Shape2b = new ModelRenderer(this, 73, 0);
		Shape2b.addBox(3F, -2.5F, -5F, 1, 4, 2);
		Shape2b.setRotationPoint(0F, 11.5F, 6F);
		Shape2b.setTextureSize(128, 128);
		Shape2b.mirror = true;
		this.setRotation(Shape2b, 0F, 0F, 0F);
		Shape2c = new ModelRenderer(this, 91, 0);
		Shape2c.addBox(-4F, -2.5F, -3F, 1, 5, 3);
		Shape2c.setRotationPoint(0F, 11.5F, 6F);
		Shape2c.setTextureSize(128, 128);
		Shape2c.mirror = true;
		this.setRotation(Shape2c, 0F, 0F, 0F);
		Shape2d = new ModelRenderer(this, 51, 9);
		Shape2d.addBox(-4F, -2.5F, -5F, 1, 4, 2);
		Shape2d.setRotationPoint(0F, 11.5F, 6F);
		Shape2d.setTextureSize(128, 128);
		Shape2d.mirror = true;
		this.setRotation(Shape2d, 0F, 0F, 0F);
		Shape2e = new ModelRenderer(this, 51, 0);
		Shape2e.addBox(-4F, -2.5F, -9F, 1, 3, 4);
		Shape2e.setRotationPoint(0F, 11.5F, 6F);
		Shape2e.setTextureSize(128, 128);
		Shape2e.mirror = true;
		this.setRotation(Shape2e, 0F, 0F, 0F);
	}

	public void renderAll(List li, float phi, float theta)
	{
		Shape5.render(f5);

		GL11.glTranslated(0, 1, 0);
		GL11.glRotatef(phi, 0, 1, 0);
		GL11.glTranslated(0, -1, 0);
		Shape8.render(f5);
		Shape8a.render(f5);
		Shape9.render(f5);
		Shape6.render(f5);
		Shape7.render(f5);
		Shape7a.render(f5);
		Shape7b.render(f5);
		Shape7c.render(f5);
		Shape7d.render(f5);
		Shape7e.render(f5);
		Shape7f.render(f5);
		Shape7g.render(f5);
		GL11.glTranslated(0, 1, 0);
		GL11.glRotatef(theta, 1, 0, 0);
		GL11.glTranslated(0, -1, 0);
		Shape1.render(f5);
		Shape3d.render(f5);
		Shape1a.render(f5);
		Shape1b.render(f5);
		Shape3da.render(f5);
		Shape2.render(f5);
		Shape2a.render(f5);
		Shape2b.render(f5);
		Shape2c.render(f5);
		Shape2d.render(f5);
		Shape2e.render(f5);
		GL11.glTranslated(0, 1, 0);
		GL11.glRotatef(-theta, 1, 0, 0);
		GL11.glTranslated(0, -1, 0);
		GL11.glTranslated(0, 1, 0);
		GL11.glRotatef(-phi, 0, 1, 0);
		GL11.glTranslated(0, -1, 0);
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f6)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5);
	}

	@Override
	public void renderAll(List li, float phi) {
		this.renderAll(li, phi, 0);
	}

}
