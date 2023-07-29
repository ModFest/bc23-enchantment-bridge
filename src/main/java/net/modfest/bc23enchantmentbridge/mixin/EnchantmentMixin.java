package net.modfest.bc23enchantmentbridge.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registries;
import net.modfest.bc23enchantmentbridge.registry.ModTags;
import net.modfest.bc23enchantmentbridge.util.TagUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Enchantment.class)
public class EnchantmentMixin {
	@Inject(method = "canCombine", at = @At("RETURN"), cancellable = true)
	private void bc23enchantmentbridge$preventCombiningOfEnchancementEnchantments(Enchantment other, CallbackInfoReturnable<Boolean> cir) {
		Enchantment thisAsEnchantment = (Enchantment) (Object)this;
		if (TagUtil.isInTag(Registries.ENCHANTMENT, thisAsEnchantment, ModTags.IS_ENCHANCEMENT) || TagUtil.isInTag(Registries.ENCHANTMENT, other, ModTags.IS_ENCHANCEMENT))
			cir.setReturnValue(false);
	}
}
