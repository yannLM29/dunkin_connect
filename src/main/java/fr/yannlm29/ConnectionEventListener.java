package fr.yannlm29;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ConnectionEventListener implements Listener{

    private static final Logger LOGGER=Logger.getLogger("dunkin_connect");
    private ConnectedPlayersList mPlayersList;


    public ConnectionEventListener(ConnectedPlayersList inPlayerListRef) {
        mPlayersList = inPlayerListRef;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if(!mPlayersList.addPlayer(event.getPlayer().getName())) {
            LOGGER.warning("Tried to add player multiple times");
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        mPlayersList.removePlayer(event.getPlayer().getName());
    }
}
