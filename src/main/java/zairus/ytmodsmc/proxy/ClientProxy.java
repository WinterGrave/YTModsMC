package zairus.ytmodsmc.proxy;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import zairus.ytmodsmc.YTConstants;
import zairus.ytmodsmc.client.renderer.ISpecialRendered;

public class ClientProxy extends CommonProxy
{
	@Override
	public void preInit(FMLPreInitializationEvent e)
	{
		super.preInit(e);
		
		//Entity renderers
	}
	
	@Override
	public void init(FMLInitializationEvent e)
	{
		super.init(e);
	}
	
	@Override
	public void postInit(FMLPostInitializationEvent e)
	{
		super.postInit(e);
	}
	
	@Override
	public void registerBlock(Block block, String name, boolean model)
	{
		super.registerBlock(block, name, model);
		
		if (model)
			registerModel(Item.getItemFromBlock(block), 0, name);
	}
	
	@Override
	public void registerItem(Item item, String name, int meta, boolean model)
	{
		super.registerItem(item, name, meta, model);
		
		if (model && item != null)
		{
			registerModel(item, meta, name);
		}
	}
	
	public void registerModel(Item item, int meta, String name)
	{
		RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
		ModelResourceLocation modelResourceLocation = new ModelResourceLocation(YTConstants.MODID + ":" + name, "inventory");
		
		renderItem.getItemModelMesher().register(item, meta, modelResourceLocation);
		
		ModelBakery.registerItemVariants(item, new ResourceLocation(YTConstants.MODID, name));
	}
	
	@Override
	public void registerBlock(Block block, String name, Class<? extends TileEntity> clazz, String tileEntityId)
	{
		super.registerBlock(block, name, clazz, tileEntityId);
		
		if (block instanceof ISpecialRendered)
		{
			Object tesrObj = ((ISpecialRendered)block).getTESR();
			if (tesrObj instanceof TileEntitySpecialRenderer)
			{
				@SuppressWarnings("unchecked")
				TileEntitySpecialRenderer<TileEntity> tesr = (TileEntitySpecialRenderer<TileEntity>)tesrObj;
				
				ClientRegistry.bindTileEntitySpecialRenderer(clazz, tesr);
			}
		}
		
		registerModel(Item.getItemFromBlock(block), 0, name);
	}
}
