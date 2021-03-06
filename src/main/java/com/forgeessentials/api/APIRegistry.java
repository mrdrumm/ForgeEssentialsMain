package com.forgeessentials.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;

import com.forgeessentials.api.permissions.IPermissionsHelper;
import com.forgeessentials.api.snooper.Response;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.eventhandler.EventBus;

/**
 * This is the central access point for all FE API functions
 *
 * @author luacs1998
 */
public class APIRegistry {

    // Use this to call API functions available in the economy module.
    public static IEconManager wallet;

    // Use to call API functions from the permissions module.
    public static IPermissionsHelper perms;

    public static NamedWorldHandler namedWorldHandler = new DefaultNamedWorldHandler();

    private static Method ResponseRegistry_regsisterResponce;

    private static final EventBus FE_EVENTBUS = new EventBus();

    /**
     * Snooper method to register your responses.
     *
     * @param ID
     * @param response
     */
    public static void registerResponse(Integer ID, Response response)
    {
        try
        {
            if (ResponseRegistry_regsisterResponce == null)
            {
                ResponseRegistry_regsisterResponce = Class.forName("com.forgeessentials.snooper.ResponseRegistry").getMethod("registerResponse", Integer.class,
                        Response.class);
            }
            ResponseRegistry_regsisterResponce.invoke(null, ID, response);
        }
        catch (Exception e)
        {
            FMLLog.warning("[FE API] Unable to register " + response.getName() + " with ID: " + ID);
            e.printStackTrace();
        }
    }

    public static EventBus getFEEventBus()
    {
        return FE_EVENTBUS;
    }

    /**
     * Use this annotation to mark classes where static methods with other FE
     * annotations might be.
     *
     * @author AbrarSyed
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ ElementType.TYPE })
    public @interface ForgeEssentialsRegistrar {
        String ident();
    }

    public static interface NamedWorldHandler {
        
        static final String WORLD_NAME_END = "end";
        static final String WORLD_NAME_NETHER = "nether";
        static final String WORLD_NAME_SURFACE = "surface";
        
        WorldServer getWorld(String name);

        String getWorldName(int dimId);
        
    }

    public static class DefaultNamedWorldHandler implements NamedWorldHandler {

        @Override
        public WorldServer getWorld(String name)
        {
            switch (name)
            {
            case WORLD_NAME_SURFACE:
                return DimensionManager.getWorld(0);
            case WORLD_NAME_NETHER:
                return DimensionManager.getWorld(-1);
            case WORLD_NAME_END:
                return DimensionManager.getWorld(1);
            default:
            {
                try
                {
                    final int dimId = Integer.parseInt(name);
                    if (DimensionManager.isDimensionRegistered(dimId))
                    {
                        if (DimensionManager.getWorld(dimId) == null)
                            DimensionManager.initDimension(dimId);
                    }
                    return DimensionManager.getWorld(Integer.parseInt(name));
                }
                catch (NumberFormatException e)
                {
                    return null;
                }
            }
            }
        }

        @Override
        public String getWorldName(int dimId)
        {
            switch (dimId)
            {
            case 0:
                return WORLD_NAME_SURFACE;
            case -1:
                return WORLD_NAME_NETHER;
            case 1:
                return WORLD_NAME_END;
            default:
                return Integer.toString(dimId);
            }
        }
        
    }

}
