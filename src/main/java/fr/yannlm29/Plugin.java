package fr.yannlm29;

import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;

/*
 * dunkin_connect java plugin
 */
public class Plugin extends JavaPlugin
{
  private static final Logger LOGGER=Logger.getLogger("dunkin_connect");

  public void onEnable()
  {
    LOGGER.info("dunkin_connect enabled");
  }

  public void onDisable()
  {
    LOGGER.info("dunkin_connect disabled");
  }
}
