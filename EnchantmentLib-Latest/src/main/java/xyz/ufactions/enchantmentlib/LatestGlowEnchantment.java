package xyz.ufactions.enchantmentlib;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

import java.util.Locale;

public class LatestGlowEnchantment extends Enchantment {

    public LatestGlowEnchantment() {
        super(new NamespacedKey("CustomCrates".toLowerCase(Locale.ROOT), "CustomCratesGlow".toLowerCase(Locale.ROOT)));
    }

    @Override
    public String getName() {
        return "CustomCratesGlow";
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public int getStartLevel() {
        return 0;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return null;
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean conflictsWith(Enchantment enchantment) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack itemStack) {
        return true;
    }
}