package com.example.util

import com.example.ui.model.AppLanguage
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateUtils {

    fun getLocale(lang: AppLanguage = AppLanguage.CZ): Locale {
        return Locale(lang.localeLanguage, lang.localeCountry)
    }

    fun formatDate(timeMillis: Long, lang: AppLanguage = AppLanguage.CZ): String {
        val pattern = when (lang) {
            AppLanguage.CZ -> "d. M. yyyy"
            AppLanguage.ENG -> "MMM d, yyyy"
            AppLanguage.ESP -> "d 'de' MMM, yyyy"
            AppLanguage.DE -> "dd.MM.yyyy"
        }
        val formatter = SimpleDateFormat(pattern, getLocale(lang))
        return formatter.format(Date(timeMillis))
    }

    fun formatDateShort(timeMillis: Long, lang: AppLanguage = AppLanguage.CZ): String {
        val pattern = when (lang) {
            AppLanguage.CZ -> "d. MMM"
            AppLanguage.ENG -> "MMM d"
            AppLanguage.ESP -> "d MMM"
            AppLanguage.DE -> "dd. MMM"
        }
        val formatter = SimpleDateFormat(pattern, getLocale(lang))
        return formatter.format(Date(timeMillis))
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

