package net.modfest.bc23enchantmentbridge.enchantment;

import net.minecraft.enchantment.*;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.registry.Registries;
import net.minecraft.util.Util;

public class EnchancementLuckOfTheSeaEnchantment extends LuckEnchantment {

	public EnchancementLuckOfTheSeaEnchantment(Rarity weight, EnchantmentTarget target, EquipmentSlot... slotTypes) {
		super(weight, target, slotTypes);
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
			this.translationKey = Util.createTranslationKey("enchantment", Registries.ENCHANTMENT.getId(Enchantments.LUCK_OF_THE_SEA));
		}

		return this.translationKey;
	}

}
