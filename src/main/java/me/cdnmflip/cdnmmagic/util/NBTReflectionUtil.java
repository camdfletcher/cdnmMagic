package me.cdnmflip.cdnmmagic.util;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

/**
 * @author codenameflip
 * @since 12/8/17
 */
public class NBTReflectionUtil {

    private static Class getCraftItemstack()
    {
        String version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];

        try
        {
            Class c = Class.forName("org.bukkit.craftbukkit." + version + ".inventory.CraftItemStack");
            return c;
        }
        catch (Exception ex)
        {
            System.out.println("Error in ItemNBTAPI! (Outdated plugin?)");
            ex.printStackTrace();

            return null;
        }
    }

    private static Object getnewNBTTag()
    {
        String version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];

        try
        {
            Class c = Class.forName("net.minecraft.server." + version + ".NBTTagCompound");
            return c.newInstance();
        }
        catch (Exception ex)
        {
            System.out.println("Error in ItemNBTAPI! (Outdated plugin?)");
            ex.printStackTrace();

            return null;
        }
    }

    private static Object setNBTTag(Object nbtTag, Object nmsItem)
    {
        try
        {
            java.lang.reflect.Method method;
            method = nmsItem.getClass().getMethod("setTag", nbtTag.getClass());
            method.invoke(nmsItem, nbtTag);

            return nmsItem;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return null;
    }

    private static Object getNMSItemStack(ItemStack item)
    {
        Class cis = getCraftItemstack();
        java.lang.reflect.Method method;

        try
        {
            method = cis.getMethod("asNMSCopy", ItemStack.class);
            Object answer = method.invoke(cis, item);

            return answer;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    private static ItemStack getBukkitItemStack(Object item)
    {
        Class cis = getCraftItemstack();
        java.lang.reflect.Method method;

        try
        {
            method = cis.getMethod("asBukkitCopy", item.getClass());
            Object answer = method.invoke(cis, item);

            return (ItemStack) answer;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    private static Object getNBTTagCompound(Object nmsitem)
    {
        Class c = nmsitem.getClass();
        java.lang.reflect.Method method;

        try
        {
            method = c.getMethod("getTag");
            Object answer = method.invoke(nmsitem);

            return answer;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }


    public static ItemStack setString(ItemStack item, String key, String Text)
    {
        Object nmsitem = getNMSItemStack(item);

        if (nmsitem == null)
        {
            System.out.println("Got null! (Outdated Plugin?)");

            return null;
        }

        Object nbttag = getNBTTagCompound(nmsitem);

        if (nbttag == null)
        {
            nbttag = getnewNBTTag();
        }

        java.lang.reflect.Method method;

        try
        {
            method = nbttag.getClass().getMethod("setString", String.class, String.class);
            method.invoke(nbttag, key, Text);

            nmsitem = setNBTTag(nbttag, nmsitem);

            return getBukkitItemStack(nmsitem);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return item;
    }

    public static String getString(ItemStack item, String key)
    {
        Object nmsitem = getNMSItemStack(item);

        if (nmsitem == null)
        {
            System.out.println("Got null! (Outdated Plugin?)");

            return null;
        }

        Object nbttag = getNBTTagCompound(nmsitem);

        if (nbttag == null)
        {
            nbttag = getnewNBTTag();
        }

        java.lang.reflect.Method method;

        try
        {
            method = nbttag.getClass().getMethod("getString", String.class);

            return (String) method.invoke(nbttag, key);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return null;
    }

    public static ItemStack setInt(ItemStack item, String key, Integer i)
    {
        Object nmsitem = getNMSItemStack(item);

        if (nmsitem == null)
        {
            System.out.println("Got null! (Outdated Plugin?)");

            return null;
        }

        Object nbttag = getNBTTagCompound(nmsitem);

        if (nbttag == null)
        {
            nbttag = getnewNBTTag();
        }

        java.lang.reflect.Method method;

        try
        {
            method = nbttag.getClass().getMethod("setInt", String.class, int.class);
            method.invoke(nbttag, key, i);

            nmsitem = setNBTTag(nbttag, nmsitem);

            return getBukkitItemStack(nmsitem);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return item;
    }

    public static Integer getInt(ItemStack item, String key)
    {
        Object nmsitem = getNMSItemStack(item);

        if (nmsitem == null)
        {
            System.out.println("Got null! (Outdated Plugin?)");

            return null;
        }

        Object nbttag = getNBTTagCompound(nmsitem);

        if (nbttag == null)
        {
            nbttag = getnewNBTTag();
        }

        java.lang.reflect.Method method;

        try
        {
            method = nbttag.getClass().getMethod("getInt", String.class);

            return (Integer) method.invoke(nbttag, key);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return null;
    }

    public static ItemStack setDouble(ItemStack item, String key, Double d)
    {
        Object nmsitem = getNMSItemStack(item);

        if (nmsitem == null)
        {
            System.out.println("Got null! (Outdated Plugin?)");

            return null;
        }

        Object nbttag = getNBTTagCompound(nmsitem);

        if (nbttag == null)
        {
            nbttag = getnewNBTTag();
        }

        java.lang.reflect.Method method;

        try
        {
            method = nbttag.getClass().getMethod("setDouble", String.class, double.class);
            method.invoke(nbttag, key, d);

            nmsitem = setNBTTag(nbttag, nmsitem);

            return getBukkitItemStack(nmsitem);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return item;
    }

    public static Double getDouble(ItemStack item, String key)
    {
        Object nmsitem = getNMSItemStack(item);

        if (nmsitem == null)
        {
            System.out.println("Got null! (Outdated Plugin?)");

            return null;
        }
        Object nbttag = getNBTTagCompound(nmsitem);

        if (nbttag == null)
        {
            nbttag = getnewNBTTag();
        }

        java.lang.reflect.Method method;

        try
        {
            method = nbttag.getClass().getMethod("getDouble", String.class);

            return (Double) method.invoke(nbttag, key);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return null;
    }

    public static ItemStack setBoolean(ItemStack item, String key, Boolean d)
    {
        Object nmsitem = getNMSItemStack(item);

        if (nmsitem == null)
        {
            System.out.println("Got null! (Outdated Plugin?)");

            return null;
        }

        Object nbttag = getNBTTagCompound(nmsitem);

        if (nbttag == null)
        {
            nbttag = getnewNBTTag();
        }

        java.lang.reflect.Method method;

        try
        {
            method = nbttag.getClass().getMethod("setBoolean", String.class, boolean.class);
            method.invoke(nbttag, key, d);

            nmsitem = setNBTTag(nbttag, nmsitem);

            return getBukkitItemStack(nmsitem);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return item;
    }

    public static Boolean getBoolean(ItemStack item, String key)
    {
        Object nmsitem = getNMSItemStack(item);

        if (nmsitem == null)
        {
            System.out.println("Got null! (Outdated Plugin?)");

            return null;
        }

        Object nbttag = getNBTTagCompound(nmsitem);

        if (nbttag == null)
        {
            nbttag = getnewNBTTag();
        }

        java.lang.reflect.Method method;

        try
        {
            method = nbttag.getClass().getMethod("getBoolean", String.class);

            return (Boolean) method.invoke(nbttag, key);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return null;
    }

    public static Boolean hasKey(ItemStack item, String key)
    {
        Object nmsitem = getNMSItemStack(item);

        if (nmsitem == null)
        {
            System.out.println("Got null! (Outdated Plugin?)");

            return null;
        }

        Object nbttag = getNBTTagCompound(nmsitem);

        if (nbttag == null)
        {
            nbttag = getnewNBTTag();
        }

        java.lang.reflect.Method method;

        try
        {
            method = nbttag.getClass().getMethod("hasKey", String.class);

            return (Boolean) method.invoke(nbttag, key);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return null;
    }

}
