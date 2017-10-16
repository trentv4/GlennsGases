package net.trentv.gases.common;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.trentv.gasesframework.api.GFManipulationAPI;

public class CommonEvents
{
	@SubscribeEvent
	public void registerBlocks(RegistryEvent.Register<Block> event)
	{
		IForgeRegistry<Block> r = event.getRegistry();
		r.register(GasesObjects.MODIFIED_BEDROCK);
		r.register(GasesObjects.WHISPERING_FOG_EMITTER);
	}
	
	@SubscribeEvent
	public void registerItems(RegistryEvent.Register<Item> event)
	{
		ItemBlock MODIFIED_BEDROCK = new ItemBlock(GasesObjects.MODIFIED_BEDROCK);
		MODIFIED_BEDROCK.setRegistryName(GasesObjects.MODIFIED_BEDROCK.getRegistryName());
		event.getRegistry().register(MODIFIED_BEDROCK);

		ItemBlock WHISPERING_FOG_EMITTER = new ItemBlock(GasesObjects.WHISPERING_FOG_EMITTER);
		WHISPERING_FOG_EMITTER.setRegistryName(GasesObjects.WHISPERING_FOG_EMITTER.getRegistryName());
		event.getRegistry().register(WHISPERING_FOG_EMITTER);
}
	
	@SubscribeEvent
	public void onBlockBreak(BlockEvent.BreakEvent event)
	{
		if (event.getState().getBlock() == Blocks.COAL_ORE)
		{
			GFManipulationAPI.addGasLevel(event.getPos(), event.getWorld(), GasesObjects.COAL_DUST, 8);
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
