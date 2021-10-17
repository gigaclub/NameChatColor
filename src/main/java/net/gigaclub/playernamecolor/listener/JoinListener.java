package net.gigaclub.playernamecolor.listener;

import me.clip.placeholderapi.PlaceholderAPI;
import net.gigaclub.playernamecolor.Main;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerJoin(PlayerJoinEvent event) {
        Main plugin = Main.getPlugin();
        Player player = event.getPlayer();
        plugin.getScoreboardUtil().registerNameTag(player);
        player.playerListName(Component.text(PlaceholderAPI.setPlaceholders(player, "%vault_prefix% " + player.getName())).asComponent());
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String playerName = player.getName();
        event.setFormat(PlaceholderAPI.setPlaceholders(player, "%vault_prefix% " + playerName + " %vault_suffix% " + event.getMessage()));
    }
}
