package net.modfest.bc23enchantmentbridge.mixin;

import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ScreenHandler.class)
public interface ScreenHandlerAccessor {

	@Accessor("type") @Mutable
	void bc23enchantmentbridge$setType(ScreenHandlerType<?> value);

}
