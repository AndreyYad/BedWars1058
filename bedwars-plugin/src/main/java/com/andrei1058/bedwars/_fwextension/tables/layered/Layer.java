package com.andrei1058.bedwars._fwextension.tables.layered;

import com.andrei1058.bedwars._fwextension.tables.Table;
import lombok.*;

@ToString
@SuppressWarnings("unused")
public class Layer<Contend> extends Table<Contend> {

    @Getter private final String name;

    public Layer(@NonNull String name, int height, int width) {
        super(height, width);
        this.name = name;
    }
}
