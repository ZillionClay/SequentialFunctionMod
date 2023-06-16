package com.zlc.seqfunction.util;

import com.google.common.collect.Queues;
import net.minecraft.client.MinecraftClient;

import java.util.Queue;
import java.util.TimerTask;

public class TickTaskUtil {

    private static final Queue<TimerTask> TICKSTARTTASKS = Queues.newArrayDeque();
    private static final Queue<TimerTask> TICKENDTASKS = Queues.newArrayDeque();

    public static void addTickStartTask(TimerTask task){
        synchronized(TICKSTARTTASKS) {
            TICKSTARTTASKS.add(task);
        }
    }

    public static void addTickEndTask(TimerTask task){
        synchronized(TICKENDTASKS) {
            TICKENDTASKS.add(task);
        }
    }

    public static void onStartClientTick(MinecraftClient client) {
        synchronized(TICKSTARTTASKS) {
            while(!TICKSTARTTASKS.isEmpty()) {
                ((TimerTask)TICKSTARTTASKS.poll()).run();
            }
        }
    }

    public static void onEndClientTick(MinecraftClient client) {
        synchronized(TICKENDTASKS) {
            while(!TICKENDTASKS.isEmpty()) {
                ((TimerTask)TICKENDTASKS.poll()).run();
            }
        }
    }

}
