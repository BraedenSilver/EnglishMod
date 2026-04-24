// Made with Blockbench 5.1.3
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package com.example.mod;
   
public class Raven extends EntityModel<Entity> {
	private final ModelPart root;
	private final ModelPart body;
	private final ModelPart left_wing;
	private final ModelPart right_wing;
	private final ModelPart head;
	private final ModelPart right_leg;
	private final ModelPart left_leg;
	public Raven(ModelPart root) {
		this.root = root.getChild("root");
		this.body = root.getChild("body");
		this.left_wing = root.getChild("left_wing");
		this.right_wing = root.getChild("right_wing");
		this.head = root.getChild("head");
		this.right_leg = root.getChild("right_leg");
		this.left_leg = root.getChild("left_leg");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 23.0F, 0.0F));

		ModelPartData body = root.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-3.0F, -8.0F, -5.0F, 6.0F, 8.0F, 5.0F, new Dilation(0.0F))
		.uv(18, 13).cuboid(-2.0F, 0.0F, -3.0F, 4.0F, 4.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -4.0F, 4.0F, 0.6109F, 0.0F, 0.0F));

		ModelPartData left_wing = body.addChild("left_wing", ModelPartBuilder.create().uv(12, 26).cuboid(0.0F, -1.0F, -2.0F, 1.0F, 6.0F, 3.0F, new Dilation(0.0F))
		.uv(0, 28).cuboid(0.0F, 5.0F, -1.0F, 1.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(3.0F, -7.0F, -1.0F));

		ModelPartData right_wing = body.addChild("right_wing", ModelPartBuilder.create().uv(12, 26).cuboid(-1.0F, -1.0F, -2.0F, 1.0F, 6.0F, 3.0F, new Dilation(0.0F))
		.uv(0, 28).cuboid(-1.0F, 5.0F, -1.0F, 1.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.0F, -7.0F, -1.0F));

		ModelPartData head = root.addChild("head", ModelPartBuilder.create().uv(20, 26).cuboid(-1.0F, -3.0F, -8.0F, 2.0F, 2.0F, 3.0F, new Dilation(0.0F))
		.uv(0, 13).cuboid(-2.0F, -4.0F, -5.0F, 4.0F, 4.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -8.0F, -1.0F));

		ModelPartData right_leg = root.addChild("right_leg", ModelPartBuilder.create().uv(0, 22).cuboid(-1.5F, 0.0F, -3.0F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.5F, -2.0F, 1.0F));

		ModelPartData left_leg = root.addChild("left_leg", ModelPartBuilder.create().uv(0, 22).cuboid(-1.5F, 0.0F, -3.0F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(1.5F, -2.0F, 1.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}
	@Override
	public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		root.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}