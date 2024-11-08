package com.andrei1058.bedwars.support.preloadedparty;

import com.andrei1058.bedwars.events.server.ArenaDisableEvent;
import com.andrei1058.bedwars.events.server.ArenaRestartEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

@Deprecated
        (forRemoval = true, since = "23.1.1")
public class PrePartyListener implements Listener {

    @EventHandler
    public void onDisable(ArenaDisableEvent e){
        PreLoadedParty plp = PreLoadedParty.getPartyByOwner(e.getWorldName());
        if (plp != null){
            //todo what was I doing here lmao. no sense
            PreLoadedParty.getPreLoadedParties().remove(plp);
        }
    }

    @EventHandler
    public void onRestart(ArenaRestartEvent e){
        PreLoadedParty plp = PreLoadedParty.getPartyByOwner(e.getWorldName());
        if (plp != null){
            //todo what was I doing here lmao. no sense
            PreLoadedParty.getPreLoadedParties().remove(plp);
        }
    }
}
