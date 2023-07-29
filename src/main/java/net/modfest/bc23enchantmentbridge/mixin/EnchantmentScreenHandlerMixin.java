package net.modfest.bc23enchantmentbridge.mixin;

import net.fabricmc.fabric.api.tag.convention.v1.TagUtil;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.screen.EnchantmentScreenHandler;
import net.modfest.bc23enchantmentbridge.registry.ModTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.List;

@Mixin(EnchantmentScreenHandler.class)
public class EnchantmentScreenHandlerMixin {
	@ModifyVariable(method = "generateEnchantments", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/enchantment/EnchantmentHelper;generateEnchantments(Lnet/minecraft/util/random/RandomGenerator;Lnet/minecraft/item/ItemStack;IZ)Ljava/util/List;"))
	private List<EnchantmentLevelEntry> bc23enchantmentbridge$removeEnchancementFromGeneration(List<EnchantmentLevelEntry> value) {
		value.removeIf(entry -> TagUtil.isIn(ModTags.IS_ENCHANCEMENT, entry.enchantment));
		return value;
	}
}
