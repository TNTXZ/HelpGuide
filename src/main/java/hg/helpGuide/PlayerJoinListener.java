package hg.helpGuide;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        boolean enableJoinMessage = HelpGuide.getPlugin().getConfig().getBoolean("guide.enable-join-message", true);
        if (enableJoinMessage) {
            String joinMessage = HelpGuide.getPlugin().getConfig().getString("guide.join-message");
            if (joinMessage != null) {
                String processedMessage = MessageUtils.processMessage(joinMessage, event.getPlayer());
                event.getPlayer().sendMessage(processedMessage);
            }
        }

        boolean enableNewPlayerGuide = HelpGuide.getPlugin().getConfig().getBoolean("guide.enable-new-player-guide", true);
        boolean enableOldPlayerGuide = HelpGuide.getPlugin().getConfig().getBoolean("guide.enable-old-player-guide", false);
        
        // 新玩家指南
        if (!event.getPlayer().hasPlayedBefore() && enableNewPlayerGuide) {
            sendGuideContent(event.getPlayer());
        }
        // 老玩家指南
        else if (event.getPlayer().hasPlayedBefore() && enableOldPlayerGuide) {
            sendGuideContent(event.getPlayer());
        }
    }

    /**
     * 发送指南内容给玩家
     */
    private void sendGuideContent(Player player) {
        String guideContent = HelpGuide.getPlugin().getConfig().getString("guide.content");
        if (guideContent != null) {
            String processedContent = MessageUtils.processMessage(guideContent, player);
            player.sendMessage(processedContent.split("\\n"));
        }
    }
}