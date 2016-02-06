package io.github.dre2n.factionslwcbridge.listener;

import io.github.dre2n.factionslwcbridge.FactionsLWCBridge;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import com.griefcraft.lwc.LWC;
import com.griefcraft.scripting.JavaModule;
import com.griefcraft.scripting.event.LWCProtectionDestroyEvent;
import com.griefcraft.scripting.event.LWCProtectionInteractEvent;
import com.massivecraft.factions.Board;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.struct.Rel;

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
		
		if ( !fPlayer.hasFaction()) {
			return false;
		}
		
		if (fPlayer.getRole() != Rel.LEADER) {
			return false;
		}
		
		if ( !Board.getFactionAt(block).equals(fPlayer.getFaction())) {
			return false;
		}
		
		return true;
	}
}
