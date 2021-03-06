package com.forgeessentials.api.permissions;

public final class FEPermissions {

	public static final String MSG_NO_COMMAND_PERM = "You don't have permissions to use this command!";
	public static final String MSG_NO_PERM = "You don't have permissions for that!";

    public static final String MSG_NO_PLAYER_COMMAND = "This command cannot be used as player";
    public static final String MSG_NO_CONSOLE_COMMAND = "This command cannot be used from console";
    public static final String MSG_NO_BLOCK_COMMAND = "This command cannot be used by command-blocks";
	
    public static final String MSG_NOT_ENOUGH_ARGUMENTS = "Not enough arguments!";
    public static final String MSG_INVALID_SYNTAX = "Invalid syntax!";

	// ------------------------------------------------
	// -- Internal permissions
	// ------------------------------------------------

	public static final String FE_INTERNAL = "fe.internal";
    public static final String DESCRIPTION_PROPERTY = ".$desc";

    public static final String ZONE = FE_INTERNAL + ".zone";
	public static final String ZONE_ENTRY_MESSAGE = ZONE + ".entry";
	public static final String ZONE_EXIT_MESSAGE = ZONE + ".exit";
    public static final String ZONE_HIDDEN = ZONE + ".hidden";

	public static final String PREFIX = FE_INTERNAL + ".prefix";
	public static final String SUFFIX = FE_INTERNAL + ".suffix";
    public static final String SPAWN = FE_INTERNAL + ".spawn";

	public static final String GROUP = FE_INTERNAL + ".group";
    public static final String GROUP_ID = GROUP + ".id";
	public static final String GROUP_PRIORITY = GROUP + ".priority";
    public static final String GROUP_INCLUDES = GROUP + ".includes";

	public static final int GROUP_PRIORITY_DEFAULT = 20;

	public static final String PLAYER = FE_INTERNAL + ".player";
	public static final String PLAYER_UUID = PLAYER + ".uuid";
	public static final String PLAYER_NAME = PLAYER + ".name";
	public static final String PLAYER_GROUPS = PLAYER + ".groups";

}