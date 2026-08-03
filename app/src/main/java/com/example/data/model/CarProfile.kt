package com.example.data.model

data class CarProfile(
    val carName: String = "",
    val spz: String = "",
    val allowedFuelTypes: List<String> = ALL_FUEL_TYPES,
    val isSetupComplete: Boolean = false
) {
    companion object {
        val ALL_FUEL_TYPES = listOf("Benzín", "Nafta", "LPG", "CNG", "Elektro")
    }
}
