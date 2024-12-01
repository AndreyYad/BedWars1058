package com.andrei1058.bedwars.arena.despawnables;

import com.andrei1058.bedwars.BedWars;
import com.andrei1058.bedwars.arena.GameState;
import com.andrei1058.bedwars.arena.IArena;
import com.andrei1058.bedwars.arena.Arena;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;

/// этот обработчик отменяет твои удары по призванным твоей командой существам.
public class TargetListener implements Listener {

    @EventHandler
    /// отмена + тут дохуя проверок что чел, который бьет, вообще с нужной арены и тд
    public void onTarget(EntityTargetLivingEntityEvent e){
        if (!(e.getTarget() instanceof Player)) return;
        IArena arena = Arena.getArenaByIdentifier(e.getEntity().getWorld().getName());
        Player p = (Player) e.getTarget();
        if (arena == null) return;
        if (!arena.isPlayer(p)) {
            e.setCancelled(true);
            return;
        }
        if (arena.getStatus() != GameState.playing){
            e.setCancelled(true);
            return;
        }
        if (BedWars.nms.isDespawnable(e.getEntity())){
            if (arena.getTeam(p) == BedWars.nms.getDespawnablesList().get(e.getEntity().getUniqueId()).getTeam()){
                e.setCancelled(true);
            }
        }
    }
}
