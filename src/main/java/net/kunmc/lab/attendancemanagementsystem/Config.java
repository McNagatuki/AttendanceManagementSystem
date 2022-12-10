package net.kunmc.lab.attendancemanagementsystem;

import net.kunmc.lab.configlib.BaseConfig;
import net.kunmc.lab.configlib.value.map.String2EnumMapValue;
import net.kunmc.lab.configlib.value.map.String2LocationMapValue;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class Config extends BaseConfig {
    String2LocationMapValue areas = new String2LocationMapValue();
    String2EnumMapValue<CardinalDirections> face = new String2EnumMapValue();

    public Config(@NotNull Plugin plugin) {
        super(plugin);
    }

    void save() {
        saveConfig();
    }
}
