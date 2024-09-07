package fr.yannlm29;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class PlayersListCommand implements CommandExecutor{
    
    ConnectedPlayersList mPlayersList;
    DbConnector mDbConnector;

    public PlayersListCommand(ConnectedPlayersList inPlayerListRef, DbConnector inDbConnector) {
        mPlayersList = inPlayerListRef;
        mDbConnector = inDbConnector;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        
        sender.sendMessage("Local Players:");
        for(ConnectedPlayer p : mPlayersList.getPlayers()) {
            sender.sendMessage(" -" + p.getPseudo() + " kills:" + p.getNumberOfKills() + " deaths:" + p.getNumberOfDeaths());
        }

        sender.sendMessage("Db Players:");
        for(DbPlayer p : mDbConnector.getPlayerList()) {
            sender.sendMessage(" -" + p.mPseudo + " kills:" + p.mNumberOfKills + " deaths:" + p.mNumberOfDeaths);
        }
        
        return true;
    }
}
