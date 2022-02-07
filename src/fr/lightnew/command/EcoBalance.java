package fr.lightnew.command;

import fr.lightnew.Economy;
import fr.lightnew.ecobuilder.EcoBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

public class EcoBalance implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (Economy.instance.getConfig().getBoolean("Eco-default.enable-command")) {
            if (sender instanceof Player) {
                Player target;
                Player player = (Player) sender;
                String perm = Economy.instance.getConfig().getString("Eco-default.perm-command");
                String message_perm = Economy.instance.getConfig().getString("Eco-default.message-player-perm").replace('&', '§');
                if (args.length == 0) {
                    EcoBuilder eco = new EcoBuilder(player);
                    try {
                        sender.sendMessage(ChatColor.YELLOW + "Votre balance : " + ChatColor.RESET + eco.getBalanceAround());
                        sender.sendMessage(ChatColor.YELLOW + "Votre balance : " + ChatColor.RESET + eco.getBalanceDecimal());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (player.hasPermission(perm)) {
                    if (args.length == 1) {
                        if (Bukkit.getPlayer(args[0]) != null) {
                            target = Bukkit.getPlayer(args[0]);
                            EcoBuilder eco = new EcoBuilder(target);
                            try {player.sendMessage(ChatColor.YELLOW + "Balance de " + ChatColor.RESET + target.getName() + ChatColor.YELLOW + " : " + ChatColor.RESET + eco.getBalanceDecimal());} catch (IOException e) {e.printStackTrace();}
                        } else {
                            EcoBuilder eco = new EcoBuilder(player);
                            try {player.sendMessage(ChatColor.YELLOW + "Balance de " + ChatColor.RESET + args[0] + ChatColor.YELLOW + " : " + ChatColor.RESET + eco.getBalanceDecimalOfflinePlayer(args[0]));} catch (IOException e) {e.printStackTrace();}
                        }
                    }
                } else player.sendMessage(message_perm);
            } else sender.sendMessage(ChatColor.RED + "Vous devez être un joueur pour faire ceci !");
        } else return true;
        return false;
    }
}
