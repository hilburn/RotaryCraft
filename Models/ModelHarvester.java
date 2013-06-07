/*******************************************************************************
 * @author Reika Kalseki
 * 
 * Copyright 2013
 * 
 * All rights reserved.
 * Distribution of the software in any form is only allowed with
 * explicit, prior permission from the owner.
 ******************************************************************************/
// Date: 10/04/2013 11:42:30 PM
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX

package Reika.RotaryCraft.Models;

import java.util.List;

import net.minecraft.client.model.ModelRenderer;
import Reika.RotaryCraft.Base.RotaryModelBase;


public class ModelHarvester extends RotaryModelBase
{
	//fields
	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer Shape2a;
	ModelRenderer Shape3;
	ModelRenderer Shape3a;
	ModelRenderer Shape4;
	ModelRenderer Shape2an;
	ModelRenderer Shape2ab1;
	ModelRenderer Shape3ab1;
	ModelRenderer Shape3ab;
	ModelRenderer Shape3a1;
	ModelRenderer Shape3a2;
	ModelRenderer Shape2ab;
	ModelRenderer Shape2aba;
	ModelRenderer Shape5;

	public ModelHarvester()
	{
		textureWidth = 128;
		textureHeight = 128;

		Shape1 = new ModelRenderer(this, 0, 0);
		Shape1.addBox(0F, 0F, 0F, 16, 14, 16);
		Shape1.setRotationPoint(-8F, 10F, -8F);
		Shape1.setTextureSize(128, 128);
		Shape1.mirror = true;
		this.setRotation(Shape1, 0F, 0F, 0F);
		Shape2 = new ModelRenderer(this, 64, 0);
		Shape2.addBox(0F, 0F, 0F, 1, 2, 16);
		Shape2.setRotationPoint(7F, 8F, -8F);
		Shape2.setTextureSize(128, 128);
		Shape2.mirror = true;
		this.setRotation(Shape2, 0F, 0F, 0F);
		Shape2a = new ModelRenderer(this, 98, 0);
		Shape2a.addBox(0F, 0F, 0F, 1, 1, 12);
		Shape2a.setRotationPoint(5F, 8F, -6F);
		Shape2a.setTextureSize(128, 128);
		Shape2a.mirror = true;
		this.setRotation(Shape2a, 0F, 0F, 0F);
		Shape3 = new ModelRenderer(this, 0, 30);
		Shape3.addBox(0F, 0F, 0F, 14, 2, 1);
		Shape3.setRotationPoint(-7F, 8F, 7F);
		Shape3.setTextureSize(128, 128);
		Shape3.mirror = true;
		this.setRotation(Shape3, 0F, 0F, 0F);
		Shape3a = new ModelRenderer(this, 98, 13);
		Shape3a.addBox(0F, 0F, 0F, 10, 1, 1);
		Shape3a.setRotationPoint(-5F, 8F, 5F);
		Shape3a.setTextureSize(128, 128);
		Shape3a.mirror = true;
		this.setRotation(Shape3a, 0F, 0F, 0F);
		Shape4 = new ModelRenderer(this, 0, 42);
		Shape4.addBox(0F, 0F, 0F, 12, 1, 12);
		Shape4.setRotationPoint(-6F, 9F, -6F);
		Shape4.setTextureSize(128, 128);
		Shape4.mirror = true;
		this.setRotation(Shape4, 0F, 0F, 0F);
		Shape2an = new ModelRenderer(this, 64, 0);
		Shape2an.addBox(0F, 0F, 0F, 1, 2, 16);
		Shape2an.setRotationPoint(-8F, 8F, -8F);
		Shape2an.setTextureSize(128, 128);
		Shape2an.mirror = true;
		this.setRotation(Shape2an, 0F, 0F, 0F);
		Shape2ab1 = new ModelRenderer(this, 0, 33);
		Shape2ab1.addBox(0F, 0F, 0F, 1, 1, 6);
		Shape2ab1.setRotationPoint(3F, 8F, -3F);
		Shape2ab1.setTextureSize(128, 128);
		Shape2ab1.mirror = true;
		this.setRotation(Shape2ab1, 0F, 0F, 0F);
		Shape3ab1 = new ModelRenderer(this, 0, 30);
		Shape3ab1.addBox(0F, 0F, 0F, 14, 2, 1);
		Shape3ab1.setRotationPoint(-7F, 8F, -8F);
		Shape3ab1.setTextureSize(128, 128);
		Shape3ab1.mirror = true;
		this.setRotation(Shape3ab1, 0F, 0F, 0F);
		Shape3ab = new ModelRenderer(this, 0, 40);
		Shape3ab.addBox(0F, 0F, 0F, 8, 1, 1);
		Shape3ab.setRotationPoint(-4F, 8F, 3F);
		Shape3ab.setTextureSize(128, 128);
		Shape3ab.mirror = true;
		this.setRotation(Shape3ab, 0F, 0F, 0F);
		Shape3a1 = new ModelRenderer(this, 98, 13);
		Shape3a1.addBox(0F, 0F, 0F, 10, 1, 1);
		Shape3a1.setRotationPoint(-5F, 8F, -6F);
		Shape3a1.setTextureSize(128, 128);
		Shape3a1.mirror = true;
		this.setRotation(Shape3a1, 0F, 0F, 0F);
		Shape3a2 = new ModelRenderer(this, 0, 40);
		Shape3a2.addBox(0F, 0F, 0F, 8, 1, 1);
		Shape3a2.setRotationPoint(-4F, 8F, -4F);
		Shape3a2.setTextureSize(128, 128);
		Shape3a2.mirror = true;
		this.setRotation(Shape3a2, 0F, 0F, 0F);
		Shape2ab = new ModelRenderer(this, 98, 0);
		Shape2ab.addBox(0F, 0F, 0F, 1, 1, 12);
		Shape2ab.setRotationPoint(-6F, 8F, -6F);
		Shape2ab.setTextureSize(128, 128);
		Shape2ab.mirror = true;
		this.setRotation(Shape2ab, 0F, 0F, 0F);
		Shape2aba = new ModelRenderer(this, 0, 33);
		Shape2aba.addBox(0F, 0F, 0F, 1, 1, 6);
		Shape2aba.setRotationPoint(-4F, 8F, -3F);
		Shape2aba.setTextureSize(128, 128);
		Shape2aba.mirror = true;
		this.setRotation(Shape2aba, 0F, 0F, 0F);
		Shape5 = new ModelRenderer(this, 14, 33);
		Shape5.addBox(0F, 0F, 0F, 4, 1, 4);
		Shape5.setRotationPoint(-2F, 8F, -2F);
		Shape5.setTextureSize(128, 128);
		Shape5.mirror = true;
		this.setRotation(Shape5, 0F, 0F, 0F);
	}

	@Override
	public void renderAll(List li, float phi)
	{
		Shape1.render(f5);
		Shape2.render(f5);
		Shape2a.render(f5);
		Shape3.render(f5);
		Shape3a.render(f5);
		Shape4.render(f5);
		Shape2an.render(f5);
		Shape2ab1.render(f5);
		Shape3ab1.render(f5);
		Shape3ab.render(f5);
		Shape3a1.render(f5);
		Shape3a2.render(f5);
		Shape2ab.render(f5);
		Shape2aba.render(f5);
		Shape5.render(f5);
	}
}
