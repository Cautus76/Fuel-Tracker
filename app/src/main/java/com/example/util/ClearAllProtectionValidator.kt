package com.example.util

object ClearAllProtectionValidator {
    const val REQUIRED_CONFIRMATION_WORD = "DELETE"

    /**
     * Checks if the user typed confirmation matches "DELETE" (case-sensitive), ignoring leading/trailing whitespace.
     */
    fun isConfirmationValid(input: String): Boolean {
        return input.trim() == REQUIRED_CONFIRMATION_WORD
    }
}
