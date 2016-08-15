package zairus.ytmodsmc.item;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import zairus.ytmodsmc.YTConstants;
import zairus.ytmodsmc.YTModsMC;

public class YTItems
{
	public static final Item YOUTUBE_WAND;
	
	static
	{
		YOUTUBE_WAND = new ItemYTWand().setRegistryName(new ResourceLocation(YTConstants.MODID, "youtube_wand")).setUnlocalizedName("youtube_wand");
	}
	
	public static void register()
	{
		YTModsMC.proxy.registerItem(YOUTUBE_WAND, "youtube_wand", 0, true);
	}
}
