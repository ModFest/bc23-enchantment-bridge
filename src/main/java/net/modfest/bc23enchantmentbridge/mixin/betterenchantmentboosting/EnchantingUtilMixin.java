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
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(value = EnchantingUtil.class)
public class EnchantingUtilMixin {
	@Inject(method = "generateEnchantments(Lnet/minecraft/item/ItemStack;IILnet/minecraft/util/random/RandomGenerator;ILnet/minecraft/world/World;Ljava/util/List;)Ljava/util/List;", at = @At("HEAD"), cancellable = true)
	private static void bc23enchantmentbridge$setReturnValueIfNotBEBTable(ItemStack stack, int slot, int power, RandomGenerator random, int seed, World world, List<BlockPos> boosterPositions, CallbackInfoReturnable<List<EnchantmentLevelEntry>> cir) {
		if (!(world.getBlockState(BetterEnchantmentBoostingUtil.bc23enchantmentbridge$storedEnchantingTableBlockPos).getBlock() instanceof BetterEnchantmentBoostingEnchantingTableBlock)) {
			cir.setReturnValue(BetterEnchantmentBoostingUtil.generateVanillaEntries(random, seed, stack, slot, power));
		}
	}

	@Unique
	private static int bc23enchantmentbridge$capturedPower;
	@Unique
	private static ItemStack bc23enchantmentbridge$capturedStack;
	@Unique
	private static boolean bc23enchantmentbridge$capturedTreasureAllowed;

	@Inject(method = "generateEnchantments(Lnet/minecraft/util/random/RandomGenerator;Lnet/minecraft/item/ItemStack;IZLjava/util/List;)Ljava/util/List;", at = @At("HEAD"))
	private static void bc23enchantmentbridge$captureEnchantmentRelatedValues(RandomGenerator random, ItemStack stack, int power, boolean treasureAllowed, List<EnchantmentLevelEntry> bonusEntries, CallbackInfoReturnable<List<EnchantmentLevelEntry>> cir) {
		bc23enchantmentbridge$capturedPower = power;
		bc23enchantmentbridge$capturedStack = stack;
		bc23enchantmentbridge$capturedTreasureAllowed = treasureAllowed;
	}

	@ModifyVariable(method = "generateEnchantments(Lnet/minecraft/util/random/RandomGenerator;Lnet/minecraft/item/ItemStack;IZLjava/util/List;)Ljava/util/List;", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/enchantment/EnchantmentHelper;getPossibleEntries(ILnet/minecraft/item/ItemStack;Z)Ljava/util/List;"), ordinal = 2)
	private static List<EnchantmentLevelEntry> bc23enchantmentbridge$generateEnchantmentsWithVanillaLogic(List<EnchantmentLevelEntry> original) {
		return BetterEnchantmentBoostingUtil.getPossibleEntries(bc23enchantmentbridge$capturedPower, bc23enchantmentbridge$capturedStack, bc23enchantmentbridge$capturedTreasureAllowed);
	}
}
