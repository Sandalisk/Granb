package com.example.blockfacecullingfix.mixin;

import net.minecraft.client.render.block.BlockModelRenderer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.block.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockModelRenderer.class)
public class MixinBlockModelRenderer {

    @Inject(method = "isFaceVisible", at = @At("HEAD"), cancellable = true)
    private void onIsFaceVisible(BlockView world, BlockPos pos, Direction face, CallbackInfoReturnable<Boolean> cir) {
        BlockPos neighborPos = pos.offset(face);
        BlockState neighborState = world.getBlockState(neighborPos);

        if (neighborState.isOpaqueFullCube(world, neighborPos)) {
            cir.setReturnValue(false);
        }
    }
}
