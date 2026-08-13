package com.tp4.map.mixin;

import com.tp4.map.ModCustomWaypoint;
import com.tp4.map.TeyvatWorldMap;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xaero.common.minimap.waypoints.Waypoint;
import xaero.hud.minimap.waypoint.render.world.WaypointWorldRenderer;
import xaero.lib.client.graphics.XaeroBufferProvider;

@Mixin(WaypointWorldRenderer.class)
public class WaypointWorldRendererMixin {
    @Inject(method = "renderIcon", at = @At("HEAD"), cancellable = true)
    private void TeyvatWorldMap$worldTeleportPointIcon(Waypoint w, boolean highlit, MatrixStack matrixStack, TextRenderer fontRenderer, XaeroBufferProvider bufferSource, CallbackInfo ci) {
        if (!TeyvatWorldMap.config.customIcon.world) return;
        if (w instanceof ModCustomWaypoint) {
            if (TeyvatWorldMap.config.world) {
                matrixStack.push();
                matrixStack.scale(0.25f, 0.25f, 0.25f);
                TeyvatWorldMap.drawNormalText(matrixStack, TeyvatWorldMap.getIcon((ModCustomWaypoint) w), -32f, -8.0f, -1, false, bufferSource);
                matrixStack.pop();
            }
            ci.cancel();
        }
    }

    @Inject(method = "renderIconWithLabels", at = @At("HEAD"), cancellable = true)
    private void TeyvatWorldMap$worldTeleportPoint(Waypoint w, boolean highlit, String name, String distanceText, String subWorldName, float iconScale, int nameScale, int distanceTextScale, TextRenderer fontRenderer, int halfIconPixel, MatrixStack matrixStack, XaeroBufferProvider bufferSource, CallbackInfo ci) {
        if (!TeyvatWorldMap.config.world && w instanceof ModCustomWaypoint) ci.cancel();
    }
}
