package kieranvs.footpaths.client;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class FootpathsTab extends CreativeTabs {

	public FootpathsTab(int position, String tabId) {
		super(position, tabId);
	}
	
	@Override
	public String getTranslatedTabLabel() {
		return "Castle Decorations";
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {
		// TODO creative tab icon
		return null;
	}

}
