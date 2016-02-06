package io.github.dre2n.factionslwcbridge;

import io.github.dre2n.factionslwcbridge.listener.LWCListener;

import com.griefcraft.lwc.LWC;
import com.massivecraft.factions.Patch;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class FactionsLWCBridge extends JavaPlugin {
	
	private static FactionsLWCBridge plugin;
	private LWC lwc;
	
	@Override
	public void onEnable() {
		try {
			lwc = LWC.getInstance();
			PluginDescriptionFile desc = lwc.getPlugin().getDescription();
			getLogger().info("Successfully hooked into " + Patch.getFullName() + " and " + desc.getName() + " v" + desc.getVersion() + ".");
			
			plugin = this;
			
		} catch (Exception exception) {
			getLogger().info("Could not find dependencies!");
		}
		
		lwc.getModuleLoader().registerModule(this, new LWCListener());
	}
	
	@Override
	public void onDisable() {
		plugin = null;
	}
	
	/**
	 * @return the plugin instance
	 */
	public static FactionsLWCBridge getPlugin() {
		return plugin;
	}
	
	/**
	 * @return the LWC instance
	 */
	public LWC getLWC() {
		return lwc;
	}
	
}
