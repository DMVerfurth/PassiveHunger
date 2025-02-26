# PassiveHunger Plugin

## Overview

PassiveHunger is a Minecraft plugin that deducts saturation/food points of players over time regardless of activity level

## Features

- Players lose food points over time
- Saturation is subtracted before hunger
- Rate of hunger can be set by those who have permission to do so

## Installation

1. Download `/build/libs/PassiveHunger-v1.0.jar`.
2. Place the `.jar` file into the `plugins` folder of your Minecraft server.
3. Restart the server to enable the plugin.

## Commands

| Command                     | Usage       | Description                                       |
|-----------------------------|-------------|---------------------------------------------------|
| `/setpassivehungerinterval` | `<seconds>` | Sets the rate of passive food decay on the server |

### Example Usage

- `/setnamecolor 420` → Sets the rate of passive food decay to 1 food point every 7 minutes (420 seconds)

## Permissions

| Permission          | Description                                        | Default |
|---------------------|----------------------------------------------------|---------|
| `passiveHunger.set` | Allows the player to set other players name color. | `op`    |

## Configuration

The plugin saves player colors in `playerColors.yml` located in the plugin's data folder. This file is automatically managed by the plugin.

## Dependencies

- Requires Minecraft `1.21+` (API version `1.21`).
- Uses [Adventure API](https://docs.adventure.kyori.net) for text formatting.

## Development

### Main Classes

- `PassiveHunger.java` - Manages the plugin lifecycle and passive hunger runnable
- `SetPassiveHungerIntervalCommand.java` - Handles the `/setpassivehungerinterval` command.
- `SetPassiveHungerIntervalTabCompleter.java` - Handles tab completion for the `/setpassivehungerinterval` command.

### Building from Source

1. Clone the repository.
2. Build using `./gradlew build`
3. Place the compiled `.jar` into your server's `plugins` folder.

## License

This plugin is provided as open-source. Feel free to modify and use it according to your needs.

---

Enjoy starving! 🍔