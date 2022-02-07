package fr.lightnew.minecraft.api;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class GetUUID {

    public static String getPlayerUUID(Player playerName) throws IOException {
        URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + playerName);
        InputStream input = url.openConnection().getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        String readLine = reader.readLine();
        String uuid = readLine.substring(readLine.length() - 34, readLine.length() - 2);
        StringBuffer send = new StringBuffer(uuid);
        send.insert(8, "-");
        send.insert(13, "-");
        send.insert(18, "-");
        send.insert(23, "-");
        return String.valueOf(send);
    }

    public static String getPlayerUUID(OfflinePlayer playerName) throws IOException {
        URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + playerName);
        InputStream input = url.openConnection().getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        String readLine = reader.readLine();
        String uuid = readLine.substring(readLine.length() - 34, readLine.length() - 2);
        StringBuffer send = new StringBuffer(uuid);
        send.insert(8, "-");
        send.insert(13, "-");
        send.insert(18, "-");
        send.insert(23, "-");
        return String.valueOf(send);
    }

    public static String getPlayerUUID(String playerName) throws IOException {
        String sends = null;
        if (playerName.length() >= 3 && playerName.length() <= 16){
            URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + playerName);
            InputStream input = url.openConnection().getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String readLine = reader.readLine();
            String uuid = readLine.substring(readLine.length() - 34, readLine.length() - 2);
            StringBuffer send = new StringBuffer(uuid);
            send.insert(8, "-");
            send.insert(13, "-");
            send.insert(18, "-");
            send.insert(23, "-");
            sends = String.valueOf(send);
        }
        return sends;
    }
}
