package com.andrei1058.bedwars.shop.templates.ordinary;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class OrdinaryPrice {

    ///! сделать возможность для нескольких пар валюта / цена
    private final OrdinaryCurrency currency;
    private final int cost;

    public enum OrdinaryCurrency {
        IRON,
        GOLD,
        EMERALD,
        DIAMOND
    }
}

