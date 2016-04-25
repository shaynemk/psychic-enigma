package com.keller23.mc.chatty;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

import static com.keller23.mc.chatty.Refs.Log;

public class ChattyCommand implements ICommand {

    private List aliases;
    ChattyCommand() {
        this.aliases = new ArrayList();
        this.aliases.add("chatty");
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
                Log.debug("Not processing command on Client.");
            }
        } else {
            if(Refs.DEBUG.getBoolean()) {
                Log.debug("Processing command on Server.");
            }

            if(p_71515_2_.length > 0) {
                if(p_71515_2_[0].equals("echo")) {
                    if(p_71515_2_.length > 1) {
                        if (p_71515_2_[1].equals("hello")) {
                            p_71515_1_.addChatMessage(new ChatComponentText("Hello, " + p_71515_1_.getCommandSenderName() + "!"));

                            for (int i = 0; i < p_71515_2_.length; i++) {
                                System.out.print(p_71515_2_[i]);
                            }
                            //Log.info(");
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

    /**
     * Returns true if the given command sender is allowed to use this command.
     *
     * @param p_71519_1_
     */
    @Override
    public boolean canCommandSenderUseCommand(ICommandSender p_71519_1_) {
        return true;
    }

    /**
     * Adds the strings available in this command to the given list of tab completion options.
     *
     * @param p_71516_1_
     * @param p_71516_2_
     */
    @Override
    public List addTabCompletionOptions(ICommandSender p_71516_1_, String[] p_71516_2_) {
        return null;
    }

    /**
     * Return whether the specified command parameter index is a username parameter.
     *
     * @param p_82358_1_
     * @param p_82358_2_
     */
    @Override
    public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_) {
        return false;
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     * <p>
     * <p>The implementor must ensure <tt>sgn(x.compareTo(y)) ==
     * -sgn(y.compareTo(x))</tt> for all <tt>x</tt> and <tt>y</tt>.  (This
     * implies that <tt>x.compareTo(y)</tt> must throw an exception iff
     * <tt>y.compareTo(x)</tt> throws an exception.)
     * <p>
     * <p>The implementor must also ensure that the relation is transitive:
     * <tt>(x.compareTo(y)&gt;0 &amp;&amp; y.compareTo(z)&gt;0)</tt> implies
     * <tt>x.compareTo(z)&gt;0</tt>.
     * <p>
     * <p>Finally, the implementor must ensure that <tt>x.compareTo(y)==0</tt>
     * implies that <tt>sgn(x.compareTo(z)) == sgn(y.compareTo(z))</tt>, for
     * all <tt>z</tt>.
     * <p>
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * <tt>(x.compareTo(y)==0) == (x.equals(y))</tt>.  Generally speaking, any
     * class that implements the <tt>Comparable</tt> interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     * <p>
     * <p>In the foregoing description, the notation
     * <tt>sgn(</tt><i>expression</i><tt>)</tt> designates the mathematical
     * <i>signum</i> function, which is defined to return one of <tt>-1</tt>,
     * <tt>0</tt>, or <tt>1</tt> according to whether the value of
     * <i>expression</i> is negative, zero or positive.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
