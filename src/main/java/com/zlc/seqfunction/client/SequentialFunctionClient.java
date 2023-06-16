package com.zlc.seqfunction.client;

import com.zlc.seqfunction.util.TickTaskUtil;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class SequentialFunctionClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // This entrypoint is suitable for setting up client-specific logic, such as rendering.
        ClientTickEvents.START_CLIENT_TICK.register(TickTaskUtil::onStartClientTick);
        ClientTickEvents.END_CLIENT_TICK.register(TickTaskUtil::onEndClientTick);

    }
}