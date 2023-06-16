package com.zlc.seqfunction.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.zlc.seqfunction.util.TimerUtil;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class TimerCommand {

    private static final SimpleCommandExceptionType FAILED_EXCEPTION = new SimpleCommandExceptionType(Text.translatable("commands.loophello.failed"));

    // then(something().then());
    // then(something()).then();
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher){
        dispatcher.register(CommandManager.literal("timer").requires((source) -> {return source.hasPermissionLevel(2);})
                .then(CommandManager.literal("start").executes(context -> {return TimerCommand.start(context.getSource());}))
                .then(CommandManager.literal("pause").executes(context -> {return TimerCommand.pause(context.getSource());}))
                .then(CommandManager.literal("end")  .executes(context -> {return TimerCommand.end  (context.getSource());})));
    }

    public static int start(ServerCommandSource source){
        TimerUtil.startLoopHello();
        return 1;
    }

    public static int pause(ServerCommandSource source){
        TimerUtil.pauseLoopHello();
        return 1;
    }

    public static int end(ServerCommandSource source){
        TimerUtil.endLoopHello();
        return 1;
    }
}
