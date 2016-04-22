package com.keller23.mc.chatty;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.world.BlockEvent;

public class ChatHandler {
    @SubscribeEvent
    public void sendMessage(BlockEvent.BreakEvent event){
        event.getPlayer().addChatComponentMessage(new ChatComponentText(
                EnumChatFormatting.GOLD +
                        "You broke a block: '" +
                        event.block.getLocalizedName() + "'."
        ));
    }

    /*@SubscribeEvent
    public void */

}
