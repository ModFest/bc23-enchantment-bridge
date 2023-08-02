package net.modfest.bc23enchantmentbridge.util;

import com.google.common.collect.Lists;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.util.Util;
import net.minecraft.util.collection.Weighting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
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

	/**
	 * A duplicate of {@link EnchantmentHelper#generateEnchantments(RandomGenerator, ItemStack, int, boolean)} meant to bypass the Redirect of Enchantery.
	 * Generate the enchantments for enchanting the {@code stack}.
	 */
	public static List<EnchantmentLevelEntry> generateEnchantments(RandomGenerator random, ItemStack stack, int level, boolean treasureAllowed) {
		List<EnchantmentLevelEntry> list = Lists.<EnchantmentLevelEntry>newArrayList();
		Item item = stack.getItem();
		int i = item.getEnchantability();
		if (i > 0) {
			level += 1 + random.nextInt(i / 4 + 1) + random.nextInt(i / 4 + 1);
			float f = (random.nextFloat() + random.nextFloat() - 1.0F) * 0.15F;
			level = MathHelper.clamp(Math.round((float) level + (float) level * f), 1, Integer.MAX_VALUE);
			List<EnchantmentLevelEntry> list2 = getPossibleEntries(level, stack, treasureAllowed);
			if (!list2.isEmpty()) {
				Weighting.getRandomItem(random, list2).ifPresent(list::add);

				while (random.nextInt(50) <= level) {
					if (!list.isEmpty()) {
						EnchantmentHelper.removeConflicts(list2, Util.getLast(list));
					}

					if (list2.isEmpty()) {
						break;
					}

					Weighting.getRandomItem(random, list2).ifPresent(list::add);
					level /= 2;
				}
			}

		}
		return list;
	}

	/**
	 * A duplicate of {@link EnchantmentHelper#getPossibleEntries(int, ItemStack, boolean)} meant to bypass the Redirect of Enchantery.
	 * Gets all the possible entries for enchanting the {@code stack} at the
	 * given {@code power}.
	 */
	public static List<EnchantmentLevelEntry> getPossibleEntries(int power, ItemStack stack, boolean treasureAllowed) {
		List<EnchantmentLevelEntry> list = Lists.<EnchantmentLevelEntry>newArrayList();
		Item item = stack.getItem();
		boolean bl = stack.isOf(Items.BOOK);

		for(Enchantment enchantment : Registries.ENCHANTMENT) {
			if ((!enchantment.isTreasure() || treasureAllowed) && enchantment.isAvailableForRandomSelection() && !enchantment.isCursed() && (enchantment.type.isAcceptableItem(item) || bl)) {
				for(int i = enchantment.getMaxLevel(); i > enchantment.getMinLevel() - 1; --i) {
					if (power >= enchantment.getMinPower(i) && power <= enchantment.getMaxPower(i)) {
						list.add(new EnchantmentLevelEntry(enchantment, i));
						break;
					}
				}
			}
		}

		return list;
	}
}
