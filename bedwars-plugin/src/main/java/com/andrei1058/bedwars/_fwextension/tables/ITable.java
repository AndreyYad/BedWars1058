package com.andrei1058.bedwars._fwextension.tables;

import lombok.NonNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@SuppressWarnings("unused")
public interface ITable<Content> {

    int getHeight();

    int getWidth();

    Content getDefaultContent();

    void setDefaultContent(Content defaultContent);

    Cell<Content> getCell(int row, int column);

    void set(int row, int column, @Nullable Content content);

    Content get(int row, int column);

    boolean isEmpty(int row, int column);

    Cell<Content> findCell(@NonNull Content content);

    List<Cell<Content>> findCells(@NonNull Content content);
}
