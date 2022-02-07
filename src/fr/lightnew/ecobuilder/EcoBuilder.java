package fr.lightnew.ecobuilder;

import fr.lightnew.Economy;
import fr.lightnew.minecraft.api.GetUUID;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class EcoBuilder {
    private File playerDataFile;
    private FileConfiguration configPlayerData;

    private final double defaultBalance = Economy.instance.getConfig().getDouble("Eco-default.default-balance");
    private final String name_eco = Economy.instance.getConfig().getString("Eco-default.name-eco");
    private final String PlayerDataParent = "plugins/YourEconomy/PlayerData";
    private final Player player;

    public EcoBuilder(Player player) {
        this.player=player;
    }

    private Boolean getBalancePlayer;
    private Boolean getPlayerBalance() {
        try {
            String uuid = GetUUID.getPlayerUUID(player.getName());
            playerDataFile = new File(PlayerDataParent, uuid + ".yml");
            if (playerDataFile.exists()) {
                getBalancePlayer = true;
            } else getBalancePlayer = false;
        } catch (IOException e) {e.printStackTrace();}
        return getBalancePlayer;
    }

    private void createFile() throws IOException, InvalidConfigurationException {
        String uuid = GetUUID.getPlayerUUID(player.getName());
        playerDataFile = new File(PlayerDataParent, uuid + ".yml");
        if (!getPlayerBalance()) {
            playerDataFile.createNewFile();
            configPlayerData = YamlConfiguration.loadConfiguration(playerDataFile);
            configPlayerData.set("User.name", player.getName());
            configPlayerData.set("User.balance", defaultBalance);
            configPlayerData.set("User.name-eco", name_eco);
            configPlayerData.save(playerDataFile);
            configPlayerData.load(playerDataFile);
        }
    }

    //Create bank of player (this is shortcuts)
    public void createBank() {
        try {
            createFile();
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    //get the player's balance in double
    public Double getBalanceDecimal() throws IOException {
        double result = defaultBalance;
        if (getPlayerBalance()) {
            String uuid = GetUUID.getPlayerUUID(player.getName());
            playerDataFile = new File(PlayerDataParent, uuid + ".yml");
            configPlayerData = YamlConfiguration.loadConfiguration(playerDataFile);
            result = configPlayerData.getDouble("User.balance");
        }
        return result;
    }

    //get the player's balance in int
    public int getBalanceAround() throws IOException {
        return getBalanceDecimal().intValue();
    }

    //get the offline player's balance (double)
    public String getBalanceDecimalOfflinePlayer(String player) throws IOException {
        String result;
        if (Bukkit.getOfflinePlayer(player) != null) {
            OfflinePlayer player1 = Bukkit.getOfflinePlayer(player);
            String uuid = player1.getUniqueId().toString();
            playerDataFile = new File(PlayerDataParent, uuid + ".yml");
            if (playerDataFile.exists()) {
                playerDataFile = new File(PlayerDataParent, uuid + ".yml");
                configPlayerData = YamlConfiguration.loadConfiguration(playerDataFile);
                result = String.valueOf(configPlayerData.getDouble("User.balance"));
            } else {
                result = ChatColor.RED + "Player is not register this server!";
            }
        } else result = ChatColor.RED + "Pseudo incorrect!";
        return result;
    }

    //reset balance in default money
    public void resetBalance() {
        if (getPlayerBalance()) {
            try {
                String uuid = GetUUID.getPlayerUUID(player.getName());
                playerDataFile = new File(PlayerDataParent, uuid + ".yml");
                configPlayerData = YamlConfiguration.loadConfiguration(playerDataFile);
                configPlayerData.set("User.balance", defaultBalance);
                configPlayerData.save(playerDataFile);
            } catch (IOException e) {e.printStackTrace();}
        }
    }

    //add money the player's balance
    public void addMoney(double amount) {
        if (getPlayerBalance()) {
            try {
                String uuid = GetUUID.getPlayerUUID(player.getName());
                playerDataFile = new File(PlayerDataParent, uuid + ".yml");
                configPlayerData = YamlConfiguration.loadConfiguration(playerDataFile);
                double result = configPlayerData.getDouble("User.balance") + amount;
                configPlayerData.set("User.balance", result);
                configPlayerData.save(playerDataFile);
            } catch (IOException e) {e.printStackTrace();}
        }
    }

    //set money the player's balance
    public void setMoney(double amount) {
        if (getPlayerBalance()) {
            try {
                String uuid = GetUUID.getPlayerUUID(player.getName());
                playerDataFile = new File(PlayerDataParent, uuid + ".yml");
                configPlayerData = YamlConfiguration.loadConfiguration(playerDataFile);
                configPlayerData.set("User.balance", amount);
                configPlayerData.save(playerDataFile);
            } catch (IOException e) {e.printStackTrace();}
        }
    }

    /**CREATE BANK FOR MANY PEOPLE ↓**/
}
