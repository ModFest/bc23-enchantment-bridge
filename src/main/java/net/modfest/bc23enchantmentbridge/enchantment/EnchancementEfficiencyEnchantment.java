package net.modfest.bc23enchantmentbridge.enchantment;

import net.minecraft.enchantment.EfficiencyEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.LuckEnchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.registry.Registries;
import net.minecraft.util.Util;

public class EnchancementEfficiencyEnchantment extends EfficiencyEnchantment {

	public EnchancementEfficiencyEnchantment(Rarity weight, EquipmentSlot... slotTypes) {
		super(weight, slotTypes);
	}

	@Override
	public int getMaxLevel() {
		return 1;
	}

	@Override
	public boolean canAccept(Enchantment other) {
		return false;
	}

	@Override
	public String getOrCreateTranslationKey() {
		if (this.translationKey == null) {
			this.translationKey = Util.createTranslationKey("enchantment", Registries.ENCHANTMENT.getId(Enchantments.EFFICIENCY));
		}

		return this.translationKey;
	}

}
