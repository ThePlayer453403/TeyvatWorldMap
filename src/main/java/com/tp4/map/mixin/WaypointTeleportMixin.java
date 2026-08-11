package com.tp4.map.mixin;

import com.tp4.map.CustomWaypoint;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xaero.common.minimap.waypoints.Waypoint;
import xaero.hud.minimap.waypoint.WaypointTeleport;
import xaero.hud.minimap.world.MinimapWorld;

@Mixin(WaypointTeleport.class)
public class WaypointTeleportMixin {
    @Inject(method = "teleportToWaypoint*", at = @At("HEAD"), cancellable = true)
    private void TeyvatWorldMap$teleport(Waypoint waypoint, MinimapWorld world, Screen screen, CallbackInfo ci) {
        if (waypoint instanceof CustomWaypoint customWaypoint && MinecraftClient.getInstance().getNetworkHandler() != null) {
            MinecraftClient.getInstance().getNetworkHandler().sendChatCommand(String.format("%s tp %s", customWaypoint.type, customWaypoint.id));
            MinecraftClient.getInstance().setScreen(null);
            ci.cancel();
        }
    }
}
