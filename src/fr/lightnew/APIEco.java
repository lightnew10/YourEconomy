package fr.lightnew;

import fr.lightnew.ecobuilder.EcoBuilder;
import org.bukkit.entity.Player;

public class APIEco {
    private Player player;
    public EcoBuilder eco;

    public APIEco(Player player) {
        this.player = player;
        this.eco = new EcoBuilder(player);
    }


}
