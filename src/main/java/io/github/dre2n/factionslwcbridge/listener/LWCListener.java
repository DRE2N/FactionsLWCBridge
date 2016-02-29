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
package io.github.dre2n.factionslwcbridge.listener;

import com.griefcraft.lwc.LWC;
import com.griefcraft.scripting.JavaModule;
import com.griefcraft.scripting.event.LWCProtectionDestroyEvent;
import com.griefcraft.scripting.event.LWCProtectionInteractEvent;
import com.massivecraft.factions.Board;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.struct.Rel;
import io.github.dre2n.factionslwcbridge.FactionsLWCBridge;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

/**
 * @author Daniel Saukel
 */
public class LWCListener extends JavaModule {

    FactionsLWCBridge plugin = FactionsLWCBridge.getPlugin();
    LWC lwc = plugin.getLWC();

    @Override
    public void onProtectionInteract(LWCProtectionInteractEvent event) {
        if (canBypass(event.getPlayer(), event.getProtection().getBlock())) {
            event.setResult(ALLOW);
        }
    }

    @Override
    public void onDestroyProtection(LWCProtectionDestroyEvent event) {
        if (canBypass(event.getPlayer(), event.getProtection().getBlock())) {
            event.getProtection().remove();
        }
    }

    public boolean canBypass(Player player, Block block) {
        FPlayer fPlayer = FPlayers.i.get(player);

        if (!fPlayer.hasFaction()) {
            return false;
        }

        if (fPlayer.getRole() != Rel.LEADER) {
            return false;
        }

        if (!Board.getFactionAt(block).equals(fPlayer.getFaction())) {
            return false;
        }

        return true;
    }

}
