package kieranvs.footpaths.client;

import com.jcraft.jorbis.Block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class FootpathsTab extends CreativeTabs {

	public FootpathsTab(int position, String tabId) {
		super(position, tabId);
	}
	
	@Override
	public String getTranslatedTabLabel() {
		return "Footpaths";
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {
		return Item.getItemFromBlock(Blocks.grass);
	}

}
