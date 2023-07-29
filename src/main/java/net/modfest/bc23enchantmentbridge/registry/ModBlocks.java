package net.modfest.bc23enchantmentbridge.registry;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.modfest.bc23enchantmentbridge.BC23EnchantmentBridge;
import net.modfest.bc23enchantmentbridge.block.BetterEnchantmentBoostingEnchantingTableBlock;
import net.modfest.bc23enchantmentbridge.block.EnchancementEnchantingTableBlock;

public class ModBlocks {
	public static final BetterEnchantmentBoostingEnchantingTableBlock BETTER_ENCHANTMENT_BOOSTING_ENCHANTING_TABLE = register(new BetterEnchantmentBoostingEnchantingTableBlock(
			AbstractBlock.Settings.create()
					.mapColor(MapColor.RED)
					.instrument(NoteBlockInstrument.BASEDRUM)
					.requiresTool()
					.luminance(state -> 7)
					.strength(5.0F, 1200.0F)), "better_enchantment_boosting_enchanting_table");
	public static final EnchancementEnchantingTableBlock ENCHANCEMENT_ENCHANTING_TABLE = register(new EnchancementEnchantingTableBlock(
		AbstractBlock.Settings.create()
			.mapColor(MapColor.RED)
			.instrument(NoteBlockInstrument.BASEDRUM)
			.requiresTool()
			.luminance(state -> 7)
			.strength(5.0F, 1200.0F)), "enchancement_enchanting_table");


	public static void registerAll() { }

	public static <T extends Block> T register(T block, String id) {
		return Registry.register(Registries.BLOCK, BC23EnchantmentBridge.identifier(id), block);
	}

}
