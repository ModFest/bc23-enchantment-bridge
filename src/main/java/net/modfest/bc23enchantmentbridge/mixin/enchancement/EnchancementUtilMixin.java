package net.modfest.bc23enchantmentbridge.mixin.enchancement;

import moriyashiine.enchancement.common.util.EnchancementUtil;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchancementUtil.class)
public class EnchancementUtilMixin {

	@Inject(method = "shouldBeUnbreakable", at = @At("HEAD"), cancellable = true)
	private static void bc23enchantmentbridge$makeEnchancementItemsUnbreakable(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
		if (net.modfest.bc23enchantmentbridge.util.EnchancementUtil.hasEnchancementEnchantment(stack)) {
			cir.setReturnValue(true);
		}
	}

}
