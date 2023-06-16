package com.zlc.seqfunction.util;

import com.google.common.collect.Queues;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

import java.util.Queue;
import java.util.TimerTask;

public class TimerUtil {
    private static final MinecraftClient CLIENT = MinecraftClient.getInstance();

    private static final TickHelloTask task = new TickHelloTask();

    public static void startLoopHello(){
        task.running = true;
        task.run();
    }

    public static void pauseLoopHello(){
        task.running = false;
    }

    public static void endLoopHello(){
        task.running = false;
        task.reset();
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

    private static class TickHelloTask extends TimerTask{

        private long tick;
        public boolean running = false;

        public TickHelloTask(){
            this.tick = 0;
        }

        public void reset(){
            this.tick = 0;
        }

        public void run(){
            if(CLIENT.player != null) {
                CLIENT.player.sendMessage(Text.literal("tick: " + Long.toString(tick)));
                tick += 1;
            }
            if(this.running) {
                TickTaskUtil.addTickEndTask(new TickEndTask(this));
            }
        }
    }

}
