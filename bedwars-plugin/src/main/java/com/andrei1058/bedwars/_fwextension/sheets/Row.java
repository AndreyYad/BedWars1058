package com.andrei1058.bedwars._fwextension.sheets;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class Row<Cell> extends ArrayList<Cell> {

    public Row() {
        super();
    }
    public Row(int width) {
        super(width);
    }

    public Cell column(int column) {
        return super.get(column);
    }
}
