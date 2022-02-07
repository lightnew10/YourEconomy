package fr.lightnew;

import fr.lightnew.command.EcoBalance;
import fr.lightnew.command.EcoCommand;
import fr.lightnew.event.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class Economy extends JavaPlugin {
    public static Economy instance;
    private final File playerdata = new File(getDataFolder() + "/PlayerData");

    @Override
    public void onEnable() {
        instance=this;
        saveDefaultConfig();
        log(ChatColor.WHITE + "[" + ChatColor.GREEN + "YourEconomy" + ChatColor.WHITE + "] " + ChatColor.YELLOW + "This plugin is ON");
        Bukkit.getPluginManager().registerEvents(new PlayerManager(), this);
        this.getCommand("eco").setExecutor(new EcoCommand());
        this.getCommand("balance").setExecutor(new EcoBalance());
        if (!playerdata.exists()) {
            if (playerdata.mkdir()) {
                log(ChatColor.GREEN + "Add " + ChatColor.YELLOW + playerdata.getName()+ ChatColor.GREEN + " to " + ChatColor.YELLOW + getDataFolder());
            } else log(ChatColor.RED + "This file was incorrectly install");
        }
    }

    @Override
    public void onDisable() {
        log(ChatColor.WHITE + "[" + ChatColor.RED + "YourEconomy" + ChatColor.WHITE + "] " + ChatColor.YELLOW + "This plugin is OFF");
    }

    public static void log(String s){Bukkit.getConsoleSender().sendMessage(s);}
}
