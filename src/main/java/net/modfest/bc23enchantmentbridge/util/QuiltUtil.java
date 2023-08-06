package net.modfest.bc23enchantmentbridge.util;

import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.world.World;
import org.quiltmc.qsl.block.content.registry.api.BlockContentRegistries;

public class QuiltUtil {

	public static boolean shouldDisplayBoosterParticles(World world, BlockPos pos, BlockPos offset, RandomGenerator random) {
		if (!world.getBlockState(pos.add(offset.getX() / 2, offset.getY(), offset.getZ() / 2)).isIn(BlockTags.ENCHANTMENT_POWER_TRANSMITTER)) {
			return false;
		}

		var blockPos = pos.add(offset);
		var blockState = world.getBlockState(blockPos);
		var block = blockState.getBlock();
		var booster = BlockContentRegistries.ENCHANTING_BOOSTERS.getNullable(block);

		if (booster != null) {
			var power = booster.getEnchantingBoost(world, blockState, blockPos);
			return random.nextFloat() * 16f <= power;
		}

		return false;
	}

}
