
package com.andrei1058.bedwars.entity;

import com.andrei1058.bedwars.BedWars;
import com.andrei1058.bedwars.arena.team.ITeam;
import com.andrei1058.bedwars.events.player.PlayerKillEvent;
import com.andrei1058.bedwars.language.Messages;
import com.andrei1058.bedwars.language.Language;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;

import java.util.UUID;

//! из АПИ

public class Despawnable {

    private LivingEntity e;
    private ITeam team;
    private int despawn = 250;
    private String namePath;
    private PlayerKillEvent.PlayerKillCause deathRegularCause, deathFinalCause;
    private UUID uuid;

    public Despawnable(LivingEntity e, ITeam team, int despawn, String namePath, PlayerKillEvent.PlayerKillCause deathFinalCause, PlayerKillEvent.PlayerKillCause deathRegularCause) {
        this.e = e;
        if (e == null) return;
        this.uuid = e.getUniqueId();
        this.team = team;
        this.deathFinalCause = deathFinalCause;
        this.deathRegularCause = deathRegularCause;
        if (despawn != 0) {
            this.despawn = despawn;
        }
        this.namePath = namePath;
        BedWars.nms.getDespawnablesList().put(uuid, this);
        this.setName();
    }

    public void refresh() {
        if (e.isDead() || e == null || team == null || team.getArena() == null) {
            BedWars.nms.getDespawnablesList().remove(uuid);
            if (team.getArena() == null){
                e.damage(e.getHealth()+100);
            }
            return;
        }
        setName();
        despawn--;
        if (despawn == 0) {
            e.damage(e.getHealth()+100);
            BedWars.nms.getDespawnablesList().remove(e.getUniqueId());
        }
    }

    private void setName() {
        int percentuale = (int) ((e.getHealth() * 100) / e.getMaxHealth() / 10);
        String name = Language.getDefaultLanguage().m(namePath).replace("{despawn}", String.valueOf(despawn)).replace("{health}",
                new String(new char[percentuale]).replace("\0", Language.getDefaultLanguage()
                        .m(Messages.FORMATTING_DESPAWNABLE_UTILITY_NPC_HEALTH)) + new String(new char[10 - percentuale]).replace("\0", "§7" + Language.getDefaultLanguage()
                        .m(Messages.FORMATTING_DESPAWNABLE_UTILITY_NPC_HEALTH)));
        if (team != null) {
            name = name.replace("{TeamColor}", team.getColor().chat().toString()).replace("{TeamName}", team.getDisplayName(Language.getDefaultLanguage()));
        }
        e.setCustomName(name);
    }

    public LivingEntity getEntity() {
        return e;
    }

    public ITeam getTeam() {
        return team;
    }

    public int getDespawn() {
        return despawn;
    }

    public PlayerKillEvent.PlayerKillCause getDeathFinalCause() {
        return deathFinalCause;
    }

    public PlayerKillEvent.PlayerKillCause getDeathRegularCause() {
        return deathRegularCause;
    }

    public void destroy(){
        if (getEntity() != null){
            getEntity().damage(Integer.MAX_VALUE);
        }
        team = null;
        BedWars.nms.getDespawnablesList().remove(uuid);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof LivingEntity) return ((LivingEntity) obj).getUniqueId().equals(e.getUniqueId());
        return false;
    }
}
