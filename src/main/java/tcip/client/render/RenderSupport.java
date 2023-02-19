package tcip.client.render;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import tcip.client.render.blocks.RenderPlatform;
import tcip.common.tile.TileSupport;

public class RenderSupport extends TileEntitySpecialRenderer {

    public static final RenderPlatform renderPlatform = new RenderPlatform();



    public RenderSupport() {

    }

    @Override
    public void renderTileEntityAt (TileEntity var1,double x, double y, double z, float var8){
        if (var1 instanceof TileSupport) {
            TileSupport supportTile = (TileSupport) var1;
            if (supportTile.hasModel && supportTile.getPlatformType() != null) {
                switch (supportTile.getPlatformType()) {

                    case STRAIGHT: {

                    }
                }
            }

        }
    }
}
