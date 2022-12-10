package net.kunmc.lab.attendancemanagementsystem;

import net.kunmc.lab.commandlib.CommandLib;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class AttendanceManagementSystem extends JavaPlugin {
    public static Config config;

    @Override
    public void onEnable() {
        config = new Config(this);
        CommandLib.register(this, new AMSCommand());
    }

    @Override
    public void onDisable() {
    }

    public static void print(Object obj) {
        if (Objects.equals(System.getProperty("plugin.env"), "DEV")) {
            System.out.printf("[%s] %s%n", net.kunmc.lab.attendancemanagementsystem.AttendanceManagementSystem.class.getSimpleName(), obj);
        }
    }

    public static void broadcast(Object obj) {
        if (Objects.equals(System.getProperty("plugin.env"), "DEV")) {
            Bukkit.broadcastMessage(String.format("[%s] %s", net.kunmc.lab.attendancemanagementsystem.AttendanceManagementSystem.class.getSimpleName(), obj));
        }
    }

    private void getPlayerHead(Player player) {
        // TODO: 実装
    }

    private void setPlayerHead() {
        // TODO: 実装
    }

    public void attend(Player player) {
        // TODO: 実装
        // 顔取得
        // 顔設置
    }
}
