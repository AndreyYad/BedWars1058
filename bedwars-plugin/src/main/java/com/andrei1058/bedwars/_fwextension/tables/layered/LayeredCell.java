package com.andrei1058.bedwars._fwextension.tables.layered;

import com.andrei1058.bedwars._fwextension.tables.Cell;
import lombok.*;

@ToString
@SuppressWarnings("unused")
public class LayeredCell<Content> extends Cell<Content> {

    @Getter private final String layer;

    public LayeredCell(@NonNull String layer, int row, int column, @NonNull Content content) {
        super(row, column, content);
        this.layer = layer;
    }
}
