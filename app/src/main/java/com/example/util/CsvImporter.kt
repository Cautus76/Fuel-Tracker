package com.example.util

import com.example.data.model.FuelRecord
import java.io.InputStream
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object CsvImporter {

    fun parseCsv(inputStream: InputStream): List<FuelRecord> {
        val content = inputStream.bufferedReader(Charsets.UTF_8).use { it.readText() }
        return parseCsvString(content)
    }

    fun parseCsvString(content: String): List<FuelRecord> {
        val cleanContent = content.removePrefix("\uFEFF").trim()
        if (cleanContent.isBlank()) return emptyList()

        val lines = cleanContent.lines().filter { it.isNotBlank() }
        if (lines.isEmpty()) return emptyList()

        val records = mutableListOf<FuelRecord>()

        for ((index, line) in lines.withIndex()) {
            // Skip header if line contains column names
            if (index == 0 && (line.contains("Datum", ignoreCase = true) || line.contains("Date", ignoreCase = true) || line.contains("Tachometr", ignoreCase = true))) {
                continue
            }

            val delimiter = if (line.contains(";")) ";" else ","
            val parts = line.split(delimiter).map { it.trim() }

            if (parts.size < 4) continue

            val rawDate = parts.getOrNull(0) ?: ""
            val rawOdometer = parts.getOrNull(1) ?: "0"
            val rawLitres = parts.getOrNull(2) ?: "0"
            val rawTotalPrice = parts.getOrNull(3) ?: "0"
            
            // Optional fields
            val fuelType = if (parts.size >= 6) parts[5].ifBlank { "Benzín" } else "Benzín"
            val stationName = if (parts.size >= 7) parts[6].replace(",", ";") else ""

            val parsedDate = parseDate(rawDate) ?: continue
            val odometer = parseDouble(rawOdometer) ?: continue
            val litres = parseDouble(rawLitres) ?: continue
            val totalPrice = parseDouble(rawTotalPrice) ?: continue

            if (odometer > 0 && litres > 0 && totalPrice > 0) {
                records.add(
                    FuelRecord(
                        date = parsedDate,
                        odometer = odometer,
                        litres = litres,
                        totalPrice = totalPrice,
                        fuelType = fuelType,
                        isFullTank = true,
                        stationName = stationName
                    )
                )
            }
        }

        return records
    }

    private fun parseDate(raw: String): String? {
        val clean = raw.trim()
        if (clean.matches(Regex("\\d{4}-\\d{2}-\\d{2}"))) {
            return clean
        }

        // Try d.M.yyyy or d. M. yyyy
        val dmyRegex = Regex("^(\\d{1,2})\\.\\s*(\\d{1,2})\\.\\s*(\\d{4})$")
        val match = dmyRegex.find(clean)
        if (match != null) {
            val (d, m, y) = match.destructured
            val day = d.padStart(2, '0')
            val month = m.padStart(2, '0')
            return "$y-$month-$day"
        }

        return try {
            LocalDate.parse(clean).toString()
        } catch (e: Exception) {
            null
        }
    }

    private fun parseDouble(raw: String): Double? {
        val clean = raw.replace(" ", "").replace(",", ".")
        return clean.toDoubleOrNull()
    }
}
