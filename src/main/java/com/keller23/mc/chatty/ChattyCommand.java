package com.keller23.mc.chatty;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

import java.util.ArrayList;
import java.util.List;

public class ChattyCommand implements ICommand {

    private List aliases;
    public ChattyCommand() {
        this.aliases = new ArrayList();
        this.aliases.add(Refs.MODID);
        //this.aliases.add("chatty");
        this.aliases.add("chm");
    }

    @Override
    public String getCommandName() {
        return Refs.MODID;
    }

    @Override
    public String getCommandUsage(ICommandSender p_71518_1_) {
        return "/chm hello - Say hello!\n" +
                "/chm <no other arguments currently>";
    }

    @Override
    public List getCommandAliases() {
        return this.aliases;
    }

    @Override
    public void processCommand(ICommandSender p_71515_1_, String[] p_71515_2_) {
        if(p_71515_2_.length > 0) {
            if(p_71515_2_[0].equals("hello")) {
                p_71515_1_.addChatMessage(new ChatComponentText(
                        "Hello, " +
                                p_71515_1_.getCommandSenderName() + "!"
                ));
                System.out.print("Player '" + p_71515_1_.getCommandSenderName() + "' ran CHM with arguments: ");
                for (int i = 0; i < p_71515_2_.length; i++) {
                    System.out.print(p_71515_2_[i]);
                }
                System.out.println();
            } else if(p_71515_2_[0].equals("whoami")) {
                p_71515_1_.addChatMessage(new ChatComponentText("You are: " + p_71515_1_.getCommandSenderName() + "."));
            }
        } else {
            p_71515_1_.addChatMessage(new ChatComponentText("You didn't tell me what to do..."));
        }
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender p_71519_1_) {
        return true;
    }

    @Override
    public List addTabCompletionOptions(ICommandSender p_71516_1_, String[] p_71516_2_) {
        return null;
    }

    @Override
    public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_) {
        return false;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
