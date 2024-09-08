package com.github.amyavi.correctuniverse.mixin;

import com.github.amyavi.correctuniverse.CorrectUniverse;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.server.Main;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.File;

@Mixin(Main.class)
public abstract class MainMixin {
    @Inject(
            method = "main",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/level/storage/LevelStorageSource;createDefault(Ljava/nio/file/Path;)Lnet/minecraft/world/level/storage/LevelStorageSource;")
    )
    private static void storeUniverse(final String[] strings, final CallbackInfo ci, final @Local File file) {
        CorrectUniverse.UNIVERSE = file;
    }
}
