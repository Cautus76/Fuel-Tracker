package com.example.util

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class ChartRangeCalculatorTest {

    @Test
    fun `calculateBounds with normal consumption values`() {
        val values = listOf(6.0, 7.5, 8.2, 10.0)
        val bounds = ChartRangeCalculator.calculateBounds(values)

        assertNotNull(bounds)
        bounds!!

        assertEquals(6.0, bounds.minConsumption, 0.001)
        assertEquals(10.0, bounds.maxConsumption, 0.001)

        val expectedDataRange = 10.0 - 6.0 // 4.0
        val expectedPadding = 4.0 * 0.15 // 0.6 (> 0.5)
        assertEquals(expectedPadding, bounds.padding, 0.001)

        val expectedMin = 6.0 - expectedPadding // 5.4
        val expectedMax = 10.0 + expectedPadding // 10.6
        assertEquals(expectedMin, bounds.chartMin, 0.001)
        assertEquals(expectedMax, bounds.chartMax, 0.001)
        assertTrue(bounds.chartMin >= 0.0)
        assertTrue(bounds.chartMax > bounds.maxConsumption)
    }

    @Test
    fun `calculateBounds with a single value uses minimum 0,5 padding`() {
        val values = listOf(6.5)
        val bounds = ChartRangeCalculator.calculateBounds(values)

        assertNotNull(bounds)
        bounds!!

        assertEquals(6.5, bounds.minConsumption, 0.001)
        assertEquals(6.5, bounds.maxConsumption, 0.001)
        assertEquals(0.5, bounds.padding, 0.001)

        assertEquals(6.0, bounds.chartMin, 0.001)
        assertEquals(7.0, bounds.chartMax, 0.001)
    }

    @Test
    fun `calculateBounds with identical values uses minimum 0,5 padding`() {
        val values = listOf(7.2, 7.2, 7.2)
        val bounds = ChartRangeCalculator.calculateBounds(values)

        assertNotNull(bounds)
        bounds!!

        assertEquals(7.2, bounds.minConsumption, 0.001)
        assertEquals(7.2, bounds.maxConsumption, 0.001)
        assertEquals(0.5, bounds.padding, 0.001)

        assertEquals(6.7, bounds.chartMin, 0.001)
        assertEquals(7.7, bounds.chartMax, 0.001)
    }

    @Test
    fun `calculateBounds with a very small range uses minimum 0,5 padding`() {
        val values = listOf(5.0, 5.1)
        val bounds = ChartRangeCalculator.calculateBounds(values)

        assertNotNull(bounds)
        bounds!!

        assertEquals(5.0, bounds.minConsumption, 0.001)
        assertEquals(5.1, bounds.maxConsumption, 0.001)
        // 0.1 * 0.15 = 0.015 < 0.5 -> padding = 0.5
        assertEquals(0.5, bounds.padding, 0.001)

        assertEquals(4.5, bounds.chartMin, 0.001)
        assertEquals(5.6, bounds.chartMax, 0.001)
    }

    @Test
    fun `calculateBounds with an extreme outlier handles extreme values without clipping`() {
        val values = listOf(6.5, 88.89)
        val bounds = ChartRangeCalculator.calculateBounds(values)

        assertNotNull(bounds)
        bounds!!

        assertEquals(6.5, bounds.minConsumption, 0.001)
        assertEquals(88.89, bounds.maxConsumption, 0.001)

        val dataRange = 88.89 - 6.5 // 82.39
        val padding = 82.39 * 0.15 // 12.3585
        assertEquals(padding, bounds.padding, 0.001)

        // 6.5 - 12.3585 = -5.8585 -> coerced to 0.0
        assertEquals(0.0, bounds.chartMin, 0.001)
        assertEquals(88.89 + padding, bounds.chartMax, 0.001)
        assertTrue(bounds.chartMax > 88.89)
    }
}
