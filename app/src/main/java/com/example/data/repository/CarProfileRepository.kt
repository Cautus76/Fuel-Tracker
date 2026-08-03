package com.example.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.example.data.model.CarProfile
import com.example.ui.theme.AppThemePalette

class CarProfileRepository(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("car_profile_prefs", Context.MODE_PRIVATE)

    fun getCarProfile(): CarProfile {
        val isSetupComplete = prefs.getBoolean("is_setup_complete", false)
        val carName = prefs.getString("car_name", "") ?: ""
        val spz = prefs.getString("spz", "") ?: ""
        val fuelTypesSet = prefs.getStringSet("allowed_fuel_types", null)

        val allowedFuelTypes = if (fuelTypesSet != null) {
            CarProfile.ALL_FUEL_TYPES.filter { fuelTypesSet.contains(it) }
        } else {
            CarProfile.ALL_FUEL_TYPES
        }

        return CarProfile(
            carName = carName,
            spz = spz,
            allowedFuelTypes = if (allowedFuelTypes.isEmpty()) CarProfile.ALL_FUEL_TYPES else allowedFuelTypes,
            isSetupComplete = isSetupComplete
        )
    }

    fun saveCarProfile(profile: CarProfile) {
        prefs.edit()
            .putString("car_name", profile.carName)
            .putString("spz", profile.spz)
            .putStringSet("allowed_fuel_types", profile.allowedFuelTypes.toSet())
            .putBoolean("is_setup_complete", profile.isSetupComplete)
            .apply()
    }

    fun getThemePalette(): AppThemePalette {
        val name = prefs.getString("theme_palette", AppThemePalette.DARK_BLUE.name) ?: AppThemePalette.DARK_BLUE.name
        return try {
            AppThemePalette.valueOf(name)
        } catch (e: Exception) {
            AppThemePalette.DARK_BLUE
        }
    }

    fun saveThemePalette(palette: AppThemePalette) {
        prefs.edit().putString("theme_palette", palette.name).apply()
    }
}

