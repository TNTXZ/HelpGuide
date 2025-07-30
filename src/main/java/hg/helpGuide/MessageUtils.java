package hg.helpGuide;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.Server;

public class MessageUtils {
    
    /**
     * 替换消息中的占位符变量
     * @param rawMessage 原始消息
     * @param player 玩家对象
     * @return 替换后的消息
     */
    public static String replacePlaceholders(String rawMessage, Player player) {
        if (rawMessage == null) return null;
        
        Server server = HelpGuide.getPlugin().getServer();
        String serverName = HelpGuide.getPlugin().getConfig().getString("guide.server-name", "服务器");
        
        return rawMessage
            .replace("{player}", player.getName())
            .replace("{server}", serverName)
            .replace("{online}", String.valueOf(server.getOnlinePlayers().size()))
            .replace("{maxplayers}", String.valueOf(server.getMaxPlayers()));
    }
    
    /**
     * 处理消息：替换占位符并转换颜色代码
     * @param rawMessage 原始消息
     * @param player 玩家对象
     * @return 处理后的消息
     */
    public static String processMessage(String rawMessage, Player player) {
        String processed = replacePlaceholders(rawMessage, player);
        return ChatColor.translateAlternateColorCodes('&', processed);
    }
}