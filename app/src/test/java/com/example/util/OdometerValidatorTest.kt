package com.example.util

import com.example.data.model.FuelRecord
import com.example.ui.model.FuelCalculations
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class OdometerValidatorTest {

    @Test
    fun `first record at 0 km is valid`() {
        val result = OdometerValidator.validate(0.0, previousOdometer = null)
        assertTrue(result is OdometerValidator.ValidationResult.Valid)
    }

    @Test
    fun `saving first record at 0 km works correctly and calculates no consumption`() {
        val previousOdometer = OdometerValidator.getPreviousOdometer(rawRecords = emptyList(), editingRecord = null)
        assertNull(previousOdometer)

        val validation = OdometerValidator.validate(0.0, previousOdometer)
        assertTrue(validation is OdometerValidator.ValidationResult.Valid)

        val firstRecord = FuelRecord(id = 1, date = "2026-08-01", odometer = 0.0, litres = 40.0, totalPrice = 1600.0)
        val (items, stats) = FuelCalculations.processRecords(listOf(firstRecord))

        assertEquals(1, items.size)
        assertNull(items[0].distanceSincePrevious)
        assertNull(items[0].segmentConsumption)
        assertNull(items[0].costPerKm)
        assertEquals(0.0, stats.totalDistanceKm, 0.001)
        assertEquals(0.0, stats.avgConsumptionL100km, 0.001)
    }

    @Test
    fun `second record above 0 km is valid and computes consumption`() {
        val firstRecord = FuelRecord(id = 1, date = "2026-08-01", odometer = 0.0, litres = 40.0, totalPrice = 1600.0)
        val previousOdometer = OdometerValidator.getPreviousOdometer(rawRecords = listOf(firstRecord), editingRecord = null)
        assertEquals(0.0, previousOdometer!!, 0.001)

        val validation = OdometerValidator.validate(500.0, previousOdometer)
        assertTrue(validation is OdometerValidator.ValidationResult.Valid)

        val secondRecord = FuelRecord(id = 2, date = "2026-08-05", odometer = 500.0, litres = 30.0, totalPrice = 1200.0)
        val (items, stats) = FuelCalculations.processRecords(listOf(firstRecord, secondRecord))

        assertEquals(2, items.size)
        // Record at 500 km should have distance = 500 km and segment consumption = (30 / 500) * 100 = 6.0
        val item500 = items.first { it.record.id == 2 }
        assertEquals(500.0, item500.distanceSincePrevious!!, 0.001)
        assertEquals(6.0, item500.segmentConsumption!!, 0.001)
        assertEquals(2.4, item500.costPerKm!!, 0.001)

        // First record still has no segment consumption
        val item0 = items.first { it.record.id == 1 }
        assertNull(item0.distanceSincePrevious)
        assertNull(item0.segmentConsumption)

        assertEquals(500.0, stats.totalDistanceKm, 0.001)
        assertEquals(6.0, stats.avgConsumptionL100km, 0.001)
    }

    @Test
    fun `rejecting a negative first value`() {
        val result = OdometerValidator.validate(-10.0, previousOdometer = null)
        assertTrue(result is OdometerValidator.ValidationResult.Invalid.NegativeValue)
    }

    @Test
    fun `rejecting a later value equal to or lower than the previous odometer reading`() {
        // Equal to previous odometer (0 km when previous is 0 km)
        val resZeroEqual = OdometerValidator.validate(0.0, previousOdometer = 0.0)
        assertTrue(resZeroEqual is OdometerValidator.ValidationResult.Invalid.ZeroNotAllowedWithPreviousRecords)

        // Equal to previous odometer (500 km when previous is 500 km)
        val resEqual = OdometerValidator.validate(500.0, previousOdometer = 500.0)
        assertTrue(resEqual is OdometerValidator.ValidationResult.Invalid.MustBeGreaterThanPrevious)
        assertEquals(500.0, (resEqual as OdometerValidator.ValidationResult.Invalid.MustBeGreaterThanPrevious).previousOdometer, 0.001)

        // Lower than previous odometer (400 km when previous is 500 km)
        val resLower = OdometerValidator.validate(400.0, previousOdometer = 500.0)
        assertTrue(resLower is OdometerValidator.ValidationResult.Invalid.MustBeGreaterThanPrevious)
        assertEquals(500.0, (resLower as OdometerValidator.ValidationResult.Invalid.MustBeGreaterThanPrevious).previousOdometer, 0.001)

        // Negative value when previous odometer exists
        val resNegative = OdometerValidator.validate(-5.0, previousOdometer = 500.0)
        assertTrue(resNegative is OdometerValidator.ValidationResult.Invalid.NegativeValue)
    }
}
