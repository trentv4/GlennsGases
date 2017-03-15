package net.trentv.gases;

import net.minecraftforge.common.MinecraftForge;
import net.trentv.gases.CommonEvents;

public abstract class CommonProxy
{
	public abstract void registerRenderers();

	public void registerEventHandlers()
	{
		MinecraftForge.EVENT_BUS.register(new CommonEvents());
	}
}
