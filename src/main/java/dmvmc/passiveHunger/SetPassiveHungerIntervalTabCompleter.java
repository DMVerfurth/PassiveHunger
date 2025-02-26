package dmvmc.passiveHunger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SetPassiveHungerIntervalTabCompleter implements TabCompleter {

    private static final List<String> COMMON_INTERVALS = Arrays.asList(
            "60", "120", "180", "240", "300", "360", "420", "480", "540", "600", "660", "720", "780", "840", "900"
    );

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String @NotNull [] args) {

        // Return empty list if command invalid
        if (args.length != 1 || !sender.hasPermission("passiveHunger.set"))
            return Collections.emptyList();

        // Return search from common intervals
        return COMMON_INTERVALS.stream()
                .filter(number -> number.startsWith(args[0]))
                .collect(Collectors.toList());

    }

}
