package com.tp4.map.mixin;

import com.tp4.map.CustomWaypoint;
import com.tp4.map.TeyvatWorldMap;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xaero.map.element.MapElementGraphics;
import xaero.map.element.render.ElementRenderInfo;
import xaero.map.graphics.renderer.multitexture.MultiTextureRenderTypeRendererProvider;
import xaero.map.misc.Misc;
import xaero.map.mods.gui.Waypoint;
import xaero.map.mods.gui.WaypointRenderer;

@Mixin(WaypointRenderer.class)
public class WaypointRendererMixin {

    @Inject(
            method = "renderElement(Lxaero/map/mods/gui/Waypoint;ZDFDDLxaero/map/element/render/ElementRenderInfo;Lxaero/map/element/MapElementGraphics;Lnet/minecraft/client/render/VertexConsumerProvider$Immediate;Lxaero/map/graphics/renderer/multitexture/MultiTextureRenderTypeRendererProvider;)Z",
            at = @At("HEAD"), cancellable = true
    )
    private void TeyvatWorldMap$worldmapTeleportPointIcon(Waypoint w, boolean hovered, double optionalDepth, float optionalScale, double partialX, double partialY, ElementRenderInfo renderInfo, MapElementGraphics guiGraphics, VertexConsumerProvider.Immediate vanillaBufferSource, MultiTextureRenderTypeRendererProvider rendererProvider, CallbackInfoReturnable<Boolean> cir) {
        if (w.getOriginal() instanceof CustomWaypoint) {
            MatrixStack matrixStack = guiGraphics.pose();
            TeyvatWorldMap.drawNormalText(matrixStack, TeyvatWorldMap.getIcon((CustomWaypoint) w.getOriginal()), -32f, -20f, -1, false, vanillaBufferSource);
            if (hovered) {
                matrixStack.push();
                matrixStack.scale(3f, 3f, 3f);
                guiGraphics.fill(8, -15, 12 + MinecraftClient.getInstance().textRenderer.getWidth(((CustomWaypoint) w.getOriginal()).getName()), -4, 0x80000000);
                Misc.drawNormalText(matrixStack, ((CustomWaypoint) w.getOriginal()).getName(), 10f, -14f, -1, true, vanillaBufferSource);
                matrixStack.pop();
            }
            cir.cancel();
        }
    }

    @Inject(
            method = "renderElementShadow(Lxaero/map/mods/gui/Waypoint;ZFDDLxaero/map/element/render/ElementRenderInfo;Lxaero/map/element/MapElementGraphics;Lnet/minecraft/client/render/VertexConsumerProvider$Immediate;Lxaero/map/graphics/renderer/multitexture/MultiTextureRenderTypeRendererProvider;)V",
            at = @At("HEAD"), cancellable = true
    )
    private void TeyvatWorldMap$worldmapTeleportPointIconShadow(Waypoint w, boolean hovered, float optionalScale, double partialX, double partialY, ElementRenderInfo renderInfo, MapElementGraphics guiGraphics, VertexConsumerProvider.Immediate vanillaBufferSource, MultiTextureRenderTypeRendererProvider rendererProvider, CallbackInfo ci) {
        if (w.getOriginal() instanceof CustomWaypoint) {
            ci.cancel();
        }
    }
}
