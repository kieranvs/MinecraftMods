package kieranvs.footpaths;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockDirtPath extends Block {

	protected BlockDirtPath() {
		super(Material.ground);
		this.setCreativeTab(mod_Footpaths.tabFootpaths);
		this.setStepSound(soundTypeGrass);
	}

}
