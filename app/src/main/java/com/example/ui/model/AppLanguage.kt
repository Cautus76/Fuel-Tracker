package com.example.ui.model

enum class AppLanguage(
    val code: String,
    val displayName: String,
    val flagEmoji: String,
    val currencySymbol: String,
    val localeLanguage: String,
    val localeCountry: String
) {
    CZ("cs", "Čeština", "🇨🇿", "Kč", "cs", "CZ"),
    ENG("en", "English", "🇬🇧", "$", "en", "US"),
    ESP("es", "Español", "🇪🇸", "€", "es", "ES"),
    DE("de", "Deutsch", "🇩🇪", "€", "de", "DE")
}
