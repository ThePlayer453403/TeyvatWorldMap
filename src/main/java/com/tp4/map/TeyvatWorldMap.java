package com.tp4.map;

import com.example.haw.TeleportPoint;
import com.example.haw.client.HomeAndWarpClient;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Style;
import net.minecraft.text.StyleSpriteSource;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import xaero.common.minimap.waypoints.Waypoint;
import xaero.hud.minimap.waypoint.set.WaypointSet;

import java.util.HashMap;
import java.util.Map;

public class TeyvatWorldMap implements ModInitializer {
    public static Map<String, WaypointSet> waypointSets = new HashMap<>();
    public static final Identifier FONT = Identifier.of("teyvatworldmap", "teleport_point");

    @Override
    public void onInitialize() {
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

    public static Text getIcon(CustomWaypoint customWaypoint) {
        return Text.literal(customWaypoint.type.equals("warp") ? "\u0001" : "\u0002").setStyle(Style.EMPTY.withFont(new StyleSpriteSource.Font(FONT)));
    }

    public static void drawNormalText(MatrixStack matrices, Text name, float x, float y, int color, boolean shadow, VertexConsumerProvider renderTypeBuffer) {
        MinecraftClient.getInstance().textRenderer.draw(name, x, y, color, shadow, matrices.peek().getPositionMatrix(), renderTypeBuffer, TextRenderer.TextLayerType.NORMAL, 0, 15728880);
    }
}
