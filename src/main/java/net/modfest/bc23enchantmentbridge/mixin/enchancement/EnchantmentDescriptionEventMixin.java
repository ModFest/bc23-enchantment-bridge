package net.modfest.bc23enchantmentbridge.mixin.enchancement;

import moriyashiine.enchancement.client.event.EnchantmentDescriptionsEvent;
import net.minecraft.enchantment.Enchantment;
import net.modfest.bc23enchantmentbridge.util.EnchancementUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(EnchantmentDescriptionsEvent.class)
public class EnchantmentDescriptionEventMixin {

	@Unique
	private static Enchantment bc23enchantmentbridge$capturedEnchantment;

	@Inject(method = "lambda$getTooltip$0", at = @At("HEAD"))
	private static void bc23enchantmentbridge$redirectDescription(List lines, Enchantment enchantment, Integer integer, CallbackInfo ci) {
		bc23enchantmentbridge$capturedEnchantment = enchantment;
	}

	@ModifyArg(method = "lambda$getTooltip$0", at = @At(value = "INVOKE", target = "Lnet/minecraft/text/Text;translatable(Ljava/lang/String;)Lnet/minecraft/text/MutableText;"))
	private static String bc23enchantmentbridge$redirectDescription(String translationKey) {
		return EnchancementUtil.getDescKeyFromEnchantment(bc23enchantmentbridge$capturedEnchantment, translationKey);
	}

}
