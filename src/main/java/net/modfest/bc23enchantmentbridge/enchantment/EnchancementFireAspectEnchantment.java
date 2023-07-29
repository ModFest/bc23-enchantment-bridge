package net.modfest.bc23enchantmentbridge.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.FireAspectEnchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.registry.Registries;
import net.minecraft.util.Util;

public class EnchancementFireAspectEnchantment extends FireAspectEnchantment {
	public EnchancementFireAspectEnchantment(Rarity weight, EquipmentSlot... slotTypes) {
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
			this.translationKey = Util.createTranslationKey("enchantment", Registries.ENCHANTMENT.getId(Enchantments.FIRE_ASPECT));
		}

		return this.translationKey;
	}

}
