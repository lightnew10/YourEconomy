package fr.lightnew.command;

import fr.lightnew.Economy;
import fr.lightnew.ecobuilder.EcoBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EcoCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (Economy.instance.getConfig().getBoolean("Eco-default.enable-command")) {
            Player target;
            String perm = Economy.instance.getConfig().getString("Eco-default.perm-command");
            String message_perm = Economy.instance.getConfig().getString("Eco-default.message-player-perm").replace('&', '§');
            if (sender.hasPermission(perm)) {
                if (args.length == 0 || args.length == 1) {
                    sender.sendMessage(ChatColor.GOLD + "--------------------------------\n" +
                            ChatColor.YELLOW + "/eco set <player> <amount>\n" +
                            "/eco give <player> <amount>\n" +
                            "/eco reset <player>\n" +
                            "/eco remove <player> <amount>\n" +
                            ChatColor.GOLD + "--------------------------------"
                    );
                }
                if (args.length > 1) {
                    target = Bukkit.getPlayer(args[1]);
                    EcoBuilder eco = new EcoBuilder(target);
                    if (target != null) {
                        if (args[0].equalsIgnoreCase("set")) {
                            double amount = Double.parseDouble(args[2]);
                            eco.setMoney(amount);
                            sender.sendMessage(ChatColor.GREEN + "Vous avez set §r" + amount + "§e à la balance de " + target.getName());
                        }
                        if (args[0].equalsIgnoreCase("give")) {
                            double amount = Double.parseDouble(args[2]);
                            eco.addMoney(amount);
                            sender.sendMessage(ChatColor.GREEN + "Vous avez ajouter §r" + amount + "§e à la balance de " + target.getName());
                        }
                        if (args[0].equalsIgnoreCase("reset")) {
                            eco.resetBalance();
                            sender.sendMessage(ChatColor.GREEN + "Vous avez reset la balance §eà " + target.getName());
                        }
                        if (args[0].equalsIgnoreCase("remove")) {
                            double amount = Double.parseDouble(args[2]);
                            eco.addMoney(-amount);
                            sender.sendMessage(ChatColor.GREEN + "Vous avez remove §r" + amount + "§e à la balance de " + target.getName());
                        }
                    } else sender.sendMessage(ChatColor.RED + "This player is offline!");
                }
            } else sender.sendMessage(message_perm);
        } else return true;
        return false;
    }
}
