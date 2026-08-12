package com.tp4.map.mixin;

import com.tp4.map.CustomWaypoint;
import com.tp4.map.TeyvatWorldMap;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xaero.common.minimap.waypoints.Waypoint;
import xaero.hud.minimap.element.render.MinimapElementGraphics;
import xaero.hud.minimap.waypoint.render.WaypointMapRenderer;
import xaero.lib.client.graphics.XaeroBufferProvider;

@Mixin(WaypointMapRenderer.class)
public class WaypointMapRendererMixin {
    @Inject(
            method = "drawIcon(Lxaero/hud/minimap/element/render/MinimapElementGraphics;Lxaero/common/minimap/waypoints/Waypoint;IIIIIIIIIFILxaero/lib/client/graphics/XaeroBufferProvider;Lnet/minecraft/client/render/VertexConsumer;Lnet/minecraft/client/render/VertexConsumer;)V",
            at = @At("HEAD"), cancellable = true
    )
    public void TeyvatWorldMap$minimapTeleportPointIcon(MinimapElementGraphics guiGraphics, Waypoint w, int drawX, int drawY, int rectX1, int rectY1, int rectX2, int rectY2, int r, int g, int b, float a, int initialsWidth, XaeroBufferProvider renderTypeBuffer, VertexConsumer waypointBackgroundConsumer, VertexConsumer texturedIconConsumer, CallbackInfo ci) {
        if (w instanceof CustomWaypoint) {
            MatrixStack matrixStack = guiGraphics.pose();
            matrixStack.push();
            matrixStack.scale(0.25f, 0.25f, 0.25f);
            TeyvatWorldMap.drawNormalText(matrixStack, TeyvatWorldMap.getIcon((CustomWaypoint) w), -32f, 16f, -1, false, renderTypeBuffer);
            matrixStack.pop();
            ci.cancel();
        }
    }

    @Inject(
            method = "drawIconGUI(Lnet/minecraft/client/gui/DrawContext;Lxaero/common/minimap/waypoints/Waypoint;IIIIIIIIIFI)V",
            at = @At("HEAD"), cancellable = true
    )
    private void TeyvatWorldMap$guiTeleportPointIcon(DrawContext guiGraphics, Waypoint w, int drawX, int drawY, int rectX1, int rectY1, int rectX2, int rectY2, int r, int g, int b, float a, int initialsWidth, CallbackInfo ci) {
        if (w instanceof CustomWaypoint) {
            guiGraphics.getMatrices().pushMatrix();
            guiGraphics.getMatrices().translate(drawX - 10, drawY + 3);
            guiGraphics.getMatrices().scale(0.25f);
            guiGraphics.drawText(MinecraftClient.getInstance().textRenderer, TeyvatWorldMap.getIcon((CustomWaypoint) w), 0, 0, -1, false);
            guiGraphics.getMatrices().popMatrix();
            ci.cancel();
        }
    }
}
