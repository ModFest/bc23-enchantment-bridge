package net.modfest.bc23enchantmentbridge.mixin;

import net.fabricmc.fabric.api.tag.convention.v1.TagUtil;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.modfest.bc23enchantmentbridge.registry.ModTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
	@Shadow
	public abstract boolean hasEnchantments();

	@Inject(method = "addEnchantment", at = @At(value = "HEAD"), cancellable = true)
	private void bc23enchantmentbridge$disallowAddingOnTopOfEnchancement(Enchantment enchantment, int level, CallbackInfo ci) {
		if (EnchantmentHelper.get((ItemStack)(Object)this).keySet().stream().anyMatch(e -> TagUtil.isIn(ModTags.IS_ENCHANCEMENT, e)) || this.hasEnchantments() && TagUtil.isIn(ModTags.IS_ENCHANCEMENT, enchantment))
			ci.cancel();
	}
}
