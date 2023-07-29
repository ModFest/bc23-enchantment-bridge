package net.modfest.bc23enchantmentbridge.registry;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.modfest.bc23enchantmentbridge.BC23EnchantmentBridge;

public class ModTags {
	public static final TagKey<Enchantment> ENABLES_MAX_LEVEL_LOYALTY = TagKey.of(RegistryKeys.ENCHANTMENT, BC23EnchantmentBridge.identifier("enables_max_level_loyalty"));
	public static final TagKey<Enchantment> IS_ENCHANCEMENT = TagKey.of(RegistryKeys.ENCHANTMENT, BC23EnchantmentBridge.identifier("is_enchancement"));
}
