package net.kunmc.lab.attendancemanagementsystem;

import net.kunmc.lab.commandlib.Command;
import net.kunmc.lab.commandlib.argument.PlayersArgument;

public class AttendCommand extends Command {
    AttendanceManagementSystem plugin;

    public AttendCommand() {
        super("attend");

        plugin = AttendanceManagementSystem.getPlugin(AttendanceManagementSystem.class);

        argument(new PlayersArgument("players"), (players, ctx) -> {
            players.forEach(player -> plugin.attend(player));
        });
    }
}
