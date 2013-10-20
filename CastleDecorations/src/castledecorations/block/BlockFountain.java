package castledecorations.block;

import castledecorations.mod_Castle;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockFountain extends Block {
	
	private Icon iconTop;
	private Icon iconSide;
	private Icon iconBottom;

	public BlockFountain(int id) {
		super(id, Material.iron);
		this.setCreativeTab(CreativeTabs.tabDecorations);
	}
	
	@Override
	public Icon getIcon(int side, int metadata){
		if(side == 0) return this.iconBottom;
		if(side == 1) return this.iconTop;
		return this.iconSide;
	}
	
	@Override
	public boolean hasTileEntity(int metadata){
		return true;
	}
	
	@Override
	public void onBlockAdded(World world, int x, int y, int z){
		super.onBlockAdded(world, x, y, z);
		world.setBlockTileEntity(x, y, z, createTileEntity(world, world.getBlockMetadata(x, y, z)));
	}
	
	@Override
	public TileEntity createTileEntity(World world, int metadata){
		return new TileEntityFountain();
	}
	
	@Override
	public void registerIcons(IconRegister iconRegister){
		this.iconBottom = iconRegister.registerIcon(mod_Castle.modid + ":" + this.getUnlocalizedName() + "_bottom");
		this.iconSide = iconRegister.registerIcon(mod_Castle.modid + ":" + this.getUnlocalizedName() + "_side");
		this.iconTop = iconRegister.registerIcon(mod_Castle.modid + ":" + this.getUnlocalizedName() + "_top");
	}

}
