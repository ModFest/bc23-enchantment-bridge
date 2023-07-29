package net.modfest.bc23enchantmentbridge.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.modfest.bc23enchantmentbridge.registry.ModBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.HashSet;
import java.util.Set;

@Mixin(BlockEntityType.Builder.class)
public class BlockEntityTypeBuilderMixin {

	@ModifyArg(method = "create", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/entity/BlockEntityType$Builder;<init>(Lnet/minecraft/block/entity/BlockEntityType$BlockEntityFactory;Ljava/util/Set;)V"))
	private static Set<Block> bc23enchantmentbridge$addExtraTablesToBlockEntityType(Set<Block> blocks) {
		if (blocks.contains(Blocks.ENCHANTING_TABLE)) {
			Set<Block> newSet = new HashSet<>(blocks);
			newSet.add(ModBlocks.ENCHANCEMENT_ENCHANTING_TABLE);
			return Set.copyOf(newSet);
		}
		return blocks;
	}

}
