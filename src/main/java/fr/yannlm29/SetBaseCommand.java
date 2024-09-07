package fr.yannlm29;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetBaseCommand implements CommandExecutor{
    
    private DbConnector mDbConnector;

    public SetBaseCommand(DbConnector inDbConnector) {
        mDbConnector = inDbConnector;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            
            double x = player.getLocation().getX();
            double y = player.getLocation().getY();
            double z = player.getLocation().getZ();

            mDbConnector.updateBasePosition(player.getName(), x, y, z);
            player.sendMessage("New base position set: " + (int)x + ", " + (int)y + ", " + (int)z);
        }
        
        return false;
    }
}
