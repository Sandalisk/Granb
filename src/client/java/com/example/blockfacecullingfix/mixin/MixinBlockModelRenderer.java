package com.example.blockfacecullingfix.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.client.render.BlockRenderView;
import net.minecraft.client.render.block.BlockModelRenderer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.BitSet;

@Mixin(BlockModelRenderer.class)
public class MixinBlockModelRenderer {

    /**
     * Инъекция в метод method_3364 (Quad Rendering),
     * прерывает отрисовку грани, если она полностью закрыта соседним блоком.
     */
    @Inject(method = "method_3364", at = @At("HEAD"), cancellable = true)
    private void onGetQuadDimensions(BlockRenderView world, BlockState state, BlockPos pos, int[] vertexData, Direction face, float[] box, BitSet flags, CallbackInfo ci) {
        BlockPos neighborPos = pos.offset(face);
        BlockState neighborState = world.getBlockState(neighborPos);

        if (neighborState.isOpaqueFullCube(world, neighborPos)) {
            ci.cancel(); // отменяем отрисовку грани
        }
    }
}
