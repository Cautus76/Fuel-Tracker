package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fuel_records")
data class FuelRecord(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: String = "",
    val odometer: Double,
    val litres: Double,
    val totalPrice: Double,
    val fuelType: String = "Benzín",
    val isFullTank: Boolean = true,
    val stationName: String = ""
) {
    // Calculated unit price (Kč / l)
    val pricePerLitre: Double
        get() = if (litres > 0) totalPrice / litres else 0.0
}
