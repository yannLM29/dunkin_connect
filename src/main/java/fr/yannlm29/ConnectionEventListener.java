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
    private DbConnector mDbConnector;


    public ConnectionEventListener(ConnectedPlayersList inPlayerListRef, DbConnector inDbConnectorRef) {
        mPlayersList = inPlayerListRef;
        mDbConnector = inDbConnectorRef;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        String pseudo = event.getPlayer().getName();
        if(!mPlayersList.addPlayer(pseudo)) {
            LOGGER.warning("Tried to add player multiple times");
        }

        if(!mDbConnector.isPlayerInDb(pseudo)) {
            mDbConnector.addPlayer(pseudo);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        String pseudo = event.getPlayer().getName();

        DbPlayer db_player = mDbConnector.getPlayerInfo(pseudo);
        ConnectedPlayer connected_player = mPlayersList.getPlayer(pseudo);

        int nb_of_kills = db_player.mNumberOfKills + connected_player.getNumberOfKills();
        int nb_of_deaths = db_player.mNumberOfDeaths + connected_player.getNumberOfDeaths();
        mDbConnector.updatePlayer(pseudo, nb_of_kills, nb_of_deaths);

        mPlayersList.removePlayer(pseudo);
    }
}
