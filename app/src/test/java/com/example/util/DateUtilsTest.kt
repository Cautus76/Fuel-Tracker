package com.example.util

import com.example.ui.model.AppLanguage
import org.junit.Assert.assertEquals
import org.junit.Test

class DateUtilsTest {

    @Test
    fun `isoToEpochMillis and epochMillisToIso roundtrip preserves exact calendar date`() {
        val originalIso = "2026-08-01"
        val millis = DateUtils.isoToEpochMillis(originalIso)
        val resultIso = DateUtils.epochMillisToIso(millis)
        assertEquals(originalIso, resultIso)
    }

    @Test
    fun `formatDate formats ISO string correctly across supported languages`() {
        val isoDate = "2026-08-01"
        assertEquals("1. 8. 2026", DateUtils.formatDate(isoDate, AppLanguage.CZ))
        assertEquals("Aug 1, 2026", DateUtils.formatDate(isoDate, AppLanguage.ENG))
        assertEquals("01.08.2026", DateUtils.formatDate(isoDate, AppLanguage.DE))
        assert(DateUtils.formatDate(isoDate, AppLanguage.ESP).contains("2026"))
    }
}
