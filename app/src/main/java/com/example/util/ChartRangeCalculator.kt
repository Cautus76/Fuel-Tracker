package com.example.util

data class ChartBounds(
    val minConsumption: Double,
    val maxConsumption: Double,
    val chartMin: Double,
    val chartMax: Double,
    val padding: Double
) {
    val chartRange: Double get() = chartMax - chartMin
}

object ChartRangeCalculator {
    fun calculateBounds(values: List<Double>): ChartBounds? {
        if (values.isEmpty()) return null
        val minConsumption = values.minOrNull() ?: return null
        val maxConsumption = values.maxOrNull() ?: return null

        val dataRange = maxConsumption - minConsumption
        val padding = maxOf(dataRange * 0.15, 0.5)

        val chartMin = maxOf(0.0, minConsumption - padding)
        val chartMax = maxConsumption + padding

        return ChartBounds(
            minConsumption = minConsumption,
            maxConsumption = maxConsumption,
            chartMin = chartMin,
            chartMax = chartMax,
            padding = padding
        )
    }
}
