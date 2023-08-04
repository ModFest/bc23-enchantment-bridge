package net.modfest.bc23enchantmentbridge.mixin.enchancement;

import moriyashiine.enchancement.client.screen.EnchantingTableScreen;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.enchantment.Enchantment;
import net.modfest.bc23enchantmentbridge.util.EnchancementUtil;
import org.quiltmc.loader.api.minecraft.ClientOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(EnchantingTableScreen.class)
@ClientOnly
public class EnchantingTableScreenMixin {

	@Unique
	private Enchantment bc23enchantmentbridge$capturedEnchantment;

	@Inject(method = "drawBackground", at = @At(value = "INVOKE", target = "Lnet/minecraft/text/Text;translatable(Ljava/lang/String;)Lnet/minecraft/text/MutableText;"), locals = LocalCapture.CAPTURE_FAILHARD)
	private void bc23enchantmentbridge$captureEnchantment(GuiGraphics context, float delta, int mouseX, int mouseY, CallbackInfo ci, int posX, int posY, int i, Enchantment enchantment) {
		this.bc23enchantmentbridge$capturedEnchantment = enchantment;
	}

	@ModifyArg(method = "drawBackground", at = @At(value = "INVOKE", target = "Lnet/minecraft/text/Text;translatable(Ljava/lang/String;)Lnet/minecraft/text/MutableText;", ordinal = 2))
	private String bc23enchantmentbridge$redirectDescriptions(String translationKey) {
		return EnchancementUtil.getDescKeyFromEnchantment(bc23enchantmentbridge$capturedEnchantment, translationKey);
	}

}
