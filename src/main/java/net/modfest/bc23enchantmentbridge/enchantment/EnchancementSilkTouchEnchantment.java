package net.modfest.bc23enchantmentbridge.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.SilkTouchEnchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.registry.Registries;
import net.minecraft.util.Util;

public class EnchancementSilkTouchEnchantment extends SilkTouchEnchantment {

	public EnchancementSilkTouchEnchantment(Rarity weight, EquipmentSlot... slotTypes) {
		super(weight, slotTypes);
	}

	@Override
	public boolean canAccept(Enchantment other) {
		return false;
	}

	@Override
	public String getOrCreateTranslationKey() {
		if (this.translationKey == null) {
			this.translationKey = Util.createTranslationKey("enchantment", Registries.ENCHANTMENT.getId(Enchantments.SILK_TOUCH));
		}

		return this.translationKey;
	}

}
