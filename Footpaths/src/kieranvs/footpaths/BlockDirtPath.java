package kieranvs.footpaths;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class BlockDirtPath extends Block {
	
	public IIcon tex;

	protected BlockDirtPath() {
		super(Material.ground);
		this.setCreativeTab(mod_Footpaths.tabFootpaths);
		this.setStepSound(soundTypeGrass);
	}

	@Override
	public Item getItemDropped(int a, Random b, int c) {
		return Item.getItemFromBlock(Blocks.dirt);
	}

	@Override
	public int getRenderType(){
		return mod_Footpaths.renderId;
	}

	@Override
	public boolean renderAsNormalBlock(){
		return false;
	}

	@Override
	public boolean isOpaqueCube(){
		return false;
	}

	@Override
	public int getRenderBlockPass(){
		return 1;
	}

	@Override
	public boolean canRenderInPass(int pass){
		mod_Footpaths.renderPass = pass;
		return true;
	}

	@Override
	public void registerBlockIcons(IIconRegister reg) {
		tex = reg.registerIcon(mod_Footpaths.modid + ":" + "paths_01");
	}
	
}
