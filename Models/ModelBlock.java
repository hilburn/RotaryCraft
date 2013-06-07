/*******************************************************************************
 * @author Reika Kalseki
 * 
 * Copyright 2013
 * 
 * All rights reserved.
 * Distribution of the software in any form is only allowed with
 * explicit, prior permission from the owner.
 ******************************************************************************/
// Date: 10/03/2013 3:09:04 PM
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX

package Reika.RotaryCraft.Models;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import Reika.RotaryCraft.Base.EntityModelBase;

public class ModelBlock extends EntityModelBase
{
	//fields
	ModelRenderer Shape1;

	public ModelBlock()
	{
		textureWidth = 16;
		textureHeight = 16;

		Shape1 = new ModelRenderer(this, 0, 0);
		Shape1.addBox(0F, 0F, 0F, 16, 16, 16);
		Shape1.setRotationPoint(-8F, 8F, -8F);
		Shape1.setTextureSize(16, 16);
		Shape1.mirror = true;
		this.setRotation(Shape1, 0F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		//super.render(entity, f, f1, f2, f3, f4, f5);
		// setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Shape1.render(f5);
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		// TODO Auto-generated method stub
	}

}
