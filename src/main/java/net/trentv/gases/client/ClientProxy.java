package net.trentv.gases.client;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.trentv.gases.common.CommonProxy;
import net.trentv.gases.common.GasesObjects;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerRenderers()
	{
		setModel(GasesObjects.HEATED_IRON);
		setModel(GasesObjects.HEATED_DIAMOND);
		setModel(GasesObjects.HEATED_GOLD);
		setModel(GasesObjects.HEATED_REDSTONE);
		setModel(GasesObjects.HEATED_LAPIS);
		setModel(GasesObjects.HEATED_STONE);
		ModelLoaderRegistry.registerLoader(new GasesModelLoader());
		ModelLoader.setCustomStateMapper(GasesObjects.MODIFIED_BEDROCK, new GasesStateMapper());
	}

	private void setModel(Block obj)
	{
		ModelLoader.setCustomModelResourceLocation(ItemBlock.REGISTRY.getObject(obj.getRegistryName()), 0, new ModelResourceLocation((obj.getRegistryName()), "inventory"));
	}

	@Override
	public void registerEventHandlers()
	{
		super.registerEventHandlers();
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
