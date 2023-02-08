
package javakun;

import net.minecraftforge.common.config.*;
import cpw.mods.fml.common.event.*;
import net.minecraftforge.common.*;
import net.minecraftforge.client.event.*;
import net.minecraft.util.*;
import cpw.mods.fml.common.*;
import net.minecraft.client.gui.*;
import java.util.*;
import cpw.mods.fml.common.eventhandler.*;

@Mod(modid = "bangui", name = "bangui", version = "0.0.1")
public class BanGuiMod
{
    public static final String modName = "bangui";
    public static final String version = "0.0.1";
    public static final String modId = "bangui";
    public static String forumLink;
    public static String buyUnbanLink;
    public static List<String> reasonKeywords;
    public static Configuration config;
    
    @Mod.EventHandler
    public void preInit(final FMLPreInitializationEvent event) {
        BanGuiMod.config = new Configuration(event.getSuggestedConfigurationFile());
        BanGuiMod.forumLink = BanGuiMod.config.getString("forumLink", "general", "LINK", "\u0421\u0441\u044b\u043b\u043a\u0430 \u043d\u0430 \u0440\u0430\u0437\u0434\u0435\u043b \u0444\u043e\u0440\u0443\u043c\u0430 \u0441 \u043e\u0431\u0436\u0430\u043b\u043e\u0432\u0430\u043d\u0438\u0435\u043c \u0431\u0430\u043d\u0430");
        BanGuiMod.buyUnbanLink = BanGuiMod.config.getString("buyUnbanLink", "general", "LINK", "\u0421\u0441\u044b\u043b\u043a\u0430 \u043d\u0430 \u043f\u043e\u043a\u0443\u043f\u043a\u0443 \u0440\u0430\u0437\u0431\u0430\u043d\u0430");
        BanGuiMod.reasonKeywords = Arrays.asList(BanGuiMod.config.getStringList("reasonKeywords", "general", new String[] { "\u0437\u0430\u0431\u043b\u043e\u043a\u0438\u0440\u043e\u0432\u0430\u043d\u044b", "\u0437\u0430\u0431\u043b\u043e\u043a\u0438\u0440\u043e\u0432\u0430\u043d", "\u0437\u0430\u0431\u0430\u043d\u0435\u043d", "\u0437\u0430\u0431\u0430\u043d\u0435\u043d\u044b", "banned" }, "\u0421\u043b\u043e\u0432\u0430-\u0442\u0440\u0438\u0433\u0433\u0435\u0440\u044b \u0434\u043b\u044f \u043e\u0442\u043e\u0431\u0440\u0430\u0436\u0435\u043d\u0438\u044f \u043c\u0435\u043d\u044e \u0440\u0430\u0437\u0431\u0430\u043d\u0430"));
        if (BanGuiMod.config.hasChanged()) {
            BanGuiMod.config.save();
        }
    }
    
    @Mod.EventHandler
    public void init(final FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register((Object)new BanGuiMod());
    }
    
    @SubscribeEvent
    public void guiOpenEvent(final GuiOpenEvent event) {
        final GuiScreen gui = event.gui;
        if (gui instanceof GuiDisconnected) {
            final GuiDisconnected disconnectGui = (GuiDisconnected)gui;
            final IChatComponent message = (IChatComponent)ObfuscationReflectionHelper.getPrivateValue((Class)GuiDisconnected.class, (Object)disconnectGui, 1);
            final String reason = (String)ObfuscationReflectionHelper.getPrivateValue((Class)GuiDisconnected.class, (Object)disconnectGui, 0);
            final String lower = message.getUnformattedText().toLowerCase();
            for (final String keyword : BanGuiMod.reasonKeywords) {
                if (lower.contains(keyword)) {
                    event.gui = new BanGuiScreen(reason, message, BanGuiMod.forumLink, BanGuiMod.buyUnbanLink);
                    break;
                }
            }
        }
    }
}
