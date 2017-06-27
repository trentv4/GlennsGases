package net.trentv.gases.common.item;

import javax.annotation.Nullable;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.trentv.gases.Gases;
import net.trentv.gasesframework.common.block.BlockGas;

public class ItemDiabalineRefined extends Item
{
	public ItemDiabalineRefined()
	{
		setRegistryName(Gases.MODID, "diabaline");
		setCreativeTab(CreativeTabs.MISC);
		setUnlocalizedName("diabaline");
		addPropertyOverride(new ResourceLocation("glowing"), new IItemPropertyGetter()
		{
			@SideOnly(Side.CLIENT)
			public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
			{
				if (entityIn != null)
				{
					BlockPos pos = entityIn.getPosition();
					if (isGasNearby(5, entityIn.getEntityWorld(), pos.getX(), pos.getY(), pos.getZ()))
					{
						stack.getItem().setUnlocalizedName("diabaline_glowing");
						return 1.0f;
					}
				}
				stack.getItem().setUnlocalizedName("diabaline");
				return 0.0f;
			}
		});
	}

	private boolean isGasNearby(int radius, World world, int startX, int startY, int startZ)
	{
		MutableBlockPos pos = new MutableBlockPos(0, 0, 0);
		for (int x = -radius; x <= radius; x++)
		{
			for (int y = -radius; y <= radius; y++)
			{
				for (int z = -radius; z <= radius; z++)
				{
					pos.setPos(x + startX, y + startY, z + startZ);
					if (world.getBlockState(pos).getBlock() instanceof BlockGas)
					{
						return true;
					}
				}
			}
		}
		return false;
	}
}
