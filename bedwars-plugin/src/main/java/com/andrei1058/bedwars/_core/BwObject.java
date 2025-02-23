package com.andrei1058.bedwars._core;

import com.andrei1058.bedwars.arena.IArena;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@EqualsAndHashCode(of = {"id"})
@SuppressWarnings("unused")
///! мб переименовать
public abstract class BwObject {

    @Getter private String id;

    @Getter private IArena arena;

    protected BwObject() {
    }
    public void _init(@NonNull IArena arena, @NonNull String id) {
        this.id = id;
        this.arena = arena;
    }

    protected BwObject(@NonNull IArena arena) {
        this.arena = arena;
    }
    public void _init(@NonNull String id) {
        this.id = id;
    }

    protected BwObject(@NonNull IArena arena, @NonNull String id) {
        this.id = id;
        this.arena = arena;
    }

    public abstract void destruct();
}
