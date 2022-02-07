package fr.lightnew.event;

import fr.lightnew.ecobuilder.EcoBuilder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.IOException;

public class PlayerManager implements Listener {

    @EventHandler
    public void onjoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        EcoBuilder eco = new EcoBuilder(player);
        eco.createBank();
    }
}
