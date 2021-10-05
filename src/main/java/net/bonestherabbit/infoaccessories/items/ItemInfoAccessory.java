package net.bonestherabbit.infoaccessories.items;

import java.awt.Color;

import net.darkhax.bookshelf.item.IColorfulItem;
import net.darkhax.bookshelf.item.ItemSubType;
import net.darkhax.bookshelf.util.PlayerUtils;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemInfoAccessory extends ItemSubType implements IColorfulItem {

    private final static String[] types = { "stopwatch", "sextant", "depth_meter", "debugger", "slimey_compass", "calendar", "biomealyzer", "chunker", "dps_monitor", "structure" };

    public ItemInfoAccessory () {

        super(types);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IItemColor getColorHandler () {

        return new ColorHandler();
    }

    @SideOnly(Side.CLIENT)
    private static class ColorHandler implements IItemColor {

        @Override
        public int colorMultiplier (ItemStack stack, int tintIndex) {

            final EntityPlayer player = PlayerUtils.getClientPlayer();
            final World world = player.world;
            final BlockPos pos = player.getPosition();
            final int meta = stack.getMetadata();
            final Biome biome = world.getBiome(pos);

            // Biome Color
            if (meta == 6 && tintIndex > 0) {

                if (tintIndex == 1) {

                    return BiomeColorHelper.getFoliageColorAtPos(world, pos);
                }

                else if (tintIndex == 2) {

                    return BiomeColorHelper.getWaterColorAtPos(world, pos);
                }

                else if (tintIndex == 3) {

                    return BiomeColorHelper.getGrassColorAtPos(world, pos);
                }

                else if (tintIndex == 4) {

                    return biome.getSkyColorByTemp(biome.getTemperature(pos));
                }
            }

            return Color.WHITE.getRGB();
        }
    }
}