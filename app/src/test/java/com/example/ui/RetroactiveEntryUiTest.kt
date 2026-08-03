package com.example.ui

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.example.data.model.FuelRecord
import com.example.ui.components.AddEditFuelDialog
import com.example.ui.model.AppLanguage
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class RetroactiveEntryUiTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `entering retroactive record triggers warning dialog and triggers onSave only after user consent`() {
        var savedRecord: FuelRecord? = null
        val existingRecord = FuelRecord(
            id = 1,
            date = "2026-08-01",
            odometer = 1000.0,
            litres = 40.0,
            totalPrice = 1600.0,
            fuelType = "BA95",
            stationName = "Shell"
        )

        composeTestRule.setContent {
            AddEditFuelDialog(
                editingRecord = null,
                previousOdometer = 1000.0,
                rawRecords = listOf(existingRecord),
                onDismiss = {},
                onSave = { date, odo, lit, pri, fuel, station ->
                    savedRecord = FuelRecord(
                        date = date,
                        odometer = odo,
                        litres = lit,
                        totalPrice = pri,
                        fuelType = fuel,
                        stationName = station
                    )
                },
                lang = AppLanguage.CZ
            )
        }

        // Enter a retroactive record (odometer 500 km < 1000 km)
        composeTestRule.onNodeWithTag("odometer_input").performTextInput("500")
        composeTestRule.onNodeWithTag("litres_input").performTextInput("30")
        composeTestRule.onNodeWithTag("price_input").performTextInput("1200")

        // Click save button
        composeTestRule.onNodeWithTag("save_button").performClick()

        // Verify warning dialog appears and record is not saved yet
        composeTestRule.onNodeWithTag("retroactive_warning_dialog").assertIsDisplayed()
        assertEquals(null, savedRecord)

        // Click confirm in the warning dialog
        composeTestRule.onNodeWithTag("retroactive_confirm_button").performClick()

        // Verify record is saved
        assertTrue(savedRecord != null)
        assertEquals(500.0, savedRecord!!.odometer, 0.001)
    }
}
