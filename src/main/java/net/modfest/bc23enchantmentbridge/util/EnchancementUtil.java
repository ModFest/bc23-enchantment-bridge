package net.modfest.bc23enchantmentbridge.util;

import net.minecraft.util.Identifier;
import net.modfest.bc23enchantmentbridge.BC23EnchantmentBridge;

import java.util.Objects;

public class EnchancementUtil {

	public static boolean isEnchancementVanillaEnchantment(Identifier vanillaId, Identifier bridgeId) {
		return Objects.equals(vanillaId.getNamespace(), "minecraft") && bridgeId.equals(BC23EnchantmentBridge.identifier("enchancement_" + vanillaId.getPath()));
	}

	public static Identifier remapToVanillaEnchantment(Identifier enchantmentId) {
		if (enchantmentId.getNamespace().equals(BC23EnchantmentBridge.MODID) && enchantmentId.getPath().startsWith("enchancement_")) {
			return new Identifier(enchantmentId.getPath().replaceFirst("enchancement_", ""));
		}
		return enchantmentId;
	}

}
