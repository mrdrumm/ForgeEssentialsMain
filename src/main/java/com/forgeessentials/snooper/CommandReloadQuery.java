package com.forgeessentials.snooper;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.permissions.PermissionsManager.RegisteredPermValue;

import com.forgeessentials.core.commands.ForgeEssentialsCommandBase;
import com.forgeessentials.util.OutputHandler;

public class CommandReloadQuery extends ForgeEssentialsCommandBase {

    @Override
    public String getCommandName()
    {
        return "queryreload";
    }

    @Override
    public void processCommandPlayer(EntityPlayerMP sender, String[] args)
    {
        reload(sender);
    }

    @Override
    public void processCommandConsole(ICommandSender sender, String[] args)
    {
        reload(sender);
    }

    public void reload(ICommandSender sender)
    {
        OutputHandler.chatNotification(sender, "Killing old one....");
        ModuleSnooper.stop();
        OutputHandler.chatNotification(sender, "Making new one....");
        ModuleSnooper.start();
    }

    @Override
    public boolean canConsoleUseCommand()
    {
        return true;
    }

    @Override
    public String getPermissionNode()
    {
        return "fe.snooper" + getCommandName();
    }

    @Override
    public String getCommandUsage(ICommandSender sender)
    {

        return "/queryreload Reload queries from the SQL database";
    }

    @Override
    public RegisteredPermValue getDefaultPermission()
    {

        return RegisteredPermValue.OP;
    }

}
