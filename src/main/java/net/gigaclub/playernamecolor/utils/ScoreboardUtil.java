package net.gigaclub.playernamecolor.utils;

import lombok.Getter;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import java.util.Arrays;
import java.util.Objects;

public class ScoreboardUtil {
    @Getter
    private Scoreboard colorBoard;

    public void registerScoreboard() {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        this.colorBoard = manager.getMainScoreboard();
    }

    public void registerNameTag(Player player) {
        Team playerTeam = getColorBoard().getTeam(player.getName());
        Component prefix = Component.text(PlaceholderAPI.setPlaceholders(player, "%vault_prefix% ")).asComponent();
        NamedTextColor color = NamedTextColor.nearestTo(Objects.requireNonNull(TextColor.fromCSSHexString(this.convertMinecraftColorCodeToHexCode(PlaceholderAPI.setPlaceholders(player, "%vault_prefix_color%")))));
        if (playerTeam != null) playerTeam.unregister();
        Team newTeam = getColorBoard().registerNewTeam(player.getName());
        newTeam.prefix(prefix);
        newTeam.color(color);
        newTeam.addEntry(player.getName());
    }

    private String convertMinecraftColorCodeToHexCode(String colorCode) {
        String reworkedColorCode = Arrays.toString(colorCode.getBytes());
        return switch (reworkedColorCode) {
            case "[-62, -89, 48]" -> "#000000";
            case "[-62, -89, 49]" -> "#0000AA";
            case "[-62, -89, 50]" -> "#00AA00";
            case "[-62, -89, 51]" -> "#00AAAA";
            case "[-62, -89, 52]" -> "#AA0000";
            case "[-62, -89, 53]" -> "#AA00AA";
            case "[-62, -89, 54]" -> "#FFAA00";
            case "[-62, -89, 55]" -> "#AAAAAA";
            case "[-62, -89, 56]" -> "#555555";
            case "[-62, -89, 57]" -> "#5555FF";
            case "[-62, -89, 65]" -> "#55FF55";
            case "[-62, -89, 66]" -> "#55FFFF";
            case "[-62, -89, 67]" -> "#FF5555";
            case "[-62, -89, 68]" -> "#FF55FF";
            case "[-62, -89, 69]" -> "#FFFF55";
            default -> "#FFFFFF";
        };
    }

}
