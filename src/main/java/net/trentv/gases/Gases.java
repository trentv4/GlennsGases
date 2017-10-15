package net.trentv.gases;

import org.apache.logging.log4j.Logger;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.trentv.gases.common.CommonProxy;
import net.trentv.gases.common.GasesObjects;

@Mod(modid = Gases.MODID, version = Gases.VERSION, acceptedMinecraftVersions = "1.10.2", dependencies = "required-after:gasesframework")
public class Gases
{
	public static final String MODID = "gases";
	public static final String VERSION = "2.0.0";

	public static final GasesCreativeTab CREATIVE_TAB = new GasesCreativeTab("gases");

	public static Logger logger;

	@SidedProxy(clientSide = "net.trentv.gases.client.ClientProxy", serverSide = "net.trentv.gases.server.ServerProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		logger = event.getModLog();

		GasesObjects.init();

		proxy.registerEventHandlers();
		proxy.registerRenderers();
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{

	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		logger.info("'Glenn's Gases: Repressurized' initialized");
	}

	private static class GasesCreativeTab extends CreativeTabs
	{
		public GasesCreativeTab(String label)
		{
			super(label);
		}

		@Override
		public ItemStack getTabIconItem()
		{
			return new ItemStack(GasesObjects.DIABALINE_REFINED);
		}
	}
}
