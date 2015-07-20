package com.lothrazar.powerinventory.inventory.client;

import com.lothrazar.powerinventory.proxy.DepositButtonPacket;
import com.lothrazar.powerinventory.ModInv;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
/** 
 * @author Lothrazar at https://github.com/PrinceOfAmber
 */
public class GuiButtonDepositAll extends GuiButton 
{
    public GuiButtonDepositAll(int buttonId, int x, int y, int w,int h)
    {
    	super(buttonId, x, y, w,h, "D");
    }

    @SideOnly(Side.CLIENT)
    @Override
    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY)
    {
    	boolean pressed = super.mousePressed(mc, mouseX, mouseY);
    	
    	if(pressed)
    	{
    		ModInv.instance.network.sendToServer(new DepositButtonPacket(new NBTTagCompound()));
    	}
    	
    	return pressed;
    }
}

