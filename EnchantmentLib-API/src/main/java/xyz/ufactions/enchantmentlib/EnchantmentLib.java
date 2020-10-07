package xyz.ufactions.enchantmentlib;

import org.bukkit.enchantments.Enchantment;

import java.lang.reflect.Field;

public class EnchantmentLib {

    private static Enchantment glowEnchantment;

    public static Enchantment getGlowEnchantment() {
        if (glowEnchantment == null) {
            try {
                Field EnchantmentFieldAcceptingNew = Enchantment.class.getDeclaredField("acceptingNew");
                EnchantmentFieldAcceptingNew.setAccessible(true);
                EnchantmentFieldAcceptingNew.set(null, true);
                if (VersionUtils.getVersion().equalOrGreater(VersionUtils.Version.V1_13)) {
                    glowEnchantment = new LatestGlowEnchantment();
                } else {
                    glowEnchantment = new LegacyGlowEnchantment();
                }
                Enchantment.registerEnchantment(glowEnchantment);
            } catch (IllegalAccessException | NoSuchFieldException e) {
                glowEnchantment = Enchantment.values()[0];
                debug("[EnchantmentLib] Cannot register glow enchantment. Stacktrace:");
                e.printStackTrace();
            }
        }
        return glowEnchantment;
    }

    private static void debug(Object o) {
        System.out.println(o);
    }
}