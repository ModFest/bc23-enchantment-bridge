package net.modfest.bc23enchantmentbridge.mixin.enchantery;

import com.ordana.enchantery.EnchanteryLogic;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.util.random.RandomGenerator;
import net.modfest.bc23enchantmentbridge.block.BetterEnchantmentBoostingEnchantingTableBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(value = EnchanteryLogic.class, remap = false)
public class EnchanteryLogicMixin {

	@Inject(method = "modifyEnchantmentList", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/ScreenHandlerContext;run(Ljava/util/function/BiConsumer;)V", shift = At.Shift.BEFORE), cancellable = true)
	private static void bc23enchantmentbridge$disableModifyingWithBetterEnchantmentBoosting(ScreenHandlerContext access, RandomGenerator random, ItemStack stack, List<EnchantmentLevelEntry> list, CallbackInfo ci) {
		if (access.get((world, blockPos) -> world.getBlockState(blockPos).getBlock() instanceof BetterEnchantmentBoostingEnchantingTableBlock, false))
			ci.cancel();
	}
}
