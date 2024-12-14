package com.andrei1058.bedwars._fwextension.sheets;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@SuppressWarnings("unused")
public class Cell<C> {

    private C content;
    private boolean placedInSheet = false;
    protected Sheet<C> sheet;
    protected int row;
    protected int column;

    public boolean isSheet() {
        return false;
    }

    public Cell() {
    }
    public Cell(@NonNull C content) {
        this.content = content;
    }


    public C content() {
        return getContent();
    }
    public void setContent(@NonNull C content) {
        this.content = content;
    }
    public void set(C content) {
        setContent(content);
    }

    protected void handleAddingInSheet(@NonNull Sheet<C> sheet, int row, int column) {
        if (this.placedInSheet) {
            throw new RuntimeException("Ячейку можно разместить только на одном листе");
        }
        this.sheet = sheet;
        this.row = row;
        this.column = column;
        this.placedInSheet = true;
    }
}
