package com.tp4.map;

import net.minecraft.util.Identifier;
import xaero.common.minimap.waypoints.Waypoint;
import xaero.hud.minimap.waypoint.WaypointColor;

public class CustomWaypoint extends Waypoint {
    public String type;
    public String id;

    public CustomWaypoint(int x, int y, int z, String name, String id, String type) {
        super(x, y, z, name.isEmpty() ? id : String.format("%s - %s", id, name), "W", WaypointColor.AQUA);
        this.id = id;
        this.type = type;
        setThirdPartyOrigin(Identifier.of(type, id.toLowerCase()));
    }
}
