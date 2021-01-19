package de.lambdaspg.enderchestpayment;

import de.lambdaspg.enderchestpayment.listeners.openEnderChest;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class EnderChestPayment extends JavaPlugin {

    private static EnderChestPayment plugin;
    private static Economy econ = null;

    @Override
    public void onEnable() {
        plugin = this;
        if (!setupEconomy() ) {
            System.out.println("No economy plugin was found");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        getServer().getPluginManager().registerEvents(new openEnderChest(this),this);


        if(getConfig().get("Enderchest.cost") == null) {
            getConfig().set("Enderchest.cost", 100);
            saveConfig();
        }
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public static Economy getEconomy() {
        return econ;
    }

    public static EnderChestPayment getPlugin(){
        return plugin;
    }

}
