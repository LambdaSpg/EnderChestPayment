package de.lambdaspg.enderchestpayment.listeners;

import de.lambdaspg.enderchestpayment.EnderChestPayment;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.Material;
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

        if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            if(e.getClickedBlock().getType().equals(Material.ENDER_CHEST)){
                if(econ.getBalance(e.getPlayer()) >= plugin.getConfig().getDouble("Enderchest.cost")){
                    econ.withdrawPlayer(e.getPlayer(), plugin.getConfig().getDouble("Enderchest.cost"));
                    p.sendMessage(ChatColor.AQUA + "$100 have been withdrawn from your bank account");
                }else {
                    p.sendMessage(ChatColor.RED + "You do not have enough money to open your Enderchest");
                    e.setCancelled(true);
                }
            }
        }
    }

}
