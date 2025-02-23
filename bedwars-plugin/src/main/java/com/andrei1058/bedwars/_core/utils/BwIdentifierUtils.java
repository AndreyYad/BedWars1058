package com.andrei1058.bedwars._core.utils;

import com.andrei1058.bedwars.BedWars;
import lombok.NonNull;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.metadata.Metadatable;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static com.andrei1058.bedwars._fwextension.utils.Utils.takeMeta;

@SuppressWarnings("unused")
public class BwIdentifierUtils {

    static private final String KEY = "BedWarsIdentifier";

    static public void writeIdentifier(@NonNull Metadatable target, String identifier) {
        target.setMetadata(KEY, new FixedMetadataValue(BedWars.plugin, identifier));
    }

    static public void writeIdentifier(@NonNull ItemStack target, String identifier) {
        ItemMeta meta = takeMeta(target);
        meta.addAttributeModifier(Attribute.ZOMBIE_SPAWN_REINFORCEMENTS, new AttributeModifier(UUID.fromString(identifier), KEY, 0, AttributeModifier.Operation.ADD_NUMBER));
    }


    static public String readIdentifier(@NonNull Metadatable target) {
        List<MetadataValue> identifier = target.getMetadata(KEY);
        if (identifier.isEmpty()) {
            return null;
        } else {
            return identifier.get(0).asString();
        }
    }

    static public String readIdentifier(@NonNull ItemStack target) {
        ItemMeta meta = takeMeta(target);
        Collection<AttributeModifier> attributes = meta.getAttributeModifiers(Attribute.ZOMBIE_SPAWN_REINFORCEMENTS);
        if (attributes != null) {
            for (AttributeModifier attribute : attributes) {
                if (attribute.getName().equals(KEY)) {
                    return attribute.getUniqueId().toString();
                }
            }
        }
        return null;
    }


    static public void eraseIdentifier(@NonNull Metadatable target) {
        target.removeMetadata(KEY, BedWars.plugin);
    }

    /// вообще фактически хз зачем оно
    static public void eraseIdentifier(@NonNull ItemStack target) {
        ItemMeta meta = takeMeta(target);
        Collection<AttributeModifier> attributes = meta.getAttributeModifiers(Attribute.ZOMBIE_SPAWN_REINFORCEMENTS);
        if (attributes != null) {
            for (AttributeModifier attribute : attributes) {
                if (attribute.getName().equals(KEY)) {
                    meta.removeAttributeModifier(Attribute.ZOMBIE_SPAWN_REINFORCEMENTS, attribute);
                }
            }
        }
    }
}
