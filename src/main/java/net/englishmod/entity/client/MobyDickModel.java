package net.englishmod.entity.client;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;

public class MobyDickModel extends EntityModel<MobyDickRenderState> {

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
            Identifier.fromNamespaceAndPath("englishmod", "moby_dick"), "main");

    private final ModelPart root;
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart tailFront;
    private final ModelPart tailBack;
    private final ModelPart leftFin;
    private final ModelPart rightFin;

    public MobyDickModel(ModelPart root) {
        super(root);
        this.root = root.getChild("root");
        this.body = this.root.getChild("body");
        this.head = this.root.getChild("head");
        this.tailFront = this.root.getChild("tail_front");
        this.tailBack = this.tailFront.getChild("tail_back");
        this.leftFin = this.body.getChild("left_fin");
        this.rightFin = this.body.getChild("right_fin");
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition partData = mesh.getRoot();

        PartDefinition root = partData.addOrReplaceChild("root",
                CubeListBuilder.create(),
                PartPose.offset(0.0F, -12.0F, 0.0F));

        PartDefinition body = root.addOrReplaceChild("body",
                CubeListBuilder.create()
                        .texOffs(0, 0).addBox(-19.0F, 0.0F, -42.0F, 38.0F, 38.0F, 85.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 0.0F, 0.0F));

        body.addOrReplaceChild("left_fin",
                CubeListBuilder.create()
                        .texOffs(246, 48).addBox(0.0F, 0.0F, 0.0F, 31.0F, 4.0F, 19.0F, new CubeDeformation(0.0F)),
                PartPose.offset(19.0F, 34.0F, -32.0F));

        body.addOrReplaceChild("right_fin",
                CubeListBuilder.create()
                        .texOffs(246, 71).addBox(-31.0F, 0.0F, 0.0F, 31.0F, 4.0F, 19.0F, new CubeDeformation(0.0F)),
                PartPose.offset(-19.0F, 34.0F, -32.0F));

        PartDefinition head = root.addOrReplaceChild("head",
                CubeListBuilder.create()
                        .texOffs(0, 123).addBox(-17.0F, -19.0F, -74.0F, 34.0F, 28.0F, 51.0F, new CubeDeformation(0.0F))
                        .texOffs(0, 231).addBox(-17.0F, -19.0F, -23.0F, 34.0F, 34.0F, 23.0F, new CubeDeformation(0.0F))
                        .texOffs(362, 0).addBox(-8.0F, 9.0F, -63.0F, 16.0F, 2.0F, 42.0F, new CubeDeformation(-0.1F)),
                PartPose.offset(0.0F, 21.0F, -42.0F));

        head.addOrReplaceChild("lowerjaw",
                CubeListBuilder.create()
                        .texOffs(246, 0).addBox(-8.0F, 0.0F, -42.0F, 16.0F, 6.0F, 42.0F, new CubeDeformation(0.0F))
                        .texOffs(362, 0).addBox(-8.0F, -2.0F, -42.0F, 16.0F, 2.0F, 42.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 9.0F, -23.0F));

        PartDefinition tailFront = root.addOrReplaceChild("tail_front",
                CubeListBuilder.create()
                        .texOffs(170, 123).addBox(-16.0F, -17.0F, 0.0F, 32.0F, 34.0F, 38.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 21.0F, 43.0F));

        tailFront.addOrReplaceChild("tail_back",
                CubeListBuilder.create()
                        .texOffs(170, 195).addBox(-13.0F, -15.0F, 0.0F, 26.0F, 25.0F, 35.0F, new CubeDeformation(0.0F))
                        .texOffs(0, 202).addBox(-25.0F, 5.0F, 29.0F, 50.0F, 5.0F, 24.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 7.0F, 38.0F));

        return LayerDefinition.create(mesh, 512, 512);
    }

    @Override
    public void setupAnim(MobyDickRenderState state) {
        super.setupAnim(state);

        float age = state.ageInTicks;
        float speed = Mth.clamp(state.swimSpeed, 0.0f, 1.0f);

        float tailFreq = 0.12f;
        float tailAmp = 0.15f + speed * 0.20f;   // idle: ~9°; full swim: ~20°
        this.tailFront.xRot = Mth.sin(age * tailFreq) * tailAmp;
        this.tailBack.xRot = Mth.sin(age * tailFreq + 0.7f) * (tailAmp * 1.4f);

        this.root.zRot = Mth.sin(age * tailFreq) * 0.03f * (0.5f + speed);

        float finDroop = 0.35f;
        float finSweep = Mth.sin(age * 0.08f) * (0.15f + speed * 0.15f);
        this.leftFin.zRot  =  finDroop + finSweep;
        this.rightFin.zRot = -(finDroop + finSweep);

        // Head tracks player / target — clamped so a massive head doesn't over-rotate
        float yaw   = Mth.clamp(state.headYaw   * Mth.DEG_TO_RAD, -0.5f, 0.5f);
        float pitch = Mth.clamp(state.headPitch * Mth.DEG_TO_RAD, -0.35f, 0.35f);
        this.head.yRot = yaw;
        this.head.xRot = pitch;
    }
}
