package net.modfest.bc23enchantmentbridge.mixin;

import com.ordana.enchantery.EnchanteryClient;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.Blocks;
import net.minecraft.block.EnchantingTableBlock;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.world.World;
import net.modfest.bc23enchantmentbridge.block.EnchancementEnchantingTableBlock;
import net.modfest.bc23enchantmentbridge.util.QuiltUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EnchantingTableBlock.class)
public abstract class EnchantingTableBlockMixin extends BlockWithEntity {

	protected EnchantingTableBlockMixin(Settings settings) {
		super(settings);
	}

	@Inject(method = "randomDisplayTick", at = @At("HEAD"), cancellable = true)
	private void handleDisplayTick(BlockState state, World world, BlockPos pos, RandomGenerator random, CallbackInfo ci) {
		if (this == Blocks.ENCHANTING_TABLE || (EnchantingTableBlock)(Object)this instanceof EnchancementEnchantingTableBlock) {
			super.randomDisplayTick(state, world, pos, random);

			for(BlockPos offset : EnchantingTableBlock.POSSIBLE_BOOKSHELF_LOCATIONS) {
				if (random.nextInt(16) == 0 && this == Blocks.ENCHANTING_TABLE) {
					EnchanteryClient.addEnchantParticles(world, pos, offset);
				}
				if (QuiltUtil.shouldDisplayBoosterParticles(world, pos, offset, random)) {
					world.addParticle(
							ParticleTypes.ENCHANT,
							(double)pos.getX() + 0.5,
							(double)pos.getY() + 2.0,
							(double)pos.getZ() + 0.5,
							(double)((float)offset.getX() + random.nextFloat()) - 0.5,
							(double)((float)offset.getY() - random.nextFloat() - 1.0F),
							(double)((float)offset.getZ() + random.nextFloat()) - 0.5
					);
				}
			}
			ci.cancel();
		}
	}

}
