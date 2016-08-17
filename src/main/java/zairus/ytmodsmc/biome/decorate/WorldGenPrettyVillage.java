package zairus.ytmodsmc.biome.decorate;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import net.minecraft.init.Biomes;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import zairus.ytmodsmc.YTModsMC;
import zairus.ytmodsmc.helpers.StructureHelper;
import zairus.ytmodsmc.helpers.StructureHelper.StructureVillageType;

public class WorldGenPrettyVillage extends WorldGenDecorationBase
{
	@Override
	public List<Biome> getAllowedBiomes()
	{
		List<Biome> biomes = Lists.<Biome>newArrayList();
		
		biomes.add(Biomes.EXTREME_HILLS);
		biomes.add(Biomes.EXTREME_HILLS_WITH_TREES);
		biomes.add(Biomes.MUTATED_EXTREME_HILLS);
		biomes.add(Biomes.MUTATED_EXTREME_HILLS_WITH_TREES);
		
		return biomes;
	}
	
	@Override
	protected boolean doGenerate(World world, Random rand, BlockPos pos)
	{
		boolean generated = false;
		int pieces = 2 + rand.nextInt(6);
		int pieces_generated = 0;
		BlockPos curPos = pos;
		
		StructureVillageType pieceType;
		List<PrettyVillagePiece> villagePieces = Lists.<PrettyVillagePiece>newArrayList();
		
		for (int i = 0; i < pieces; ++ i)
		{
			curPos = curPos.add(0, -5, 0);
			generated = false;
			pieceType = StructureVillageType.values()[rand.nextInt(StructureVillageType.values().length)];
			
			for (int h = 0; !generated && h < 10; ++h)
			{
				curPos = curPos.up();
				generated = this.checkSurface(
						world, 
						curPos.add(MathHelper.abs_max(pieceType.getBounds().xCoord, pieceType.getBounds().zCoord), 0, MathHelper.abs_max(pieceType.getBounds().xCoord, pieceType.getBounds().zCoord)), 
						(int)(MathHelper.abs_max(pieceType.getBounds().xCoord, pieceType.getBounds().zCoord) * 0.45D), 
						(int)(pieceType.getBounds().yCoord * 0.7D));
			}
			
			if (generated)
			{
				++pieces_generated;
				villagePieces.add(new PrettyVillagePiece().setType(pieceType).setPos(curPos));
				
				int xDist = (int)(pieceType.getBounds().xCoord) + 6 + rand.nextInt(4);
				int zDist = (int)(pieceType.getBounds().zCoord) + 6 + rand.nextInt(4);
				
				if (rand.nextInt(2) == 0)
					curPos = curPos.add(xDist, 0, 0);
				else if (rand.nextInt(2) == 0)
					curPos = curPos.add(0, 0, zDist);
				else
					curPos = curPos.add(xDist, 0, zDist);
			}
		}
		
		YTModsMC.logger.info("pieces: " + pieces_generated);
		
		if (pieces_generated < 2)
			return false;
		
		for (PrettyVillagePiece vPiece : villagePieces)
		{
			EnumFacing offset = vPiece.type.getOffse();
			
			StructureHelper.spawnStructure(
					world, 
					((offset == null)? vPiece.pos : vPiece.pos.offset(offset)), 
					vPiece.type,
					Mirror.values()[rand.nextInt(Mirror.values().length)],
					Rotation.values()[rand.nextInt(Rotation.values().length)]);
		}
		
		/*
		HOUSE1("Villager_House"),
		HOUSE2("Villager_House2"),
		CHURCH("Church"),
		BLACKSMITH("Villiager_Blacksmith"),
		CLOCKTOWER("Villiage_Clocktower"),
		STABLES("Stables"),
		LIBRARY("Villiager_Library");
		*/
		
		return true;
	}
	
	private class PrettyVillagePiece
	{
		public BlockPos pos;
		public StructureVillageType type;
		
		public PrettyVillagePiece()
		{
			;
		}
		
		public PrettyVillagePiece setType(StructureVillageType t)
		{
			this.type = t;
			return this;
		}
		
		public PrettyVillagePiece setPos(BlockPos p)
		{
			this.pos = p;
			return this;
		}
	}
}
