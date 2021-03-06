package com.sk89q.craftbook.mech.dispenser;

import com.sk89q.worldedit.blocks.BlockID;
import com.sk89q.worldedit.blocks.ItemID;
import org.bukkit.block.Dispenser;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

/**
 * @author Me4502
 */
public class SnowShooter extends Recipe {

    public SnowShooter(int[] recipe) {

        super(recipe);
    }

    public SnowShooter() {

        super(new int[] {
                BlockID.AIR, BlockID.SNOW_BLOCK, BlockID.AIR,
                BlockID.SNOW_BLOCK, ItemID.POTION, BlockID.SNOW_BLOCK,
                BlockID.AIR, BlockID.SNOW_BLOCK, BlockID.AIR
        });
    }

    @Override
    public boolean doAction(Dispenser dis, ItemStack item, Vector velocity, BlockDispenseEvent event) {

        event.setItem(new ItemStack(ItemID.SNOWBALL, 1));
        return true;
    }
}