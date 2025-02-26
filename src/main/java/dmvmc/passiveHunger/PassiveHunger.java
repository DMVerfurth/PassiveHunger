package dmvmc.passiveHunger;


import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class PassiveHunger extends JavaPlugin {

    private int passiveHungerInterval;

    @Override
    public void onEnable() {

        saveDefaultConfig();
        FileConfiguration config = getConfig();
        passiveHungerInterval = config.getInt("passive-hunger-interval");

        startHungerTask();
        getLogger().info("PassiveHunger has been enabled with an interval of " + passiveHungerInterval + " seconds.!");

    }

    @Override
    public void onDisable() {
        getLogger().info("PassiveHunger has been disabled!");
    }

    private void startHungerTask() {

        BukkitRunnable passiveHungerRunnable = new BukkitRunnable() {

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

        passiveHungerRunnable.runTaskTimer(this, passiveHungerInterval * 20L, passiveHungerInterval * 20L);

    }

}
