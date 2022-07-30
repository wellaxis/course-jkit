package com.witalis.jkit.usage.core.invoke.section.lambda.content.pattern.strategy;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class Asset {

    public enum AssetType { BOND, STOCK };

    private final AssetType type;
    private final int value;

    public Asset(final AssetType assetType, final int assetValue) {
        type = assetType;
        value = assetValue;
    }

    public AssetType getType() {
        return type;
    }

    public int getValue() {
        return value;
    }
}
