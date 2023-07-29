package net.modfest.bc23enchantmentbridge.registry;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.modfest.bc23enchantmentbridge.BC23EnchantmentBridge;

public class ModItems {
	public static final Item ENCHANCEMENT_ENCHANTING_TABLE = register(new BlockItem(ModBlocks.ENCHANCEMENT_ENCHANTING_TABLE, new Item.Settings()), "enchancement_enchanting_table");

	public static void registerAll() { }

	public static <T extends Item> T register(T item, String id) {
		return Registry.register(Registries.ITEM, BC23EnchantmentBridge.identifier(id), item);
	}

}
