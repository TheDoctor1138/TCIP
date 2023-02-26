package tcip.client.render.blocks;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import tcip.common.library.Info;
import tcip.common.tile.TileSignal;
import tmt.Tessellator;

public class RenderSignal extends TileEntitySpecialRenderer {



    static final ModelSpanishSignal modelSpanishSignal = new ModelSpanishSignal();



    private ResourceLocation[] texture = {new ResourceLocation(Info.resourceLocation, Info.modelTexPrefix + "Signal_green.png"), new ResourceLocation(Info.resourceLocation, Info.modelTexPrefix + "Signal_red.png"), new ResourceLocation(Info.resourceLocation, Info.modelTexPrefix + "Signal_yellow.png")};

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float tick) {
        Tessellator.bindTexture(texture[(((TileSignal) tileEntity).getSignalValue())]);
        GL11.glPushMatrix();
        GL11.glTranslatef((float) (x + 0.5f), (float)y + 0.65f, (float)z + 0.5f);
        GL11.glRotatef(180,0,0,1);
        switch (((TileSignal) tileEntity).getFacing()) {
            case 4: {
                GL11.glRotated(90, 0, 1, 0);
                modelSpanishSignal.render(null, 0,0,0,0,0,1.0f);
                break;
            }
            case 0: {
                GL11.glRotated(0, 0, 0, 1);
                GL11.glRotated(270, 0, 1, 0);
                modelSpanishSignal.render(null, 0,0,0,0,0,1.0f);
                break;
            }
            case 6: {
                GL11.glRotated(0, 0, 0, 1);
                GL11.glRotated(180, 0, 1, 0);
                modelSpanishSignal.render(null, 0,0,0,0,0,1.0f);
                break;
            }
            case 2: {
                GL11.glRotated(0, 0, 0, 1);
                GL11.glRotated(0, 0, 1, 0);
                modelSpanishSignal.render(null, 0,0,0,0,0,1.0f);
                break;
            }
            case 1: {
                GL11.glRotated(0, 0, 1, 0);
                GL11.glRotated(-45, 0, 1, 0);
                modelSpanishSignal.render(null, 0,0,0,0,0,1.0f);
                break;
            }
            case 3: {
                GL11.glRotated(0, 0, 0, 1);
                GL11.glRotated(45, 0, 1, 0);
                modelSpanishSignal.render(null, 0,0,0,0,0,1.0f);
                break;
            }
            case 5: {
                GL11.glRotated(0, 0, 0, 1);
                GL11.glRotated(135, 0, 1, 0);
                modelSpanishSignal.render(null, 0,0,0,0,0,1.0f);
                break;
            }
            case 7: {
                GL11.glRotated(0, 0, 0, 1);
                GL11.glRotated(-135, 0, 1, 0);
                modelSpanishSignal.render(null, 0,0,0,0,0,1.0f);
                break;
            }

            default: {

            }


        }


        GL11.glPopMatrix();
    }


}
