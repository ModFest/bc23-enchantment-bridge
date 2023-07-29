package net.modfest.bc23enchantmentbridge.mixin.enchancement;

import moriyashiine.enchancement.common.screenhandlers.EnchantingTableScreenHandler;
import net.fabricmc.fabric.api.tag.convention.v1.TagUtil;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.modfest.bc23enchantmentbridge.registry.ModBlocks;
import net.modfest.bc23enchantmentbridge.registry.ModTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = EnchantingTableScreenHandler.class, remap = false)
public class EnchantingTableScreenHandlerMixin {
	@ModifyArg(method = "canUse", at = @At(value = "INVOKE", target = "Lmoriyashiine/enchancement/common/screenhandlers/EnchantingTableScreenHandler;canUse(Lnet/minecraft/screen/ScreenHandlerContext;Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/block/Block;)Z"), index = 2)
	private Block bc23enchantmentbridge$changeToEnchancementTable(Block block) {
		return ModBlocks.ENCHANCEMENT_ENCHANTING_TABLE;
	}

	@Inject(method = "isEnchantmentAllowed", at = @At("HEAD"), cancellable = true)
	private static void bc23enchantmentbridge$disallowNonEnchancementEnchantments(Enchantment enchantment, ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
		if (!TagUtil.isIn(ModTags.IS_ENCHANCEMENT, enchantment))
			cir.setReturnValue(false);
	}
}
