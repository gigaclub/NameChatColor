package net.gigaclub.playernamecolor;

import lombok.Getter;
import net.gigaclub.playernamecolor.listener.JoinListener;
import net.gigaclub.playernamecolor.utils.ScoreboardUtil;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class Main extends JavaPlugin {

    @Getter
    private ScoreboardUtil scoreboardUtil;

    public static Main getPlugin() {
        return Main.getPlugin(Main.class);
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        getPlugin().getLogger().log(Level.INFO, "Enabling Plugin...");
        this.registerUtils();
        this.registerListener();
        getPlugin().getLogger().log(Level.INFO, "Plugin enabled");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getPlugin().getLogger().log(Level.INFO, "Bye bye");
    }

    private void registerUtils() {
        getPlugin().getLogger().log(Level.INFO, "Registering utils...");
        this.scoreboardUtil = new ScoreboardUtil();
        this.scoreboardUtil.registerScoreboard();
        getPlugin().getLogger().log(Level.INFO, "Utils registered");
    }

    private void registerListener() {
        getPlugin().getLogger().log(Level.INFO, "Registering listener...");
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new JoinListener(), this);
        getPlugin().getLogger().log(Level.INFO, "Listener registered");
    }

}
