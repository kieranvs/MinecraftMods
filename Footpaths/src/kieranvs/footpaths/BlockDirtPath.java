package kieranvs.footpaths;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class BlockDirtPath extends Block {
	
	public IIcon tex[];

	protected BlockDirtPath() {
		super(Material.ground);
		this.setCreativeTab(mod_Footpaths.tabFootpaths);
		this.setStepSound(soundTypeGrass);
		this.setHardness(0.7f);
		tex = new IIcon[16];
	}

	@Override
	public Item getItemDropped(int a, Random b, int c) {
		return Item.getItemFromBlock(Blocks.dirt);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_){
		if(player.getHeldItem() != null && player.getHeldItem().getItem() == Items.wheat_seeds && world.getBlockMetadata(x, y, z) != 0){
        	world.setBlockMetadataWithNotify(x, y, z, (world.getBlockMetadata(x, y, z) - 1), 0x02);
        	player.getHeldItem().stackSize -= 1;
        	return true;
        }
		if(player.getHeldItem() != null && (player.getHeldItem().getItem() == Items.wooden_hoe || player.getHeldItem().getItem() == Items.stone_hoe ||player.getHeldItem().getItem() == Items.iron_hoe || player.getHeldItem().getItem() == Items.golden_hoe || player.getHeldItem().getItem() == Items.diamond_hoe)){
        	world.setBlock(x, y, z, Blocks.farmland, 0, 0x02);
        	player.getHeldItem().attemptDamageItem(1, world.rand);
        	return true;
        }
		return false;
    }

	@Override
	public int getRenderType(){
		return mod_Footpaths.renderId;
	}

	@Override
	public boolean renderAsNormalBlock(){
		return true; //True fixes the lighting bug, no side effects AFAIK
	}

	@Override
	public boolean isOpaqueCube(){
		return true;
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
	public IIcon getIcon(int side, int meta){
		return tex[7];
	}

	@Override
	public void registerBlockIcons(IIconRegister reg) {
		for(int x = 0; x < 16; x++){
			tex[x] = reg.registerIcon(mod_Footpaths.modid + ":" + "paths_" + x);
		}
	}
	
}
