package com.tp4.map;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import xaero.hud.minimap.waypoint.WaypointColor;

@Config(name = "teyvatworldmap")
public class ModConfig implements ConfigData {
    public boolean world = true;
    public boolean minimap = true;
    public boolean worldmap = true;
    public boolean statueOfTheSeven = true;

    @ConfigEntry.Gui.CollapsibleObject
    public CustomIcon customIcon = new CustomIcon();

    public static class CustomIcon {
        public WaypointColor color = WaypointColor.AQUA;
        public String initial = "W";

        public boolean world = true;
        public boolean minimap = true;
        public boolean worldmap = true;
        public boolean gui = true;
    }
}
