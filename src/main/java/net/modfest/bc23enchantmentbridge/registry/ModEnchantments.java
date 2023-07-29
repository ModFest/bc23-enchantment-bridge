package net.modfest.bc23enchantmentbridge.registry;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.modfest.bc23enchantmentbridge.BC23EnchantmentBridge;
import net.modfest.bc23enchantmentbridge.enchantment.*;

public class ModEnchantments {

	public static final EnchancementChannelingEnchantment ENCHANCEMENT_CHANNELING = register(new EnchancementChannelingEnchantment(Enchantment.Rarity.VERY_RARE, EquipmentSlot.MAINHAND), "enchancement_channeling");
	public static final EnchancementEfficiencyEnchantment ENCHANCEMENT_EFFICIENCY = register(new EnchancementEfficiencyEnchantment(Enchantment.Rarity.COMMON, EquipmentSlot.MAINHAND), "enchancement_efficiency");
	public static final EnchancementFireAspectEnchantment ENCHANCEMENT_FIRE_ASPECT = register(new EnchancementFireAspectEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.MAINHAND), "enchancement_fire_aspect");
	public static final EnchancementInfinityEnchantment ENCHANCEMENT_INFINITY = register(new EnchancementInfinityEnchantment(Enchantment.Rarity.VERY_RARE, EquipmentSlot.MAINHAND), "enchancement_infinity");
	public static final EnchancementLuckOfTheSeaEnchantment ENCHANCEMENT_LUCK_OF_THE_SEA = register(new EnchancementLuckOfTheSeaEnchantment(Enchantment.Rarity.RARE, EnchantmentTarget.FISHING_ROD, EquipmentSlot.MAINHAND), "enchancement_luck_of_the_sea");
	public static final EnchancementRiptideEnchantment ENCHANCEMENT_RIPTIDE = register(new EnchancementRiptideEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.MAINHAND), "enchancement_riptide");
	public static final EnchancementSilkTouchEnchantment ENCHANCEMENT_SILK_TOUCH = register(new EnchancementSilkTouchEnchantment(Enchantment.Rarity.VERY_RARE, EquipmentSlot.MAINHAND), "enchancement_silk_touch");

	public static void registerAll() { }

	public static <T extends Enchantment> T register(T enchantment, String id) {
		return Registry.register(Registries.ENCHANTMENT, BC23EnchantmentBridge.identifier(id), enchantment);
	}

}
