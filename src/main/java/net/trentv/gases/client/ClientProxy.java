package net.trentv.gases.client;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.trentv.gases.common.CommonProxy;
import net.trentv.gases.common.GasesObjects;
import net.trentv.gases.common.block.BlockHeated;
import net.trentv.gases.client.ClientEvents;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerRenderers()
	{
		BlockHeated[] allBlockHeated = GasesObjects.getAllHeated();
		for (BlockHeated a : allBlockHeated)
		{
			setBlockModel(a);
		}
		setBlockModel(GasesObjects.WHISPERING_FOG_EMITTER);
		setItemModel(GasesObjects.DIABALINE_REFINED);

		ModelLoaderRegistry.registerLoader(new GasesModelLoader());
		ModelLoader.setCustomStateMapper(GasesObjects.MODIFIED_BEDROCK, new GasesStateMapper());
	}

	private void setBlockModel(Block obj)
	{
		ModelLoader.setCustomModelResourceLocation(ItemBlock.REGISTRY.getObject(obj.getRegistryName()), 0, new ModelResourceLocation((obj.getRegistryName()), "inventory"));
	}

	private void setItemModel(Item in)
	{
		ModelLoader.setCustomModelResourceLocation(in, 0, new ModelResourceLocation(in.getRegistryName(), "inventory"));
	}

	@Override
	public void registerEventHandlers()
	{
		super.registerEventHandlers();
		MinecraftForge.EVENT_BUS.register(new ClientEvents());
	}

	private static class GasesStateMapper implements IStateMapper
	{
		private Map<IBlockState, ModelResourceLocation> cachedMap = new HashMap<IBlockState, ModelResourceLocation>();

		@Override
		public Map<IBlockState, ModelResourceLocation> putStateModelLocations(Block blockIn)
		{
			if (cachedMap.isEmpty())
				cachedMap.put(GasesObjects.MODIFIED_BEDROCK.getDefaultState(), new ModelResourceLocation("minecraft:bedrock"));
			return cachedMap;
		}
	}
}
