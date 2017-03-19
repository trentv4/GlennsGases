package net.trentv.gases.common;

import net.minecraftforge.common.MinecraftForge;

public abstract class CommonProxy
{
	public abstract void registerRenderers();

	public void registerEventHandlers()
	{
		MinecraftForge.EVENT_BUS.register(new CommonEvents());
	}
}
