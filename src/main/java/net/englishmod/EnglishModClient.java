package net.englishmod;

import net.englishmod.entity.client.MobyDickModel;
import net.englishmod.entity.client.MobyDickRenderer;
import net.englishmod.entity.client.RavenModel;
import net.englishmod.entity.client.RavenRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.renderer.entity.EntityRenderers;

public class EnglishModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModelLayerRegistry.registerModelLayer(RavenModel.LAYER_LOCATION, RavenModel::getTexturedModelData);
        EntityRenderers.register(ModEntities.RAVEN, RavenRenderer::new);

        ModelLayerRegistry.registerModelLayer(MobyDickModel.LAYER_LOCATION, MobyDickModel::getTexturedModelData);
        EntityRenderers.register(ModEntities.MOBY_DICK, MobyDickRenderer::new);
    }
}
