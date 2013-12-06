package realtech.renderer;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import realtech.lib.Reference;
import realtech.model.ModelFluorescentLamp;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class ModelFluorescentLampRenderer extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler {
	
	public int renderID;
	private final ModelFluorescentLamp model;
	
	public ModelFluorescentLampRenderer(int id) {
		this.model = new ModelFluorescentLamp();
		this.renderID = id;
	}
	
	private void adjustRotatePivotViaMeta(World world, int x, int y, int z) {
        int meta = world.getBlockMetadata(x, y, z);
        GL11.glPushMatrix();
        GL11.glRotatef(meta * (-90), 0.0F, 0.0F, 1.0F);
        GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y,
			double z, float f) {
		//The PushMatrix tells the renderer to "start" doing something.
        GL11.glPushMatrix();
        //This is setting the initial location.
        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
       
        //Use in 1.6.2  this
        ResourceLocation textures = (new ResourceLocation(Reference.modid + ":textures/models/ModelFluorescentLamp.png")); 
        Minecraft.getMinecraft().renderEngine.bindTexture(textures);
        //This rotation part is very important! Without it, your model will render upside-down! And for some reason you DO need PushMatrix again!                      
        GL11.glPushMatrix();
        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
        switch(te.getBlockMetadata()){
        case 2:
        	break;
        case 4:
        	GL11.glRotatef(90F, 0F, 1F, 0F);
        	break;
        case 5:
        	GL11.glRotatef(270F, 0F, 1F, 0F);
        	break;
        case 3:
        	GL11.glRotatef(180F, 0F, 1F, 0F);
        	break;
        default:
        	break;
        }
        
        this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);     
        
        GL11.glPopMatrix();
        GL11.glPopMatrix();
	}
	
	//Set the lighting stuff, so it changes it's brightness properly.      
    private void adjustLightFixture(World world, int i, int j, int k, Block block) {
            Tessellator tess = Tessellator.instance;
            float brightness = block.getBlockBrightness(world, i, j, k);
            int skyLight = world.getLightBrightnessForSkyBlocks(i, j, k, 0);
            int modulousModifier = skyLight % 65536;
            int divModifier = skyLight / 65536;
            tess.setColorOpaque_F(brightness, brightness, brightness);
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit,  modulousModifier,  divModifier);
    }
	

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID,
			RenderBlocks renderer) {
		
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer) {

		return false;
	}

	@Override
	public boolean shouldRender3DInInventory() {

		return false;
	}

	@Override
	public int getRenderId() {

		return renderID;
	}

}