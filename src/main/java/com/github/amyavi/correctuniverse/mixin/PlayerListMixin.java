package com.github.amyavi.correctuniverse.mixin;

import com.github.amyavi.correctuniverse.CorrectUniverse;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.players.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.io.File;

@Mixin(PlayerList.class)
public abstract class PlayerListMixin {
    @WrapOperation(
            method = "<init>",
            at = @At(value = "NEW", target = "(Ljava/io/File;)Lnet/minecraft/server/players/UserBanList;")
    )
    private UserBanList newUserBanList(final File file, final Operation<UserBanList> original,
                                       final @Local(argsOnly = true) MinecraftServer server) {
        return original.call(new File(CorrectUniverse.UNIVERSE, file.getName()));
    }

    @WrapOperation(
            method = "<init>",
            at = @At(value = "NEW", target = "(Ljava/io/File;)Lnet/minecraft/server/players/IpBanList;")
    )
    private IpBanList newIpBanList(final File file, final Operation<IpBanList> original,
                                   final @Local(argsOnly = true) MinecraftServer server) {
        return original.call(new File(CorrectUniverse.UNIVERSE, file.getName()));
    }

    @WrapOperation(
            method = "<init>",
            at = @At(value = "NEW", target = "(Ljava/io/File;)Lnet/minecraft/server/players/ServerOpList;")
    )
    private ServerOpList newServerOpList(final File file, final Operation<ServerOpList> original,
                                         final @Local(argsOnly = true) MinecraftServer server) {
        return original.call(new File(CorrectUniverse.UNIVERSE, file.getName()));
    }

    @WrapOperation(
            method = "<init>",
            at = @At(value = "NEW", target = "(Ljava/io/File;)Lnet/minecraft/server/players/UserWhiteList;")
    )
    private UserWhiteList newUserWhiteList(final File file, final Operation<UserWhiteList> original,
                                           final @Local(argsOnly = true) MinecraftServer server) {
        return original.call(new File(CorrectUniverse.UNIVERSE, file.getName()));
    }
}
