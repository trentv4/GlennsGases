package net.trentv.gases.common;

import net.minecraft.init.Blocks;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.trentv.gasesframework.api.GFManipulationAPI;

public class CommonEvents
{
	@SubscribeEvent
	public void onBlockBreak(BlockEvent.HarvestDropsEvent event)
	{
		if (event.getState().getBlock() == Blocks.COAL_ORE)
		{
			GFManipulationAPI.addGasLevel(event.getPos(), event.getWorld(), GasesObjects.COAL_DUST, 10);
		}
	}

	boolean isPlayerPlaceEvent = false;

}
