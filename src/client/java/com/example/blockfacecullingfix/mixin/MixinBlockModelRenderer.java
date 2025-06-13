package com.example.blockfacecullingfix.mixin;

import net.minecraft.client.render.block.BlockModelRenderer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.block.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.BitSet;

@Mixin(BlockModelRenderer.class)
public class MixinBlockModelRenderer {

    /**
     * Инъекция в метод method_3364 — заменяет старый isFaceVisible.
     * Прерывает отрисовку граней, которые закрыты соседним непрозрачным блоком.
     */
    @Inject(method = "method_3364", at = @At("HEAD"), cancellable = true)
    private void onGetQuadDimensions(BlockView world, BlockState state, BlockPos pos, int[] vertexData, Direction face, float[] box, BitSet flags, CallbackInfo ci) {
        BlockPos neighborPos = pos.offset(face);
        BlockState neighborState = world.getBlockState(neighborPos);

        if (neighborState.isOpaqueFullCube(world, neighborPos)) {
            ci.cancel(); // отменяем отрисовку грани
        }
    }
}
