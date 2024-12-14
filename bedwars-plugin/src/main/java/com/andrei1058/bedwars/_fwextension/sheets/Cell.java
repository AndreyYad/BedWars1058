package com.andrei1058.bedwars._fwextension.sheets;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@SuppressWarnings("unused")
public class Cell<Content> {

    private Content content;
    private boolean placedInSheet = false;
    protected Sheet<Content> sheet;
    protected int row;
    protected int column;

    public Cell() {
    }
    public Cell(@NonNull Content content) {
        this.content = content;
    }


    public Content content() {
        return getContent();
    }
    public void setContent(@NonNull Content content) {
        this.content = content;
    }
    public void set(Content content) {
        setContent(content);
    }

    protected void handleAddingInSheet(@NonNull Sheet<Content> sheet, int row, int column) {
        if (this.placedInSheet) {
            throw new RuntimeException("Ячейку можно разместить только на одном листе.");
        }
        this.sheet = sheet;
        this.row = row;
        this.column = column;
        this.placedInSheet = true;
    }
}
