package fr.yannlm29;

import java.util.LinkedList;

import org.bukkit.Bukkit;

public class ConnectedPlayersList {
    private LinkedList<ConnectedPlayer> mPlayers;

    public ConnectedPlayersList() {
        mPlayers = new LinkedList<ConnectedPlayer>();
    }

    // -------------------- GET --------------------
    public LinkedList<ConnectedPlayer> getPlayers() {
        return mPlayers;
    }

    public ConnectedPlayer getPlayer(String inPseudo) {
        for(ConnectedPlayer player : mPlayers) {
            if(player.getPseudo().equals(inPseudo)) {
               return player;
            }
        }
        return null;
    }

    // -------------------- SET --------------------
    public Boolean addPlayer(String inPseudo) {

        // Check if player is not already in the list
        for(ConnectedPlayer player : mPlayers) {
            if(player.getPseudo().equals(inPseudo)) {
               return false;
            }
        }

        mPlayers.add(new ConnectedPlayer(inPseudo));
        return true;
    }

    public void removePlayer(String inPseudo) {
        mPlayers.removeIf(player -> player.getPseudo().equals(inPseudo));
    }
}
