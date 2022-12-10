package net.kunmc.lab.attendancemanagementsystem;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.kunmc.lab.commandlib.argument.exception.IncorrectArgumentInputException;
import net.minecraft.server.v1_16_R3.CommandListenerWrapper;
import net.kunmc.lab.commandlib.Argument;

import java.util.function.Consumer;
import java.util.stream.Stream;

public class AreaRegisteredArgument extends Argument<String> {

    public AreaRegisteredArgument(String name) {
        this(name, option -> {
        });
    }

    public AreaRegisteredArgument(String name, Consumer<Option<String>> options) {
        super(name, StringArgumentType.string());

        setSuggestionAction(sb -> {
            Stream<String> keySetStream = AttendanceManagementSystem.config.face.keySet().stream();
            keySetStream.filter(x -> sb.getLatestInput().isEmpty() || x.contains(sb.getLatestInput()))
                    .forEach(sb::suggest);
        });
        setOptions(options);
    }

    @Override
    public String cast(Object parsedArgument) {
        return ((String) parsedArgument);
    }

    @Override
    public String parse(CommandContext<CommandListenerWrapper> ctx) throws IncorrectArgumentInputException {
        String s = StringArgumentType.getString(ctx, name);
        Stream<String> keySetStream = AttendanceManagementSystem.config.face.keySet().stream();
        return keySetStream.filter(e -> e.equals(s))
                .findFirst()
                .orElseThrow(() -> new IncorrectArgumentInputException(this, ctx, s));
    }
}
