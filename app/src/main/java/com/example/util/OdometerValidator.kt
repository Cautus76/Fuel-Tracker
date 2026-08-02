package com.example.util

import com.example.data.model.FuelRecord

object OdometerValidator {

    sealed class ValidationResult {
        object Valid : ValidationResult()
        sealed class Invalid : ValidationResult() {
            object EmptyOrInvalidNumber : Invalid()
            object NegativeValue : Invalid()
            object ZeroNotAllowedWithPreviousRecords : Invalid()
            data class MustBeGreaterThanPrevious(val previousOdometer: Double) : Invalid()
        }
    }

    /**
     * Determines the previous odometer reading for a new or edited record.
     * Returns null if no previous record exists (i.e. this is the first record).
     */
    fun getPreviousOdometer(rawRecords: List<FuelRecord>, editingRecord: FuelRecord?): Double? {
        if (editingRecord == null) {
            return rawRecords.maxOfOrNull { it.odometer }
        } else {
            val otherRecords = rawRecords.filter { it.id != editingRecord.id }
            if (otherRecords.isEmpty()) return null
            val earlierRecords = otherRecords.filter {
                it.odometer < editingRecord.odometer ||
                        (it.odometer == editingRecord.odometer && it.date <= editingRecord.date)
            }
            return earlierRecords.maxOfOrNull { it.odometer }
        }
    }

    /**
     * Validates an odometer value against previous odometer reading.
     * @param odometer parsed odometer value, or null if input was empty/not a number
     * @param previousOdometer previous record's odometer reading, or null if no previous record exists
     */
    fun validate(odometer: Double?, previousOdometer: Double?): ValidationResult {
        if (odometer == null) {
            return ValidationResult.Invalid.EmptyOrInvalidNumber
        }
        if (odometer < 0.0) {
            return ValidationResult.Invalid.NegativeValue
        }
        if (previousOdometer == null) {
            // First record: 0.0 and positive values are valid
            return ValidationResult.Valid
        } else {
            // Subsequent record: 0 km not allowed, and value must be > previousOdometer
            if (odometer <= previousOdometer) {
                if (odometer == 0.0) {
                    return ValidationResult.Invalid.ZeroNotAllowedWithPreviousRecords
                }
                return ValidationResult.Invalid.MustBeGreaterThanPrevious(previousOdometer)
            }
            return ValidationResult.Valid
        }
    }
}
