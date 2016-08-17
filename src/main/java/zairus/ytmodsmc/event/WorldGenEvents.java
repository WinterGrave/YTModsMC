package zairus.ytmodsmc.event;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import zairus.ytmodsmc.biome.decorate.WorldGenDecorationBase;
import zairus.ytmodsmc.biome.decorate.WorldGenPrettyVillage;

public class WorldGenEvents
{
	private static List<WorldGenDecorationBase> decorations;
	
	static
	{
		decorations = new ArrayList<WorldGenDecorationBase>();
		
		decorations.add(new WorldGenPrettyVillage().setRarity(3));
	}
	
	public static void init()
	{
		WorldGenEvents eventHandler	= new WorldGenEvents();
		
		MinecraftForge.EVENT_BUS.register(eventHandler);
        MinecraftForge.TERRAIN_GEN_BUS.register(eventHandler);
        MinecraftForge.ORE_GEN_BUS.register(eventHandler);
	}
	
	@SubscribeEvent
	public void BiomeDecorate(DecorateBiomeEvent.Post event)
	{
		World world = event.getWorld();
		BlockPos pos = event.getPos();
		Random rand = event.getRand();
		Biome biome = world.getBiomeForCoordsBody(pos);
		
		for (WorldGenDecorationBase decoration : decorations)
		{
			if (decoration.getAllowedBiomes().contains(biome))
			{
				decoration.generate(world, rand, pos);
			}
		}
	}
}
