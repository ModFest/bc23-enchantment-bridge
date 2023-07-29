package net.modfest.bc23enchantmentbridge.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.RiptideEnchantment;
import net.minecraft.enchantment.SilkTouchEnchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.registry.Registries;
import net.minecraft.util.Util;

public class EnchancementRiptideEnchantment extends RiptideEnchantment {

	public EnchancementRiptideEnchantment(Rarity weight, EquipmentSlot... slotTypes) {
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
			this.translationKey = Util.createTranslationKey("enchantment", Registries.ENCHANTMENT.getId(Enchantments.RIPTIDE));
		}

		return this.translationKey;
	}

}
