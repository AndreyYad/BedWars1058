package com.andrei1058.bedwars._fwextension.tables;

import lombok.*;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static com.andrei1058.bedwars._fwextension.utils.Utils.times;

@ToString
@SuppressWarnings("unused")
public class Table<Content> implements ITable<Content> {

    @Getter private final int height;
    @Getter private final int width;

    private final List<List<Cell<Content>>> rows;
    private final List<List<Cell<Content>>> columns;
    private final List<Cell<Content>> cells;

    @Getter @Setter private Content defaultContent = null;

    public Table(int height, int width) {
        this.height = height;
        this.width = width;
        rows = new ArrayList<>(height);
        for (int time : times(height)) {
            rows.add(new ArrayList<>(width));
        }
        columns = new ArrayList<>(width);
        for (int time : times(width)) {
            columns.add(new ArrayList<>(height));
        }
        cells = new ArrayList<>(height * width);
    }


    @SafeVarargs
    public final void cells(Content... contents) {
        for (Content content : contents) {
            addCell(content);
        }
        if (cells.size() != height * width) {
            throw new RuntimeException("У этого листа не хватает ячеек.");
        }
    }

    private void addCell(@Nullable Content content) {
        int row = 0;
        while (rows.get(row).size() == width) {
            row++;
        }
        if (row >= height) {
            throw new RuntimeException("В этом листе только %s строк по %s столбцов. Остальные ячейки лишние.".formatted(height, width));
        }
        int column = rows.get(row).size();

        Cell<Content> cell = new Cell<>(row, column, content);
        rows.get(row).add(cell);
        columns.get(column).add(cell);
        cells.add(cell);
    }

    public Cell<Content> getCell(int row, int column) {
        checkRow(row);
        checkColumn(column);
        return rows.get(row).get(column);
    }

    public void set(int row, int column, @Nullable Content content) {
        getCell(row, column).setContent(content);
    }

    public Content get(int row, int column) {
        return getCell(row, column).getContent();
    }

    public boolean isEmpty(int row, int column) {
        return get(row, column) == null;
    }

    public Cell<Content> findCell(@NonNull Content content) {
        for (Cell<Content> cell : cells) {
            /// проверяем по ссылке
            if (cell.getContent() == content) {
                return cell;
            }
        }
        return null;
    }

    /// такое вызываем если предполагаем, что там не в одно место этот контент вставлен
    public List<Cell<Content>> findCells(@NonNull Content content) {
        List<Cell<Content>> foundCells = new ArrayList<>();
        for (Cell<Content> cell : cells) {
            /// проверяем по ссылке
            if (cell.getContent() == content) {
                foundCells.add(cell);
            }
        }
        return foundCells;
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
