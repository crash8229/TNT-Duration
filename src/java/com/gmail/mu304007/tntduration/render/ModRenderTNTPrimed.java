package com.gmail.mu304007.tntduration.render;

import org.lwjgl.opengl.GL11;

import com.gmail.mu304007.tntduration.block.ModBlocks;
import com.gmail.mu304007.tntduration.entity.ModEntityTNTPrimed;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class ModRenderTNTPrimed extends Render
{
    private RenderBlocks blockRenderer = new RenderBlocks();
    
    public ModRenderTNTPrimed()
    {
        this.shadowSize = 0.5F;
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void func_76986_a(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(ModEntityTNTPrimed par1TNTPrimed, double x, double y, double z, float par5, float par6)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)x, (float)y, (float)z);
        float f2;

        if ((float)par1TNTPrimed.getFuse() - par6 + 1.0F < 10.0F)
        {
            f2 = 1.0F - ((float)par1TNTPrimed.getFuse() - par6 + 1.0F) / 10.0F;

            if (f2 < 0.0F)
            {
                f2 = 0.0F;
            }

            if (f2 > 1.0F)
            {
                f2 = 1.0F;
            }

            f2 *= f2;
            f2 *= f2;
            float f3 = 1.0F + f2 * 0.3F;
            GL11.glScalef(f3, f3, f3);
        }

        f2 = (1.0F - ((float)par1TNTPrimed.getFuse() - par6 + 1.0F) / 100.0F) * 0.8F;
        this.bindEntityTexture(par1TNTPrimed);
        this.blockRenderer.renderBlockAsItem(ModBlocks.modTNT[(par1TNTPrimed.getFuse() / 20) + 1], 0, par1TNTPrimed.getBrightness(par6));

//        if (par1TNTPrimed.getFuse() / 5 % 2 == 0)
//        {
//            GL11.glDisable(GL11.GL_TEXTURE_2D);
//            GL11.glDisable(GL11.GL_LIGHTING);
//            GL11.glEnable(GL11.GL_BLEND);
//            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_DST_ALPHA);
//            GL11.glColor4f(1.0F, 1.0F, 1.0F, f2);
//            this.blockRenderer.renderBlockAsItem(ModBlocks.modTNT[(par1TNTPrimed.getFuse() / 20) + 1], 0, 1.0F);
//            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
//            GL11.glDisable(GL11.GL_BLEND);
//            GL11.glEnable(GL11.GL_LIGHTING);
//            GL11.glEnable(GL11.GL_TEXTURE_2D);
//        }

        GL11.glPopMatrix();
    }

//    @Override
//	public void updateIcons(IIconRegister p_94143_1_) {
//		super.updateIcons(p_94143_1_);
//	}

	/**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    
    protected ResourceLocation func_110808_a(ModEntityTNTPrimed par1EntityTNTPrimed) {
        return TextureMap.locationBlocksTexture;
     }

     protected ResourceLocation getEntityTexture(Entity par1Entity) {
        return this.func_110808_a((ModEntityTNTPrimed)par1Entity);
     }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void func_76986_a(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity par1Entity, double x, double y, double z, float par5, float par6)
    {
    	this.doRender((ModEntityTNTPrimed)par1Entity, x, y, z, par5, par6);
    }
}