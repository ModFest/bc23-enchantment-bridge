/**
 * MIT License
 *
 * Copyright (c) 2022 RedstoneParadox
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package net.modfest.bc23enchantmentbridge.screen;

import io.github.redstoneparadox.betterenchantmentboosting.util.EnchantingUtil;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.screen.EnchantmentScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.util.math.BlockPos;
import net.modfest.bc23enchantmentbridge.mixin.EnchantmentScreenHandlerAccessor;
import net.modfest.bc23enchantmentbridge.mixin.ScreenHandlerAccessor;
import net.modfest.bc23enchantmentbridge.registry.ModBlocks;
import net.modfest.bc23enchantmentbridge.registry.ModScreenHandlers;

import java.util.List;

public class BetterEnchantmentBoostingEnchantingTableScreenHandler extends EnchantmentScreenHandler {

	public BetterEnchantmentBoostingEnchantingTableScreenHandler(int syncId, PlayerInventory playerInventory) {
		this(syncId, playerInventory, ScreenHandlerContext.EMPTY);
	}

	public BetterEnchantmentBoostingEnchantingTableScreenHandler(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
		super(syncId, playerInventory, context);
		((ScreenHandlerAccessor)this).bc23enchantmentbridge$setType(ModScreenHandlers.BETTER_ENCHANTMENT_BOOSTING_ENCHANTING_TABLE);
	}

	@Override
	public void onContentChanged(Inventory inventory) {
		if (inventory == ((EnchantmentScreenHandlerAccessor)this).bc23enchantmentbridge$getInventory()) {
			ItemStack itemStack = inventory.getStack(0);
			if (!itemStack.isEmpty() && itemStack.isEnchantable()) {
				((EnchantmentScreenHandlerAccessor) this).bc23enchantmentbridge$getContext().run((world, pos) -> {List<BlockPos> boosterPositions = EnchantingUtil.search(world, pos);
					double power = EnchantingUtil.getPower(world, boosterPositions);

					((EnchantmentScreenHandlerAccessor) this).bc23enchantmentbridge$getRandom().setSeed(((EnchantmentScreenHandlerAccessor) this).bc23enchantmentbridge$getSeed().get());
					for (int slot = 0; slot < 3; ++slot) {
						enchantmentPower[slot] = EnchantmentHelper.calculateRequiredExperienceLevel(((EnchantmentScreenHandlerAccessor) this).bc23enchantmentbridge$getRandom(), slot, (int) power, inventory.getStack(0));
						enchantmentId[slot] = -1;
						enchantmentLevel[slot] = -1;
						if (enchantmentPower[slot] >= slot + 1) continue;
						enchantmentPower[slot] = 0;
					}
					for (int slot = 0; slot < 3; ++slot) {
						List<EnchantmentLevelEntry> list = EnchantingUtil.generateEnchantments(
								inventory.getStack(0),
								slot,
								enchantmentPower[slot],
								((EnchantmentScreenHandlerAccessor) this).bc23enchantmentbridge$getRandom(),
								((EnchantmentScreenHandlerAccessor) this).bc23enchantmentbridge$getSeed().get(),
								world,
								boosterPositions
						);
						if (enchantmentPower[slot] <= 0 || list.isEmpty()) continue;
						EnchantmentLevelEntry enchantmentLevelEntry = list.get(((EnchantmentScreenHandlerAccessor) this).bc23enchantmentbridge$getRandom().nextInt(list.size()));
						enchantmentId[slot] = Registries.ENCHANTMENT.getRawId(enchantmentLevelEntry.enchantment);
						enchantmentLevel[slot] = enchantmentLevelEntry.level;
					}
					sendContentUpdates();
				});
			} else {
				for(int i = 0; i < 3; ++i) {
					this.enchantmentPower[i] = 0;
					this.enchantmentId[i] = -1;
					this.enchantmentLevel[i] = -1;
				}
			}
		}
	}

	public boolean canUse(PlayerEntity player) {
		return canUse(((EnchantmentScreenHandlerAccessor)this).bc23enchantmentbridge$getContext(), player, ModBlocks.BETTER_ENCHANTMENT_BOOSTING_ENCHANTING_TABLE);
	}

}
