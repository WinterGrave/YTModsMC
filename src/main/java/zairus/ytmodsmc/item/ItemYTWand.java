package zairus.ytmodsmc.item;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;
import zairus.ytmodsmc.YTConstants;
import zairus.ytmodsmc.YTModsMC;

public class ItemYTWand extends Item
{
	public ItemYTWand()
	{
		this.setCreativeTab(CreativeTabs.TOOLS);
	}
	
	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		player.swingArm(hand);
		
		spawnStructure(world, pos);
		
		return EnumActionResult.SUCCESS;
	}
	
	private boolean spawnStructure(World world, BlockPos pos)
	{
		if (world.isRemote)
			return false;
		
		WorldServer worldserver = (WorldServer)world;
		TemplateManager templatemanager = worldserver.getStructureTemplateManager();
		Template template = templatemanager.func_189942_b(null, new ResourceLocation(YTConstants.MODID, "Villager_House"));
		
		YTModsMC.logger.info("spawned 2");
		
		if (template == null)
			return false;
		
		PlacementSettings placementsettings = (new PlacementSettings()).setMirror(Mirror.NONE).setRotation(Rotation.COUNTERCLOCKWISE_90).setIgnoreEntities(false).setChunk((ChunkPos)null).setReplacedBlock((Block)null).setIgnoreStructureBlock(false);
		
		template.addBlocksToWorldChunk(world, pos.up(), placementsettings);
		
		world.markChunkDirty(pos, null);
		
		YTModsMC.logger.info("spawned strc");
		
		return true;
	}
}
