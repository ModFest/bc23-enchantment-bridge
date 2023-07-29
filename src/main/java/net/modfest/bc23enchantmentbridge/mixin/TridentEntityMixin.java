package net.modfest.bc23enchantmentbridge.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.ItemStack;
import net.modfest.bc23enchantmentbridge.registry.ModEnchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(TridentEntity.class)
public class TridentEntityMixin {
	@Shadow
	private ItemStack tridentStack;

	@ModifyExpressionValue(method = "onEntityHit", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;isThundering()Z"))
	private boolean bc23enchantmentbridge$makeEnchancementChannelingWorkAtAllTimes(boolean value) {
		return value || EnchantmentHelper.getLevel(ModEnchantments.ENCHANCEMENT_CHANNELING, this.tridentStack) > 0;
	}
}
