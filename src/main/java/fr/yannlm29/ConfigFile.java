package fr.yannlm29;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class ConfigFile {
    private JavaPlugin mPlugin;
    private FileConfiguration mConfig;
    private File mConfigFile;

    public ConfigFile(JavaPlugin inPluginRef) {
        this.mPlugin = inPluginRef;
    }

    public FileConfiguration get() {
        return mConfig;
    }

    public void load(String inFileName) {
        if (mConfigFile == null) {
          mConfigFile = new File(mPlugin.getDataFolder(), inFileName);
        }

        // create config file if doesn't exist
        if (!mConfigFile.exists()) {
            mPlugin.saveResource(inFileName, false);
        }

        mConfig = YamlConfiguration.loadConfiguration(mConfigFile);
    }

    public void load() {
        load("config.yml");
    }

}
