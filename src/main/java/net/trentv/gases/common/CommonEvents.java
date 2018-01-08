package net.trentv.gases.common;

import net.minecraft.init.Blocks;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.trentv.gasesframework.api.GFManipulationAPI;

public class CommonEvents
{
	@SubscribeEvent
	public void onBlockBreak(BlockEvent.BreakEvent event)
	{
		if (event.getState().getBlock() == Blocks.COAL_ORE)
		{
			GFManipulationAPI.addGasLevel(event.getPos(), event.getWorld(), GasesObjects.COAL_DUST, 10);
			event.setCanceled(true);
		}
	}

	boolean isPlayerPlaceEvent = false;

	// This handles water + lava creating steam as well as obsidian. BlockEvent is used
	// because distinction is needed between a "place" event and a "reaction" event.
	// PlaceEvent will always be followed by a NeighborNotifyEvent, but a NeighborNotifyEvent
	// is not always preceded by a PlaceEvent.
	@SubscribeEvent
	public void onBlockEvent(BlockEvent event)
	{
		if (event.getState().getBlock() == Blocks.OBSIDIAN)
		{
			if (event instanceof BlockEvent.PlaceEvent)
			{
				isPlayerPlaceEvent = true;
			}
			if (event instanceof BlockEvent.NeighborNotifyEvent)
			{
				if (isPlayerPlaceEvent)
				{
					isPlayerPlaceEvent = false; // Resetting for the next BlockEvent
				}
				else
				{
					double quantity = 4; // 8. This event is fired twice for ??? reasons. Just cut it in half.
					GFManipulationAPI.addGasLevel(event.getPos().up(), event.getWorld(), GasesObjects.STEAM, (int) quantity * 2);
				}
			}
		}
	}
}
