package net.modfest.bc23enchantmentbridge.util;

import net.fabricmc.fabric.api.tag.convention.v1.TagUtil;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.FireAspectEnchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.modfest.bc23enchantmentbridge.BC23EnchantmentBridge;
import net.modfest.bc23enchantmentbridge.enchantment.EnchancementChannelingEnchantment;
import net.modfest.bc23enchantmentbridge.enchantment.EnchancementLuckOfTheSeaEnchantment;
import net.modfest.bc23enchantmentbridge.registry.ModTags;

import java.util.Objects;

public class EnchancementUtil {

	public static boolean hasEnchancementEnchantment(ItemStack stack) {
		return EnchantmentHelper.get(stack).keySet().stream().anyMatch(enchantment -> TagUtil.isIn(ModTags.IS_ENCHANCEMENT, enchantment));
	}

	public static boolean isEnchancementVanillaEnchantment(Identifier vanillaId, Identifier bridgeId) {
		return Objects.equals(vanillaId.getNamespace(), "minecraft") && bridgeId.equals(BC23EnchantmentBridge.identifier("enchancement_" + vanillaId.getPath()));
	}

	public static Identifier remapToVanillaEnchantment(Identifier enchantmentId) {
		if (enchantmentId.getNamespace().equals(BC23EnchantmentBridge.MODID) && enchantmentId.getPath().startsWith("enchancement_")) {
			return new Identifier(enchantmentId.getPath().replaceFirst("enchancement_", ""));
		}
		return enchantmentId;
	}

	public static String getDescKeyFromEnchantment(Enchantment enchantment, String original) {
		if (enchantment instanceof EnchancementChannelingEnchantment)
			return "enchantment.minecraft.channeling.desc.redirect_melee_thunderless";
		if (enchantment instanceof FireAspectEnchantment)
			return "enchantment.minecraft.fire_aspect.desc.redirect";
		if (enchantment instanceof EnchancementLuckOfTheSeaEnchantment)
			return "enchantment.minecraft.luck_of_the_sea.desc.redirect";

		return original;
	}

}
