package castledecorations.client;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CastleDecorationsTab extends CreativeTabs {

	public CastleDecorationsTab(int position, String tabId) {
		super(position, tabId);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getTabIconItemIndex() {
		return 451;
	}

	@Override
	public String getTranslatedTabLabel() {
		return "Castle Decorations";
	}

}
