package com.zlc.seqfunction.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.function.CommandFunction;

import java.util.ArrayList;
import java.util.TimerTask;

public class SeqFunctionUtil {
    private static final MinecraftClient CLIENT = MinecraftClient.getInstance();

    public static void startSequence(ServerCommandSource source,ArrayList<CommandFunction> cmdfs){
        new TickSequenceTask(source, cmdfs).run();
    }

    private static class TickEndTask extends TimerTask {
        private TimerTask nextTask;
        public TickEndTask(TimerTask nextTask) {
            this.nextTask = nextTask;
        }

        public void run() {
            TickTaskUtil.addTickStartTask(this.nextTask);
        }
    }

    private static class TickSequenceTask extends TimerTask {

        ArrayList<CommandFunction> commandSequence;
        int frameCount;
        int currentFrame;

        ServerCommandSource source;

        public TickSequenceTask(ServerCommandSource source ,ArrayList<CommandFunction> cmdfs){
            this.commandSequence = cmdfs;
            this.frameCount = cmdfs.size();
            this.currentFrame = 0;
            this.source = source;
        }


        @Override
        public void run() {
            CommandFunction function = this.commandSequence.get(currentFrame);
            for(CommandFunction.Element e: function.getElements()){
            }
            this.source.getServer().getCommandFunctionManager().execute(function, source.withSilent().withMaxLevel(2));
            if(currentFrame < frameCount-1){
                currentFrame += 1;
                TickTaskUtil.addTickEndTask(new TickEndTask(this));
            }
        }
    }
}
