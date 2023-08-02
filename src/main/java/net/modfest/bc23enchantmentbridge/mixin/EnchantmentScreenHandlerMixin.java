package net.modfest.bc23enchantmentbridge.mixin;

import net.fabricmc.fabric.api.tag.convention.v1.TagUtil;
import net.minecraft.block.EnchantingTableBlock;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.screen.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.world.World;
import net.modfest.bc23enchantmentbridge.block.BetterEnchantmentBoostingEnchantingTableBlock;
import net.modfest.bc23enchantmentbridge.registry.ModTags;
import net.modfest.bc23enchantmentbridge.util.BetterEnchantmentBoostingUtil;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.function.BiConsumer;

@Mixin(value = EnchantmentScreenHandler.class, priority = 1500)
public abstract class EnchantmentScreenHandlerMixin extends ScreenHandler {

	@Shadow
	@Final
	private RandomGenerator random;

	@Shadow
	@Final
	private Property seed;

	@Shadow
	@Final
	public int[] enchantmentPower;

	@Shadow
	@Final
	public int[] enchantmentLevel;

	@Shadow
	@Final
	public int[] enchantmentId;

	@Shadow
	protected abstract List<EnchantmentLevelEntry> generateEnchantments(ItemStack stack, int slot, int level);

	protected EnchantmentScreenHandlerMixin(@Nullable ScreenHandlerType<?> type, int syncId) {
		super(type, syncId);
	}

	@ModifyVariable(method = "generateEnchantments", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/enchantment/EnchantmentHelper;generateEnchantments(Lnet/minecraft/util/random/RandomGenerator;Lnet/minecraft/item/ItemStack;IZ)Ljava/util/List;"))
	private List<EnchantmentLevelEntry> bc23enchantmentbridge$removeEnchancementFromGeneration(List<EnchantmentLevelEntry> value) {
		value.removeIf(entry -> TagUtil.isIn(ModTags.IS_ENCHANCEMENT, entry.enchantment));
		return value;
	}

	ItemStack bc23enchantmentbridge$capturedItemStack;

	@Inject(method = "onContentChanged", at = @At("HEAD"))
	private void bc23enchantmentbridge$captureItemStack(Inventory inventory, CallbackInfo ci) {
		this.bc23enchantmentbridge$capturedItemStack = inventory.getStack(0);
	}

	@ModifyArg(method = "onContentChanged", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/ScreenHandlerContext;run(Ljava/util/function/BiConsumer;)V"))
	private BiConsumer<World, BlockPos> bc23enchantmentbridge$cancelOutBetterEnchantmentBoostingCall(BiConsumer<World, BlockPos> original) {
		return (world, pos) -> {
			if (world.getBlockState(pos).getBlock() instanceof BetterEnchantmentBoostingEnchantingTableBlock) {
				original.accept(world, pos);
				return;
			}
			int ix = 0;

			for(BlockPos blockPos : EnchantingTableBlock.POSSIBLE_BOOKSHELF_LOCATIONS) {
				if (EnchantingTableBlock.isValidForBookshelf(world, pos, blockPos)) {
					++ix;
				}
			}

			this.random.setSeed((long)this.seed.get());

			for(int j = 0; j < 3; ++j) {
				this.enchantmentPower[j] = EnchantmentHelper.calculateRequiredExperienceLevel(this.random, j, ix, this.bc23enchantmentbridge$capturedItemStack);
				this.enchantmentId[j] = -1;
				this.enchantmentLevel[j] = -1;
				if (this.enchantmentPower[j] < j + 1) {
					this.enchantmentPower[j] = 0;
				}
			}

			for(int j = 0; j < 3; ++j) {
				if (this.enchantmentPower[j] > 0) {
					BetterEnchantmentBoostingUtil.bc23enchantmentbridge$storedEnchantingTableBlockPos = pos;
					bc23enchantmentbridge$capturedWorld = world;
					List<EnchantmentLevelEntry> list = this.generateEnchantments(this.bc23enchantmentbridge$capturedItemStack, j, this.enchantmentPower[j]);
					if (list != null && !list.isEmpty()) {
						EnchantmentLevelEntry enchantmentLevelEntry = (EnchantmentLevelEntry)list.get(this.random.nextInt(list.size()));
						this.enchantmentId[j] = Registries.ENCHANTMENT.getRawId(enchantmentLevelEntry.enchantment);
						this.enchantmentLevel[j] = enchantmentLevelEntry.level;
					}
				}
			}
			this.sendContentUpdates();
		};
	}

	private World bc23enchantmentbridge$capturedWorld;
	private ItemStack bc23enchantmentbridge$capturedGenerateStack;
	private int bc23enchantmentbridge$capturedLevel;

	@Inject(method = "method_17410", at = @At("HEAD"))
	private void bc23enchantmentbridge$captureBlockPos(ItemStack itemStack, int i, PlayerEntity playerEntity, int j, ItemStack itemStack2, World world, BlockPos pos, CallbackInfo ci) {
		BetterEnchantmentBoostingUtil.bc23enchantmentbridge$storedEnchantingTableBlockPos = pos;
		this.bc23enchantmentbridge$capturedWorld = world;
	}

	@Inject(method = "generateEnchantments", at = @At("HEAD"))
	private void bc23enchantmentbridge$captureGenerateEnchantmentValues(ItemStack stack, int slot, int level, CallbackInfoReturnable<List<EnchantmentLevelEntry>> cir) {
		this.bc23enchantmentbridge$capturedGenerateStack = stack;
		this.bc23enchantmentbridge$capturedLevel = level;
	}

	@ModifyVariable(method = "generateEnchantments", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/enchantment/EnchantmentHelper;generateEnchantments(Lnet/minecraft/util/random/RandomGenerator;Lnet/minecraft/item/ItemStack;IZ)Ljava/util/List;"))
	private List<EnchantmentLevelEntry> bc23enchantmentbridge$swapOutEnchantmentGenerationMethod(List<EnchantmentLevelEntry> original) {
		if (bc23enchantmentbridge$capturedWorld.getBlockState(BetterEnchantmentBoostingUtil.bc23enchantmentbridge$storedEnchantingTableBlockPos).getBlock() instanceof BetterEnchantmentBoostingEnchantingTableBlock)
			return BetterEnchantmentBoostingUtil.generateEnchantments(this.random, this.bc23enchantmentbridge$capturedGenerateStack, this.bc23enchantmentbridge$capturedLevel, false);

		return original;
	}
}
