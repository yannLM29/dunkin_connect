package fr.yannlm29;

import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;

/*
 * dunkin_connect java plugin
 */
public class Plugin extends JavaPlugin
{
  private static final Logger LOGGER=Logger.getLogger("dunkin_connect");
  private ConnectedPlayersList mPlayersList;
  private ConfigFile mConfigFile;
  private DbConnector mDbConnector;


  public Plugin() {
    super();

    mConfigFile = new ConfigFile(this);
    mPlayersList = new ConnectedPlayersList();
  }

  public void onEnable() {
    // Events
    this.getServer().getPluginManager().registerEvents(new ConnectionEventListener(mPlayersList), this);
    this.getServer().getPluginManager().registerEvents(new GameEventListener(mPlayersList), this);

    // Commands
    this.getCommand("getPlayers").setExecutor(new PlayersListCommand(mPlayersList));

    // Load config file
    mConfigFile.load();

    // Connect to Db
    try {
      mDbConnector.Connect(
        mConfigFile.get().getString("database.ip"),
        mConfigFile.get().getInt("database.port"),
        mConfigFile.get().getString("database.user"),
        mConfigFile.get().getString("database.password"));
    } catch (Exception e) {
      LOGGER.warning("Cannot connect to database");
      this.getPluginLoader().disablePlugin(this);
    }
    
    // Log "enable"
    LOGGER.info("dunkin_connect enabled");
  }

  public void onDisable() {
    LOGGER.info("dunkin_connect disabled");
  }
}
