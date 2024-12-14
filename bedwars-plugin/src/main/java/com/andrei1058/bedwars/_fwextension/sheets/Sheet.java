package com.andrei1058.bedwars._fwextension.sheets;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

import static com.andrei1058.bedwars._fwextension.utils.Utils.times;

@Getter
@ToString
@EqualsAndHashCode
@SuppressWarnings("unused")
public class Sheet<Content> {

    protected final int height;
    protected final int width;
    private final Column<Row<Cell<Content>>> rows;
    private final Row<Column<Cell<Content>>> columns;
    private final List<Cell<Content>> cells;

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

    public void postInit() {
        if (cells.size() != height * width) {
            throw new RuntimeException("У этого листа не хватает ячеек.");
        }
    }

    ///! мб сделать проверку что добавили все нужные ячейки
    protected Sheet<Content> cell(@NonNull Cell<Content> cell) {
        int row = 0;
        while (rows.get(row).size() == width) {
            row++;
        }
        if (row >= height) {
            throw new RuntimeException("В этом листе только %s строк по %s столбцов. Остальные ячейки лишние.".formatted(height, width));
        }
        int column = rows.get(row).size();
        cell.handleAddingInSheet(this, row, column);
        rows.get(row).add(cell);
        columns.get(column).add(cell);
        cells.add(cell);
        return this;
    }

    protected Sheet<Content> cell() {
        return cell(new Cell<>());
    }

    public Row<Cell<Content>> row(int row) {
        checkRow(row);
        return rows.get(row);
    } 
    
    public Column<Cell<Content>> column(int column) {
        checkColumn(column);
        return columns.get(column);
    }

    public Cell<Content> get(int row, int column) {
        checkRow(row);
        checkColumn(column);
        return rows.get(row).get(column);
    }
    
    public List<Cell<Content>> getAllCells() {
        return cells;
    }
    
    private void checkRow(int row) {
        if (row >= height) {
            throw new RuntimeException("Высота этого листа равна %s. Нельзя получить строку номер %s.".formatted(height, row));
        }
    }
    private void checkColumn(int column) {
        if (column >= width) {
            throw new RuntimeException("Ширина этого листа равна %s. Нельзя получить колонку номер %s.".formatted(height, column));
        }
    }
}
