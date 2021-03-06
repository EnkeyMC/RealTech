package realtech.proxy;

import realtech.renderer.ModelFluorescentLampRenderer;
import realtech.tileentity.TileEntityFluorescentLamp;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy{
	@Override
	public void registerRenderers() {
		int ModelFluorescentLampRendererID = RenderingRegistry.getNextAvailableRenderId();
	    ModelFluorescentLampRenderer FluorescentLampRender = new ModelFluorescentLampRenderer(ModelFluorescentLampRendererID); 
	    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFluorescentLamp.class, FluorescentLampRender);
	    RenderingRegistry.registerBlockHandler(FluorescentLampRender);
	}
}
