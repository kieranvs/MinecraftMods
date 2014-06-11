package kieranvs.footpaths;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class BlockDirtPath extends Block {

	protected BlockDirtPath() {
		super(Material.ground);
		this.setCreativeTab(mod_Footpaths.tabFootpaths);
		this.setStepSound(soundTypeGrass);
	}

	@Override
	public Item getItemDropped(int a, Random b, int c) {
		return Item.getItemFromBlock(Blocks.dirt);
	}
	
}
