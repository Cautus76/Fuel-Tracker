package com.example.util

import com.example.ui.model.AppLanguage
import java.text.NumberFormat
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.Locale

object DateUtils {

    fun getLocale(lang: AppLanguage = AppLanguage.CZ): Locale {
        return Locale(lang.localeLanguage, lang.localeCountry)
    }

    fun todayIsoDate(): String {
        return LocalDate.now().toString()
    }

    fun formatDate(dateStr: String, lang: AppLanguage = AppLanguage.CZ): String {
        if (dateStr.isBlank()) return ""
        val localDate = try {
            LocalDate.parse(dateStr)
        } catch (e: Exception) {
            return dateStr
        }
        val pattern = when (lang) {
            AppLanguage.CZ -> "d. M. yyyy"
            AppLanguage.ENG -> "MMM d, yyyy"
            AppLanguage.ESP -> "d 'de' MMM, yyyy"
            AppLanguage.DE -> "dd.MM.yyyy"
        }
        val formatter = DateTimeFormatter.ofPattern(pattern, getLocale(lang))
        return localDate.format(formatter)
    }

    fun formatDateShort(dateStr: String, lang: AppLanguage = AppLanguage.CZ): String {
        if (dateStr.isBlank()) return ""
        val localDate = try {
            LocalDate.parse(dateStr)
        } catch (e: Exception) {
            return dateStr
        }
        val pattern = when (lang) {
            AppLanguage.CZ -> "d. MMM"
            AppLanguage.ENG -> "MMM d"
            AppLanguage.ESP -> "d MMM"
            AppLanguage.DE -> "dd. MMM"
        }
        val formatter = DateTimeFormatter.ofPattern(pattern, getLocale(lang))
        return localDate.format(formatter)
    }

    fun isoToEpochMillis(dateStr: String): Long {
        return try {
            LocalDate.parse(dateStr).atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli()
        } catch (e: Exception) {
            LocalDate.now().atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli()
        }
    }

    fun epochMillisToIso(millis: Long): String {
        return Instant.ofEpochMilli(millis).atZone(ZoneOffset.UTC).toLocalDate().toString()
    }

    fun formatNumber(value: Double, decimals: Int = 2, lang: AppLanguage = AppLanguage.CZ): String {
        val nf = NumberFormat.getNumberInstance(getLocale(lang)).apply {
            minimumFractionDigits = decimals
            maximumFractionDigits = decimals
        }
        return nf.format(value)
    }

    fun formatInt(value: Int, lang: AppLanguage = AppLanguage.CZ): String {
        val nf = NumberFormat.getNumberInstance(getLocale(lang))
        return nf.format(value)
    }

    fun formatCurrency(value: Double, lang: AppLanguage = AppLanguage.CZ): String {
        val nf = NumberFormat.getNumberInstance(getLocale(lang)).apply {
            minimumFractionDigits = 0
            maximumFractionDigits = 2
        }
        val numStr = nf.format(value)
        return when (lang) {
            AppLanguage.CZ -> "$numStr Kč"
            AppLanguage.ENG -> "$$numStr"
            AppLanguage.ESP, AppLanguage.DE -> "$numStr €"
        }
    }
}

