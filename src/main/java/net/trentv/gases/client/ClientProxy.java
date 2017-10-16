package net.trentv.gases.client;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.trentv.gases.common.CommonProxy;
import net.trentv.gases.common.GasesObjects;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerRenderers()
	{
		ModelLoaderRegistry.registerLoader(new GasesModelLoader());
		ModelLoader.setCustomStateMapper(GasesObjects.MODIFIED_BEDROCK, new GasesStateMapper());
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
