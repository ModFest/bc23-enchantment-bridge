package net.modfest.bc23enchantmentbridge.client;

import net.minecraft.client.gui.screen.ingame.EnchantmentScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.modfest.bc23enchantmentbridge.registry.ModScreenHandlers;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;

public class BC23EnchantmentBridgeClient implements ClientModInitializer {
	@Override
	public void onInitializeClient(ModContainer mod) {
		HandledScreens.register(ModScreenHandlers.BETTER_ENCHANTMENT_BOOSTING_ENCHANTING_TABLE, EnchantmentScreen::new);
	}
}
