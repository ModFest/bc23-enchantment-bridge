package net.modfest.bc23enchantmentbridge.mixin;

import net.minecraft.inventory.Inventory;
import net.minecraft.screen.EnchantmentScreenHandler;
import net.minecraft.screen.Property;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.util.random.RandomGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

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
}
