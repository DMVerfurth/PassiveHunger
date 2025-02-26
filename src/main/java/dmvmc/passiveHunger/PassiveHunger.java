package dmvmc.passiveHunger;


import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class PassiveHunger extends JavaPlugin {

    private int passiveHungerInterval;
    private BukkitRunnable passiveHungerRunnable;

    @Override
    public void onEnable() {

        // Start hunger runnable
        saveDefaultConfig();
        loadNewConfig();
        startHungerTask();

        // Register command and tab completer
        getCommand("setPassiveHungerInterval").setExecutor(new SetPassiveHungerIntervalCommand(this));
        getCommand("setPassiveHungerInterval").setTabCompleter(new SetPassiveHungerIntervalTabCompleter());
        getLogger().info("PassiveHunger has been enabled with an interval of " + passiveHungerInterval + " seconds!");

    }

    @Override
    public void onDisable() {
        stopHungerTask();
        getLogger().info("PassiveHunger has been disabled!");
    }

    private void loadNewConfig() {
        // Update passive hunger interval from config file
        reloadConfig();
        passiveHungerInterval = getConfig().getInt("passive-hunger-interval");
    }

    private void stopHungerTask() {
        // Cancel hunger runnable if running
        if (passiveHungerRunnable != null) {
            passiveHungerRunnable.cancel();
        }
    }

    private void startHungerTask() {

        // Create new runnable to periodically decrement player food
        passiveHungerRunnable = new BukkitRunnable() {

            @Override
            public void run() {

                Bukkit.getOnlinePlayers().forEach(player -> {

                    // Decrement saturation or food
                    if (player.getSaturation() > 0) {
                        player.setSaturation(Math.max(0, player.getSaturation() - 1f));
                    } else {
                        player.setFoodLevel(Math.max(0, player.getFoodLevel() - 1));
                    }

                });

            }

        };

        // Set runnable timer to config interval
        passiveHungerRunnable.runTaskTimer(this, passiveHungerInterval * 20L, passiveHungerInterval * 20L);

    }

    public void restartHungerTask() {
        loadNewConfig();
        stopHungerTask();
        startHungerTask();
    }

}
