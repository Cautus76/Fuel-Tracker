package com.example.util

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ClearAllProtectionValidatorTest {

    @Test
    fun `exact uppercase DELETE is valid`() {
        assertTrue(ClearAllProtectionValidator.isConfirmationValid("DELETE"))
    }

    @Test
    fun `leading and trailing spaces are ignored`() {
        assertTrue(ClearAllProtectionValidator.isConfirmationValid("   DELETE   "))
        assertTrue(ClearAllProtectionValidator.isConfirmationValid("\tDELETE\n"))
    }

    @Test
    fun `lowercase delete is invalid`() {
        assertFalse(ClearAllProtectionValidator.isConfirmationValid("delete"))
    }

    @Test
    fun `mixed case Delete is invalid`() {
        assertFalse(ClearAllProtectionValidator.isConfirmationValid("Delete"))
    }

    @Test
    fun `incomplete text or empty string is invalid`() {
        assertFalse(ClearAllProtectionValidator.isConfirmationValid(""))
        assertFalse(ClearAllProtectionValidator.isConfirmationValid("   "))
        assertFalse(ClearAllProtectionValidator.isConfirmationValid("DEL"))
        assertFalse(ClearAllProtectionValidator.isConfirmationValid("DELETE!"))
    }
}
