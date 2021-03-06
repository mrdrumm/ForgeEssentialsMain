package com.forgeessentials.auth.lists;

import com.forgeessentials.api.APIRegistry;
import com.forgeessentials.auth.AuthEventHandler;
import com.forgeessentials.core.commands.ForgeEssentialsCommandBase;
import com.forgeessentials.util.OutputHandler;
import com.forgeessentials.util.UserIdent;
import net.minecraft.command.ICommandSender;
import net.minecraftforge.permissions.PermissionsManager.RegisteredPermValue;

public class CommandWhiteList extends ForgeEssentialsCommandBase {

	@Override
	public String getCommandName()
	{

		return "whitelist";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args)
	{
		if (!AuthEventHandler.whitelist)
		{
			OutputHandler.chatWarning(sender, "The whitelist is not enabled. You can enable it in server.properties or your auth config file.");
			OutputHandler.chatWarning(sender, "Note that server.properties will take precedent over the auth config.");

		}

		else if (args.length == 1 && args[0].equalsIgnoreCase("toggle"))
		{
			if (AuthEventHandler.whitelist)
			{
				AuthEventHandler.whitelist = false;
				OutputHandler.chatConfirmation(sender, "FE Whitelist was on, it is now turned off.");
			}
			else
			{
				AuthEventHandler.whitelist = true;
				OutputHandler.chatConfirmation(sender, "FE Whitelist was off, it is now turned on.");
			}
		}

		else if (args.length == 2)
		{
			if (args[0].equalsIgnoreCase("add"))
			{
				APIRegistry.perms.getServerZone().setPlayerPermission(new UserIdent(args[1]), "fe.auth.whitelist", true);
			}
			else if (args[0].equalsIgnoreCase("remove"))
			{
				APIRegistry.perms.getServerZone().setPlayerPermission(new UserIdent(args[1]), "fe.auth.whitelist", false);
			}
		}
	}

	@Override
	public boolean canConsoleUseCommand()
	{

		return true;
	}

	@Override
	public String getPermissionNode()
	{
		return "fe.auth.whitelist.admin";
	}

	@Override
	public String getCommandUsage(ICommandSender sender)
	{

		return "/whitelist ";
	}

	@Override
	public RegisteredPermValue getDefaultPermission()
	{

		return RegisteredPermValue.OP;
	}

}
