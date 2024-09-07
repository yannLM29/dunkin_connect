package fr.yannlm29;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class GameEventListener implements Listener{
    
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player deadPlayer = event.getEntity();


        if (deadPlayer.getKiller() != null) {
            Player killer = deadPlayer.getKiller();
        }
    }
}
