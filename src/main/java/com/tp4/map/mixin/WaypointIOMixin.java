package com.tp4.map.mixin;

import com.tp4.map.ModCustomWaypoint;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import xaero.common.minimap.waypoints.Waypoint;
import xaero.hud.minimap.waypoint.io.WaypointIO;
import xaero.hud.minimap.waypoint.set.WaypointSet;

import java.util.ArrayList;
import java.util.List;

@Mixin(WaypointIO.class)
public class WaypointIOMixin {
    @Redirect(method = "saveWaypoints", at = @At(value = "INVOKE", target = "Lxaero/hud/minimap/waypoint/set/WaypointSet;getWaypoints()Ljava/lang/Iterable;"))
    private Iterable<Waypoint> redirectGetWaypoints(WaypointSet set) {
        List<Waypoint> ret = new ArrayList<>();
        set.getWaypoints().forEach(waypoint -> {
            if (!(waypoint instanceof ModCustomWaypoint)) {
                ret.add(waypoint);
            }
        });
        return ret;
    }
}
