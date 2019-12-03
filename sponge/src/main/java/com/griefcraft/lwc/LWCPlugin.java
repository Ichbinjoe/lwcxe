package com.griefcraft.lwc;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.game.state.GameStoppedServerEvent;
import org.spongepowered.api.plugin.Plugin;

@Plugin(id = "lwc", name = "LWC", version = "1.0")
public class LWCPlugin {

    @Inject
    private Logger logger;

    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        
    }

    @Listener
    public void onServerStart(GameStoppedServerEvent event) {
        
    }
    
}
