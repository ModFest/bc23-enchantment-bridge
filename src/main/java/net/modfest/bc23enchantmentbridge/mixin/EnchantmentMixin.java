package net.modfest.bc23enchantmentbridge.mixin;

import net.fabricmc.fabric.api.tag.convention.v1.TagUtil;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registries;
import net.modfest.bc23enchantmentbridge.registry.ModTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Enchantment.class)
public class EnchantmentMixin {
	@Inject(method = "canCombine", at = @At("RETURN"), cancellable = true)
	private void bc23enchantmentbridge$preventCombiningOfEnchancementEnchantments(Enchantment other, CallbackInfoReturnable<Boolean> cir) {
		Enchantment thisAsEnchantment = (Enchantment) (Object)this;
		if (TagUtil.isIn(ModTags.IS_ENCHANCEMENT, thisAsEnchantment) || TagUtil.isIn(ModTags.IS_ENCHANCEMENT, other))
			cir.setReturnValue(false);
	}
}
