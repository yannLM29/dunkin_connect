package fr.yannlm29;

import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;

/*
 * dunkin_connect java plugin
 */
public class Plugin extends JavaPlugin
{
  private static final Logger LOGGER=Logger.getLogger("dunkin_connect");
  ConnectedPlayersList mPlayersList;

  public Plugin() {
    super();
    mPlayersList = new ConnectedPlayersList();
  }

  public void onEnable()
  {
    getServer().getPluginManager().registerEvents(new ConnectionEventListener(mPlayersList), this);
    getServer().getPluginManager().registerEvents(new GameEventListener(), this);

    LOGGER.info("dunkin_connect enabled");
  }

  public void onDisable()
  {
    LOGGER.info("dunkin_connect disabled");
  }
}
