package by.epamtc.servlet.command;

import by.epamtc.servlet.command.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {

    private static final CommandProvider instance = new CommandProvider();

    private Map<CommandName, Command> commands = new HashMap<>();

    private CommandProvider() {
        commands.put(CommandName.MAIN, new ToMainCommand());
        commands.put(CommandName.ERROR, new ToErrorCommand());
        commands.put(CommandName.PARSE, new ParseCommand());
        commands.put(CommandName.VALIDATE, new ValidateCommand());
        commands.put(CommandName.PARSER, new ToParserCommand());
        commands.put(CommandName.VALIDATOR, new ToValidatorCommand());
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
