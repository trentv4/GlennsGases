package net.trentv.gases.common.item;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.trentv.gases.Gases;
import net.trentv.gasesframework.api.GasType;
import net.trentv.gasesframework.api.IGasEffectProtector;
import net.trentv.gasesframework.api.reaction.entity.IEntityReaction;

public class ItemRespirator extends ItemArmor implements IGasEffectProtector
{
	private List<Class<? extends IEntityReaction>> blockedReactions;
	private Item repairMaterial;

	public ItemRespirator(List<Class<? extends IEntityReaction>> list, ArmorMaterial material, String name, Item repairMaterial)
	{
		super(material, 1, EntityEquipmentSlot.HEAD);
		setCreativeTab(Gases.CREATIVE_TAB);
		this.blockedReactions = list;
		this.repairMaterial = repairMaterial;
		setRegistryName(Gases.MODID, name);
		setUnlocalizedName(name);
	}

	private int damageDelay = 0;

	@Override
	public boolean apply(EntityLivingBase entity, IEntityReaction reaction, GasType type, ItemStack itemstack)
	{
		if (blockedReactions.contains(reaction.getClass()))
		{
			if (damageDelay-- == 0)
			{
				itemstack.damageItem(1, entity);
				damageDelay = 30;
			}
			return true;
		}
		return false;
	}

	/**
	 * Return whether this item is repairable in an anvil.
	 */
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
	{
		return (repair.getItem() == repairMaterial);
	}
}
