package net.modfest.bc23enchantmentbridge;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.modfest.bc23enchantmentbridge.registry.ModBlocks;
import net.modfest.bc23enchantmentbridge.registry.ModEnchantments;
import net.modfest.bc23enchantmentbridge.registry.ModItems;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BC23EnchantmentBridge implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod name as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("Blanketcon 23 Enchantment Bridge");
	public static final String MODID = "bc23enchantmentbridge";

	@Override
	public void onInitialize(ModContainer mod) {
		ModBlocks.registerAll();
		ModEnchantments.registerAll();
		ModItems.registerAll();
		placeItemsInCreativeMenu();

		UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
			if (player.isSneaking() && EnchantmentHelper.get(player.getStackInHand(hand)).containsKey(ModEnchantments.ENCHANCEMENT_FIRE_ASPECT)) {
				ActionResult result = Items.FLINT_AND_STEEL.useOnBlock(new ItemUsageContext(player, hand, hitResult));
				if (result.isAccepted()) {
					return result;
				}
			}
			return ActionResult.PASS;
		});
	}

	private static void placeItemsInCreativeMenu() {
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL_BLOCKS).register(entries -> {
			entries.addAfter(Items.ENCHANTING_TABLE, ModBlocks.ENCHANCEMENT_ENCHANTING_TABLE);
		});
	}

	public static Identifier identifier(String path) {
		return new Identifier(MODID, path);
	}
}
