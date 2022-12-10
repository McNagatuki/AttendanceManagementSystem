package net.kunmc.lab.attendancemanagementsystem;

import net.kunmc.lab.commandlib.Command;
import net.kunmc.lab.commandlib.argument.LocationArgument;
import net.kunmc.lab.commandlib.argument.StringArgument;
import org.bukkit.Location;

public class AreaCommand extends Command {

    public static class SetCommand extends Command {
        public SetCommand() {
            super("set");

            argument(
                    new StringArgument("area"),
                    new LocationArgument("pos1"),
                    new LocationArgument("pos2"),
                    new CardinalDirectionsArgument("face"),
                    (area, pos1, pos2, face, ctx) -> {
                        AttendanceManagementSystem.config.areas.put(area + "pos1", pos1);
                        AttendanceManagementSystem.config.areas.put(area + "pos2", pos2);
                        AttendanceManagementSystem.config.face.put(area, face);
                        AttendanceManagementSystem.config.save();
                        ctx.sendSuccess(area + "を追加しました。");
                    });
        }
    }

    public static class GetCommand extends Command {

        public GetCommand() {
            super("get");

            argument(
                    new AreaRegisteredArgument("area"),
                    (area, ctx) -> {
                        if (AttendanceManagementSystem.config.face.containsKey(area)) {
                            Location pos1 = AttendanceManagementSystem.config.areas.get(area + "pos1");
                            Location pos2 = AttendanceManagementSystem.config.areas.get(area + "pos2");
                            CardinalDirections cardinalDirections = AttendanceManagementSystem.config.face.get(area);

                            ctx.sendSuccess("pos1: " + pos1.toString());
                            ctx.sendSuccess("pos2: " + pos2.toString());
                            ctx.sendSuccess("face: " + cardinalDirections.name().toLowerCase());
                        } else {
                            ctx.sendFailure(area + "は存在しません。");
                        }
                    });
        }
    }

    public static class RemoveCommand extends Command {
        public RemoveCommand() {
            super("remove");

            argument(new AreaRegisteredArgument("area"), (area, ctx) -> {
                // Configからareaを削除
                if (AttendanceManagementSystem.config.face.containsKey(area)) {
                    AttendanceManagementSystem.config.face.remove(area);
                    AttendanceManagementSystem.config.areas.remove(area + "pos1");
                    AttendanceManagementSystem.config.areas.remove(area + "pos2");
                    AttendanceManagementSystem.config.save();
                    ctx.sendSuccess(area + "を削除しました。");
                } else {
                    ctx.sendFailure(area + "は存在しません。");
                }
            });
        }
    }

    public static class ListCommand extends Command {
        public ListCommand() {
            super("list");
            execute(ctx ->
                    ctx.sendSuccess(AttendanceManagementSystem.config.face.keySet().toString())
            );
        }
    }

    public AreaCommand() {
        super("area");
        addChildren(new SetCommand(), new GetCommand(), new RemoveCommand(), new ListCommand());
    }
}

