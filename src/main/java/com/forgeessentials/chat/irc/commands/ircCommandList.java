package com.forgeessentials.chat.irc.commands;

import net.minecraft.server.MinecraftServer;

import org.pircbotx.User;

public class ircCommandList extends ircCommand {

    @Override
    public String[] getAliases()
    {
        return new String[] { "online", "who", "players" };
    }

    @Override
    public String getCommandInfo()
    {
        return "Lists all the players on the server";
    }

    @Override
    public String getCommandUsage()
    {
        return "%list";
    }

    @Override
    public void execute(String[] args, User user)
    {
        user.sendMessage("Players online: ");
        for (String username : MinecraftServer.getServer().getConfigurationManager().getAllUsernames())
        {
            user.sendMessage(username);
        }
    }

}
