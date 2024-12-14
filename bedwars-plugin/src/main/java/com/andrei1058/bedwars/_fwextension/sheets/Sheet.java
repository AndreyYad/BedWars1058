package com.andrei1058.bedwars._fwextension.sheets;

import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

import static com.andrei1058.bedwars._fwextension.utils.Utils.times;
import static java.lang.String.format;

@Getter
@SuppressWarnings("unused")
public abstract class Sheet<C> extends Cell<C> {

    protected final int height;
    protected final int width;
    private final Column<Row<Cell<C>>> rows;
    private final Row<Column<Cell<C>>> columns;
    private final List<Cell<C>> cells;

    @Override
    public boolean isSheet() {
        return true;
    }

    protected Sheet(int height, int width) {
        this.height = height;
        this.width = width;
        rows = new Column<>(height);
        for (int time : times(height)) {
            rows.add(new Row<>(width));
        }
        columns = new Row<>(width);
        for (int time : times(width)) {
            columns.add(new Column<>(height));
        }
        cells = new ArrayList<>(height * width);
    }

    ///! мб сделать проверку что добавили все нужные ячейки
    protected Sheet<C> addCell(@NonNull Cell<C> cell) {
        int row = 0;
        while (rows.get(row).size() == width) {
            row++;
        }
        if (row >= height) {
            throw new RuntimeException(format("В этом листе только %s строк по %s столбцов. Остальные ячейки лишние.", height, width));
        }
        int column = rows.get(row).size();
        cell.handleAddingInSheet(this, row, column);
        rows.get(row).add(cell);
        columns.get(column).add(cell);
        cells.add(cell);
        return this;
    }

    protected Sheet<C> addCell() {
        return addCell(new Cell<>());
    }

    public Row<Cell<C>> row(int row) {
        checkRow(row);
        return rows.get(row);
    } 
    
    public Column<Cell<C>> column(int column) {
        checkColumn(column);
        return columns.get(column);
    }

    public Cell<C> get(int row, int column) {
        checkRow(row);
        checkColumn(column);
        return rows.get(row).get(column);
    }
    
    public List<Cell<C>> getAllCells() {
        return cells;
    }
    
    private void checkRow(int row) {
        if (row >= height) {
            throw new RuntimeException(format("Высота листа равна %s. Нельзя получить строку номер %s", height, row));
        }
    }
    private void checkColumn(int column) {
        if (column >= width) {
            throw new RuntimeException(format("Ширина листа равна %s. Нельзя получить колонку номер %s", height, column));
        }
    }
}
