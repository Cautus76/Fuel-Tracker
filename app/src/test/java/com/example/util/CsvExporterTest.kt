package com.example.util

import com.example.data.model.FuelRecord
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class CsvExporterTest {

    @Test
    fun `generateCsvContent includes UTF-8 BOM and correct header and data rows`() {
        val records = listOf(
            FuelRecord(
                id = 1,
                date = "2026-05-10",
                odometer = 120000.0,
                litres = 40.0,
                totalPrice = 1520.0,
                fuelType = "Benzín",
                stationName = "ORLEN Benzina"
            ),
            FuelRecord(
                id = 2,
                date = "2026-05-20",
                odometer = 120560.0,
                litres = 38.5,
                totalPrice = 1463.0,
                fuelType = "Benzín",
                stationName = "Shell; Express"
            )
        )

        val csv = CsvExporter.generateCsvContent(records)

        // Check UTF-8 BOM at beginning
        assertTrue(csv.startsWith("\uFEFF"))
        assertTrue(csv.contains("Datum;Tachometr (km);Litry (l);Cena celkem (Kč);Cena za litr (Kč);Typ paliva;Čerpací stanice"))

        // Check data lines
        assertTrue(csv.contains("2026-05-10;120000.0;40.0;1520.0;38.00;Benzín;ORLEN Benzina"))
        // Semicolons in station names should be converted to commas to avoid breaking CSV columns
        assertTrue(csv.contains("2026-05-20;120560.0;38.5;1463.0;38.00;Benzín;Shell, Express"))
    }
}
