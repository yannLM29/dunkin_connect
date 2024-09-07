package fr.yannlm29;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class GameEventListener implements Listener{
    
    private ConnectedPlayersList mPlayersList;

    public GameEventListener(ConnectedPlayersList inPlayerListRef) {
        mPlayersList = inPlayerListRef;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player deadPlayer = event.getEntity();
        mPlayersList.getPlayer(deadPlayer.getName()).addDeath();

        if (deadPlayer.getKiller() != null) {
            Player killer = deadPlayer.getKiller();
            mPlayersList.getPlayer(killer.getName()).addKill();
        }
    }
}
