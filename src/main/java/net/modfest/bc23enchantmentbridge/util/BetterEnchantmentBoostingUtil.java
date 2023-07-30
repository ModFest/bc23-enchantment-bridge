package net.modfest.bc23enchantmentbridge.util;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.random.RandomGenerator;

import java.util.List;

public class BetterEnchantmentBoostingUtil {

	public static BlockPos bc23enchantmentbridge$storedEnchantingTableBlockPos;

	public static List<EnchantmentLevelEntry> generateVanillaEntries(RandomGenerator random, int seed, ItemStack stack, int slot, int level) {
		random.setSeed(seed + slot);
		List<EnchantmentLevelEntry> list = EnchantmentHelper.generateEnchantments(random, stack, level, false);
		if (stack.isOf(Items.BOOK) && list.size() > 1) {
			list.remove(random.nextInt(list.size()));
		}

		return list;
	}

}
