package kris91268.lbd.Tileentity;

import kris91268.lbd.Models.ModelGravityLift;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

/**
 * 
 * @author Arbiter
 *
 */
public class TileEntityGravityLiftRenderer extends TileEntitySpecialRenderer
{
	private ModelGravityLift model = new ModelGravityLift();
	
	public void renderAModelAt(TileEntityGravityLift par1, double par2, double par3, double par4, float par5)
	{
		int par6 = 0;
		if (par1.getWorldObj() != null)
		{
			par6 = par1.getBlockMetadata();
		}
		short par7 = (short)(par6 * 90);
		this.bindTexture(LightTextures.gravityTexture);
		GL11.glPushMatrix();
		GL11.glTranslatef((float)par2 + 0.5F, (float)par3 + 1.5F, (float)par4 + 0.5F);
		GL11.glRotatef((float)par7, 0.0F, 1.0F, 0.0F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		this.model.renderModel(0.0625F);
		GL11.glPopMatrix();
	}
	public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par3, double par4, float par5)
	{
		this.renderAModelAt((TileEntityGravityLift)par1TileEntity, par2, par3, par4, par5);
	}
}