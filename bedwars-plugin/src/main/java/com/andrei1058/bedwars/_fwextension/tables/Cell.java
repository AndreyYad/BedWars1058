package com.andrei1058.bedwars._fwextension.tables;

import lombok.*;

@ToString
@SuppressWarnings("unused")
public class Cell<Content> {

    @Getter private final int row;
    @Getter private final int column;

    @Getter @Setter private Content content;

    public Cell(int row, int column, Content content) {
        this.row = row;
        this.column = column;
        this.content = content;
    }

    public Cell(int row, int column) {
        this(row, column, null);
    }
}
