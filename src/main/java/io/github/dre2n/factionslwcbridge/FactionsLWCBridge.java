/*
 * Copyright (C) 2016 Daniel Saukel
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.dre2n.factionslwcbridge;

import com.griefcraft.lwc.LWC;
import com.massivecraft.factions.Patch;
import io.github.dre2n.factionslwcbridge.listener.LWCListener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Daniel Saukel
 */
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
