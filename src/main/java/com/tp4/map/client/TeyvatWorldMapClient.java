package com.tp4.map.client;

import com.example.haw.TeleportPoint;
import com.example.haw.client.HomeAndWarpClient;
import com.tp4.map.CustomWaypoint;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientWorldEvents;
import xaero.common.minimap.waypoints.Waypoint;
import xaero.hud.minimap.waypoint.set.WaypointSet;

import java.util.HashMap;
import java.util.Map;

public class TeyvatWorldMapClient implements ClientModInitializer {
    public static Map<String, WaypointSet> waypointSets = new HashMap<>();

    @Override
    public void onInitializeClient() {
        ClientWorldEvents.AFTER_CLIENT_WORLD_CHANGE.register((client, world) -> reload());
    }

    public static Waypoint getWaypoint(TeleportPoint teleportPoint, String type) {
        return new CustomWaypoint((int) teleportPoint.x, (int) teleportPoint.y, (int) teleportPoint.z, teleportPoint.note, teleportPoint.name, type);
    }

    public static void reload() {
        if (waypointSets.isEmpty()) return;

        if (HomeAndWarpClient.warp != null) {
            HomeAndWarpClient.warp.forEach((s, teleportPoint) -> {
                if (waypointSets.get(teleportPoint.world) != null) {
                    tryAddWaypoint(waypointSets.get(teleportPoint.world), teleportPoint, "warp");
                }
            });
        }
        if (HomeAndWarpClient.home != null) {
            HomeAndWarpClient.home.forEach((s, teleportPoint) -> {
                if (waypointSets.get(teleportPoint.world) != null) {
                    tryAddWaypoint(waypointSets.get(teleportPoint.world), teleportPoint, "home");
                }
            });
        }
        waypointSets.values().forEach(waypointSet -> {
            for (int i = 0; i < waypointSet.size(); i++) {
                if (!(waypointSet.get(i) instanceof CustomWaypoint waypoint)) {
                    continue;
                }
                if (waypoint.type.equals("warp") && !HomeAndWarpClient.warp.containsKey(waypoint.id) || (waypoint.type.equals("home") && !HomeAndWarpClient.home.containsKey(waypoint.id))) {
                    waypointSet.remove(i);
                }
            }
        });
    }

    public static void tryAddWaypoint(WaypointSet waypointSet, TeleportPoint teleportPoint, String type) {
        for (int i=0; i<waypointSet.size(); i++) {
            if (waypointSet.get(i) instanceof CustomWaypoint customWaypoint) {
                if (customWaypoint.id.equals(teleportPoint.name) && customWaypoint.type.equals(type)) {
                    return;
                }
            }
        }
        waypointSet.add(getWaypoint(teleportPoint, type));
    }
}
