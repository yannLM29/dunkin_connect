package fr.yannlm29;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SetBaseCommand implements CommandExecutor{
    
    private DbConnector mDbConnector;

    public SetBaseCommand(DbConnector inDbConnector) {
        mDbConnector = inDbConnector;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // TODO Auto-generated method stub
        sender.sendMessage("OK");
        return false;
    }
}
