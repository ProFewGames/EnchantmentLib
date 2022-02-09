package xyz.ufactions.enchantmentlib;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.Locale;

public class EnchantmentLib {

    private static Enchantment glowEnchantment;

    public static Enchantment getGlowEnchantment() {
        if (glowEnchantment == null) {
            Plugin plugin = JavaPlugin.getProvidingPlugin(EnchantmentLib.class);
            if (VersionUtils.getVersion().equalOrGreater(VersionUtils.Version.V1_13)) { // Check if enchantment is already registered.
                glowEnchantment = Enchantment.getByKey(new NamespacedKey("CustomCrates".toLowerCase(Locale.ROOT), "CustomCratesGlow".toLowerCase(Locale.ROOT)));
                if (glowEnchantment != null){
                    debug(plugin, "Glow enchantment already initialized. Using located enchantment.");
                    return glowEnchantment;
                }
            }
            try {
                Field EnchantmentFieldAcceptingNew = Enchantment.class.getDeclaredField("acceptingNew");
                EnchantmentFieldAcceptingNew.setAccessible(true);
                EnchantmentFieldAcceptingNew.set(null, true);
                debug(plugin, "Version Detected: " + VersionUtils.getVersion().name());
                if (VersionUtils.getVersion().equalOrGreater(VersionUtils.Version.V1_13)) {
                    debug(plugin, "Using latest enchantment api");
                    glowEnchantment = new LatestGlowEnchantment();
                } else {
                    debug(plugin, "Using legacy enchantment api");
                    glowEnchantment = new LegacyGlowEnchantment();
                }
                Enchantment.registerEnchantment(glowEnchantment);
            } catch (IllegalAccessException | NoSuchFieldException e) {
                glowEnchantment = Enchantment.values()[0];
                debug(plugin, "Cannot register glow enchantment. " +
                        "Has the plugin been reloaded? " +
                        "Please provide the following error when creating a support ticket:");
                e.printStackTrace();
            }
        }
        return glowEnchantment;
    }

    private static void debug(Plugin plugin, String message) {
        plugin.getLogger().info("[EnchantmentLib] " + message);
    }
}