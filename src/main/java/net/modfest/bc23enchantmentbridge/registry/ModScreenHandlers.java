package net.modfest.bc23enchantmentbridge.registry;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.feature_flags.FeatureFlags;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.modfest.bc23enchantmentbridge.BC23EnchantmentBridge;
import net.modfest.bc23enchantmentbridge.screen.BetterEnchantmentBoostingEnchantingTableScreenHandler;

public class ModScreenHandlers {

	public static final ScreenHandlerType<BetterEnchantmentBoostingEnchantingTableScreenHandler> BETTER_ENCHANTMENT_BOOSTING_ENCHANTING_TABLE = register(new ScreenHandlerType<>(BetterEnchantmentBoostingEnchantingTableScreenHandler::new, FeatureFlags.DEFAULT_SET), "better_enchantment_boosting_enchanting_table");


	public static void registerAll() { }

	public static <T extends ScreenHandlerType<?>> T register(T block, String id) {
		return Registry.register(Registries.SCREEN_HANDLER_TYPE, BC23EnchantmentBridge.identifier(id), block);
	}

}
