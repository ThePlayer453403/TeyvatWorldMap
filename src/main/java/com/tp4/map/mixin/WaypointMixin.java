package com.tp4.map.mixin;

import com.tp4.map.TeyvatWorldMap;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import xaero.hud.minimap.waypoint.set.WaypointSet;
import xaero.hud.minimap.world.MinimapWorld;

import java.util.Map;

@Mixin(MinimapWorld.class)
public abstract class WaypointMixin {

    @Shadow
    @Final
    private Map<String, WaypointSet> waypointSets;

    @Shadow
    private RegistryKey<World> dimId;

    /**
     * @author ThePlayer453403
     * @reason export waypoint sets
     */
    @Overwrite
    public void addWaypointSet(String s) {
        WaypointSet set = xaero.hud.minimap.waypoint.set.WaypointSet.Builder.begin().setName(s).build();
        TeyvatWorldMap.waypointSets.putIfAbsent(dimId.getValue().toString(), set);
        this.waypointSets.put(s, set);
        TeyvatWorldMap.reload();
    }
}
