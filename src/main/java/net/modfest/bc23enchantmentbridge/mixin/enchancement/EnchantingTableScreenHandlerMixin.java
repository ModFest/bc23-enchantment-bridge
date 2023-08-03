package net.modfest.bc23enchantmentbridge.mixin.enchancement;

import moriyashiine.enchancement.common.screenhandlers.EnchantingTableScreenHandler;
import net.fabricmc.fabric.api.tag.convention.v1.TagUtil;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.modfest.bc23enchantmentbridge.registry.ModBlocks;
import net.modfest.bc23enchantmentbridge.registry.ModTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Iterator;

@Mixin(value = EnchantingTableScreenHandler.class)
public class EnchantingTableScreenHandlerMixin {
	@ModifyArg(method = "canUse", at = @At(value = "INVOKE", target = "Lmoriyashiine/enchancement/common/screenhandlers/EnchantingTableScreenHandler;canUse(Lnet/minecraft/screen/ScreenHandlerContext;Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/block/Block;)Z"), index = 2)
	private Block bc23enchantmentbridge$changeToEnchancementTable(Block block) {
		return ModBlocks.ENCHANCEMENT_ENCHANTING_TABLE;
	}

	@Inject(method = "lambda$onButtonClick$1", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;addEnchantment(Lnet/minecraft/enchantment/Enchantment;I)V"), locals = LocalCapture.CAPTURE_FAILHARD)
	private void bc23enchantmentbridge$resetItemDurabilityUponEnchant(PlayerEntity player, World world, BlockPos pos, CallbackInfo ci, ItemStack stack, boolean stackChanged, Iterator var6, Enchantment enchantment) {
		stack.setDamage(0);
	}

	@Inject(method = "isEnchantmentAllowed", at = @At("HEAD"), cancellable = true, remap = false)
	private static void bc23enchantmentbridge$disallowNonEnchancementEnchantments(Enchantment enchantment, ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
		if (!TagUtil.isIn(ModTags.IS_ENCHANCEMENT, enchantment))
			cir.setReturnValue(false);
	}
}
