package com.tp4.map.client;

import com.tp4.map.TeyvatWorldMap;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientWorldEvents;

public class TeyvatWorldMapClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientWorldEvents.AFTER_CLIENT_WORLD_CHANGE.register((client, world) -> TeyvatWorldMap.reload());
    }
}
