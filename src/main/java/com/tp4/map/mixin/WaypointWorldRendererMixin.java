package com.tp4.map.mixin;

import com.tp4.map.CustomWaypoint;
import com.tp4.map.TeyvatWorldMap;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xaero.common.minimap.waypoints.Waypoint;
import xaero.common.misc.Misc;
import xaero.hud.minimap.waypoint.render.world.WaypointWorldRenderer;
import xaero.lib.client.graphics.XaeroBufferProvider;

@Mixin(WaypointWorldRenderer.class)
public class WaypointWorldRendererMixin {
    @Inject(method = "renderIcon", at = @At("HEAD"), cancellable = true)
    private void TeyvatWorldMap$worldTeleportPointIcon(Waypoint w, boolean highlit, MatrixStack matrixStack, TextRenderer fontRenderer, XaeroBufferProvider bufferSource, CallbackInfo ci) {
        if (w instanceof CustomWaypoint) {
            matrixStack.push();
            matrixStack.scale(0.25f, 0.25f, 0.25f);
            TeyvatWorldMap.drawNormalText(matrixStack, TeyvatWorldMap.getIcon((CustomWaypoint) w), -32f, -8.0f, -1, false, bufferSource);
            matrixStack.pop();
            ci.cancel();
        }
    }
}
