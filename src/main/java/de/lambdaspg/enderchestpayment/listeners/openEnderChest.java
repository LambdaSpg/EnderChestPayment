package de.lambdaspg.enderchestpayment.listeners;

import de.lambdaspg.enderchestpayment.EnderChestPayment;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class openEnderChest implements Listener {
    EnderChestPayment plugin;

    public openEnderChest(EnderChestPayment plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onOpen(PlayerInteractEvent e){
        Economy econ = EnderChestPayment.getEconomy();
        Player p = e.getPlayer();
        World world = p.getWorld();
        Block clickedBlock = e.getClickedBlock();

        if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            if(e.getClickedBlock().getType().equals(Material.ENDER_CHEST)){
                Block loc = world.getBlockAt(e.getClickedBlock().getX(), clickedBlock.getY() - 1, clickedBlock.getZ());
                if(loc.getType().equals(Material.NETHERITE_BLOCK)) {
                    if (econ.getBalance(e.getPlayer()) >= plugin.getConfig().getDouble("Enderchest.cost")) {
                        econ.withdrawPlayer(e.getPlayer(), plugin.getConfig().getDouble("Enderchest.cost"));
                        p.sendMessage(ChatColor.AQUA + "$" + String.valueOf(plugin.getConfig().getDouble("Enderchest.cost")) + " have been withdrawn from your bank account");
                    } else {
                        p.sendMessage(ChatColor.RED + "You do not have enough money to open your Enderchest");
                        e.setCancelled(true);
                    }
                }
            }
        }
    }

}
