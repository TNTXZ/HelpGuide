package hg.helpGuide;

import org.bukkit.plugin.java.JavaPlugin;

public final class HelpGuide extends JavaPlugin {

    private static HelpGuide plugin;
    private static final int CONFIG_VERSION = 2;

    @Override
    public void onEnable() {
        plugin = this;
        saveDefaultConfig();
        checkConfigVersion();
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getCommand("hg").setExecutor(new HelpGuideCommand());
        getLogger().info("HelpGuide插件已启用!");
    }

    /**
     * 检查并更新配置文件版本
     */
    private void checkConfigVersion() {
        int currentVersion = getConfig().getInt("version", 1);
        if (currentVersion < CONFIG_VERSION) {
            getLogger().warning("配置文件版本过时 (v" + currentVersion + "), 正在更新到v" + CONFIG_VERSION + "...");

            // 备份旧配置
            getConfig().options().copyDefaults(true);
            getConfig().set("version", CONFIG_VERSION);
            saveConfig();
            reloadConfig();
            getLogger().info("配置文件已更新!");
        }
    } // 修复：添加了缺失的方法结束大括号

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("HelpGuide插件已禁用!");
    }

    public static HelpGuide getPlugin() {
        return plugin;
    }
}