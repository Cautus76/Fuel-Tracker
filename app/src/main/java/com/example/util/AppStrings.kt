package com.example.util

import com.example.ui.model.AppLanguage
import com.example.ui.model.FuelSortOption

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

    fun sortByLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Řadit dle"
        AppLanguage.ENG -> "Sort by"
        AppLanguage.ESP -> "Ordenar por"
        AppLanguage.DE -> "Sortieren nach"
    }

    fun sortOptionLabel(option: FuelSortOption, lang: AppLanguage): String = when (option) {
        FuelSortOption.DATE_DESC -> when (lang) {
            AppLanguage.CZ -> "podle data – od nejnovějších"
            AppLanguage.ENG -> "by date – newest first"
            AppLanguage.ESP -> "por fecha – más reciente primero"
            AppLanguage.DE -> "nach Datum – neueste zuerst"
        }
        FuelSortOption.DATE_ASC -> when (lang) {
            AppLanguage.CZ -> "podle data – od nejstarších"
            AppLanguage.ENG -> "by date – oldest first"
            AppLanguage.ESP -> "por fecha – más antigua primero"
            AppLanguage.DE -> "nach Datum – älteste zuerst"
        }
        FuelSortOption.PRICE_ASC -> when (lang) {
            AppLanguage.CZ -> "podle ceny – od nejnižší"
            AppLanguage.ENG -> "by price – lowest first"
            AppLanguage.ESP -> "por precio – más bajo primero"
            AppLanguage.DE -> "nach Preis – niedrigster zuerst"
        }
        FuelSortOption.PRICE_DESC -> when (lang) {
            AppLanguage.CZ -> "podle ceny – od nejvyšší"
            AppLanguage.ENG -> "by price – highest first"
            AppLanguage.ESP -> "por precio – más alto primero"
            AppLanguage.DE -> "nach Preis – höchster zuerst"
        }
        FuelSortOption.QUANTITY_ASC -> when (lang) {
            AppLanguage.CZ -> "podle litrů – od nejmenšího množství"
            AppLanguage.ENG -> "by litres – lowest amount first"
            AppLanguage.ESP -> "por litros – menor cantidad primero"
            AppLanguage.DE -> "nach Litern – geringste Menge zuerst"
        }
        FuelSortOption.QUANTITY_DESC -> when (lang) {
            AppLanguage.CZ -> "podle litrů – od největšího množství"
            AppLanguage.ENG -> "by litres – highest amount first"
            AppLanguage.ESP -> "por litros – mayor cantidad primero"
            AppLanguage.DE -> "nach Litern – höchste Menge zuerst"
        }
    }

    fun exportDataCsv(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Exportovat data (CSV)"
        AppLanguage.ENG -> "Export data (CSV)"
        AppLanguage.ESP -> "Exportar datos (CSV)"
        AppLanguage.DE -> "Daten exportieren (CSV)"
    }

    fun importDataCsv(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Importovat data (CSV)"
        AppLanguage.ENG -> "Import data (CSV)"
        AppLanguage.ESP -> "Importar datos (CSV)"
        AppLanguage.DE -> "Daten importieren (CSV)"
    }

    fun importSuccess(count: Int, lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Úspěšně importováno $count záznamů"
        AppLanguage.ENG -> "Successfully imported $count records"
        AppLanguage.ESP -> "Se importaron $count registros con éxito"
        AppLanguage.DE -> "Erfolgreich $count Einträge importiert"
    }

    fun importFailed(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Import se nezdařil nebo soubor neobsahuje platná data"
        AppLanguage.ENG -> "Import failed or file contains no valid data"
        AppLanguage.ESP -> "Error en la importación o archivo sin datos válidos"
        AppLanguage.DE -> "Import fehlgeschlagen oder keine gültigen Daten"
    }

    fun exportDialogTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Export dat"
        AppLanguage.ENG -> "Export Data"
        AppLanguage.ESP -> "Exportar Datos"
        AppLanguage.DE -> "Daten exportieren"
    }

    fun exportDialogDesc(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Exportujte záznamy do CSV souboru pro Google Disk, Excel nebo e-mail."
        AppLanguage.ENG -> "Export records to a CSV file for Google Drive, Excel, or Email."
        AppLanguage.ESP -> "Exporte registros a un archivo CSV para Google Drive, Excel o correo electrónico."
        AppLanguage.DE -> "Exportieren Sie Einträge in eine CSV-Datei für Google Drive, Excel oder E-Mail."
    }

    fun exportOptionShare(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Sdílet přes aplikaci"
        AppLanguage.ENG -> "Share via App"
        AppLanguage.ESP -> "Compartir por aplicación"
        AppLanguage.DE -> "Über App teilen"
    }

    fun exportOptionShareDesc(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Odeslat do Google Disk, Gmail, WhatsApp nebo jiné aplikace se spolehlivým přístupem."
        AppLanguage.ENG -> "Send to Google Drive, Gmail, WhatsApp or other apps with reliable access."
        AppLanguage.ESP -> "Enviar a Google Drive, Gmail, WhatsApp u otras aplicaciones."
        AppLanguage.DE -> "An Google Drive, Gmail, WhatsApp oder andere Apps senden."
    }

    fun exportOptionSave(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Uložit do zařízení / Google Disk"
        AppLanguage.ENG -> "Save to device / Google Drive"
        AppLanguage.ESP -> "Guardar en dispositivo / Google Drive"
        AppLanguage.DE -> "Auf Gerät / Google Drive speichern"
    }

    fun exportOptionSaveDesc(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Vyberte cílovou složku v telefonu nebo na Google Disku pro přímé uložení."
        AppLanguage.ENG -> "Select a folder on device or Google Drive for direct saving."
        AppLanguage.ESP -> "Seleccione una carpeta en el dispositivo o Google Drive para guardar."
        AppLanguage.DE -> "Wählen Sie einen Ordner auf dem Gerät oder Google Drive zum direkten Speichern."
    }

    fun exportSaveSuccess(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Soubor byl úspěšně uložen"
        AppLanguage.ENG -> "File was successfully saved"
        AppLanguage.ESP -> "El archivo se guardó con éxito"
        AppLanguage.DE -> "Datei wurde erfolgreich gespeichert"
    }

    fun exportSaveError(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Při ukládání souboru došlo k chybě"
        AppLanguage.ENG -> "Error saving file"
        AppLanguage.ESP -> "Error al guardar el archivo"
        AppLanguage.DE -> "Fehler beim Speichern der Datei"
    }

    fun workEmailLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Pracovní / Kontaktní e-mail (volitelné)"
        AppLanguage.ENG -> "Work / Contact Email (optional)"
        AppLanguage.ESP -> "Correo laboral / contacto (opcional)"
        AppLanguage.DE -> "Arbeits- / Kontakt-E-Mail (optional)"
    }

    fun workEmailHint(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Při odeslání e-mailem bude automaticky předvyplněn příjemce."
        AppLanguage.ENG -> "When sending via email, the recipient will be prefilled."
        AppLanguage.ESP -> "Al enviar por correo, el destinatario se completará automáticamente."
        AppLanguage.DE -> "Beim E-Mail-Versand wird der Empfänger vorausgefüllt."
    }

    fun exportShareTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Exportovat záznamy o tankování"
        AppLanguage.ENG -> "Export refueling records"
        AppLanguage.ESP -> "Exportar registros de repostaje"
        AppLanguage.DE -> "Betankungsdaten exportieren"
    }

    fun exportShareSubject(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Export tankování - Kniha jízd a paliva"
        AppLanguage.ENG -> "Fuel Export - Refueling Records"
        AppLanguage.ESP -> "Exportación de combustible - Registros de repostaje"
        AppLanguage.DE -> "Kraftstoffexport - Tankaufzeichnungen"
    }

    fun exportShareText(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Přikládám CSV soubor se všemi záznamy o tankování."
        AppLanguage.ENG -> "Attached is the CSV file with all refueling records."
        AppLanguage.ESP -> "Adjunto el archivo CSV con todos los registros de repostaje."
        AppLanguage.DE -> "Anbei finden Sie die CSV-Datei mit allen Tankaufzeichnungen."
    }

    fun noDataToExport(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Žádná data k exportu. Nejprve přidejte záznam o tankování."
        AppLanguage.ENG -> "No data to export. Please add a refueling record first."
        AppLanguage.ESP -> "No hay datos para exportar. Agregue un registro de repostaje primero."
        AppLanguage.DE -> "Keine Daten zum Exportieren. Bitte fügen Sie zuerst einen Tankeintrag hinzu."
    }

    fun vehicleSetupTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Nastavení vozidla"
        AppLanguage.ENG -> "Vehicle Setup"
        AppLanguage.ESP -> "Configuración del vehículo"
        AppLanguage.DE -> "Fahrzeugeinstellungen"
    }

    fun vehicleSetupSubtitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Zadejte SPZ, název auta a vyberte paliva, která budete tankovat."
        AppLanguage.ENG -> "Enter license plate, car name and select fuels you refuel."
        AppLanguage.ESP -> "Ingrese matrícula, nombre del coche y seleccione combustibles."
        AppLanguage.DE -> "Geben Sie Kennzeichen, Autonamen und Kraftstoffe ein."
    }

    fun carNameLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Název auta"
        AppLanguage.ENG -> "Car name"
        AppLanguage.ESP -> "Nombre del coche"
        AppLanguage.DE -> "Autiname"
    }

    fun carNamePlaceholder(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "např. Škoda Octavia"
        AppLanguage.ENG -> "e.g. Škoda Octavia"
        AppLanguage.ESP -> "ej. Škoda Octavia"
        AppLanguage.DE -> "z.B. Škoda Octavia"
    }

    fun spzLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "SPZ (Registrační značka)"
        AppLanguage.ENG -> "License Plate"
        AppLanguage.ESP -> "Matrícula"
        AppLanguage.DE -> "Kennzeichen"
    }

    fun spzPlaceholder(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "např. 1ABC234"
        AppLanguage.ENG -> "e.g. 1ABC234"
        AppLanguage.ESP -> "ej. 1ABC234"
        AppLanguage.DE -> "z.B. 1ABC234"
    }

    fun selectFuelsHeader(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Paliva, která budete tankovat:"
        AppLanguage.ENG -> "Fuels you will refuel:"
        AppLanguage.ESP -> "Combustibles que repostará:"
        AppLanguage.DE -> "Kraftstoffe, die Sie tanken:"
    }

    fun setupValidationError(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Vyplňte název auta, SPZ a vyberte alespoň jedno palivo."
        AppLanguage.ENG -> "Please enter car name, license plate and select at least one fuel."
        AppLanguage.ESP -> "Ingrese el nombre del coche, matrícula y seleccione al menos un combustible."
        AppLanguage.DE -> "Bitte geben Sie Autonamen, Kennzeichen ein und wählen Sie mindestens einen Kraftstoff."
    }

    fun saveAndContinue(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Uložit a pokračovat"
        AppLanguage.ENG -> "Save and continue"
        AppLanguage.ESP -> "Guardar y continuar"
        AppLanguage.DE -> "Speichern und fortfahren"
    }

    fun editVehicleProfile(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Profil vozidla"
        AppLanguage.ENG -> "Vehicle profile"
        AppLanguage.ESP -> "Perfil del vehículo"
        AppLanguage.DE -> "Fahrzeugprofil"
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
        AppLanguage.ESP -> "¿Eliminar registro?"
        AppLanguage.DE -> "Eintrag löschen?"
    }

    fun deleteConfirmMsg(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Opravdu chcete smazat tento záznam o tankování? Tuto akci nelze vrátit zpět."
        AppLanguage.ENG -> "Are you sure you want to delete this refueling record? This action cannot be undone."
        AppLanguage.ESP -> "¿Está seguro de eliminar este registro de repostaje? Esta acción no se puede deshacer."
        AppLanguage.DE -> "Möchten Sie diesen Betankungseintrag wirklich löschen? Diese Aktion kann nicht rückgängig gemacht werden."
    }

    fun deleteRecordConfirmMsg(dateStr: String, odoStr: String, lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Opravdu chcete smazat záznam z $dateStr (tachometr: $odoStr km)? Tuto akci nelze vrátit zpět."
        AppLanguage.ENG -> "Are you sure you want to delete the record from $dateStr (odometer: $odoStr km)? This action cannot be undone."
        AppLanguage.ESP -> "¿Está seguro de eliminar el registro del $dateStr (cuentakilómetros: $odoStr km)? Esta acción no se puede deshacer."
        AppLanguage.DE -> "Möchten Sie den Eintrag vom $dateStr (Kilometerstand: $odoStr km) wirklich löschen? Diese Aktion kann nicht rückgängig gemacht werden."
    }

    fun clearAllStep1Title(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Vymazat všechna data?"
        AppLanguage.ENG -> "Clear all data?"
        AppLanguage.ESP -> "¿Borrar todos los datos?"
        AppLanguage.DE -> "Alle Daten löschen?"
    }

    fun clearAllStep1Msg(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Chcete vymazat všechna data aplikace? Tento krok odstraní všechny záznamy o tankování, statistiky a historii."
        AppLanguage.ENG -> "Do you want to clear all application data? This step will remove all refueling records, statistics, and history."
        AppLanguage.ESP -> "¿Desea borrar todos los datos de la aplicación? Este paso eliminará todos los registros de repostaje, estadísticas e historial."
        AppLanguage.DE -> "Möchten Sie alle Anwendungsdaten löschen? Dieser Schritt entfernt alle Betankungseinträge, Statistiken und Historie."
    }

    fun continueButton(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Pokračovat"
        AppLanguage.ENG -> "Continue"
        AppLanguage.ESP -> "Continuar"
        AppLanguage.DE -> "Weiter"
    }

    fun clearAllStep2Title(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Trvalé odstranění"
        AppLanguage.ENG -> "Permanent deletion"
        AppLanguage.ESP -> "Eliminación permanente"
        AppLanguage.DE -> "Dauerhafte Löschung"
    }

    fun clearAllStep2Msg(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Chystáte se trvale odstranit všechny záznamy o tankování, statistiky a historii. Tuto akci nelze vrátit zpět a odstraněná data nebude možné obnovit.\n\nPro potvrzení napište DELETE."
        AppLanguage.ENG -> "You are about to permanently delete all refueling records, statistics, and history. This action cannot be undone and deleted data cannot be recovered.\n\nType DELETE to confirm."
        AppLanguage.ESP -> "Está a punto de eliminar permanentemente todos los registros de repostaje, estadísticas e historial. Esta acción no se puede deshacer y los datos no se podrán recuperar.\n\nEscribe DELETE para confirmar."
        AppLanguage.DE -> "Sie sind dabei, alle Betankungseinträge, Statistiken und Historie dauerhaft zu löschen. Diese Aktion kann nicht rückgängig gemacht werden und die Daten können nicht wiederhergestellt werden.\n\nGeben Sie zur Bestätigung DELETE ein."
    }

    fun permanentlyDeleteButton(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Trvale smazat"
        AppLanguage.ENG -> "Permanently delete"
        AppLanguage.ESP -> "Eliminar permanentemente"
        AppLanguage.DE -> "Dauerhaft löschen"
    }

    fun typeDeletePlaceholder(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Napište DELETE"
        AppLanguage.ENG -> "Type DELETE"
        AppLanguage.ESP -> "Escriba DELETE"
        AppLanguage.DE -> "Geben Sie DELETE ein"
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

    fun changeTheme(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Změnit barevný motiv"
        AppLanguage.ENG -> "Change color theme"
        AppLanguage.ESP -> "Cambiar tema de color"
        AppLanguage.DE -> "Farbthema ändern"
    }

    fun themeTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Barevný motiv"
        AppLanguage.ENG -> "Color Theme"
        AppLanguage.ESP -> "Tema de Color"
        AppLanguage.DE -> "Farbmotiv"
    }

    fun themeDarkBlue(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Tmavě modrá (výchozí)"
        AppLanguage.ENG -> "Dark Blue (default)"
        AppLanguage.ESP -> "Azul oscuro (predeterminado)"
        AppLanguage.DE -> "Dunkelblau (Standard)"
    }

    fun themePastelMint(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Pastelově mátová"
        AppLanguage.ENG -> "Pastel Mint"
        AppLanguage.ESP -> "Menta pastel"
        AppLanguage.DE -> "Pastell-Minze"
    }

    fun themePastelLavender(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Pastelově fialová"
        AppLanguage.ENG -> "Pastel Lavender"
        AppLanguage.ESP -> "Lavanda pastel"
        AppLanguage.DE -> "Pastell-Lavendel"
    }

    fun themePastelCoral(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Pastelově korálová"
        AppLanguage.ENG -> "Pastel Coral"
        AppLanguage.ESP -> "Coral pastel"
        AppLanguage.DE -> "Pastell-Koralle"
    }

    fun themePastelAmber(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Pastelově jantarová"
        AppLanguage.ENG -> "Pastel Amber"
        AppLanguage.ESP -> "Ámbar pastel"
        AppLanguage.DE -> "Pastell-Bernstein"
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

    fun retroactiveWarningTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Zpětné zadání tankování"
        AppLanguage.ENG -> "Retroactive refueling entry"
        AppLanguage.ESP -> "Entrada de repostaje retroactiva"
        AppLanguage.DE -> "Nachträgliche Betankungseingabe"
    }

    fun retroactiveWarningMsg(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Zadáváte tankování s minulým datem nebo nižším stavem tachometru, než má nejnovější záznam.\n\nToto zadání vloží záznam do historie a přepočítá spotřebu paliva a statistiky. Chcete toto tankování uložit?"
        AppLanguage.ENG -> "You are entering a refueling record with a past date or lower odometer reading than the latest record.\n\nThis entry will insert the record into history and recalculate fuel consumption and statistics. Do you want to save this record?"
        AppLanguage.ESP -> "Está ingresando un repostaje con una fecha pasada o un kilometraje inferior al del último registro.\n\nEsta entrada insertará el registro en el historial y recalculará el consumo de combustible y las estadísticas. ¿Desea guardar este registro?"
        AppLanguage.DE -> "Sie geben einen Tankvorgang mit einem vergangenem Datum oder einem niedrigeren Kilometerstand als der neueste Eintrag ein.\n\nDieser Eintrag wird in die Historie eingefügt und berechnet den Kraftstoffverbrauch und die Statistiken neu. Möchten Sie diesen Eintrag speichern?"
    }

    fun retroactiveConfirm(lang: AppLanguage): String = when (lang) {
        AppLanguage.CZ -> "Ano, uložit"
        AppLanguage.ENG -> "Yes, save"
        AppLanguage.ESP -> "Sí, guardar"
        AppLanguage.DE -> "Ja, speichern"
    }

    fun odometerError(result: OdometerValidator.ValidationResult.Invalid, lang: AppLanguage): String = when (result) {
        is OdometerValidator.ValidationResult.Invalid.EmptyOrInvalidNumber -> when (lang) {
            AppLanguage.CZ -> "Zadejte platný stav tachometru"
            AppLanguage.ENG -> "Enter a valid odometer reading"
            AppLanguage.ESP -> "Introduzca un valor de cuentakilómetros válido"
            AppLanguage.DE -> "Geben Sie einen gültigen Kilometerstand ein"
        }
        is OdometerValidator.ValidationResult.Invalid.NegativeValue -> when (lang) {
            AppLanguage.CZ -> "Stav tachometru nesmí být záporný"
            AppLanguage.ENG -> "Odometer reading cannot be negative"
            AppLanguage.ESP -> "El cuentakilómetros no puede ser negativo"
            AppLanguage.DE -> "Der Kilometerstand darf nicht negativ sein"
        }
        is OdometerValidator.ValidationResult.Invalid.ZeroNotAllowedWithPreviousRecords -> when (lang) {
            AppLanguage.CZ -> "Hodnota 0 km je povolena pouze pro první záznam vozidla"
            AppLanguage.ENG -> "0 km is only allowed for the first vehicle record"
            AppLanguage.ESP -> "0 km solo está permitido para el primer registro del vehículo"
            AppLanguage.DE -> "0 km ist nur für den ersten Fahrzeugeintrag erlaubt"
        }
        is OdometerValidator.ValidationResult.Invalid.MustBeGreaterThanPrevious -> when (lang) {
            AppLanguage.CZ -> "Stav tachometru musí být vyšší než předchozí (${DateUtils.formatNumber(result.previousOdometer, 0, lang)} km)"
            AppLanguage.ENG -> "Odometer reading must be higher than previous (${DateUtils.formatNumber(result.previousOdometer, 0, lang)} km)"
            AppLanguage.ESP -> "El cuentakilómetros debe ser superior al anterior (${DateUtils.formatNumber(result.previousOdometer, 0, lang)} km)"
            AppLanguage.DE -> "Der Kilometerstand muss höher sein als der vorherige (${DateUtils.formatNumber(result.previousOdometer, 0, lang)} km)"
        }
    }
}
