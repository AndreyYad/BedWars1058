package com.andrei1058.bedwars._fwextension.tables.layered;

import com.andrei1058.bedwars._fwextension.tables.Cell;
import com.andrei1058.bedwars._fwextension.tables.ITable;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import org.jetbrains.annotations.Nullable;

import java.util.*;

import static com.andrei1058.bedwars._fwextension.utils.Utils.ints;

@ToString
@SuppressWarnings("unused")
public class Projection<Content> implements ITable<Content> {

    @Getter private final LayeredTable<Content> layeredTable;
    @Getter private final int height;
    @Getter private final int width;
    @Getter private final List<String> layersOrder;
    @Getter private final String baseLayer;
    @Getter private final List<List<String>> layersProjection;

    public Projection(@NonNull LayeredTable<Content> layeredTable, @NonNull List<String> layersOrder) {
        this.layeredTable = layeredTable;
        this.height = layeredTable.getHeight();
        this.width = layeredTable.getWidth();
        if (layersOrder.size() == 0) {
            throw new RuntimeException("В проекции должен быть хотя бы один слой");
        }
        if (layersOrder.size() == new HashSet<>(layersOrder).size()) {
            throw new RuntimeException("Названия слоев не могут дублироваться в проекции");
        }
        this.layersOrder = layersOrder;
        this.baseLayer = layersOrder.get(0);
        /// приходит от нижнего к верхнему, а тут меняют наоборот
        Collections.reverse(this.layersOrder);
        this.layersProjection = createLayersProjection();
    }

    private List<List<String>> createLayersProjection() {
        List<List<String>> layersProjection = new ArrayList<>(height);
        for (int row : ints(0, height)) {
            layersProjection.add(new ArrayList<>(width));
            for (int column : ints(0, width)) {
                String upperLayer = baseLayer;
                for (String layer: layersOrder) {
                    Content content = layeredTable.get(layer, row, column);
                    if (content != null) {
                        upperLayer = layer;
                        break;
                    }
                }
                layersProjection.get(row).add(upperLayer);
            }
        }
        return layersProjection;
    }

    public String getUpperLayerFor(int row, int column) {
        return layersProjection.get(row).get(column);
    }

    public void set(int row, int column, @Nullable Content content) {
        layeredTable.set(getUpperLayerFor(row, column), row, column, content);
    }

    public Cell<Content> getCell(int row, int column) {
        return layeredTable.getCell(getUpperLayerFor(row, column), row, column);
    }

    public Content get(int row, int column) {
        return layeredTable.get(getUpperLayerFor(row, column), row, column);
    }

    public boolean isEmpty(int row, int column) {
        return layeredTable.isEmpty(getUpperLayerFor(row, column), row, column);
    }


    public Content getDefaultContent() {
        throw new RuntimeException("Нельзя получить дефолтный контент из проекции");
    }
    public void setDefaultContent(Content defaultContent) {
        throw new RuntimeException("Нельзя установить дефолтный контент для проекции");
    }
    public Cell<Content> findCell(@NonNull Content content) {
        throw new RuntimeException("Нельзя искать ячейку по контенту в проекции");
    }
    public List<Cell<Content>> findCells(@NonNull Content content) {
        throw new RuntimeException("Нельзя искать ячейки по контенту в проекции");
    }
}
