package zairus.ytmodsmc.helpers;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;
import zairus.ytmodsmc.YTConstants;

public class StructureHelper
{
	public static boolean spawnStructure(World world, BlockPos pos)
	{
		return spawnStructure(world, pos, StructureVillageType.CHURCH, Mirror.NONE, Rotation.NONE);
	}
	
	public static boolean spawnStructure(World world, BlockPos pos, StructureVillageType type, Mirror mirror, Rotation rotation)
	{
		if (world.isRemote)
			return false;
		
		WorldServer worldserver = (WorldServer)world;
		TemplateManager templatemanager = worldserver.getStructureTemplateManager();
		ResourceLocation structLocation = new ResourceLocation(YTConstants.MODID, type.toString());
		Template template = templatemanager.func_189942_b(null, structLocation);
		
		if (template == null)
			return false;
		
		PlacementSettings placementsettings = (new PlacementSettings())
				.setMirror(mirror)
				.setRotation(rotation)
				.setIgnoreEntities(false)
				.setChunk((ChunkPos)null)
				.setReplacedBlock((Block)null)
				.setIgnoreStructureBlock(true);
		
		template.addBlocksToWorldChunk(world, pos.up(), placementsettings);
		
		world.markChunkDirty(pos, null);
		
		return true;
	}
	
	public enum StructureVillageType
	{
		HOUSE1("Villager_House", null, 17, 29, 16),
		HOUSE2("Villager_House2", null, 13, 19, 16),
		CHURCH("Church", EnumFacing.DOWN, 16, 18, 25),
		BLACKSMITH("Villiager_Blacksmith", null, 16, 15, 15),
		CLOCKTOWER("Villiage_Clocktower", null, 9, 25, 9),
		STABLES("Stables", EnumFacing.DOWN, 13, 8, 12),
		LIBRARY("Villiager_Library", null, 25, 20, 23);
		
		private String name;
		private EnumFacing offset;
		private int xSize;
		private int ySize;
		private int zSize;
		
		private StructureVillageType(String name, @Nullable EnumFacing offset, int xSize, int ySize, int zSize)
		{
			this.name = name;
			this.offset = offset;
			this.xSize = xSize;
			this.ySize = ySize;
			this.zSize = zSize;
		}
		
		@Override
		public String toString()
		{
			return this.name;
		}
		
		public EnumFacing getOffse()
		{
			return this.offset;
		}
		
		public Vec3d getBounds()
		{
			return new Vec3d(this.xSize, this.ySize, this.zSize);
		}
	}
}
