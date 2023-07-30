package net.modfest.bc23enchantmentbridge.mixin;

import com.ordana.enchantery.EnchanteryClient;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.Blocks;
import net.minecraft.block.EnchantingTableBlock;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.world.World;
import net.modfest.bc23enchantmentbridge.block.EnchancementEnchantingTableBlock;
import net.modfest.bc23enchantmentbridge.registry.ModBlocks;
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

			for(BlockPos blockPos : EnchantingTableBlock.POSSIBLE_BOOKSHELF_LOCATIONS) {
				if (random.nextInt(16) == 0) {
					if (this == Blocks.ENCHANTING_TABLE) {
						EnchanteryClient.addEnchantParticles(world, pos, blockPos);
					}
					if (EnchantingTableBlock.isValidForBookshelf(world, pos, blockPos)) {
						world.addParticle(
								ParticleTypes.ENCHANT,
								(double)pos.getX() + 0.5,
								(double)pos.getY() + 2.0,
								(double)pos.getZ() + 0.5,
								(double)((float)blockPos.getX() + random.nextFloat()) - 0.5,
								(double)((float)blockPos.getY() - random.nextFloat() - 1.0F),
								(double)((float)blockPos.getZ() + random.nextFloat()) - 0.5
						);
					}
				}
			}
			ci.cancel();
		}
	}

}
