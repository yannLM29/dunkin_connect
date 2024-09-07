package fr.yannlm29;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class PlayersListCommand implements CommandExecutor{
    
    ConnectedPlayersList mPlayersList;

    public PlayersListCommand(ConnectedPlayersList inPlayerListRef) {
        mPlayersList = inPlayerListRef;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        
        for(ConnectedPlayer p : mPlayersList.getPlayers()) {
            sender.sendMessage("Players:");
            sender.sendMessage(p.getPseudo() + " kills:" + p.getNumberOfKills() + " deaths:" + p.getNumberOfDeaths());
        }
        
        return true;
    }
}
