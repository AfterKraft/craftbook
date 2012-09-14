package com.sk89q.craftbook.mech;

import java.util.HashMap;

import org.bukkit.Art;
import org.bukkit.entity.Painting;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;

import com.sk89q.craftbook.LocalPlayer;
import com.sk89q.craftbook.bukkit.MechanismsPlugin;

/**
 * @author Me4502
 */
public class PaintingSwitch implements Listener {

    MechanismsPlugin plugin;
    HashMap<Painting, String> paintings = new HashMap<Painting, String>();
    HashMap<String, Painting> players = new HashMap<String, Painting>();

    public PaintingSwitch(MechanismsPlugin plugin) {

        this.plugin = plugin;
    }

    public boolean isBeingEdited(Painting paint) {
        if(paintings.get(paint) != null && players.get(paintings.get(paint)) != null) {
            Player p = plugin.getServer().getPlayer(paintings.get(paint));
            if(p == null || p.isDead()) return false;
            return true;
        }
        return false;
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {

        if (event.getRightClicked() instanceof Painting) {
            LocalPlayer player = plugin.wrap(event.getPlayer());
            if (!plugin.getLocalConfiguration().paintingSettings.enabled) return;
            Painting paint = (Painting) event.getRightClicked();
            if(!plugin.canUseInArea(paint.getLocation(), event.getPlayer()))
                return;
            if (player.hasPermission("craftbook.mech.paintingswitch.use")) {
                if (!isBeingEdited(paint)) {
                    paintings.put(paint, player.getName());
                    players.put(player.getName(), paint);
                    player.print("You are now editing the painting!");
                } else if (paintings.get(paint).equalsIgnoreCase(player.getName())) {
                    paintings.remove(paint);
                    players.remove(player.getName());
                    player.print("You are no longer editing the painting!");
                } else if (isBeingEdited(paint))
                    player.print("The painting is already being edited by " + paintings.get(paint) + "!");
                else
                    return;
                event.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onHeldItemChange(PlayerItemHeldEvent event) {

        if (!plugin.getLocalConfiguration().paintingSettings.enabled) return;
        LocalPlayer player = plugin.wrap(event.getPlayer());
        if (!player.hasPermission("craftbook.mech.paintingswitch.use")) return;
        if (players.get(player.getName()) == null) {
            try {
                paintings.remove(players.get(event.getPlayer().getName()));
                players.remove(event.getPlayer().getName());
            } catch (Exception ignored) {
            }
            return;
        }
        boolean isForwards;
        if (event.getNewSlot() > event.getPreviousSlot()) isForwards = true;
        else if (event.getNewSlot() < event.getPreviousSlot()) isForwards = false;
        else return;
        if (event.getPreviousSlot() == 0 && event.getNewSlot() == 8) isForwards = false;
        else if (event.getPreviousSlot() == 8 && event.getNewSlot() == 0) isForwards = true;
        Art[] art = Art.values();
        Painting paint = players.get(event.getPlayer().getName());
        int newID = paint.getArt().getId() + (isForwards ? -1 : 1);
        if (newID < 0) newID = art.length - 1;
        if (newID >= art.length) newID = 0;
        paint.setArt(art[newID]);
    }
}