package net.modfest.bc23enchantmentbridge.mixin;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import net.modfest.bc23enchantmentbridge.registry.ModEnchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(CrossbowItem.class)
public class CrossbowItemMixin {

	@ModifyVariable(method = "createArrow", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/item/ArrowItem;createArrow(Lnet/minecraft/world/World;Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/LivingEntity;)Lnet/minecraft/entity/projectile/PersistentProjectileEntity;"))
	private static PersistentProjectileEntity bc23enchantmentbridge$makeArrowPersistentWithEnchancementInfinity(PersistentProjectileEntity original, World world, LivingEntity entity, ItemStack crossbow, ItemStack arrow) {
		if (arrow.isOf(Items.ARROW) && EnchantmentHelper.getLevel(ModEnchantments.ENCHANCEMENT_INFINITY, crossbow) > 0)
			original.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;

		return original;
	}

	@ModifyVariable(method = "loadProjectile", at = @At("HEAD"), ordinal = 0, argsOnly = true)
	private static boolean bc23enchantmentbridge$dontConsumeArrowWithEnchancementIfninity(boolean simulated, LivingEntity shooter, ItemStack crossbow, ItemStack projectile, boolean creative) {
		if (projectile.isOf(Items.ARROW) && EnchantmentHelper.getLevel(ModEnchantments.ENCHANCEMENT_INFINITY, crossbow) > 0)
			return true;
		return simulated;
	}

}
