package com.witalis.jkit.usage.core.invoke.section.lambda.content.pattern.strategy;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.function.Predicate;

@Slf4j
public final class AssetUtils {

    private AssetUtils() {
        super();
    }

    public static int totalAssetValues(final List<Asset> assets) {
        return assets.stream()
            .mapToInt(Asset::getValue)
            .sum();
    }

    public static int totalBondValues(final List<Asset> assets) {
        return assets.stream()
            .mapToInt(asset ->
                asset.getType() == Asset.AssetType.BOND ? asset.getValue() : 0)
            .sum();
    }

    public static int totalStockValues(final List<Asset> assets) {
        return assets.stream()
            .mapToInt(asset ->
                asset.getType() == Asset.AssetType.STOCK ? asset.getValue() : 0)
            .sum();
    }

    public static int totalAssetValues(
        final List<Asset> assets,
        final Predicate<Asset> assetSelector
    ) {
        return assets.stream()
            .filter(assetSelector)
            .mapToInt(Asset::getValue)
            .sum();
    }
}
