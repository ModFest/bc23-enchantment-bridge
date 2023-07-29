package net.modfest.bc23enchantmentbridge.mixin;

import net.fabricmc.fabric.api.tag.convention.v1.TagUtil;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.modfest.bc23enchantmentbridge.registry.ModTags;
import net.modfest.bc23enchantmentbridge.util.EnchancementUtil;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {

	@Inject(method = "getLevel", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/Identifier;equals(Ljava/lang/Object;)Z"), locals = LocalCapture.CAPTURE_FAILHARD, cancellable = true)
	private static void bc23enchantmentbridge$returnOriginalEnchantmentLevels(Enchantment enchantment, ItemStack stack, CallbackInfoReturnable<Integer> cir, Identifier identifier, NbtList nbtList, int i, NbtCompound nbtCompound, Identifier identifier2) {
		if (EnchancementUtil.isEnchancementVanillaEnchantment(identifier, identifier2))
			cir.setReturnValue(enchantment.getMaxLevel());
	}

	@ModifyArg(method = "forEachEnchantment(Lnet/minecraft/enchantment/EnchantmentHelper$Consumer;Lnet/minecraft/item/ItemStack;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/registry/Registry;getOrEmpty(Lnet/minecraft/util/Identifier;)Ljava/util/Optional;"))
	private static Identifier bc23enchantmentbridge$returnOriginalEnchantment(@Nullable Identifier original) {
		if (original == null)
			return original;
		return EnchancementUtil.remapToVanillaEnchantment(original);
	}

	@Unique
	private static Enchantment bc23enchantmentbridge$enchantment;

	@Inject(method = "method_17883", at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/EnchantmentHelper$Consumer;accept(Lnet/minecraft/enchantment/Enchantment;I)V"))
	private static void bc23enchantmentbridge$returnOriginalEnchantment(EnchantmentHelper.Consumer consumer, NbtCompound nbtCompound, Enchantment enchantment, CallbackInfo ci) {
		bc23enchantmentbridge$enchantment = enchantment;
	}

	@ModifyArg(method = "method_17883", at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/EnchantmentHelper$Consumer;accept(Lnet/minecraft/enchantment/Enchantment;I)V"), index = 1)
	private static int bc23enchantmentbridge$returnOriginalEnchantment(int original) {
		return bc23enchantmentbridge$enchantment.getMaxLevel();
	}

	@Inject(method = "getLoyalty", at = @At("HEAD"), cancellable = true)
	private static void bc23enchantmentbridge$giveEnchancementEnchantmentsMaxLoyalty(ItemStack stack, CallbackInfoReturnable<Integer> cir) {
		if (stack.isIn(moriyashiine.enchancement.common.init.ModTags.Items.NO_LOYALTY)) {
			if (EnchantmentHelper.fromNbt(stack.getEnchantments()).keySet().stream().anyMatch(enchantment -> TagUtil.isIn(ModTags.ENABLES_MAX_LEVEL_LOYALTY, enchantment))) {
				cir.setReturnValue(Enchantments.LOYALTY.getMaxLevel());
			}
		}
	}

}
