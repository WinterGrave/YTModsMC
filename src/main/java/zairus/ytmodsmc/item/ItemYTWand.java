package zairus.ytmodsmc.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import zairus.ytmodsmc.biome.decorate.WorldGenPrettyVillage;

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
		
		WorldGenPrettyVillage village = new WorldGenPrettyVillage();
		
		village.setRarity(1);
		
		if (!world.isRemote)
			village.generate(world, itemRand, pos.up());
		
		return EnumActionResult.SUCCESS;
	}
}
