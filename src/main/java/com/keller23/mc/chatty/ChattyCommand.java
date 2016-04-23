package com.keller23.mc.chatty;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class ChattyCommand implements ICommand {

    private List aliases;
    ChattyCommand() {
        this.aliases = new ArrayList();
        this.aliases.add(Refs.MODID);
        this.aliases.add("chm");
    }

    @Override
    public String getCommandName() {
        return this.aliases.get(0).toString();
    }

    @Override
    public String getCommandUsage(ICommandSender p_71518_1_) {
        return Refs.CMD + " help"
        /*return Refs.CMD + " hello - Say hello!" +
                Refs.CMD + " <no other arguments currently>"*/;
    }

    @Override
    public List getCommandAliases() {
        return this.aliases;
    }

    @Override
    public void processCommand(ICommandSender p_71515_1_, String[] p_71515_2_) {
        World world = p_71515_1_.getEntityWorld();
        if(world.isRemote) {
            if(Refs.DEBUG.getBoolean()) {
                System.out.println("Not processing command on Client.");
            }
        } else {
            if(Refs.DEBUG.getBoolean()) {
                System.out.println("Processing command on Server.");
            }

            if(p_71515_2_.length > 0) {
                if(p_71515_2_[0].equals("echo")) {
                    if(p_71515_2_.length > 1) {
                        if (p_71515_2_[1].equals("hello")) {
                            p_71515_1_.addChatMessage(new ChatComponentText("Hello, " + p_71515_1_.getCommandSenderName() + "!"));

                            for (int i = 0; i < p_71515_2_.length; i++) {
                                System.out.print(p_71515_2_[i]);
                            }
                            System.out.println();
                        } else if (p_71515_2_[1].equals("whoami")) {
                            p_71515_1_.addChatMessage(new ChatComponentText("You are: " + p_71515_1_.getCommandSenderName() + "."));
                        }
                    } else {
                        p_71515_1_.addChatMessage(new ChatComponentText("Not enough arguments"));
                        p_71515_1_.addChatMessage(new ChatComponentText(Refs.CMD + " hello - Say hello!"));
                        p_71515_1_.addChatMessage(new ChatComponentText(Refs.CMD + " whoami - Echo your name"));
                    }
                }
                // Help
                else if(p_71515_2_[0].equals("help")) {
                    p_71515_1_.addChatMessage(new ChatComponentText(Refs.CMD + " help - lmgty"));
                    p_71515_1_.addChatMessage(new ChatComponentText(Refs.CMD + " config - config"));
                    p_71515_1_.addChatMessage(new ChatComponentText(Refs.CMD + " echo - Random echos, maybe useful for T/S"));
                }
                // Config
                else if(p_71515_2_[0].equals("config")) {
                    if(p_71515_2_.length > 1) {
                        if(p_71515_2_[1].equals("help")) {
                            p_71515_1_.addChatMessage(new ChatComponentText(Refs.CMD + " config help - This"));
                            p_71515_1_.addChatMessage(new ChatComponentText(Refs.CMD + " config debug - Debug Logging WIP"));
                            p_71515_1_.addChatMessage(new ChatComponentText(Refs.CMD + " config breakmsg - En/Disable Block broken messages"));
                        } else if(p_71515_2_[1].equals("breakmsg")) {
                            if(p_71515_2_.length > 2) {
                                if(p_71515_2_[2].equals("enable")) {
                                    // enable block break messages
                                } else {
                                    //disable block break messages
                                }
                            } else p_71515_1_.addChatMessage(new ChatComponentText("Not enough arguments"));
                        }
                        else {
                            p_71515_1_.addChatMessage(new ChatComponentText("Unknown argument. Use:"));
                            p_71515_1_.addChatMessage(new ChatComponentText(Refs.CMD + " config help"));
                        }
                    } else {
                        p_71515_1_.addChatMessage(new ChatComponentText(Refs.CMD + " config help"));
                    }

                }
            } else {
                p_71515_1_.addChatMessage(new ChatComponentText("Not enough arguments"));
                p_71515_1_.addChatMessage(new ChatComponentText(Refs.CMD + " help"));
            }
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
