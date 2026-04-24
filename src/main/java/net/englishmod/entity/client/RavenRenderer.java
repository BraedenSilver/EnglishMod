package net.englishmod.entity.client;

import net.englishmod.entity.RavenEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class RavenRenderer extends MobRenderer<RavenEntity, RavenEntityRenderState, RavenModel> {

    private static final Identifier TEXTURE =
            Identifier.fromNamespaceAndPath("englishmod", "textures/entity/raven.png");

    public RavenRenderer(EntityRendererProvider.Context context) {
        super(context, new RavenModel(RavenModel.getTexturedModelData().bakeRoot()), 0.3f);
    }

    @Override
    public RavenEntityRenderState createRenderState() {
        return new RavenEntityRenderState();
    }

    @Override
    public void extractRenderState(RavenEntity entity, RavenEntityRenderState state, float tickDelta) {
        super.extractRenderState(entity, state, tickDelta);
        state.headYaw = entity.getYHeadRot() - entity.getYRot();
        state.headPitch = entity.getXRot();
        state.walkAnimationPos = entity.walkAnimation.position(tickDelta);
        state.walkAnimationSpeed = entity.walkAnimation.speed(tickDelta);
    }

    @Override
    public Identifier getTextureLocation(RavenEntityRenderState state) {
        return TEXTURE;
    }
}
