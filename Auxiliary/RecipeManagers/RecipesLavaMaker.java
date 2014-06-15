/*******************************************************************************
 * @author Reika Kalseki
 * 
 * Copyright 2014
 * 
 * All rights reserved.
 * Distribution of the software in any form is only allowed with
 * explicit, prior permission from the owner.
 ******************************************************************************/
package Reika.RotaryCraft.Auxiliary.RecipeManagers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import Reika.RotaryCraft.Registry.ItemRegistry;

public class RecipesLavaMaker {

	private static final RecipesLavaMaker recipes = new RecipesLavaMaker();

	public static final RecipesLavaMaker getRecipes() {
		return recipes;
	}

	private final HashMap<List<Integer>, FluidStack> list = new HashMap();
	private final HashMap<List<Integer>, Integer> temperatures = new HashMap();

	private RecipesLavaMaker() {
		this.addRecipe(Block.stone, FluidRegistry.LAVA, 1000, 1200);
		this.addRecipe(Block.cobblestone, FluidRegistry.LAVA, 500, 1000);
		this.addRecipe(Block.netherrack, FluidRegistry.LAVA, 2000, 600);
		this.addRecipe(Block.stoneBrick, FluidRegistry.LAVA, 1000, 1200);

		this.addRecipe("stone", FluidRegistry.LAVA, 1000, 1200);
		this.addRecipe("cobblestone", FluidRegistry.LAVA, 500, 1000);

		this.addRecipe("dustGlowstone", "glowstone", 250, 400);
		this.addRecipe(Block.glowStone, "glowstone", 1000, 500);
		this.addRecipe("dustRedstone", "redstone", 100, 600);
		this.addRecipe(Block.blockRedstone, "redstone", 900, 750);
		this.addRecipe(Item.enderPearl, "ender", 250, 400);
		this.addRecipe("dustCoal", "coal", 250, 300);

		this.addRecipe("shardCrystal", "potion crystal", 8000, 500);
		this.addRecipe(ItemRegistry.ETHANOL.getStackOf(), "rc ethanol", 100, 180);
	}

	private void addRecipe(String in, String out, int amt, int temperature) {
		if (this.validateFluid(out)) {
			ArrayList<ItemStack> li = OreDictionary.getOres(in);
			for (int i = 0; i < li.size(); i++)
				this.addRecipe(li.get(i), new FluidStack(FluidRegistry.getFluid(out), amt), temperature);
		}
	}

	private void addRecipe(String in, Fluid out, int amt, int temperature) {
		ArrayList<ItemStack> li = OreDictionary.getOres(in);
		for (int i = 0; i < li.size(); i++)
			this.addRecipe(li.get(i), new FluidStack(out, amt), temperature);
	}

	private void addRecipe(ItemStack in, String out, int amt, int temperature) {
		if (this.validateFluid(out))
			this.addRecipe(in, new FluidStack(FluidRegistry.getFluid(out), amt), temperature);
	}

	private void addRecipe(Item in, String out, int amt, int temperature) {
		if (this.validateFluid(out))
			this.addRecipe(new ItemStack(in), new FluidStack(FluidRegistry.getFluid(out), amt), temperature);
	}

	private void addRecipe(Block in, String out, int amt, int temperature) {
		if (this.validateFluid(out))
			this.addRecipe(new ItemStack(in), new FluidStack(FluidRegistry.getFluid(out), amt), temperature);
	}

	private void addRecipe(Block in, Fluid out, int amt, int temperature) {
		this.addRecipe(new ItemStack(in), new FluidStack(out, amt), temperature);
	}

	private void addRecipe(Item in, Fluid out, int amt, int temperature) {
		this.addRecipe(new ItemStack(in), new FluidStack(out, amt), temperature);
	}

	private void addRecipe(ItemStack in, FluidStack out, int temperature) {
		list.put(Arrays.asList(in.itemID, in.getItemDamage()), out);
		temperatures.put(Arrays.asList(in.itemID, in.getItemDamage()), temperature);
	}

	private boolean validateFluid(String s) {
		return FluidRegistry.getFluid(s) != null;
	}

	public FluidStack getMelting(ItemStack is) {
		return list.get(Arrays.asList(is.itemID, is.getItemDamage()));
	}

	public int getMeltTemperature(ItemStack is) {
		List key = Arrays.asList(is.itemID, is.getItemDamage());
		return temperatures.containsKey(key) ? temperatures.get(key) : Integer.MIN_VALUE;
	}

	public boolean isValidFuel(ItemStack is) {
		return list.containsKey(Arrays.asList(is.itemID, is.getItemDamage()));
	}

	public ArrayList<ItemStack> getSourceItems(Fluid f) {
		ArrayList<ItemStack> li = new ArrayList();
		for (List<Integer> key : list.keySet()) {
			FluidStack fs = list.get(key);
			if (fs.getFluid().equals(f))
				li.add(new ItemStack(key.get(0), 1, key.get(1)));
		}
		return li;
	}

}
