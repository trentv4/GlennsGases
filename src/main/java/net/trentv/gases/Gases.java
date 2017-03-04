package net.trentv.gases;

import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Gases.MODID, version = Gases.VERSION, acceptedMinecraftVersions = "1.10.2", dependencies = "required-after:gasesframework")
public class Gases
{
	public static final String MODID = "gases";
	public static final String VERSION = "2.0.0";

	public static Logger logger;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		logger = event.getModLog();
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{

	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		logger.info("Glenn's Gases initialized");
	}
}
