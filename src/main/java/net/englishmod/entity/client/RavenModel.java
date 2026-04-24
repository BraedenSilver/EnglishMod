package net.englishmod.entity.client;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.resources.Identifier;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
public class RavenModel extends EntityModel<RavenEntityRenderState> {

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
            Identifier.fromNamespaceAndPath("englishmod", "raven"), "main");

    private final ModelPart pivotRoot;
    private final ModelPart body;
    private final ModelPart leftWing;
    private final ModelPart rightWing;
    private final ModelPart head;
    private final ModelPart rightLeg;
    private final ModelPart leftLeg;

    public RavenModel(ModelPart root) {
        super(root);
        this.pivotRoot = root.getChild("root");
        this.body = this.pivotRoot.getChild("body");
        this.leftWing = this.body.getChild("left_wing");
        this.rightWing = this.body.getChild("right_wing");
        this.head = this.pivotRoot.getChild("head");
        this.rightLeg = this.pivotRoot.getChild("right_leg");
        this.leftLeg = this.pivotRoot.getChild("left_leg");
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition partData = mesh.getRoot();

        PartDefinition root = partData.addOrReplaceChild("root",
                CubeListBuilder.create(),
                PartPose.offset(0.0F, 23.0F, 0.0F));

        PartDefinition body = root.addOrReplaceChild("body",
                CubeListBuilder.create()
                        .texOffs(0, 0).addBox(-3.0F, -8.0F, -5.0F, 6.0F, 8.0F, 5.0F, new CubeDeformation(0.0F))
                        .texOffs(18, 13).addBox(-2.0F, 0.0F, -3.0F, 4.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, -4.0F, 4.0F, 0.6109F, 0.0F, 0.0F));

        body.addOrReplaceChild("left_wing",
                CubeListBuilder.create()
                        .texOffs(12, 26).addBox(0.0F, -1.0F, -2.0F, 1.0F, 6.0F, 3.0F, new CubeDeformation(0.0F))
                        .texOffs(0, 28).addBox(0.0F, 5.0F, -1.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
                PartPose.offset(3.0F, -7.0F, -1.0F));

        body.addOrReplaceChild("right_wing",
                CubeListBuilder.create()
                        .texOffs(12, 26).addBox(-1.0F, -1.0F, -2.0F, 1.0F, 6.0F, 3.0F, new CubeDeformation(0.0F))
                        .texOffs(0, 28).addBox(-1.0F, 5.0F, -1.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
                PartPose.offset(-3.0F, -7.0F, -1.0F));

        root.addOrReplaceChild("head",
                CubeListBuilder.create()
                        .texOffs(20, 26).addBox(-1.0F, -3.0F, -8.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                        .texOffs(0, 13).addBox(-2.0F, -4.0F, -5.0F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, -8.0F, -1.0F));

        root.addOrReplaceChild("right_leg",
                CubeListBuilder.create()
                        .texOffs(0, 22).addBox(-1.5F, 0.0F, -3.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
                PartPose.offset(-1.5F, -2.0F, 1.0F));

        root.addOrReplaceChild("left_leg",
                CubeListBuilder.create()
                        .texOffs(0, 22).addBox(-1.5F, 0.0F, -3.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
                PartPose.offset(1.5F, -2.0F, 1.0F));

        return LayerDefinition.create(mesh, 64, 64);
    }

    @Override
    public void setupAnim(RavenEntityRenderState state) {
        super.setupAnim(state);

        float yaw = state.headYaw * ((float) Math.PI / 180f);
        float pitch = state.headPitch * ((float) Math.PI / 180f);

        yaw = Math.max(-1.2f, Math.min(1.2f, yaw));
        pitch = Math.max(-0.5f, Math.min(0.5f, pitch));

        this.head.yRot = yaw;
        this.head.xRot = pitch;

        // idle wing sway
        float wingBob = (float) Math.sin(state.ageInTicks * 0.15f) * 0.15f;
        this.leftWing.zRot = wingBob;
        this.rightWing.zRot = -wingBob;

        // leg hop cycle when walking
        this.rightLeg.xRot = (float) Math.cos(state.walkAnimationPos * 0.6662f) * 1.2f * state.walkAnimationSpeed;
        this.leftLeg.xRot = (float) Math.cos(state.walkAnimationPos * 0.6662f + (float) Math.PI) * 1.2f * state.walkAnimationSpeed;

        // body bob when walking
        this.pivotRoot.y = 23.0f + (float) Math.sin(state.walkAnimationPos * 1.3324f) * 0.4f * state.walkAnimationSpeed;
    }
}
