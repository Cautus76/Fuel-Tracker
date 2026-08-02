# Fuel Tracker

[English](#english) · [Čeština](#čeština) · [Deutsch](#deutsch) · [Español](#español)

---

## English

Fuel Tracker is a simple Android application for recording refuelling data, monitoring fuel consumption, and tracking vehicle operating costs.

The application is free and open-source software.

### Features

* Record refuelling date
* Record odometer reading
* Record fuel quantity
* Record fuel price
* Calculate fuel consumption in l/100 km
* Calculate average fuel consumption
* Calculate total fuel expenditure
* Calculate average fuel price per litre
* Calculate average cost per kilometre
* Display fuel-consumption history in a chart
* Filter, edit, and delete refuelling records
* Localized interface

### Calculation method

Fuel consumption is calculated between consecutive refuelling records.

The first refuelling record establishes the initial odometer and fuel state. Because no previous distance exists, the first record is excluded from:

* fuel-consumption calculations;
* cost-per-kilometre calculations.

The first record remains included in:

* total fuel expenditure;
* total fuel quantity;
* average fuel price per litre.

### Recent changes

* Fixed the cost-per-kilometre calculation
* Fixed refuelling dates shifting by one day
* Removed misleading example values from input fields
* Improved dynamic scaling of the fuel-consumption chart
* Added padding to prevent chart points and curves from being clipped
* Added and updated calculation tests

### Known issues

* The first refuelling record still needs to accept an odometer reading of exactly `0 km`
* Deleting all application data still needs stronger protection with two confirmations and typing `DELETE`

### Privacy

Fuel Tracker is designed to store refuelling information locally on the user’s device.

The application does not require an account and is not intended to collect or transmit personal data.

---

## Čeština

Fuel Tracker je jednoduchá aplikace pro Android určená k evidenci tankování, sledování spotřeby paliva a provozních nákladů vozidla.

Aplikace je zdarma a má otevřený zdrojový kód.

### Funkce

* Evidence data tankování
* Evidence stavu tachometru
* Evidence množství paliva
* Evidence ceny paliva
* Výpočet spotřeby v l/100 km
* Výpočet průměrné spotřeby
* Výpočet celkových nákladů na palivo
* Výpočet průměrné ceny za litr
* Výpočet průměrných nákladů na kilometr
* Graf historie spotřeby
* Filtrování, úprava a mazání záznamů
* Vícejazyčné uživatelské rozhraní

### Způsob výpočtu

Spotřeba paliva se počítá mezi dvěma po sobě jdoucími tankováními.

První záznam určuje počáteční stav tachometru a paliva. Protože před ním neexistuje žádná ujetá vzdálenost, nezapočítává se do:

* výpočtu spotřeby;
* výpočtu nákladů na kilometr.

První záznam se nadále započítává do:

* celkových nákladů na palivo;
* celkového množství paliva;
* průměrné ceny za litr.

### Poslední změny

* Opraven výpočet nákladů na kilometr
* Opraven posun data tankování o jeden den
* Odstraněny matoucí ukázkové hodnoty ze vstupních polí
* Vylepšeno dynamické měřítko grafu spotřeby
* Přidána rezerva kolem křivky a bodů grafu
* Doplněny a upraveny testy výpočtů

### Známé problémy

* První záznam tankování zatím neumožňuje zadat stav tachometru přesně `0 km`
* Mazání všech dat ještě potřebuje dvojité potvrzení a zadání slova `DELETE`

### Soukromí

Fuel Tracker je navržen tak, aby ukládal údaje o tankování místně v zařízení uživatele.

Aplikace nevyžaduje účet a není určena ke shromažďování ani odesílání osobních údajů.

---

## Deutsch

Fuel Tracker ist eine einfache Android-Anwendung zur Erfassung von Tankvorgängen, zur Überwachung des Kraftstoffverbrauchs und zur Kontrolle der Fahrzeugkosten.

Die Anwendung ist kostenlos und quelloffen.

### Funktionen

* Tankdatum erfassen
* Kilometerstand erfassen
* Kraftstoffmenge erfassen
* Kraftstoffpreis erfassen
* Verbrauch in l/100 km berechnen
* Durchschnittsverbrauch berechnen
* Gesamte Kraftstoffkosten berechnen
* Durchschnittspreis pro Liter berechnen
* Durchschnittliche Kosten pro Kilometer berechnen
* Verbrauchsverlauf als Diagramm anzeigen
* Tankvorgänge filtern, bearbeiten und löschen
* Mehrsprachige Benutzeroberfläche

### Berechnungsmethode

Der Kraftstoffverbrauch wird zwischen zwei aufeinanderfolgenden Tankvorgängen berechnet.

Der erste Tankvorgang legt den anfänglichen Kilometerstand und Kraftstoffzustand fest. Da keine vorherige Fahrstrecke vorhanden ist, wird dieser Eintrag nicht berücksichtigt bei:

* der Verbrauchsberechnung;
* der Berechnung der Kosten pro Kilometer.

Der erste Eintrag bleibt enthalten bei:

* den gesamten Kraftstoffkosten;
* der gesamten Kraftstoffmenge;
* dem durchschnittlichen Preis pro Liter.

### Letzte Änderungen

* Berechnung der Kosten pro Kilometer korrigiert
* Verschiebung des Tankdatums um einen Tag behoben
* Irreführende Beispielwerte aus Eingabefeldern entfernt
* Dynamische Skalierung des Verbrauchsdiagramms verbessert
* Abstand hinzugefügt, damit Punkte und Kurven nicht abgeschnitten werden
* Berechnungstests ergänzt und aktualisiert

### Bekannte Probleme

* Beim ersten Tankvorgang muss ein Kilometerstand von genau `0 km` noch zugelassen werden
* Das Löschen aller Daten benötigt noch zwei Bestätigungen und die Eingabe von `DELETE`

### Datenschutz

Fuel Tracker speichert Tankdaten lokal auf dem Gerät des Benutzers.

Die Anwendung benötigt kein Benutzerkonto und ist nicht dafür vorgesehen, personenbezogene Daten zu sammeln oder zu übertragen.

---

## Español

Fuel Tracker es una aplicación sencilla para Android destinada a registrar repostajes, controlar el consumo de combustible y seguir los costes de funcionamiento del vehículo.

La aplicación es gratuita y de código abierto.

### Funciones

* Registrar la fecha del repostaje
* Registrar el kilometraje
* Registrar la cantidad de combustible
* Registrar el precio del combustible
* Calcular el consumo en l/100 km
* Calcular el consumo medio
* Calcular el gasto total de combustible
* Calcular el precio medio por litro
* Calcular el coste medio por kilómetro
* Mostrar el historial de consumo en un gráfico
* Filtrar, editar y eliminar registros
* Interfaz localizada

### Método de cálculo

El consumo de combustible se calcula entre dos repostajes consecutivos.

El primer registro establece el kilometraje y el estado inicial del combustible. Como no existe una distancia anterior, ese registro se excluye de:

* los cálculos de consumo;
* los cálculos del coste por kilómetro.

El primer registro sigue incluyéndose en:

* el gasto total de combustible;
* la cantidad total de combustible;
* el precio medio por litro.

### Cambios recientes

* Corregido el cálculo del coste por kilómetro
* Corregido el desplazamiento de la fecha de repostaje en un día
* Eliminados los valores de ejemplo confusos de los campos de entrada
* Mejorada la escala dinámica del gráfico de consumo
* Añadido margen para evitar que los puntos y la curva queden recortados
* Añadidas y actualizadas pruebas de cálculo

### Problemas conocidos

* El primer repostaje todavía debe permitir un kilometraje inicial de exactamente `0 km`
* La eliminación de todos los datos todavía necesita dos confirmaciones y escribir `DELETE`

### Privacidad

Fuel Tracker está diseñado para guardar la información de repostaje localmente en el dispositivo del usuario.

La aplicación no requiere una cuenta y no está destinada a recopilar ni transmitir datos personales.

---

## Development

The application was created with the assistance of Google AI Studio and reviewed by the author.

Fuel Tracker is an independent project and is not an official Google product.

## Licence

This project is licensed under the GNU General Public License v3.0 or later.

See the [LICENSE](LICENSE) file for details.

## Author

Copyright © 2026 Tomáš Pokorný
