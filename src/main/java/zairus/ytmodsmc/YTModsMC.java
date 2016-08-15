package zairus.ytmodsmc;

import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import zairus.ytmodsmc.proxy.CommonProxy;

@Mod(modid = YTConstants.MODID, name = YTConstants.MODNAME, version = YTConstants.VERSION)
public class YTModsMC
{
	@Mod.Instance(YTConstants.MODID)
	public static YTModsMC instance;
	
	@SidedProxy(clientSide = YTConstants.CLIENT_PROXY, serverSide = YTConstants.COMMON_PROXY)
	public static CommonProxy proxy;
	
	public static Logger logger;
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		logger = event.getModLog();
		
		proxy.preInit(event);
	}
	
	@Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
		proxy.init(event);
    }
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		proxy.postInit(event);
	}
}
