package com.andrei1058.bedwars._fwextension.sheets;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class Column<Cell> extends ArrayList<Cell> {

    public Column() {
        super();
    }
    public Column(int height) {
        super(height);
    }

    public Cell row(int row) {
        return super.get(row);
    }
}
