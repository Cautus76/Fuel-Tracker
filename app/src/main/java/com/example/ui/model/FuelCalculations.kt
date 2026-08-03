package com.example.ui.model

import com.example.data.model.FuelRecord

enum class FuelSortOption {
    DATE_DESC,
    DATE_ASC,
    PRICE_ASC,
    PRICE_DESC,
    QUANTITY_ASC,
    QUANTITY_DESC
}

data class FuelRecordUiItem(
    val record: FuelRecord,
    val distanceSincePrevious: Double?, // km
    val segmentConsumption: Double?, // l/100km
    val costPerKm: Double? // Kč/km
)

data class FuelStats(
    val totalRecords: Int = 0,
    val totalDistanceKm: Double = 0.0,
    val totalLitres: Double = 0.0,
    val totalCostKc: Double = 0.0,
    val avgConsumptionL100km: Double = 0.0,
    val avgPricePerLitreKc: Double = 0.0,
    val avgCostPerKmKc: Double = 0.0,
    val minConsumptionL100km: Double = 0.0,
    val maxConsumptionL100km: Double = 0.0
)

object FuelCalculations {

    /**
     * Computes list of UI items and overall stats from raw records.
     * Sorts UI items according to [sortOption].
     */
    fun processRecords(
        records: List<FuelRecord>,
        sortOption: FuelSortOption = FuelSortOption.DATE_DESC
    ): Pair<List<FuelRecordUiItem>, FuelStats> {
        if (records.isEmpty()) {
            return Pair(emptyList(), FuelStats())
        }

        // Ensure records are sorted chronologically (newest first) for metric calculation
        val sortedDesc = records.sortedWith(compareByDescending<FuelRecord> { it.date }.thenByDescending { it.odometer })
        val uiItems = mutableListOf<FuelRecordUiItem>()

        var totalLitresExcludingFirst = 0.0
        var totalCostExcludingFirst = 0.0
        var totalCost = 0.0
        var totalLitres = 0.0
        val segmentConsumptions = mutableListOf<Double>()

        for (i in sortedDesc.indices) {
            val current = sortedDesc[i]
            totalCost += current.totalPrice
            totalLitres += current.litres

            val previous = if (i + 1 < sortedDesc.size) sortedDesc[i + 1] else null
            val distance = if (previous != null) (current.odometer - previous.odometer) else null

            var segmentCons: Double? = null
            var costPerKm: Double? = null

            if (distance != null && distance > 0) {
                totalLitresExcludingFirst += current.litres
                totalCostExcludingFirst += current.totalPrice
                segmentCons = (current.litres / distance) * 100.0
                costPerKm = current.totalPrice / distance
                segmentConsumptions.add(segmentCons)
            }

            uiItems.add(
                FuelRecordUiItem(
                    record = current,
                    distanceSincePrevious = distance,
                    segmentConsumption = segmentCons,
                    costPerKm = costPerKm
                )
            )
        }

        val totalDistance = if (sortedDesc.size >= 2) {
            (sortedDesc.first().odometer - sortedDesc.last().odometer).coerceAtLeast(0.0)
        } else {
            0.0
        }

        val overallAvgConsumption = if (totalDistance > 0 && totalLitresExcludingFirst > 0) {
            (totalLitresExcludingFirst / totalDistance) * 100.0
        } else {
            0.0
        }

        val avgPricePerLitre = if (totalLitres > 0) totalCost / totalLitres else 0.0
        val avgCostPerKm = if (totalDistance > 0 && totalCostExcludingFirst > 0) {
            totalCostExcludingFirst / totalDistance
        } else {
            0.0
        }

        val stats = FuelStats(
            totalRecords = records.size,
            totalDistanceKm = totalDistance,
            totalLitres = totalLitres,
            totalCostKc = totalCost,
            avgConsumptionL100km = overallAvgConsumption,
            avgPricePerLitreKc = avgPricePerLitre,
            avgCostPerKmKc = avgCostPerKm,
            minConsumptionL100km = segmentConsumptions.minOrNull() ?: 0.0,
            maxConsumptionL100km = segmentConsumptions.maxOrNull() ?: 0.0
        )

        val sortedUiItems = when (sortOption) {
            FuelSortOption.DATE_DESC -> uiItems.sortedWith(
                compareByDescending<FuelRecordUiItem> { it.record.date }
                    .thenByDescending { it.record.odometer }
            )
            FuelSortOption.DATE_ASC -> uiItems.sortedWith(
                compareBy<FuelRecordUiItem> { it.record.date }
                    .thenBy { it.record.odometer }
            )
            FuelSortOption.PRICE_ASC -> uiItems.sortedWith(
                compareBy<FuelRecordUiItem> { it.record.totalPrice }
                    .thenByDescending { it.record.date }
            )
            FuelSortOption.PRICE_DESC -> uiItems.sortedWith(
                compareByDescending<FuelRecordUiItem> { it.record.totalPrice }
                    .thenByDescending { it.record.date }
            )
            FuelSortOption.QUANTITY_ASC -> uiItems.sortedWith(
                compareBy<FuelRecordUiItem> { it.record.litres }
                    .thenByDescending { it.record.date }
            )
            FuelSortOption.QUANTITY_DESC -> uiItems.sortedWith(
                compareByDescending<FuelRecordUiItem> { it.record.litres }
                    .thenByDescending { it.record.date }
            )
        }

        return Pair(sortedUiItems, stats)
    }
}
