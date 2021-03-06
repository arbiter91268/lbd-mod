package kris91268.lbd.Blocks;

import static net.minecraftforge.common.util.ForgeDirection.EAST;
import static net.minecraftforge.common.util.ForgeDirection.NORTH;
import static net.minecraftforge.common.util.ForgeDirection.SOUTH;
import static net.minecraftforge.common.util.ForgeDirection.WEST;

import java.util.Random;

import kris91268.lbd.ModLBD;
import kris91268.lbd.Tileentity.TileEntityLightBarrierSource;
import kris91268.lbd.Tileentity.TileEntityLightBarrierSourceActivated;
import kris91268.lbd.Tileentity.TileEntityLightBridgeSourceActivated;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * 
 * @author Arbiter
 *
 */
public class BlockLightBarrierSourceActivated extends BlockContainer implements ILightSource
{
	public BlockLightBarrierSourceActivated(Material material)
	{
		super(material);
		setResistance(6000000.0F);
		setBlockUnbreakable();
		disableStats();
		setStepSound(Block.soundTypeGlass);
		setBlockName("lightBarrierSourceActive");
		setBlockTextureName("lbd:lightSection");
		setLightLevel(0.4F);
	}
	@Override
	public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
	{
		return false;
	}
	@Override
	public int getRenderBlockPass()
	{
		return 1;
	}
	public void activate(World par1World, int par2, int par3, int par4, int par5, boolean continued)
	{
		
	}
	public void deactivate(World par1World, int par2, int par3, int par4, int par5, boolean continued)
	{
		if (par5 == 5)
		{
			par1World.setBlock(par2, par3, par4, ModLBD.lightBarrierSource, 5, 2);
			outer : for (int par6 = par2 + 1; par6 <= par2 + ModLBD.barrierLength; ++par6)
			{
				if (par1World.getBlock(par6, par3, par4) == ModLBD.lightBarrierSection)
				{
					par1World.setBlockToAir(par6, par3, par4);
				}
				else
				{
					for (Block b : allowedBlocks)
					{
						if (par1World.getBlock(par6, par3, par4) == b)
						{
							continue outer;
						}
					}
					break;
				}
			}
		}
		if (par5 == 1)
		{
			par1World.setBlock(par2, par3, par4, ModLBD.lightBarrierSource, 1, 2);
			outer : for (int par6 = par4 - 1; par6 >= par4 - ModLBD.barrierLength; --par6)
			{
				if (par1World.getBlock(par2, par3, par6) == ModLBD.lightBarrierSection)
				{
					par1World.setBlockToAir(par2, par3, par6);
				}
				else
				{
					for (Block b : allowedBlocks)
					{
						if (par1World.getBlock(par2, par3, par6) == b)
						{
							continue outer;
						}
					}
					break;
				}
			}
		}
		if (par5 == 2)
		{
			par1World.setBlock(par2, par3, par4, ModLBD.lightBarrierSource, 2, 2);
			outer : for (int par6 = par2 - 1; par6 >= par2 - ModLBD.barrierLength; --par6)
			{
				if (par1World.getBlock(par6, par3, par4) == ModLBD.lightBarrierSection)
				{
					par1World.setBlockToAir(par6, par3, par4);
				}
				else
				{
					for (Block b : allowedBlocks)
					{
						if (par1World.getBlock(par6, par3, par4) == b)
						{
							continue outer;
						}
					}
					break;
				}
			}
		}
		if (par5 == 3)
		{
			par1World.setBlock(par2, par3, par4, ModLBD.lightBarrierSource, 3, 2);
			outer : for (int par6 = par4 + 1; par6 <= par4 + ModLBD.barrierLength; ++par6)
			{
				if (par1World.getBlock(par2, par3, par6) == ModLBD.lightBarrierSection)
				{
					par1World.setBlockToAir(par2, par3, par6);
				}
				else 
				{
					for (Block b : allowedBlocks)
					{
						if (par1World.getBlock(par2, par3, par6) == b)
						{
							continue outer;
						}
					}
					break;
				}
			}
		}
		TileEntity passover = par1World.getTileEntity(par2, par3, par4);
		if (passover instanceof TileEntityLightBarrierSource)
		{
			((TileEntityLightBarrierSource)passover).onFinished();
		}
	}
	@Override
	public void onBlockAdded(World par1World, int par2, int par3, int par4)
	{
		if (par1World.isRemote)
		{
			if (!par1World.isBlockIndirectlyGettingPowered(par2, par3, par4) && 
					!LinkMaker.isAdjacentBarrierActive(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4)))
			{
				this.deactivate(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), false);
			}
		}
	}
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
	{
		int par5 = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
		float par6 = 0.625F;
		float par7 = 0.525F;
		if (par5 == 5)
		{
			setBlockBounds(0.0F, 0.0F, 1.0F - par7, 1.0F, 1.0F, 0.625F);
		}
		if (par5 == 1)
		{
			setBlockBounds(1.0F - par7, 0.0F, 0.0F, 0.625F, 1.0F, 1.0F);
		}
		if (par5 == 2)
		{
			setBlockBounds(0.0F, 0.0F, 1.0F - par6, 1.0F, 1.0F, 0.55F);
		}
		if (par5 == 3)
		{
			setBlockBounds(1.0F - par6, 0.0F, 0.0F, 0.55F, 1.0F, 1.0F);
		}
	}
	@Override
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block par5)
	{
		super.onNeighborBlockChange(par1World, par2, par3, par4, par5);
		int par6 = par1World.getBlockMetadata(par2, par3, par4);
		boolean flag = false;
		if (!par1World.isSideSolid(par2 - 1, par3, par4, EAST, true) && par6 == 5)
		{
			flag = true;
		}
		if (!par1World.isSideSolid(par2 + 1, par3, par4, WEST, true) && par6 == 2)
		{
			flag = true;
		}
		if (!par1World.isSideSolid(par2, par3, par4 - 1, SOUTH, true) && par6 == 3)
		{
			flag = true;
		}
		if (!par1World.isSideSolid(par2, par3, par4 + 1, NORTH, true) && par6 == 1)
		{
			flag = true;
		}
		if (flag)
		{
			this.deactivate(par1World, par2, par3, par4, par6, false);
			par1World.setBlockToAir(par2, par3, par4);
		}
		if (!par1World.isRemote)
		{
			if (!par1World.isBlockIndirectlyGettingPowered(par2, par3, par4))
			{
				this.deactivate(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), false);
			}
		}
	}
	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLiving, ItemStack par6ItemStack)
	{
		int par7 = MathHelper.floor_double((double)(par5EntityLiving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		if (par7 == 0)
		{
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 5, 2);
		}
		if (par7 == 1)
		{
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 3, 2);
		}
		if (par7 == 2)
		{
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 2, 2);
		}
		if (par7 == 3)
		{
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 1, 2);
		}
		if (par6ItemStack.hasDisplayName())
		{
			((TileEntityLightBarrierSourceActivated)par1World.getTileEntity(par2, par3, par4)).func_91429_a(par6ItemStack.getDisplayName());
		}
	}
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}
	@Override
	public int getRenderType()
	{
		return -1;
	}
	public TileEntity createNewTileEntity(World par1World, int par2)
	{
		return new TileEntityLightBarrierSourceActivated();
	}
}