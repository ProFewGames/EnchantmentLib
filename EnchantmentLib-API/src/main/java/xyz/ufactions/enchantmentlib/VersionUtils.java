package xyz.ufactions.enchantmentlib;

import org.bukkit.Bukkit;

public class VersionUtils {

    public static Version getVersion() {
        if (Bukkit.getVersion().contains("1.8")) return Version.V1_8;
        if (Bukkit.getVersion().contains("1.9")) return Version.V1_9;
        if (Bukkit.getVersion().contains("1.10")) return Version.V1_10;
        if (Bukkit.getVersion().contains("1.11")) return Version.V1_11;
        if (Bukkit.getVersion().contains("1.12")) return Version.V1_12;
        if (Bukkit.getVersion().contains("1.13")) return Version.V1_13;
        if (Bukkit.getVersion().contains("1.14")) return Version.V1_14;
        if (Bukkit.getVersion().contains("1.15")) return Version.V1_15;
        if (Bukkit.getVersion().contains("1.16")) return Version.V1_16;
        if (Bukkit.getVersion().contains("1.17")) return Version.V1_17;
        if (Bukkit.getVersion().contains("1.18")) return Version.V1_18;
        return Version.UNKNOWN;
    }

    public enum Version {
        UNKNOWN(Integer.MAX_VALUE),
        V1_8(1),
        V1_9(2),
        V1_10(3),
        V1_11(4),
        V1_12(5),
        V1_13(6),
        V1_14(7),
        V1_15(8),
        V1_16(9),
        V1_17(10),
        V1_18(11);

        private final int ID;

        Version(int ID) {
            this.ID = ID;
        }

        /**
         * Returns {@code true} if the current server version is greater or
         * equal to {@param version}
         */
        public boolean equalOrGreater(Version version) {
            return ID >= version.ID;
        }

        public int getID() {
            return ID;
        }
    }
}