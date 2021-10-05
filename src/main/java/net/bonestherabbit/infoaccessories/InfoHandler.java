package net.bonestherabbit.infoaccessories;

import java.util.ArrayList;
import java.util.List;

import net.bonestherabbit.infoaccessories.addons.baubles.BaublesCapabilityHandler;
import net.bonestherabbit.infoaccessories.info.InfoType;
import net.bonestherabbit.infoaccessories.items.ItemInfoAccessory;
import net.bonestherabbit.infoaccessories.ConfigurationHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class InfoHandler {

    @SubscribeEvent(priority = EventPriority.LOWEST)
    @SideOnly(Side.CLIENT)
    public void onDebugScreen (RenderGameOverlayEvent.Text event) {

        final Minecraft mc = Minecraft.getMinecraft();
        final EntityPlayer player = mc.player;
        final List<String> left = event.getLeft();
        final List<String> right = event.getRight();

        final boolean show = ConfigurationHandler.invertF3 ? mc.gameSettings.showDebugInfo : !mc.gameSettings.showDebugInfo;

        // Exit if not debug mode
        if (show) {

            return;
        }

        // Removes existing info
        // TODO add config to keep entries.
        left.clear();
        right.clear();

        // Adds info from the onfo types
        for (final InfoType type : InfoType.values()) {

            if (type.canPlayerSee(player)) {

                type.getInfo(player.world, player, left, right);
            }
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onTooltipEvent (ItemTooltipEvent event) {

        if (event.getEntityPlayer() == null) {

            return;
        }

        // Adds info from the info types
        for (final InfoType type : InfoType.values()) {

            if (type.isValidItem(event.getItemStack())) {

                // If item is the compass and coordinates are disabled, don't add the inventory needed tooltip
                if ((type.isCompass() == true) && (ConfigurationHandler.compassCoords == false)) {

                    break;
                }

                if (!type.canPlayerSee(event.getEntityPlayer())) {

                    event.getToolTip().add(TextFormatting.RED + I18n.format("tooltip.infoaccessories.lack.item"));
                    break;
                }

                else {

                    type.getInfo(event.getEntityPlayer().world, event.getEntityPlayer(), event.getToolTip(), event.getFlags().isAdvanced() ? event.getToolTip() : new ArrayList<>());
                }
            }
        }
    }

    @SubscribeEvent
    @Optional.Method(modid = "baubles")
    public void onItemCapability (AttachCapabilitiesEvent<ItemStack> event) {

        if (event.getObject().getItem() instanceof ItemInfoAccessory) {

            event.addCapability(new ResourceLocation("infoaccessories", "baubles_charm"), BaublesCapabilityHandler.INSTANCE);
        }
    }
}
