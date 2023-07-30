package net.modfest.bc23enchantmentbridge.mixin;

import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.EnchantmentScreenHandler;
import net.minecraft.screen.Property;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.util.random.RandomGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.List;

@Mixin(EnchantmentScreenHandler.class)
public interface EnchantmentScreenHandlerAccessor {
	@Accessor("inventory")
	Inventory bc23enchantmentbridge$getInventory();

	@Accessor("context")
	ScreenHandlerContext bc23enchantmentbridge$getContext();

	@Accessor("random")
	RandomGenerator bc23enchantmentbridge$getRandom();

	@Accessor("seed")
	Property bc23enchantmentbridge$getSeed();

	@Invoker("generateEnchantments")
	List<EnchantmentLevelEntry> bc23enchantmentbridge$invokeGenerateEnchantments(ItemStack stack, int slot, int level);
}
