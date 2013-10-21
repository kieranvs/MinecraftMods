package castledecorations;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import castledecorations.block.BlockFlag;
import castledecorations.block.BlockFountain;
import castledecorations.block.TileEntityFlag;
import castledecorations.block.TileEntityFountain;
import castledecorations.client.CastleDecorationsTab;
import castledecorations.server.ServerProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = mod_Castle.modid, name = "CastleDecorations", version = mod_Castle.version)
public class mod_Castle {

	public static final String modid = "castledecorations";
	public static final String version = "0.0.1";
	
	public static int renderId;
	
	public static CreativeTabs tabCastleDecorations = new CastleDecorationsTab(CreativeTabs.getNextID(), "tabCastleDecorations");

	public static BlockFlag flagBlock;
	public static BlockFountain fountainBlock;
	
	@Instance("CastleDecorations")
	public static mod_Castle instance;

	@SidedProxy(clientSide = "castledecorations.client.ClientProxy", serverSide = "castledecorations.server.ServerProxy")
	public static ServerProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
	}

	@EventHandler
	public void serverStarting(FMLServerStartingEvent event) {
	}

	@EventHandler
	public void load(FMLInitializationEvent event) {
		instance = this;
		proxy.load();
		proxy.registerRenderers();
		
		flagBlock = (BlockFlag) new BlockFlag(450).setUnlocalizedName("flagBlock");
		GameRegistry.registerBlock(flagBlock, "flagBlock");
		LanguageRegistry.addName(flagBlock, "Flag");
		
		fountainBlock = (BlockFountain) new BlockFountain(451).setUnlocalizedName("Fountain");
		GameRegistry.registerBlock(fountainBlock, "Fountain");
		LanguageRegistry.addName(fountainBlock, "Fountain");
		
		GameRegistry.registerTileEntity(TileEntityFlag.class, "Flag");
		GameRegistry.registerTileEntity(TileEntityFountain.class, "Fountain");
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
	}

}
