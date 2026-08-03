package com.example.data.repository

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.model.CarProfile
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class CarProfileRepositoryTest {

    private lateinit var repository: CarProfileRepository
    private lateinit var context: Context

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        val prefs = context.getSharedPreferences("car_profile_prefs", Context.MODE_PRIVATE)
        prefs.edit().clear().commit()
        repository = CarProfileRepository(context)
    }

    @Test
    fun `default profile when fresh install is not setup`() {
        val profile = repository.getCarProfile()
        assertFalse(profile.isSetupComplete)
        assertEquals("", profile.carName)
        assertEquals("", profile.spz)
        assertEquals(CarProfile.ALL_FUEL_TYPES, profile.allowedFuelTypes)
    }

    @Test
    fun `saving profile persists all fields correctly`() {
        val newProfile = CarProfile(
            carName = "Škoda Octavia",
            spz = "1ABC234",
            allowedFuelTypes = listOf("Benzín", "Nafta"),
            isSetupComplete = true
        )
        repository.saveCarProfile(newProfile)

        val loaded = repository.getCarProfile()
        assertTrue(loaded.isSetupComplete)
        assertEquals("Škoda Octavia", loaded.carName)
        assertEquals("1ABC234", loaded.spz)
        assertEquals(listOf("Benzín", "Nafta"), loaded.allowedFuelTypes)
    }
}
