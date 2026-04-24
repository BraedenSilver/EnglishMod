package net.englishmod.entity.client;

import net.englishmod.entity.MobyDickEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class MobyDickRenderer extends MobRenderer<MobyDickEntity, MobyDickRenderState, MobyDickModel> {

    private static final Identifier TEXTURE =
            Identifier.fromNamespaceAndPath("englishmod", "textures/entity/moby_dick.png");

    public MobyDickRenderer(EntityRendererProvider.Context context) {
        super(context, new MobyDickModel(MobyDickModel.getTexturedModelData().bakeRoot()), 2.5f);
    }

    @Override
    public MobyDickRenderState createRenderState() {
        return new MobyDickRenderState();
    }

    @Override
    public void extractRenderState(MobyDickEntity entity, MobyDickRenderState state, float tickDelta) {
        super.extractRenderState(entity, state, tickDelta);
        state.swimSpeed = entity.walkAnimation.speed(tickDelta);
        state.headYaw = entity.getYHeadRot() - entity.getYRot();
        state.headPitch = entity.getXRot();
    }

    @Override
    public Identifier getTextureLocation(MobyDickRenderState state) {
        return TEXTURE;
    }
}
