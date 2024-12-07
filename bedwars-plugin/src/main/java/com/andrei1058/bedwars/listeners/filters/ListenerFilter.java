package com.andrei1058.bedwars.listeners.filters;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEvent;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class ListenerFilter<T extends PlayerEvent> implements Listener {
    private final Class<T> eventType;
    private final Predicate<Player> filter;
    private final Consumer<T> handler;

    public ListenerFilter(Class<T> eventType, Predicate<Player> filter, Consumer<T> handler) {
        this.eventType = eventType;
        this.filter = filter;
        this.handler = handler;
    }


    @EventHandler
    public void onEvent(Event event) {
        if (eventType.isInstance(event)) {
            T castedEvent = eventType.cast(event);
            Player player = ((PlayerEvent) castedEvent).getPlayer();
            if (filter.test(player)) {
                handler.accept(castedEvent);
            }
        }
    }
}