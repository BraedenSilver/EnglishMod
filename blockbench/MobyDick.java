// Made with Blockbench 5.1.3
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package com.example.mod;
   
public class MobyDick extends EntityModel<Entity> {
	private final ModelPart root;
	private final ModelPart tail_front;
	private final ModelPart tail_back;
	private final ModelPart head;
	private final ModelPart lowerjaw;
	private final ModelPart body;
	private final ModelPart left_fin;
	private final ModelPart right_fin;
	public MobyDick(ModelPart root) {
		this.root = root.getChild("root");
		this.tail_front = root.getChild("tail_front");
		this.tail_back = root.getChild("tail_back");
		this.head = root.getChild("head");
		this.lowerjaw = root.getChild("lowerjaw");
		this.body = root.getChild("body");
		this.left_fin = root.getChild("left_fin");
		this.right_fin = root.getChild("right_fin");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -12.0F, 0.0F));

		ModelPartData tail_front = root.addChild("tail_front", ModelPartBuilder.create().uv(170, 123).cuboid(-16.0F, -17.0F, 0.0F, 32.0F, 34.0F, 38.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 21.0F, 43.0F));

		ModelPartData tail_back = tail_front.addChild("tail_back", ModelPartBuilder.create().uv(170, 195).cuboid(-13.0F, -15.0F, 0.0F, 26.0F, 25.0F, 35.0F, new Dilation(0.0F))
		.uv(0, 202).cuboid(-25.0F, 5.0F, 29.0F, 50.0F, 5.0F, 24.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 7.0F, 38.0F));

		ModelPartData head = root.addChild("head", ModelPartBuilder.create().uv(0, 123).cuboid(-17.0F, -19.0F, -74.0F, 34.0F, 28.0F, 51.0F, new Dilation(0.0F))
		.uv(0, 231).cuboid(-17.0F, -19.0F, -23.0F, 34.0F, 34.0F, 23.0F, new Dilation(0.0F))
		.uv(362, 0).cuboid(-8.0F, 9.0F, -63.0F, 16.0F, 2.0F, 42.0F, new Dilation(-0.1F)), ModelTransform.pivot(0.0F, 21.0F, -42.0F));

		ModelPartData lowerjaw = head.addChild("lowerjaw", ModelPartBuilder.create().uv(246, 0).cuboid(-8.0F, 0.0F, -42.0F, 16.0F, 6.0F, 42.0F, new Dilation(0.0F))
		.uv(362, 0).cuboid(-8.0F, -2.0F, -42.0F, 16.0F, 2.0F, 42.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 9.0F, -23.0F));

		ModelPartData body = root.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-19.0F, 0.0F, -42.0F, 38.0F, 38.0F, 85.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData left_fin = body.addChild("left_fin", ModelPartBuilder.create().uv(246, 48).cuboid(0.0F, 0.0F, 0.0F, 31.0F, 4.0F, 19.0F, new Dilation(0.0F)), ModelTransform.pivot(19.0F, 34.0F, -32.0F));

		ModelPartData right_fin = body.addChild("right_fin", ModelPartBuilder.create().uv(246, 71).cuboid(-31.0F, 0.0F, 0.0F, 31.0F, 4.0F, 19.0F, new Dilation(0.0F)), ModelTransform.pivot(-19.0F, 34.0F, -32.0F));
		return TexturedModelData.of(modelData, 512, 512);
	}
	@Override
	public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		root.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}