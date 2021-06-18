package by.epamtc.servlet.command;

import by.epamtc.servlet.Command;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CommandProvider {

    private static final CommandProvider instance = new CommandProvider();

    private Map<CommandName, Command> commands = new HashMap<>();

    private CommandProvider() {
        commands.put(CommandName.MAIN, new MainCommand());
        commands.put(CommandName.ERROR, new ErrorCommand());
        commands.put(CommandName.PARSER, new ParserCommand());
        commands.put(CommandName.VALIDATOR, new ValidatorCommand());
    }

    public static CommandProvider getInstance() {
        return instance;
    }

    public Command getCommand(String commandName) {
        CommandName name = CommandName.valueOf(commandName.toUpperCase());
        Command command;
        if (name != null) {
            command = commands.get(name);
        } else {
            command = commands.get(CommandName.ERROR);
        }
        return command;
    }

}
