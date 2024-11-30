package com.andrei1058.bedwars.support.version.v1_19_R3.despawnable;

import com.andrei1058.bedwars.arena.team.ITeam;
import com.andrei1058.bedwars.server.VersionSupport;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/// из versionsupport_v1_19_R3

public class DespawnableFactory {

    private final VersionSupport versionSupport;
    private final List<DespawnableProvider<? extends LivingEntity>> providers = new ArrayList<>();

    public DespawnableFactory(VersionSupport versionSupport) {
        this.versionSupport = versionSupport;
        providers.add(new TeamIronGolem());
        providers.add(new TeamSilverfish());
    }

    public LivingEntity spawn(@NotNull DespawnableAttributes attr, @NotNull Location location, @NotNull ITeam team){
        return providers.stream().filter(provider -> provider.getType() == attr.type())
                .findFirst().orElseThrow().spawn(attr, location,team, versionSupport);
    }
}
