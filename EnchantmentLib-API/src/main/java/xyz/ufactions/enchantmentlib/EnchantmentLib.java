package xyz.ufactions.enchantmentlib;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.Locale;

public class EnchantmentLib {

    private static JavaPlugin loaderPlugin;
    private static Enchantment glowEnchantment;

    public static Enchantment getGlowEnchantment() {
        if (glowEnchantment == null) {
            if (VersionUtils.getVersion().equalOrGreater(VersionUtils.Version.V1_13)) { // Check if enchantment is already registered.
                glowEnchantment = Enchantment.getByKey(new NamespacedKey("CustomCrates".toLowerCase(Locale.ROOT), "CustomCratesGlow".toLowerCase(Locale.ROOT)));
                if (glowEnchantment != null) {
                    debug("Glow enchantment already initialized. Using located enchantment.");
                    return glowEnchantment;
                }
            }
            try {
                Field EnchantmentFieldAcceptingNew = Enchantment.class.getDeclaredField("acceptingNew");
                EnchantmentFieldAcceptingNew.setAccessible(true);
                EnchantmentFieldAcceptingNew.set(null, true);
                debug("Version Detected: " + VersionUtils.getVersion().name());
                if (VersionUtils.getVersion().equalOrGreater(VersionUtils.Version.V1_13)) {
                    debug("Using latest enchantment api");
                    glowEnchantment = new LatestGlowEnchantment();
                } else {
                    debug("Using legacy enchantment api");
                    glowEnchantment = new LegacyGlowEnchantment();
                }
                Enchantment.registerEnchantment(glowEnchantment);
            } catch (IllegalAccessException | NoSuchFieldException e) {
                glowEnchantment = null;
                debug("Cannot register glow enchantment. " +
                        "Has the plugin been reloaded? " +
                        "Please provide the following error when creating a support ticket:");
                e.printStackTrace();
            }
        }
        return glowEnchantment;
    }

    private static Plugin getLoaderPlugin() {
        if (loaderPlugin == null) {
            loaderPlugin = JavaPlugin.getProvidingPlugin(EnchantmentLib.class);
        }
        return loaderPlugin;
    }

    private static void debug(String message) {
        getLoaderPlugin().getLogger().info("[EnchantmentLib] " + message);
    }
}