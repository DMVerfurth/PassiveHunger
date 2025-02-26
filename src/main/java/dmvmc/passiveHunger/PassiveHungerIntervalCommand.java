package dmvmc.passiveHunger;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class PassiveHungerIntervalCommand implements CommandExecutor {

    private final PassiveHunger plugin;
    public PassiveHungerIntervalCommand(PassiveHunger plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand (@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String @NotNull [] args) {

        // Check permission to set passive hunger interval
        if (!sender.hasPermission("passiveHunger.set")) {
            sender.sendMessage(Component.text()
                    .content("You do not have permission to use this command!")
                    .color(NamedTextColor.RED));
            return true;
        }

        // Ensure a value has been passed
        if (args.length != 1) {
            sender.sendMessage("Usage: /setPassiveHungerInterval <seconds>");
            return true;
        }

        try {
            // Convert input into integer
            int interval = Integer.parseInt(args[0]);
            if (interval <= 0) {
                sender.sendMessage(Component.text()
                        .content("The interval must be a positive integer!")
                        .color(NamedTextColor.RED));
                return true;
            }

            // Update config and send success message
            plugin.getConfig().set("passive-hunger-interval", interval);
            plugin.saveConfig();
            sender.sendMessage(Component.text()
                    .content("Passive hunger interval set to " + plugin.getConfig().getInt("passive-hunger-interval"))
                    .color(NamedTextColor.GREEN));

        } catch (NumberFormatException e) {
            // Send invalid number error message
            sender.sendMessage(Component.text()
                    .content("Invalid number format! Please enter a valid integer.")
                    .color(NamedTextColor.RED));
            return true;
        }

        // Restart hunger task with new config
        plugin.restartHungerTask();
        return true;

    }

}
