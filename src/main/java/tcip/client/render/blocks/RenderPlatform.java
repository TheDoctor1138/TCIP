package tcip.client.render.blocks;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import tcip.common.library.Info;
import tcip.common.tile.TileSupport;
import tmt.Tessellator;

public class RenderPlatform extends TileEntitySpecialRenderer {



    static final ModelPlatform modelPlatform = new ModelPlatform();
    static final ModelPlatformCorner modelPlatformCorner = new ModelPlatformCorner();



    private ResourceLocation texture = new ResourceLocation(Info.resourceLocation, Info.modelTexPrefix + "platform.png");

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float tick) {
        Tessellator.bindTexture(texture);
        GL11.glPushMatrix();
        GL11.glTranslatef((float) (x + 0.5f), (float)y + 1f, (float)z + 0.5f);
        GL11.glRotatef(180,0,0,1);
        switch (((TileSupport) tileEntity).getFacing()) {
            case 4: {
                GL11.glRotated(90, 0, 1, 0);
                modelPlatform.render(null, 0,0,0,0,0,1.0f);
                break;
            }
            case 0: {
                GL11.glRotated(0, 0, 0, 1);
                GL11.glRotated(270, 0, 1, 0);
                modelPlatform.render(null, 0,0,0,0,0,1.0f);
                break;
            }
            case 6: {
                GL11.glRotated(0, 0, 0, 1);
                GL11.glRotated(180, 0, 1, 0);
                modelPlatform.render(null, 0,0,0,0,0,1.0f);
                break;
            }
            case 2: {
                GL11.glRotated(0, 0, 0, 1);
                GL11.glRotated(0, 0, 1, 0);
                modelPlatform.render(null, 0,0,0,0,0,1.0f);
                break;
            }
            case 1: {
                GL11.glRotated(0, 0, 1, 0);
                modelPlatformCorner.render(null, 0,0,0,0,0,1.0f);
                break;
            }
            case 3: {
                GL11.glRotated(0, 0, 0, 1);
                GL11.glRotated(90, 0, 1, 0);
                modelPlatformCorner.render(null, 0,0,0,0,0,1.0f);
                break;
            }
            case 5: {
                GL11.glRotated(0, 0, 0, 1);
                GL11.glRotated(180, 0, 1, 0);
                modelPlatformCorner.render(null, 0,0,0,0,0,1.0f);
                break;
            }
            case 7: {
                GL11.glRotated(0, 0, 0, 1);
                GL11.glRotated(270, 0, 1, 0);
                modelPlatformCorner.render(null, 0,0,0,0,0,1.0f);
                break;
            }

            default: {

            }


        }


        GL11.glPopMatrix();
    }


}
