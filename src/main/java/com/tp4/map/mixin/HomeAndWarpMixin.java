package com.tp4.map.mixin;

import com.example.haw.DataSendS2CPayload;
import com.example.haw.client.HomeAndWarpClient;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.tp4.map.client.TeyvatWorldMapClient.reload;

@Mixin(HomeAndWarpClient.class)
public class HomeAndWarpMixin {
    @Inject(method = "lambda$onInitializeClient$0", at = @At("TAIL"))
    private static void TeyvatWorldMap$reloadWaypoint(DataSendS2CPayload payload, ClientPlayNetworking.Context context, CallbackInfo ci) {
        reload();
    }
}
