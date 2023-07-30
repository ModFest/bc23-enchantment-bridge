package net.modfest.bc23enchantmentbridge.mixin.betterenchantmentboosting;

import io.github.redstoneparadox.betterenchantmentboosting.util.EnchantingUtil;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.world.World;
import net.modfest.bc23enchantmentbridge.block.BetterEnchantmentBoostingEnchantingTableBlock;
import net.modfest.bc23enchantmentbridge.util.BetterEnchantmentBoostingUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(value = EnchantingUtil.class, remap = false)
public class EnchantingUtilMixin {
	@Inject(method = "generateEnchantments(Lnet/minecraft/item/ItemStack;IILnet/minecraft/util/random/RandomGenerator;ILnet/minecraft/world/World;Ljava/util/List;)Ljava/util/List;", at = @At("HEAD"), cancellable = true)
	private static void bc23enchantmentbridge$setReturnValueIfNotBEBTable(ItemStack stack, int slot, int power, RandomGenerator random, int seed, World world, List<BlockPos> boosterPositions, CallbackInfoReturnable<List<EnchantmentLevelEntry>> cir) {
		if (!(world.getBlockState(BetterEnchantmentBoostingUtil.bc23enchantmentbridge$storedEnchantingTableBlockPos).getBlock() instanceof BetterEnchantmentBoostingEnchantingTableBlock)) {
			cir.setReturnValue(BetterEnchantmentBoostingUtil.generateVanillaEntries(random, seed, stack, slot, power));
		}
	}
}
