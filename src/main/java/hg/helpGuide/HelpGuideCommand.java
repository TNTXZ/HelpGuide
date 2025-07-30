package hg.helpGuide;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import hg.helpGuide.MessageUtils;

public class HelpGuideCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("只有玩家可以使用此命令!");
            return true;
        }
        if (!sender.hasPermission("helpGuide.use")) {
            sender.sendMessage("你没有权限使用此命令!");
            return true;
        }
        String guideContent = HelpGuide.getPlugin().getConfig().getString("guide.content");
        if (guideContent != null) {
            String processedContent = MessageUtils.processMessage(guideContent, (Player)sender);
            sender.sendMessage(processedContent.split("\\n"));
        } else {
            sender.sendMessage(ChatColor.RED + "服务器指南配置不存在，请联系管理员!");
        }
        return true;
    }
}