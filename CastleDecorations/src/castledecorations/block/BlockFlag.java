package castledecorations.block;

import castledecorations.mod_Castle;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBrewingStand;
import net.minecraft.world.World;

public class BlockFlag extends Block {

	public BlockFlag(int id, Material material) {
		super(id, material);
		setUnlocalizedName("Flag");
		this.setCreativeTab(mod_Castle.tabCastleDecorations);

	}
	
	@Override
	public TileEntity createTileEntity(World world, int meta) {
		return new TileEntityFlag();
	}

	@Override
	public int getRenderType() {
		return mod_Castle.renderId;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public void onBlockAdded(World par1World, int par2, int par3, int par4) {
		super.onBlockAdded(par1World, par2, par3, par4);
		par1World.setBlockTileEntity(par2, par3, par4, createTileEntity(par1World, par1World.getBlockMetadata(par2, par3, par4)));
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean hasTileEntity(int metadata) {
		return true;
	}

	@Override
	public void registerIcons(IconRegister iconregister) {
		this.blockIcon = iconregister.registerIcon(mod_Castle.modid + ":" + this.getUnlocalizedName());
	}

	
}
