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

public class ModelPlatformCorner extends ModelConverter //Same as Filename
{
	int textureX = 128;
	int textureY = 64;

	public ModelPlatformCorner() //Same as Filename
	{
		bodyModel = new ModelRendererTurbo[17];

		initbodyModel_1();

		translateAll(0F, 0F, 0F);


		flipAll();
	}

	private void initbodyModel_1()
	{
		bodyModel[0] = new ModelRendererTurbo(this, 3, 23, textureX, textureY); // Box 0
		bodyModel[1] = new ModelRendererTurbo(this, 75, 2, textureX, textureY); // Box 1
		bodyModel[2] = new ModelRendererTurbo(this, 75, 21, textureX, textureY); // Box 1
		bodyModel[3] = new ModelRendererTurbo(this, 6, 6, textureX, textureY); // Box 6
		bodyModel[4] = new ModelRendererTurbo(this, 44, 7, textureX, textureY); // Box 7
		bodyModel[5] = new ModelRendererTurbo(this, 45, 8, textureX, textureY); // Box 8
		bodyModel[6] = new ModelRendererTurbo(this, 43, 6, textureX, textureY); // Box 9
		bodyModel[7] = new ModelRendererTurbo(this, 42, 5, textureX, textureY); // Box 10
		bodyModel[8] = new ModelRendererTurbo(this, 48, 31, textureX, textureY); // Box 8
		bodyModel[9] = new ModelRendererTurbo(this, 48, 28, textureX, textureY); // Box 9
		bodyModel[10] = new ModelRendererTurbo(this, 48, 25, textureX, textureY); // Box 10
		bodyModel[11] = new ModelRendererTurbo(this, 48, 22, textureX, textureY); // Box 11
		bodyModel[12] = new ModelRendererTurbo(this, 69, 38, textureX, textureY); // Box 12
		bodyModel[13] = new ModelRendererTurbo(this, 67, 43, textureX, textureY); // Box 13
		bodyModel[14] = new ModelRendererTurbo(this, 42, 2, textureX, textureY); // Box 14
		bodyModel[15] = new ModelRendererTurbo(this, 42, 2, textureX, textureY); // Box 15
		bodyModel[16] = new ModelRendererTurbo(this, 42, 2, textureX, textureY); // Box 16

		bodyModel[0].addShapeBox(0F, 0F, 0F, 14, 8, 14, 0F,0F, -0.1F, 0F, 0F, -0.1F, 0F, 0F, -0.1F, 0F, 0F, -0.1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 0
		bodyModel[0].setRotationPoint(-6F, 8F, -6F);

		bodyModel[1].addShapeBox(0F, 0F, 0F, 2, 2, 14, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -2F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -2F, 0F); // Box 1
		bodyModel[1].setRotationPoint(-8F, 10F, -6F);

		bodyModel[2].addBox(0F, 0F, 0F, 2, 2, 14, 0F); // Box 1
		bodyModel[2].setRotationPoint(-8F, 8F, -6F);

		bodyModel[3].addShapeBox(0F, 0F, 0F, 11, 1, 11, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.9F, 0F, 0F, -0.9F, 0F, 0F, -0.9F, 0F, 0F, -0.9F, 0F); // Box 6
		bodyModel[3].setRotationPoint(-3F, 8F, -3F);

		bodyModel[4].addShapeBox(0F, 0F, 0F, 1, 1, 12, 0F,0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, -0.9F, 0F, -0.5F, -0.9F, 0F, -0.5F, -0.9F, 0F, 0F, -0.9F, 0F); // Box 7
		bodyModel[4].setRotationPoint(-4.5F, 8F, -4F);

		bodyModel[5].addShapeBox(0F, 0F, 0F, 1, 1, 11, 0F,0F, 0F, 0.25F, -0.5F, 0F, 0.25F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, -0.9F, 0.25F, -0.5F, -0.9F, 0.25F, -0.5F, -0.9F, 0F, 0F, -0.9F, 0F); // Box 8
		bodyModel[5].setRotationPoint(-3.75F, 8F, -3F);

		bodyModel[6].addShapeBox(0F, 0F, 0F, 1, 1, 13, 0F,0F, 0F, -0.25F, -0.5F, 0F, -0.25F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, -0.9F, -0.25F, -0.5F, -0.9F, -0.25F, -0.5F, -0.9F, 0F, 0F, -0.9F, 0F); // Box 9
		bodyModel[6].setRotationPoint(-5.25F, 8F, -5F);

		bodyModel[7].addShapeBox(0F, 0F, 0F, 1, 1, 14, 0F,0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, -0.9F, 0F, -0.5F, -0.9F, 0F, -0.5F, -0.9F, 0F, 0F, -0.9F, 0F); // Box 10
		bodyModel[7].setRotationPoint(-6F, 8F, -6F);

		bodyModel[8].addShapeBox(0F, 0F, 0F, 12, 1, 1, 0F,-0.25F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, -0.25F, 0F, -0.5F, -0.25F, -0.9F, 0F, 0F, -0.9F, 0F, 0F, -0.9F, -0.5F, -0.25F, -0.9F, -0.5F); // Box 8
		bodyModel[8].setRotationPoint(-4F, 8F, -3.75F);

		bodyModel[9].addShapeBox(0F, 0F, 0F, 13, 1, 1, 0F,0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0.5F, 0F, -0.5F, 0.5F, -0.9F, 0F, 0F, -0.9F, 0F, 0F, -0.9F, -0.5F, 0.5F, -0.9F, -0.5F); // Box 9
		bodyModel[9].setRotationPoint(-5F, 8F, -6F);

		bodyModel[10].addShapeBox(0F, 0F, 0F, 12, 1, 1, 0F,0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0.5F, 0F, -0.5F, 0.5F, -0.9F, 0F, 0F, -0.9F, 0F, 0F, -0.9F, -0.5F, 0.5F, -0.9F, -0.5F); // Box 10
		bodyModel[10].setRotationPoint(-4F, 8F, -4.5F);

		bodyModel[11].addShapeBox(0F, 0F, 0F, 13, 1, 1, 0F,0.25F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0.25F, 0F, -0.5F, 0.25F, -0.9F, 0F, 0F, -0.9F, 0F, 0F, -0.9F, -0.5F, 0.25F, -0.9F, -0.5F); // Box 11
		bodyModel[11].setRotationPoint(-5F, 8F, -5.25F);

		bodyModel[12].addBox(0F, 0F, 0F, 14, 2, 2, 0F); // Box 12
		bodyModel[12].setRotationPoint(-6F, 8F, -8F);

		bodyModel[13].addShapeBox(0F, 0F, 0F, 14, 2, 2, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -2F, 0F, 0F, -2F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 13
		bodyModel[13].setRotationPoint(-6F, 10F, -8F);

		bodyModel[14].addShapeBox(0F, 0F, 0F, 2, 2, 2, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -2F, 0F, 0F, 0F, -2F, 0F, 0F, 0F, 0F, -2F, 0F); // Box 14
		bodyModel[14].setRotationPoint(-8F, 10F, -8F);

		bodyModel[15].addBox(0F, 0F, 0F, 2, 2, 2, 0F); // Box 15
		bodyModel[15].setRotationPoint(-8F, 8F, -8F);

		bodyModel[16].addBox(0F, 0F, 0F, 2, 2, 2, 0F); // Box 16
		bodyModel[16].setRotationPoint(-8F, 8F, -8F);
	}
}