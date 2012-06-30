package com.sk89q.craftbook.util;

import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

public class ItemUtil {

    public static boolean areItemsSimilar(ItemStack item, ItemStack item2) {
	if(item.getTypeId() == item2.getTypeId()) return true;
	return false;
    }

    public static boolean areItemsIdentical(ItemStack item, ItemStack item2) {
	if(item.getTypeId() == item2.getTypeId()) {
	    if(item.getData() == item2.getData()) {
		return true;
	    }
	}
	return false;
    }

    public static boolean isItemSimilarTo(ItemStack item, int type) {
	if(item.getTypeId() == type) return true;
	return false;
    }

    public static boolean isItemIdenticalTo(ItemStack item, int type, byte data) {
	if(item.getTypeId() == type) {
	    if(item.getData().getData() == (byte) data) {
		return true;
	    }
	}
	return false;
    }
    
    public static void setItemTypeAndData(ItemStack item, int type, byte data) {
	item.setTypeId(type);
	item.setData(new MaterialData(type,data));
    }
}
