package net.kunmc.lab.attendancemanagementsystem;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.kunmc.lab.commandlib.argument.exception.IncorrectArgumentInputException;
import net.minecraft.server.v1_16_R3.CommandListenerWrapper;
import net.kunmc.lab.commandlib.Argument;

import java.util.Arrays;
import java.util.function.Consumer;


public class CardinalDirectionsArgument extends Argument<CardinalDirections> {
    public CardinalDirectionsArgument(String name) {
        this(name, option -> {
        });
    }

    public CardinalDirectionsArgument(String name, Consumer<Option<CardinalDirections>> options) {
        super(name, StringArgumentType.string());

        setSuggestionAction(sb -> {
            Arrays.stream(CardinalDirections.values())
                    .map(e -> e.name().toLowerCase())
                    .filter(x -> sb.getLatestInput()
                            .isEmpty() || x.contains(sb.getLatestInput()))
                    .forEach(sb::suggest);
        });
        setOptions(options);
    }

    @Override
    public CardinalDirections cast(Object parsedArgument) {
        return ((CardinalDirections) parsedArgument);
    }

    @Override
    public CardinalDirections parse(CommandContext<CommandListenerWrapper> ctx) throws IncorrectArgumentInputException {
        String s = StringArgumentType.getString(ctx, name);
        String direction = Arrays.stream(CardinalDirections.values())
                .map(e -> e.name())
                .filter(e -> e.equalsIgnoreCase(s))
                .findFirst()
                .orElseThrow(() -> new IncorrectArgumentInputException(this, ctx, s));
        return CardinalDirections.valueOf(direction);
    }
}
