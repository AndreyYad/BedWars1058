package com.andrei1058.bedwars._fwextension.tables.layered;

import com.andrei1058.bedwars._fwextension.tables.Cell;
import lombok.*;
import org.jetbrains.annotations.Nullable;

import java.util.*;

@ToString
@SuppressWarnings("unused")
public class LayeredTable<Content> {

    @Getter private final int height;
    @Getter private final int width;

    private final Map<String, Layer<Content>> layers = new HashMap<>();
    private final Map<String, Projection<Content>> layersProjection = new HashMap<>();

    @Getter @Setter private Content defaultContent = null;

    public LayeredTable(int height, int width) {
        this.height = height;
        this.width = width;
    }


    public void addLayer(@NonNull Layer<Content> layer) {
        checkLayerSize(layer);
        layer.setDefaultContent(defaultContent);
        layers.put(layer.getName(), layer);
    }

    private void checkLayerSize(@NonNull Layer<Content> layer) {
        if (layer.getHeight() != height || layer.getWidth() != width) {
            throw new RuntimeException("Этот слой (%s) должен быть такого же размера как слоенная таблица, куда он добавляется".formatted(layer.getName()));
        }
    }


    public Projection<Content> getProjection(List<String> layersOrder) {
        String joinLayersOrder = String.join(":", layersOrder);
        layersProjection.putIfAbsent(joinLayersOrder, new Projection<>(this, layersOrder));
        return layersProjection.get(joinLayersOrder);
    }

    public Cell<Content> getCell(@NonNull String layerName, int row, int column) {
        return layers.get(layerName).getCell(row, column);
    }

    public void set(@NonNull String layerName, int row, int column, @Nullable Content content) {
        layers.get(layerName).set(row, column, content);
    }

    public Content get(@NonNull String layerName, int row, int column) {
        return layers.get(layerName).get(row, column);
    }

    public boolean isEmpty(@NonNull String layerName, int row, int column) {
        return layers.get(layerName).isEmpty(row, column);
    }

    public Cell<Content> findCell(@NonNull Content content) {
        for (Layer<Content> layer : layers.values()) {
            if (layer.findCell(content) != null) {
                return layer.findCell(content);
            }
        }
        return null;
    }

    public List<Cell<Content>> findCells(@NonNull Content content) {
        List<Cell<Content>> foundCells = new ArrayList<>();
        for (Layer<Content> layer : layers.values()) {
            foundCells.addAll(layer.findCells(content));
        }
        return foundCells;
    }
}
