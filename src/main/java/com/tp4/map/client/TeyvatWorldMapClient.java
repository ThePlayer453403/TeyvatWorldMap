package com.tp4.map.client;

import com.tp4.map.TeyvatWorldMap;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientWorldEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;

import java.util.HashMap;

public class TeyvatWorldMapClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientWorldEvents.AFTER_CLIENT_WORLD_CHANGE.register((client, world) -> TeyvatWorldMap.reload());
        ClientPlayConnectionEvents.DISCONNECT.register(((handler, client) -> TeyvatWorldMap.waypointSets = new HashMap<>()));
    }
}
