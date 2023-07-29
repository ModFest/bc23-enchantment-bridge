package net.modfest.bc23enchantmentbridge.util;

import net.minecraft.registry.Registry;
import net.minecraft.registry.tag.TagKey;

public class TagUtil {
	public static <T> boolean isInTag(Registry<T> registry, T object, TagKey<T> tagKey) {
		var registryKey = registry.getKey(object);
		if (registryKey.isEmpty())
			return false;

		var holder = registry.getHolder(registryKey.get());
		return holder.filter(enchantmentReference -> enchantmentReference.isBound() && enchantmentReference.isIn(tagKey)).isPresent();
	}
}
