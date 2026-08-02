package com.example.ui.model

import com.example.data.model.FuelRecord
import org.junit.Assert.assertEquals
import org.junit.Test

class FuelCalculationsTest {

    @Test
    fun `processRecords excludes first record from avgCostPerKm and avgConsumption`() {
        val record1 = FuelRecord(id = 1, date = "2026-08-01", odometer = 10000.0, litres = 40.0, totalPrice = 1600.0)
        val record2 = FuelRecord(id = 2, date = "2026-08-05", odometer = 10500.0, litres = 30.0, totalPrice = 1200.0)
        val record3 = FuelRecord(id = 3, date = "2026-08-10", odometer = 11000.0, litres = 35.0, totalPrice = 1400.0)

        val records = listOf(record1, record2, record3)
        val (_, stats) = FuelCalculations.processRecords(records)

        // Total distance: 11000 - 10000 = 1000 km
        assertEquals(1000.0, stats.totalDistanceKm, 0.001)

        // Total cost for all records: 1600 + 1200 + 1400 = 4200
        assertEquals(4200.0, stats.totalCostKc, 0.001)

        // Total litres for all records: 40 + 30 + 35 = 105
        assertEquals(105.0, stats.totalLitres, 0.001)

        // Avg price per litre includes all records: 4200 / 105 = 40.0
        assertEquals(40.0, stats.avgPricePerLitreKc, 0.001)

        // Cost excluding first record: 1200 + 1400 = 2600
        // Avg cost per km: 2600 / 1000 = 2.6
        assertEquals(2.6, stats.avgCostPerKmKc, 0.001)

        // Litres excluding first record: 30 + 35 = 65
        // Avg consumption: (65 / 1000) * 100 = 6.5 l/100km
        assertEquals(6.5, stats.avgConsumptionL100km, 0.001)
    }

    @Test
    fun `processRecords with single record returns 0 for distance avgCostPerKm and avgConsumption`() {
        val record1 = FuelRecord(id = 1, date = "2026-08-01", odometer = 10000.0, litres = 40.0, totalPrice = 1600.0)

        val (_, stats) = FuelCalculations.processRecords(listOf(record1))

        assertEquals(0.0, stats.totalDistanceKm, 0.001)
        assertEquals(1600.0, stats.totalCostKc, 0.001)
        assertEquals(40.0, stats.totalLitres, 0.001)
        assertEquals(40.0, stats.avgPricePerLitreKc, 0.001)
        assertEquals(0.0, stats.avgCostPerKmKc, 0.001)
        assertEquals(0.0, stats.avgConsumptionL100km, 0.001)
    }
}
