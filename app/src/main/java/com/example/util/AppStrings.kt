package com.example.util

import com.example.ui.model.AppLanguage

object AppStrings {

    fun appName(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Tankování"
        AppLanguage.ENG -> "Fuel Tracker"
        AppLanguage.ESP -> "Repostaje"
        AppLanguage.DE -> "Tanken"
    }

    fun avgConsumption(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Průměrná spotřeba"
        AppLanguage.ENG -> "Average consumption"
        AppLanguage.ESP -> "Consumo medio"
        AppLanguage.DE -> "Durchschnittsverbrauch"
    }

    fun unitL100km(lang: AppLanguage): String = "l / 100 km"

    fun totalDistance(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Najeto celkem"
        AppLanguage.ENG -> "Total distance"
        AppLanguage.ESP -> "Distancia total"
        AppLanguage.DE -> "Gesamtdistanz"
    }

    fun totalCost(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Cena celkem"
        AppLanguage.ENG -> "Total cost"
        AppLanguage.ESP -> "Coste total"
        AppLanguage.DE -> "Gesamtkosten"
    }

    fun totalVolume(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Objem celkem"
        AppLanguage.ENG -> "Total volume"
        AppLanguage.ESP -> "Volumen total"
        AppLanguage.DE -> "Gesamtvolumen"
    }

    fun avgPricePerLitre(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Prům. cena/l"
        AppLanguage.ENG -> "Avg. price/l"
        AppLanguage.ESP -> "Precio med./l"
        AppLanguage.DE -> "Durchschn. Preis/l"
    }

    fun avgCostPerKm(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Cena za km"
        AppLanguage.ENG -> "Cost per km"
        AppLanguage.ESP -> "Coste por km"
        AppLanguage.DE -> "Kosten pro km"
    }

    fun totalRecords(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Záznamů"
        AppLanguage.ENG -> "Records"
        AppLanguage.ESP -> "Registros"
        AppLanguage.DE -> "Einträge"
    }

    fun recordsHistory(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Historie záznamů"
        AppLanguage.ENG -> "Refueling history"
        AppLanguage.ESP -> "Historial de repostajes"
        AppLanguage.DE -> "Tankhistorie"
    }

    fun searchPlaceholder(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Hledat stanici, palivo..."
        AppLanguage.ENG -> "Search station, fuel..."
        AppLanguage.ESP -> "Buscar estación, combustible..."
        AppLanguage.DE -> "Tankstelle, Kraftstoff suchen..."
    }

    fun allFuelTypes(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Všechna paliva"
        AppLanguage.ENG -> "All fuel types"
        AppLanguage.ESP -> "Todos los combustibles"
        AppLanguage.DE -> "Alle Kraftstoffarten"
    }

    fun addRecord(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Přidat tankování"
        AppLanguage.ENG -> "Add refueling"
        AppLanguage.ESP -> "Añadir repostaje"
        AppLanguage.DE -> "Betankung hinzufügen"
    }

    fun editRecord(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Upravit tankování"
        AppLanguage.ENG -> "Edit refueling"
        AppLanguage.ESP -> "Editar repostaje"
        AppLanguage.DE -> "Betankung bearbeiten"
    }

    fun odometer(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Stav tachometru (km)"
        AppLanguage.ENG -> "Odometer (km)"
        AppLanguage.ESP -> "Cuentakilómetros (km)"
        AppLanguage.DE -> "Kilometerstand (km)"
    }

    fun litres(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Objem paliva (l)"
        AppLanguage.ENG -> "Fuel volume (l)"
        AppLanguage.ESP -> "Volumen de combustible (l)"
        AppLanguage.DE -> "Kraftstoffmenge (l)"
    }

    fun totalPrice(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Celková cena"
        AppLanguage.ENG -> "Total price"
        AppLanguage.ESP -> "Precio total"
        AppLanguage.DE -> "Gesamtpreis"
    }

    fun fuelType(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Typ paliva"
        AppLanguage.ENG -> "Fuel type"
        AppLanguage.ESP -> "Tipo de combustible"
        AppLanguage.DE -> "Kraftstoffart"
    }

    fun stationName(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Čerpací stanice (volitelné)"
        AppLanguage.ENG -> "Gas station (optional)"
        AppLanguage.ESP -> "Gasolinera (opcional)"
        AppLanguage.DE -> "Tankstelle (optional)"
    }

    fun date(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Datum"
        AppLanguage.ENG -> "Date"
        AppLanguage.ESP -> "Fecha"
        AppLanguage.DE -> "Datum"
    }

    fun cancel(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Zrušit"
        AppLanguage.ENG -> "Cancel"
        AppLanguage.ESP -> "Cancelar"
        AppLanguage.DE -> "Abbrechen"
    }

    fun save(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Uložit"
        AppLanguage.ENG -> "Save"
        AppLanguage.ESP -> "Guardar"
        AppLanguage.DE -> "Speichern"
    }

    fun delete(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Smazat"
        AppLanguage.ENG -> "Delete"
        AppLanguage.ESP -> "Eliminar"
        AppLanguage.DE -> "Löschen"
    }

    fun deleteConfirmTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Smazat záznam?"
        AppLanguage.ENG -> "Delete record?"
        AppLanguage.ESP -> "¡Eliminar registro?"
        AppLanguage.DE -> "Eintrag löschen?"
    }

    fun deleteConfirmMsg(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Opravdu chcete smazat tento záznam o tankování?"
        AppLanguage.ENG -> "Are you sure you want to delete this refueling record?"
        AppLanguage.ESP -> "¿Está seguro de eliminar este registro de repostaje?"
        AppLanguage.DE -> "Möchten Sie diesen Betankungseintrag wirklich löschen?"
    }

    fun clearAllConfirmTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Vymazat všechna data?"
        AppLanguage.ENG -> "Clear all data?"
        AppLanguage.ESP -> "¿Borrar todos los datos?"
        AppLanguage.DE -> "Alle Daten löschen?"
    }

    fun clearAllConfirmMsg(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Opravdu chcete smazat všechny záznamy o tankování? Tuto akci nelze vrátit zpět."
        AppLanguage.ENG -> "Are you sure you want to delete all refueling records? This action cannot be undone."
        AppLanguage.ESP -> "¿Está seguro de que desea eliminar todos los registros de repostaje? Esta acción no se puede deshacer."
        AppLanguage.DE -> "Möchten Sie wirklich alle Betankungseinträge löschen? Diese Aktion kann nicht rückgängig gemacht werden."
    }

    fun emptyStateTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Zatím žádné záznamy"
        AppLanguage.ENG -> "No records yet"
        AppLanguage.ESP -> "Aún no hay registros"
        AppLanguage.DE -> "Noch keine Einträge"
    }

    fun emptyStateSub(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Přidejte své první tankování pomocí tlačítka +, nebo načtěte ukázková data v menu."
        AppLanguage.ENG -> "Add your first refueling using the + button, or load sample data from the menu."
        AppLanguage.ESP -> "Añada su primer repostaje con el botón +, o cargue datos de muestra en el menú."
        AppLanguage.DE -> "Fügen Sie Ihre erste Betankung mit der Schaltfläche + hinzu oder laden Sie Beispieldaten aus dem Menü."
    }

    fun noSearchResultsTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Nenalezeny žádné výsledky"
        AppLanguage.ENG -> "No results found"
        AppLanguage.ESP -> "No se encontraron resultados"
        AppLanguage.DE -> "Keine Ergebnisse gefunden"
    }

    fun noSearchResultsSub(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Zkuste změnit vyhledávaný výraz nebo filtr paliva."
        AppLanguage.ENG -> "Try changing your search term or fuel filter."
        AppLanguage.ESP -> "Pruebe a cambiar el término de búsqueda o el filtro de combustible."
        AppLanguage.DE -> "Versuchen Sie, den Suchbegriff oder den Kraftstofffilter zu ändern."
    }

    fun loadSampleData(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Načíst ukázková data"
        AppLanguage.ENG -> "Load sample data"
        AppLanguage.ESP -> "Cargar datos de muestra"
        AppLanguage.DE -> "Beispieldaten laden"
    }

    fun clearAllData(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Vymazat všechna data"
        AppLanguage.ENG -> "Clear all data"
        AppLanguage.ESP -> "Borrar todos los datos"
        AppLanguage.DE -> "Alle Daten löschen"
    }

    fun language(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Jazyk"
        AppLanguage.ENG -> "Language"
        AppLanguage.ESP -> "Idioma"
        AppLanguage.DE -> "Sprache"
    }

    fun recordSaved(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Záznam byl uložen"
        AppLanguage.ENG -> "Record saved"
        AppLanguage.ESP -> "Registro guardado"
        AppLanguage.DE -> "Eintrag gespeichert"
    }

    fun recordUpdated(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Záznam byl upraven"
        AppLanguage.ENG -> "Record updated"
        AppLanguage.ESP -> "Registro modificado"
        AppLanguage.DE -> "Eintrag bearbeitet"
    }

    fun recordDeleted(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Záznam byl smazán"
        AppLanguage.ENG -> "Record deleted"
        AppLanguage.ESP -> "Registro eliminado"
        AppLanguage.DE -> "Eintrag gelöscht"
    }

    fun sampleDataLoaded(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Ukázková data byla načtena"
        AppLanguage.ENG -> "Sample data loaded"
        AppLanguage.ESP -> "Datos de muestra cargados"
        AppLanguage.DE -> "Beispieldaten geladen"
    }

    fun allDataCleared(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Všechny záznamy byly smazány"
        AppLanguage.ENG -> "All records deleted"
        AppLanguage.ESP -> "Todos los registros eliminados"
        AppLanguage.DE -> "Alle Einträge gelöscht"
    }

    fun consumptionChartTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Vývoj spotřeby"
        AppLanguage.ENG -> "Consumption trend"
        AppLanguage.ESP -> "Evolución del consumo"
        AppLanguage.DE -> "Verbrauchsverlauf"
    }

    fun firstRefuelNotice(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "První tankování (výpočet spotřeby proběhne při dalším tankování)"
        AppLanguage.ENG -> "First refueling (consumption will be calculated on next refueling)"
        AppLanguage.ESP -> "Primer repostaje (el cálculo del consumo se hará en el siguiente repostaje)"
        AppLanguage.DE -> "Erste Betankung (Verbrauchsberechnung erfolgt bei der nächsten Betankung)"
    }

    fun selectLanguage(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Vybrat jazyk"
        AppLanguage.ENG -> "Select language"
        AppLanguage.ESP -> "Seleccionar idioma"
        AppLanguage.DE -> "Sprache auswählen"
    }
}
