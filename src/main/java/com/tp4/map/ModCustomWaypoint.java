package com.tp4.map;

import net.minecraft.util.Identifier;
import xaero.common.minimap.waypoints.Waypoint;

public class ModCustomWaypoint extends Waypoint {
    public String type;
    public String id;

    public ModCustomWaypoint(int x, int y, int z, String name, String id, String type) {
        super(x, y, z, name.isEmpty() ? id : String.format("%s - %s", id, name), TeyvatWorldMap.config.customIcon.initial, TeyvatWorldMap.config.customIcon.color);
        this.id = id;
        this.type = type;
        setThirdPartyOrigin(Identifier.of(type, id.toLowerCase()));
    }
}
