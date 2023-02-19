//This File was created with the Minecraft-SMP Modelling Toolbox 2.3.0.0
// Copyright (C) 2023 Minecraft-SMP.de
// This file is for Flan's Flying Mod Version 4.0.x+

// Model: 
// Model Creator: 
// Created on: 16.02.2023 - 18:39:55
// Last changed on: 16.02.2023 - 18:39:55

package tcip.client.render.blocks; //Path where the model is located


import tmt.ModelConverter;
import tmt.ModelRendererTurbo;

public class ModelPlatform extends ModelConverter //Same as Filename
{
	int textureX = 128;
	int textureY = 64;

	public ModelPlatform() //Same as Filename
	{
		bodyModel = new ModelRendererTurbo[8];

		initbodyModel_1();

		translateAll(0F, 0F, 0F);


		flipAll();
	}

	private void initbodyModel_1()
	{
		bodyModel[0] = new ModelRendererTurbo(this, 1, 21, textureX, textureY); // Box 0
		bodyModel[1] = new ModelRendererTurbo(this, 73, 0, textureX, textureY); // Box 1
		bodyModel[2] = new ModelRendererTurbo(this, 73, 19, textureX, textureY); // Box 1
		bodyModel[3] = new ModelRendererTurbo(this, 1, 1, textureX, textureY); // Box 6
		bodyModel[4] = new ModelRendererTurbo(this, 40, 3, textureX, textureY); // Box 7
		bodyModel[5] = new ModelRendererTurbo(this, 40, 3, textureX, textureY); // Box 8
		bodyModel[6] = new ModelRendererTurbo(this, 40, 3, textureX, textureY); // Box 9
		bodyModel[7] = new ModelRendererTurbo(this, 40, 3, textureX, textureY); // Box 10

		bodyModel[0].addShapeBox(0F, 0F, 0F, 14, 8, 16, 0F,0F, -0.1F, 0F, 0F, -0.1F, 0F, 0F, -0.1F, 0F, 0F, -0.1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 0
		bodyModel[0].setRotationPoint(-6F, 8F, -8F);

		bodyModel[1].addShapeBox(0F, 0F, 0F, 2, 2, 16, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -2F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -2F, 0F); // Box 1
		bodyModel[1].setRotationPoint(-8F, 10F, -8F);

		bodyModel[2].addBox(0F, 0F, 0F, 2, 2, 16, 0F); // Box 1
		bodyModel[2].setRotationPoint(-8F, 8F, -8F);

		bodyModel[3].addShapeBox(0F, 0F, 0F, 11, 1, 16, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.9F, 0F, 0F, -0.9F, 0F, 0F, -0.9F, 0F, 0F, -0.9F, 0F); // Box 6
		bodyModel[3].setRotationPoint(-3F, 8F, -8F);

		bodyModel[4].addShapeBox(0F, 0F, 0F, 1, 1, 16, 0F,0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, -0.9F, 0F, -0.5F, -0.9F, 0F, -0.5F, -0.9F, 0F, 0F, -0.9F, 0F); // Box 7
		bodyModel[4].setRotationPoint(-4.5F, 8F, -8F);

		bodyModel[5].addShapeBox(0F, 0F, 0F, 1, 1, 16, 0F,0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, -0.9F, 0F, -0.5F, -0.9F, 0F, -0.5F, -0.9F, 0F, 0F, -0.9F, 0F); // Box 8
		bodyModel[5].setRotationPoint(-3.75F, 8F, -8F);

		bodyModel[6].addShapeBox(0F, 0F, 0F, 1, 1, 16, 0F,0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, -0.9F, 0F, -0.5F, -0.9F, 0F, -0.5F, -0.9F, 0F, 0F, -0.9F, 0F); // Box 9
		bodyModel[6].setRotationPoint(-5.25F, 8F, -8F);

		bodyModel[7].addShapeBox(0F, 0F, 0F, 1, 1, 16, 0F,0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, -0.9F, 0F, -0.5F, -0.9F, 0F, -0.5F, -0.9F, 0F, 0F, -0.9F, 0F); // Box 10
		bodyModel[7].setRotationPoint(-6F, 8F, -8F);
	}
}