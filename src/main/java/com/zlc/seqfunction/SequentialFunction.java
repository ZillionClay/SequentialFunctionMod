package com.zlc.seqfunction;

import com.zlc.seqfunction.commands.DelayCommand;
import com.zlc.seqfunction.commands.SeqFunctionCommand;
import com.zlc.seqfunction.commands.TimerCommand;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SequentialFunction implements ModInitializer {
    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.

    public static final String MOD_ID = "particlecmd";
    public static final Logger LOGGER = LoggerFactory.getLogger("particlecmd");

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> TimerCommand.register(dispatcher));
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> SeqFunctionCommand.register(dispatcher));
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> DelayCommand.register(dispatcher));
    }
}