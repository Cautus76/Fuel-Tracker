package com.example.util

import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.FileProvider
import com.example.data.model.FuelRecord
import com.example.ui.model.AppLanguage
import java.io.File
import java.time.LocalDate
import java.util.Locale

object CsvExporter {

    fun getExportFileName(): String {
        val dateStr = LocalDate.now().toString()
        return "tankovani_export_$dateStr.csv"
    }

    fun generateCsvContent(records: List<FuelRecord>): String {
        val sb = StringBuilder()
        // UTF-8 BOM so Microsoft Excel correctly detects Czech character encoding
        sb.append("\uFEFF")
        sb.append("Datum;Tachometr (km);Litry (l);Cena celkem (Kč);Cena za litr (Kč);Typ paliva;Čerpací stanice\n")

        records.sortedBy { it.date }.forEach { record ->
            val pricePerLitre = if (record.litres > 0) record.totalPrice / record.litres else 0.0
            val formattedPricePerLitre = String.format(Locale.US, "%.2f", pricePerLitre)
            sb.append("${record.date};")
            sb.append("${record.odometer};")
            sb.append("${record.litres};")
            sb.append("${record.totalPrice};")
            sb.append("${formattedPricePerLitre};")
            sb.append("${record.fuelType};")
            sb.append("${record.stationName.replace(";", ",")}\n")
        }
        return sb.toString()
    }

    fun writeRecordsToUri(context: Context, uri: Uri, records: List<FuelRecord>): Boolean {
        return try {
            val csvContent = generateCsvContent(records)
            context.contentResolver.openOutputStream(uri)?.use { outputStream ->
                outputStream.write(csvContent.toByteArray(Charsets.UTF_8))
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    fun exportAndShareRecords(
        context: Context,
        records: List<FuelRecord>,
        lang: AppLanguage
    ) {
        val csvData = generateCsvContent(records)
        val fileName = getExportFileName()
        val exportDir = File(context.cacheDir, "exports").apply { mkdirs() }
        val exportFile = File(exportDir, fileName)
        exportFile.writeText(csvData, Charsets.UTF_8)

        val uri = FileProvider.getUriForFile(
            context,
            "${context.packageName}.fileprovider",
            exportFile
        )

        val clipData = ClipData.newRawUri(AppStrings.exportShareTitle(lang), uri)

        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/csv"
            putExtra(Intent.EXTRA_SUBJECT, AppStrings.exportShareSubject(lang))
            putExtra(Intent.EXTRA_TEXT, AppStrings.exportShareText(lang))
            putExtra(Intent.EXTRA_STREAM, uri)
            this.clipData = clipData
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }

        val chooser = Intent.createChooser(shareIntent, AppStrings.exportShareTitle(lang)).apply {
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }

        // Explicitly grant read permission to all matched packages to ensure first-pass success in apps like Google Drive
        val resInfoList = context.packageManager.queryIntentActivities(
            shareIntent,
            android.content.pm.PackageManager.MATCH_DEFAULT_ONLY
        )
        for (resolveInfo in resInfoList) {
            val packageName = resolveInfo.activityInfo.packageName
            context.grantUriPermission(packageName, uri, Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }

        context.startActivity(chooser)
    }
}
