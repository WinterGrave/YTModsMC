package zairus.ytmodsmc.proxy;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import zairus.ytmodsmc.YTConstants;
import zairus.ytmodsmc.item.YTItems;

public class CommonProxy
{
	public void preInit(FMLPreInitializationEvent e)
	{
		//Register entities
	}
	
	public void init(FMLInitializationEvent e)
	{
		YTItems.register();
	}
	
	public void postInit(FMLPostInitializationEvent e)
	{
		;
	}
	
	public void registerItem(Item item, String name, int meta, boolean model)
	{
		if (meta == 0)
			GameRegistry.register(item);
	}
	
	public void registerBlock(Block block, String name, boolean model)
	{
		GameRegistry.register(block);
		registerItem(new ItemBlock(block).setRegistryName(new ResourceLocation(YTConstants.MODID, name)), name, 0, model);
	}
	
	public void registerBlock(Block block, String name, Class<? extends TileEntity> clazz, String tileEntityId)
	{
		registerBlock(block, name, false);
		GameRegistry.registerTileEntity(clazz, tileEntityId);
	}
}
