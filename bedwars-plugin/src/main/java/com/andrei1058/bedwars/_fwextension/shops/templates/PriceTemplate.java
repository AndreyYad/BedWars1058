package com.andrei1058.bedwars._fwextension.shops.templates;

import com.andrei1058.bedwars._fwextension.common.templates.ITemplate;
import com.andrei1058.bedwars._fwextension.materials.ExtMaterial;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.andrei1058.bedwars._fwextension.utils.Utils.checkEmpty;
import static com.andrei1058.bedwars._fwextension.utils.Utils.indexes;


@ToString
@SuppressWarnings("unused")
public class PriceTemplate implements ITemplate {

    private List<ExtMaterial> currencies = new ArrayList<>();
    private List<Integer> costs = new ArrayList<>();
    @Getter private Map<ExtMaterial, Integer> price = new HashMap<>();

    public void _read() {
        if (price.isEmpty()) {
            checkEmpty("currency", currencies);
            checkEmpty("cost", costs);
            if (currencies.size() != costs.size()) {
                throw new RuntimeException("Количество валют %s не соответствуют количеству цен %s".formatted(currencies.size(), costs.size()));
            }
            for (int number : indexes(currencies)) {
                price.put(currencies.get(number), costs.get(number));
            }
        }
    }

    ///! как-то так себе звучит price.price, мб переделать
    public PriceTemplate price(@NonNull ExtMaterial currency, int cost) {
        price.put(currency, cost);
        return this;
    }
    public PriceTemplate price(@NonNull ExtMaterial currency1, int cost1, @NonNull ExtMaterial currency2, int cost2) {
        price(currency1, cost1);
        price(currency2, cost2);
        return this;
    }
    public PriceTemplate price(@NonNull ExtMaterial currency1, int cost1, @NonNull ExtMaterial currency2, int cost2, @NonNull ExtMaterial currency3, int cost3) {
        price(currency1, cost1);
        price(currency2, cost2);
        price(currency3, cost3);
        return this;
    }

    public PriceTemplate currency(@NonNull ExtMaterial currency) {
        currencies.add(currency);
        return this;
    }
    public PriceTemplate cost(int cost) {
        costs.add(cost);
        return this;
    }
}
