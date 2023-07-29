package net.modfest.bc23enchantmentbridge.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.InfinityEnchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.util.Util;

public class EnchancementInfinityEnchantment extends InfinityEnchantment {

	public EnchancementInfinityEnchantment(Rarity weight, EquipmentSlot... slotTypes) {
		super(weight, slotTypes);
	}

	@Override
	public boolean canAccept(Enchantment other) {
		return false;
	}

	@Override
	public String getOrCreateTranslationKey() {
		if (this.translationKey == null) {
			this.translationKey = Util.createTranslationKey("enchantment", Registries.ENCHANTMENT.getId(Enchantments.INFINITY));
		}

		return this.translationKey;
	}

	@Override
	public boolean isAcceptableItem(ItemStack stack) {
		return super.isAcceptableItem(stack) || stack.getItem() instanceof CrossbowItem;
	}

}
