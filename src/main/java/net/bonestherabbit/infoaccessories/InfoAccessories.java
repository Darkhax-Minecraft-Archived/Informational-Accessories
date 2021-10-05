package net.bonestherabbit.infoaccessories;

import net.darkhax.bookshelf.lib.LoggingHelper;
import net.darkhax.bookshelf.registry.RegistryHelper;
import net.bonestherabbit.infoaccessories.items.ItemInfoAccessory;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLFingerprintViolationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "infoaccessories", name = "Info Accessories", version = "@VERSION@", dependencies = "required-after:bookshelf@[2.2.462,);")
public class InfoAccessories {

    public static final LoggingHelper LOG = new LoggingHelper("Info Accessories");
    public static final RegistryHelper REGISTRY = new RegistryHelper("infoaccessories").enableAutoRegistration().setTab(CreativeTabs.MISC);
    public static Item infoItem;

    @EventHandler
    public void preInit (FMLPreInitializationEvent event) {

        new ConfigurationHandler(event.getSuggestedConfigurationFile());
        MinecraftForge.EVENT_BUS.register(new InfoHandler());
        infoItem = REGISTRY.registerItem(new ItemInfoAccessory(), "info_accessory");
    }
}