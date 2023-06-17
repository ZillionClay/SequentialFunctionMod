package com.zlc.seqfunction.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.zlc.seqfunction.util.SeqFunctionUtil;
import net.minecraft.command.CommandSource;
import net.minecraft.command.argument.CommandFunctionArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.function.CommandFunction;
import net.minecraft.server.function.CommandFunctionManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;



public class SeqFunctionCommand {

    public static final SuggestionProvider<ServerCommandSource> SUGGESTION_PROVIDER = (context, builder) -> {
        CommandFunctionManager commandFunctionManager = ((ServerCommandSource)context.getSource()).getServer().getCommandFunctionManager();
        CommandSource.suggestIdentifiers(commandFunctionManager.getFunctionTags(), builder, "#");
        return CommandSource.suggestIdentifiers(commandFunctionManager.getAllFunctions(), builder);
    };

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("seqfunction").requires((source) -> {
            return source.hasPermissionLevel(2);
        }).then(CommandManager.argument("name", CommandFunctionArgumentType.commandFunction()).suggests(SUGGESTION_PROVIDER).executes((context) -> {
            return execute(context.getSource(), CommandFunctionArgumentType.getFunctions(context, "name"));
        })));
    }

    private static int execute(ServerCommandSource source, Collection<CommandFunction> functions) {
        int counter = 0;

        CommandFunction commandFunction;
        for(Iterator var3 = functions.iterator(); var3.hasNext(); ) {
            commandFunction = (CommandFunction)var3.next();

            ArrayList<CommandFunction> commandFunctions = new ArrayList<>();
            ArrayList<CommandFunction.Element> elementArray = new ArrayList<>();
            for(CommandFunction.Element element : commandFunction.getElements()){
                if(element.toString().equals("delay")){
                    commandFunctions.add(new CommandFunction(commandFunction.getId(), elementArray.toArray(new CommandFunction.Element[0])));
                    elementArray.clear();
                }else {
                    elementArray.add(element);
                }
                counter += 1;
            }
            commandFunctions.add(new CommandFunction(commandFunction.getId(), elementArray.toArray(new CommandFunction.Element[0])));
            elementArray.clear();
            SeqFunctionUtil.startSequence(source, commandFunctions);
        }

        return counter;
    }
}
