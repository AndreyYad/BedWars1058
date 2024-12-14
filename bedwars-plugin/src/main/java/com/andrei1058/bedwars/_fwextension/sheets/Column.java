package com.andrei1058.bedwars._fwextension.sheets;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class Column<C> extends ArrayList<C> {

    public Column() {
        super();
    }
    public Column(int height) {
        super(height);
    }

    public C row(int row) {
        return super.get(row);
    }
}
