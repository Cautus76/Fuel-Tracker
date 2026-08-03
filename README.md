# Fuel Tracker

[English](#english) · [Čeština](#čeština) · [Deutsch](#deutsch) · [Español](#español)

---

## English

Fuel Tracker is a free and open-source Android application for recording refuelling data, monitoring fuel consumption, and tracking vehicle operating costs.

The application stores its data locally on the user’s device and does not require an account.

### Features

* Record refuelling date
* Record odometer reading
* Record fuel quantity
* Record total fuel price
* Record fuel type and filling station
* Add and edit refuelling records
* Add older refuelling records with a confirmation warning
* Calculate fuel consumption in l/100 km
* Calculate average fuel consumption
* Calculate total fuel expenditure
* Calculate average fuel price per litre
* Calculate average cost per kilometre
* Display fuel-consumption history in a chart
* Sort refuelling history by:

  * date, newest or oldest first;
  * total price, lowest or highest first;
  * fuel quantity, lowest or highest first
* Configure vehicle name and licence plate
* Select the fuel types used by the vehicle
* Hide fuel types that are not enabled in the vehicle profile
* Load sample data when the application is empty
* Prevent repeated loading of sample data
* Export refuelling data to CSV
* Share exported files through any compatible application installed on the device
* Import refuelling data from CSV
* Delete all application data with `DELETE` confirmation
* Localized interface in English, Czech, German, and Spanish

### First launch

When Fuel Tracker is launched for the first time, the user is asked to configure the vehicle profile.

The setup includes:

* vehicle name;
* licence plate;
* fuel types used by the vehicle.

Only the selected fuel types are displayed when filtering records or adding a new refuelling entry.

The vehicle profile can later be changed from the application menu.

### Sample data

When the application does not contain any refuelling records, the user can load a set of sample data from the vehicle settings.

The sample dataset contains ten realistic refuelling records and can be used to test the charts, statistics, sorting, and other application features.

Sample data cannot be loaded again while records already exist, preventing duplicate entries.

### Retroactive refuelling records

Fuel Tracker allows the user to add a refuelling record with:

* a date earlier than the newest existing record;
* an odometer reading lower than the current maximum.

Before such a record is saved, the application displays a warning explaining that inserting it into the history may recalculate fuel consumption and statistics.

The record is saved only after explicit user confirmation.

### Sorting

The refuelling history is sorted by date from newest to oldest by default.

The sorting dropdown above the history supports:

* date — newest first;
* date — oldest first;
* total price — lowest first;
* total price — highest first;
* fuel quantity — lowest first;
* fuel quantity — highest first.

The selected sorting method remains visible in the dropdown.

### Data export

Fuel Tracker exports refuelling records as a CSV file encoded in UTF-8 with BOM for compatibility with applications such as:

* Microsoft Excel;
* Google Sheets;
* Apple Numbers;
* LibreOffice Calc.

The exported file contains:

* date;
* odometer reading;
* fuel quantity;
* total price;
* price per litre;
* fuel type;
* filling station.

The application uses the standard Android sharing menu. The user can therefore send or save the file using any compatible application installed on the device, including email clients, cloud storage applications, file managers, and spreadsheet applications.

Fuel Tracker does not require a Google account and is not directly connected to Google Drive or Gmail.

### Data import

Fuel Tracker can import refuelling records from a compatible CSV file selected from the device storage.

Before relying on an imported file as a complete backup, the import result should be reviewed in the application.

### Calculation method

Fuel consumption is calculated between consecutive refuelling records.

The first refuelling record establishes the initial odometer and fuel state. Because no previous distance exists, the first record is excluded from:

* fuel-consumption calculations;
* cost-per-kilometre calculations.

The first record remains included in:

* total fuel expenditure;
* total fuel quantity;
* average fuel price per litre.

When an older record is inserted into the history, consumption and statistics for subsequent records may be recalculated.

### Deleting all data

Deleting all application data requires the user to manually enter:

`DELETE`

The confirmation field is empty when the dialog opens, and the delete button remains disabled until the correct confirmation text is entered.

After all data has been deleted, the application returns to the initial vehicle setup and again offers the option to load sample data.

### Recent changes

* Added vehicle profile setup on first launch
* Added vehicle name, licence plate, and fuel-type selection
* Added filtering based on enabled fuel types
* Added ten sample refuelling records
* Prevented repeated loading of sample data
* Added support for retroactive refuelling records with confirmation
* Added a sorting dropdown above the refuelling history
* Added ascending and descending sorting by date, price, and fuel quantity
* Added CSV export through the Android system sharing menu
* Added CSV import from device storage
* Added stronger protection when deleting all data
* Added and updated unit and user-interface tests
* Added English, Czech, German, and Spanish localization for new features
* Fixed the cost-per-kilometre calculation
* Fixed refuelling dates shifting by one day
* Improved dynamic scaling and padding of the fuel-consumption chart

### Known limitations

* Fuel Tracker currently manages one vehicle profile at a time
* CSV import should be reviewed after completion to verify that all records were interpreted correctly
* Service records, maintenance reminders, receipt scanning, GPS station tracking, and multi-vehicle management are not currently available

### Privacy

Fuel Tracker stores refuelling information locally on the user’s device.

The application:

* does not require an account;
* does not require Google sign-in;
* is not designed to collect personal data;
* does not automatically upload refuelling records;
* shares exported data only after an explicit user action.

---

## Čeština

Fuel Tracker je bezplatná open-source aplikace pro Android určená k evidenci tankování, sledování spotřeby paliva a provozních nákladů vozidla.

Aplikace ukládá data místně v zařízení uživatele a nevyžaduje žádný účet.

### Funkce

* Evidence data tankování
* Evidence stavu tachometru
* Evidence množství paliva
* Evidence celkové ceny tankování
* Evidence druhu paliva a čerpací stanice
* Přidávání a úprava záznamů o tankování
* Zadávání starších tankování s potvrzovacím upozorněním
* Výpočet spotřeby v l/100 km
* Výpočet průměrné spotřeby
* Výpočet celkových nákladů na palivo
* Výpočet průměrné ceny za litr
* Výpočet průměrných nákladů na kilometr
* Graf historie spotřeby
* Řazení historie podle:

  * data od nejnovějších nebo nejstarších;
  * celkové ceny od nejnižší nebo nejvyšší;
  * množství paliva od nejmenšího nebo největšího
* Nastavení názvu vozidla a SPZ
* Výběr používaných druhů paliva
* Skrytí druhů paliva, které nejsou povoleny v profilu vozidla
* Načtení ukázkových dat, pokud je aplikace prázdná
* Ochrana proti opakovanému načtení ukázkových dat
* Export záznamů do CSV
* Sdílení exportovaného souboru prostřednictvím libovolné kompatibilní aplikace v telefonu
* Import záznamů z CSV
* Smazání všech dat s potvrzením pomocí slova `DELETE`
* Uživatelské rozhraní v češtině, angličtině, němčině a španělštině

### První spuštění

Při prvním spuštění aplikace Fuel Tracker je uživatel vyzván k nastavení profilu vozidla.

Nastavení obsahuje:

* název vozidla;
* SPZ;
* druhy paliva, které vozidlo používá.

Při filtrování historie a zadávání nového tankování se následně zobrazují pouze vybrané druhy paliva.

Profil vozidla lze později upravit v nabídce aplikace.

### Ukázková data

Pokud aplikace neobsahuje žádné záznamy o tankování, může uživatel v nastavení vozidla načíst ukázková data.

Ukázková sada obsahuje deset realistických záznamů a umožňuje vyzkoušet grafy, statistiky, řazení a další funkce aplikace.

Pokud již v aplikaci nějaké záznamy jsou, ukázková data nelze načíst znovu. Tím je zabráněno jejich zdvojení.

### Zpětné zadávání tankování

Fuel Tracker umožňuje vložit záznam s:

* datem starším než nejnovější uložený záznam;
* stavem tachometru nižším než dosavadní maximum.

Před uložením takového záznamu aplikace zobrazí upozornění, že jeho vložení do historie může změnit výpočet spotřeby a statistik.

Záznam se uloží až po výslovném potvrzení uživatelem.

### Řazení historie

Historie tankování je ve výchozím nastavení seřazena podle data od nejnovějších záznamů.

Rozbalovací nabídka nad historií umožňuje zvolit:

* datum — od nejnovějších;
* datum — od nejstarších;
* celkovou cenu — od nejnižší;
* celkovou cenu — od nejvyšší;
* množství paliva — od nejmenšího;
* množství paliva — od největšího.

Aktuálně zvolený způsob řazení zůstává v rozbalovacím poli viditelný.

### Export dat

Fuel Tracker exportuje záznamy do souboru CSV s kódováním UTF-8 a BOM. Díky tomu lze soubor otevřít se správnou českou diakritikou například v:

* Microsoft Excelu;
* Google Tabulkách;
* Apple Numbers;
* LibreOffice Calc.

Exportovaný soubor obsahuje:

* datum;
* stav tachometru;
* množství paliva;
* celkovou cenu;
* cenu za litr;
* druh paliva;
* čerpací stanici.

Aplikace používá standardní systémovou nabídku sdílení Androidu. Uživatel si proto může vybrat libovolnou kompatibilní aplikaci nainstalovanou v telefonu, například e-mailový klient, cloudové úložiště, správce souborů nebo tabulkový procesor.

Fuel Tracker nevyžaduje účet Google a není přímo propojen s Google Diskem ani Gmailem.

### Import dat

Fuel Tracker umožňuje importovat záznamy z kompatibilního souboru CSV vybraného z úložiště zařízení.

Po importu je vhodné výsledné záznamy zkontrolovat, než bude soubor považován za úplnou a ověřenou zálohu.

### Způsob výpočtu

Spotřeba paliva se počítá mezi dvěma po sobě jdoucími tankováními.

První záznam určuje počáteční stav tachometru a paliva. Protože před ním neexistuje žádná ujetá vzdálenost, nezapočítává se do:

* výpočtu spotřeby;
* výpočtu nákladů na kilometr.

První záznam se nadále započítává do:

* celkových nákladů na palivo;
* celkového množství paliva;
* průměrné ceny za litr.

Pokud je do historie vloženo starší tankování, mohou se spotřeba a statistiky následujících záznamů přepočítat.

### Smazání všech dat

Pro smazání všech dat musí uživatel ručně zadat:

`DELETE`

Potvrzovací pole je při otevření dialogu prázdné. Tlačítko pro definitivní smazání zůstává neaktivní, dokud uživatel nezadá správný potvrzovací text.

Po smazání všech dat se aplikace vrátí k úvodnímu nastavení vozidla a znovu nabídne možnost načíst ukázková data.

### Poslední změny

* Přidáno nastavení profilu vozidla při prvním spuštění
* Přidán název vozidla, SPZ a výběr druhů paliva
* Přidána filtrace podle paliv povolených v profilu
* Přidáno deset ukázkových záznamů o tankování
* Zamezeno opakovanému načítání ukázkových dat
* Přidána možnost zadávat starší tankování s potvrzovacím upozorněním
* Přidána rozbalovací nabídka pro řazení historie
* Přidáno vzestupné a sestupné řazení podle data, ceny a množství paliva
* Přidán export CSV přes systémovou nabídku sdílení Androidu
* Přidán import CSV z úložiště zařízení
* Přidána silnější ochrana při smazání všech dat
* Doplněny a aktualizovány jednotkové a uživatelské testy
* Doplněny české, anglické, německé a španělské překlady nových funkcí
* Opraven výpočet nákladů na kilometr
* Opraven posun data tankování o jeden den
* Vylepšeno dynamické měřítko a odsazení grafu spotřeby

### Známá omezení

* Fuel Tracker v současnosti spravuje pouze jeden profil vozidla
* Po importu CSV je vhodné zkontrolovat, zda byly všechny záznamy správně rozpoznány
* Aplikace zatím neobsahuje servisní deník, připomínky údržby, skenování účtenek, evidenci GPS polohy čerpacích stanic ani správu více vozidel

### Soukromí

Fuel Tracker ukládá informace o tankování místně v zařízení uživatele.

Aplikace:

* nevyžaduje uživatelský účet;
* nevyžaduje přihlášení ke Googlu;
* není určena ke shromažďování osobních údajů;
* automaticky nenahrává záznamy na internet;
* sdílí exportovaná data pouze po výslovné akci uživatele.

---

## Deutsch

Fuel Tracker ist eine kostenlose und quelloffene Android-Anwendung zur Erfassung von Tankvorgängen, zur Überwachung des Kraftstoffverbrauchs und zur Kontrolle der Fahrzeugkosten.

Die Anwendung speichert ihre Daten lokal auf dem Gerät und benötigt kein Benutzerkonto.

### Funktionen

* Tankdatum erfassen
* Kilometerstand erfassen
* Kraftstoffmenge erfassen
* Gesamtkosten eines Tankvorgangs erfassen
* Kraftstoffart und Tankstelle erfassen
* Tankvorgänge hinzufügen und bearbeiten
* Ältere Tankvorgänge nach einer Warnung und Bestätigung einfügen
* Verbrauch in l/100 km berechnen
* Durchschnittsverbrauch berechnen
* Gesamte Kraftstoffkosten berechnen
* Durchschnittspreis pro Liter berechnen
* Durchschnittliche Kosten pro Kilometer berechnen
* Verbrauchsverlauf als Diagramm anzeigen
* Tankhistorie sortieren nach:

  * Datum, neueste oder älteste zuerst;
  * Gesamtpreis, niedrigster oder höchster zuerst;
  * Kraftstoffmenge, kleinste oder größte zuerst
* Fahrzeugname und Kennzeichen konfigurieren
* Verwendete Kraftstoffarten auswählen
* Nicht aktivierte Kraftstoffarten ausblenden
* Beispieldaten laden, wenn die Anwendung leer ist
* Mehrfaches Laden von Beispieldaten verhindern
* Tankdaten als CSV exportieren
* Exportierte Dateien über jede kompatible installierte Anwendung teilen
* Tankdaten aus CSV importieren
* Alle Daten mit einer `DELETE`-Bestätigung löschen
* Benutzeroberfläche auf Englisch, Tschechisch, Deutsch und Spanisch

### Erster Start

Beim ersten Start von Fuel Tracker wird der Benutzer aufgefordert, ein Fahrzeugprofil einzurichten.

Die Einrichtung umfasst:

* Fahrzeugname;
* Kennzeichen;
* vom Fahrzeug verwendete Kraftstoffarten.

Beim Filtern der Historie und beim Hinzufügen eines neuen Tankvorgangs werden nur die ausgewählten Kraftstoffarten angezeigt.

Das Fahrzeugprofil kann später über das Anwendungsmenü geändert werden.

### Beispieldaten

Wenn die Anwendung noch keine Tankvorgänge enthält, kann der Benutzer in den Fahrzeugeinstellungen Beispieldaten laden.

Der Beispieldatensatz enthält zehn realistische Tankvorgänge und ermöglicht das Testen von Diagrammen, Statistiken, Sortierung und weiteren Funktionen.

Sobald bereits Datensätze vorhanden sind, können die Beispieldaten nicht erneut geladen werden. Dadurch werden doppelte Einträge verhindert.

### Nachträgliche Tankvorgänge

Fuel Tracker erlaubt das Einfügen eines Tankvorgangs mit:

* einem Datum vor dem neuesten vorhandenen Datensatz;
* einem Kilometerstand unter dem bisherigen Höchstwert.

Vor dem Speichern zeigt die Anwendung eine Warnung an, dass dadurch der Kraftstoffverbrauch und die Statistiken nachfolgender Datensätze neu berechnet werden können.

Der Datensatz wird nur nach ausdrücklicher Bestätigung gespeichert.

### Sortierung

Die Tankhistorie wird standardmäßig nach Datum mit dem neuesten Eintrag zuerst sortiert.

Das Auswahlmenü über der Historie unterstützt:

* Datum — neueste zuerst;
* Datum — älteste zuerst;
* Gesamtpreis — niedrigster zuerst;
* Gesamtpreis — höchster zuerst;
* Kraftstoffmenge — kleinste zuerst;
* Kraftstoffmenge — größte zuerst.

Die ausgewählte Sortiermethode bleibt im Auswahlfeld sichtbar.

### Datenexport

Fuel Tracker exportiert Tankdaten als CSV-Datei mit UTF-8-Kodierung und BOM. Dadurch ist die Datei unter anderem kompatibel mit:

* Microsoft Excel;
* Google Tabellen;
* Apple Numbers;
* LibreOffice Calc.

Die exportierte Datei enthält:

* Datum;
* Kilometerstand;
* Kraftstoffmenge;
* Gesamtpreis;
* Preis pro Liter;
* Kraftstoffart;
* Tankstelle.

Die Anwendung verwendet das standardmäßige Android-Menü zum Teilen. Der Benutzer kann die Datei daher mit jeder kompatiblen installierten Anwendung speichern oder versenden, beispielsweise mit einem E-Mail-Client, einer Cloud-Speicher-Anwendung, einem Dateimanager oder einer Tabellenkalkulation.

Fuel Tracker benötigt kein Google-Konto und ist nicht direkt mit Google Drive oder Gmail verbunden.

### Datenimport

Fuel Tracker kann Tankdaten aus einer kompatiblen CSV-Datei importieren, die aus dem Gerätespeicher ausgewählt wird.

Nach dem Import sollten die Datensätze überprüft werden, bevor die Datei als vollständig geprüfte Sicherung verwendet wird.

### Berechnungsmethode

Der Kraftstoffverbrauch wird zwischen zwei aufeinanderfolgenden Tankvorgängen berechnet.

Der erste Tankvorgang legt den anfänglichen Kilometerstand und Kraftstoffzustand fest. Da keine vorherige Fahrstrecke vorhanden ist, wird dieser Eintrag nicht berücksichtigt bei:

* der Verbrauchsberechnung;
* der Berechnung der Kosten pro Kilometer.

Der erste Eintrag bleibt enthalten bei:

* den gesamten Kraftstoffkosten;
* der gesamten Kraftstoffmenge;
* dem durchschnittlichen Preis pro Liter.

Wenn ein älterer Tankvorgang in die Historie eingefügt wird, können Verbrauch und Statistiken der folgenden Einträge neu berechnet werden.

### Alle Daten löschen

Zum Löschen aller Anwendungsdaten muss der Benutzer manuell Folgendes eingeben:

`DELETE`

Das Bestätigungsfeld ist beim Öffnen des Dialogs leer. Die Schaltfläche zum endgültigen Löschen bleibt deaktiviert, bis der richtige Bestätigungstext eingegeben wurde.

Nach dem Löschen aller Daten kehrt die Anwendung zur ersten Fahrzeugeinrichtung zurück und bietet erneut das Laden von Beispieldaten an.

### Letzte Änderungen

* Fahrzeugprofil beim ersten Start hinzugefügt
* Fahrzeugname, Kennzeichen und Kraftstoffauswahl hinzugefügt
* Filterung nach aktivierten Kraftstoffarten hinzugefügt
* Zehn Beispiel-Tankvorgänge hinzugefügt
* Mehrfaches Laden von Beispieldaten verhindert
* Nachträgliche Tankvorgänge mit Warnung und Bestätigung hinzugefügt
* Auswahlmenü zur Sortierung der Tankhistorie hinzugefügt
* Auf- und absteigende Sortierung nach Datum, Preis und Kraftstoffmenge hinzugefügt
* CSV-Export über das Android-Systemmenü hinzugefügt
* CSV-Import aus dem Gerätespeicher hinzugefügt
* Schutz beim Löschen aller Daten verbessert
* Unit- und Benutzeroberflächentests ergänzt und aktualisiert
* Neue Funktionen auf Englisch, Tschechisch, Deutsch und Spanisch lokalisiert
* Berechnung der Kosten pro Kilometer korrigiert
* Verschiebung des Tankdatums um einen Tag behoben
* Dynamische Skalierung und Abstände im Verbrauchsdiagramm verbessert

### Bekannte Einschränkungen

* Fuel Tracker verwaltet derzeit nur ein Fahrzeugprofil
* Nach einem CSV-Import sollte geprüft werden, ob alle Datensätze korrekt erkannt wurden
* Wartungsprotokolle, Wartungserinnerungen, Belegscannen, GPS-Erfassung von Tankstellen und Mehrfahrzeugverwaltung sind derzeit nicht verfügbar

### Datenschutz

Fuel Tracker speichert Tankdaten lokal auf dem Gerät des Benutzers.

Die Anwendung:

* benötigt kein Benutzerkonto;
* benötigt keine Google-Anmeldung;
* ist nicht dafür vorgesehen, personenbezogene Daten zu sammeln;
* lädt Tankdaten nicht automatisch ins Internet hoch;
* teilt exportierte Daten nur nach einer ausdrücklichen Benutzeraktion.

---

## Español

Fuel Tracker es una aplicación gratuita y de código abierto para Android destinada a registrar repostajes, controlar el consumo de combustible y seguir los costes de funcionamiento del vehículo.

La aplicación guarda los datos localmente en el dispositivo y no requiere una cuenta.

### Funciones

* Registrar la fecha del repostaje
* Registrar el kilometraje
* Registrar la cantidad de combustible
* Registrar el precio total del repostaje
* Registrar el tipo de combustible y la estación de servicio
* Añadir y editar repostajes
* Añadir repostajes anteriores con advertencia y confirmación
* Calcular el consumo en l/100 km
* Calcular el consumo medio
* Calcular el gasto total de combustible
* Calcular el precio medio por litro
* Calcular el coste medio por kilómetro
* Mostrar el historial de consumo en un gráfico
* Ordenar el historial por:

  * fecha, de más reciente a más antigua o al contrario;
  * precio total, de menor a mayor o al contrario;
  * cantidad de combustible, de menor a mayor o al contrario
* Configurar el nombre y la matrícula del vehículo
* Seleccionar los tipos de combustible utilizados
* Ocultar los combustibles no activados en el perfil del vehículo
* Cargar datos de ejemplo cuando la aplicación está vacía
* Evitar la carga repetida de datos de ejemplo
* Exportar repostajes a CSV
* Compartir el archivo exportado mediante cualquier aplicación compatible instalada
* Importar repostajes desde CSV
* Eliminar todos los datos mediante la confirmación `DELETE`
* Interfaz disponible en inglés, checo, alemán y español

### Primer inicio

Al iniciar Fuel Tracker por primera vez, se solicita al usuario que configure el perfil del vehículo.

La configuración incluye:

* nombre del vehículo;
* matrícula;
* tipos de combustible utilizados por el vehículo.

Al filtrar el historial o añadir un nuevo repostaje, solamente se muestran los combustibles seleccionados.

El perfil puede modificarse posteriormente desde el menú de la aplicación.

### Datos de ejemplo

Cuando la aplicación no contiene repostajes, el usuario puede cargar datos de ejemplo desde la configuración del vehículo.

El conjunto contiene diez repostajes realistas y permite probar los gráficos, estadísticas, opciones de ordenación y otras funciones.

Los datos de ejemplo no pueden volver a cargarse mientras existan registros, evitando así la creación de duplicados.

### Repostajes anteriores

Fuel Tracker permite añadir un repostaje con:

* una fecha anterior al registro más reciente;
* un kilometraje inferior al máximo actual.

Antes de guardarlo, la aplicación muestra una advertencia indicando que su inserción en el historial puede recalcular el consumo y las estadísticas de los registros posteriores.

El registro solamente se guarda después de la confirmación expresa del usuario.

### Ordenación

El historial de repostajes se ordena de forma predeterminada por fecha, mostrando primero los registros más recientes.

El menú desplegable situado sobre el historial permite elegir:

* fecha — más recientes primero;
* fecha — más antiguos primero;
* precio total — menor primero;
* precio total — mayor primero;
* cantidad de combustible — menor primero;
* cantidad de combustible — mayor primero.

La opción seleccionada permanece visible en el menú desplegable.

### Exportación de datos

Fuel Tracker exporta los repostajes en un archivo CSV codificado en UTF-8 con BOM para garantizar la compatibilidad con aplicaciones como:

* Microsoft Excel;
* Google Sheets;
* Apple Numbers;
* LibreOffice Calc.

El archivo exportado contiene:

* fecha;
* kilometraje;
* cantidad de combustible;
* precio total;
* precio por litro;
* tipo de combustible;
* estación de servicio.

La aplicación utiliza el menú estándar de uso compartido de Android. El usuario puede guardar o enviar el archivo mediante cualquier aplicación compatible instalada en el dispositivo, como clientes de correo electrónico, servicios de almacenamiento en la nube, administradores de archivos o aplicaciones de hojas de cálculo.

Fuel Tracker no requiere una cuenta de Google y no está conectado directamente con Google Drive ni Gmail.

### Importación de datos

Fuel Tracker puede importar repostajes desde un archivo CSV compatible seleccionado en el almacenamiento del dispositivo.

Después de la importación, se recomienda revisar los registros antes de considerar el archivo como una copia de seguridad completa y verificada.

### Método de cálculo

El consumo de combustible se calcula entre dos repostajes consecutivos.

El primer registro establece el kilometraje y el estado inicial del combustible. Como no existe una distancia anterior, ese registro se excluye de:

* los cálculos de consumo;
* los cálculos del coste por kilómetro.

El primer registro sigue incluyéndose en:

* el gasto total de combustible;
* la cantidad total de combustible;
* el precio medio por litro.

Cuando se inserta un repostaje anterior en el historial, el consumo y las estadísticas de los registros siguientes pueden recalcularse.

### Eliminación de todos los datos

Para eliminar todos los datos de la aplicación, el usuario debe escribir manualmente:

`DELETE`

El campo de confirmación aparece vacío al abrir el diálogo. El botón de eliminación permanece desactivado hasta que se introduce el texto correcto.

Después de eliminar todos los datos, la aplicación vuelve a la configuración inicial del vehículo y ofrece nuevamente la posibilidad de cargar datos de ejemplo.

### Cambios recientes

* Añadida la configuración del perfil del vehículo durante el primer inicio
* Añadidos el nombre del vehículo, la matrícula y la selección de combustibles
* Añadido el filtrado según los combustibles activados
* Añadidos diez repostajes de ejemplo
* Evitada la carga repetida de datos de ejemplo
* Añadidos repostajes anteriores con advertencia y confirmación
* Añadido un menú desplegable para ordenar el historial
* Añadida la ordenación ascendente y descendente por fecha, precio y cantidad de combustible
* Añadida la exportación CSV mediante el menú del sistema Android
* Añadida la importación CSV desde el almacenamiento del dispositivo
* Mejorada la protección al eliminar todos los datos
* Añadidas y actualizadas pruebas unitarias y de interfaz
* Traducidas las nuevas funciones al inglés, checo, alemán y español
* Corregido el cálculo del coste por kilómetro
* Corregido el desplazamiento de la fecha del repostaje en un día
* Mejoradas la escala dinámica y los márgenes del gráfico de consumo

### Limitaciones conocidas

* Fuel Tracker actualmente administra un solo perfil de vehículo
* Después de importar un archivo CSV, se recomienda comprobar que todos los registros se hayan interpretado correctamente
* Actualmente no están disponibles el historial de mantenimiento, los recordatorios de servicio, el escaneo de recibos, el registro GPS de estaciones ni la gestión de varios vehículos

### Privacidad

Fuel Tracker guarda la información de repostaje localmente en el dispositivo del usuario.

La aplicación:

* no requiere una cuenta;
* no requiere iniciar sesión en Google;
* no está diseñada para recopilar datos personales;
* no sube automáticamente los registros a Internet;
* solamente comparte los datos exportados después de una acción explícita del usuario.

---

## Development

The application was created with the assistance of Google AI Studio and reviewed by the author.

Fuel Tracker is an independent project and is not an official Google product.

The project uses automated unit and user-interface tests to verify calculations and selected application workflows.

## Licence

This project is licensed under the GNU General Public License v3.0 or later.

See the [LICENSE](LICENSE) file for details.

## Author

Copyright © 2026 Tomáš Pokorný
