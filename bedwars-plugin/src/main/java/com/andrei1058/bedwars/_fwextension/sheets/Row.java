package com.andrei1058.bedwars._fwextension.sheets;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class Row<C> extends ArrayList<C> {

    public Row() {
        super();
    }
    public Row(int width) {
        super(width);
    }

    public C column(int column) {
        return super.get(column);
    }
}
