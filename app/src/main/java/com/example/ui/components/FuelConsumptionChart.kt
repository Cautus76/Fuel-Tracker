package com.example.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ShowChart
import androidx.compose.material.icons.filled.ShowChart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ui.model.AppLanguage
import com.example.ui.model.FuelRecordUiItem
import com.example.util.AppStrings
import com.example.util.ChartRangeCalculator
import com.example.util.DateUtils

@Composable
fun FuelConsumptionChart(
    items: List<FuelRecordUiItem>,
    lang: AppLanguage = AppLanguage.CZ,
    modifier: Modifier = Modifier
) {
    // Filter items that have segment consumption computed
    // Note: items are passed in order (newest first). Let's sort them chronological (oldest first) for left-to-right chart rendering!
    val validPoints = items
        .filter { it.segmentConsumption != null && it.segmentConsumption > 0 }
        .sortedBy { it.record.odometer }

    if (validPoints.isEmpty()) return

    val values = validPoints.map { it.segmentConsumption!! }
    val bounds = ChartRangeCalculator.calculateBounds(values) ?: return

    val primaryColor = MaterialTheme.colorScheme.primary
    val secondaryColor = MaterialTheme.colorScheme.secondary
    val surfaceVariant = MaterialTheme.colorScheme.surfaceVariant
    val onSurfaceVariant = MaterialTheme.colorScheme.onSurfaceVariant

    val avgConsumption = values.average()

    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = surfaceVariant.copy(alpha = 0.6f)),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(28.dp)
                            .background(primaryColor.copy(alpha = 0.15f), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ShowChart,
                            contentDescription = null,
                            tint = primaryColor,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                    Text(
                        text = AppStrings.consumptionChartTitle(lang),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .background(primaryColor, CircleShape)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = AppStrings.unitL100km(lang),
                        style = MaterialTheme.typography.labelSmall,
                        color = onSurfaceVariant
                    )
                }
            }

            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)
            ) {
                val width = size.width
                val height = size.height
                val paddingX = 40f
                val paddingY = 20f

                val drawWidth = width - (paddingX * 2)
                val drawHeight = height - (paddingY * 2)

                val range = bounds.chartRange.toFloat()
                val chartMin = bounds.chartMin.toFloat()

                val yAvg = paddingY + drawHeight - (((avgConsumption - chartMin) / range) * drawHeight).toFloat()
                drawLine(
                    color = secondaryColor.copy(alpha = 0.4f),
                    start = Offset(paddingX, yAvg),
                    end = Offset(width - paddingX, yAvg),
                    strokeWidth = 2f,
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                )

                // Compute point coordinates
                val points = validPoints.mapIndexed { idx, item ->
                    val cons = (item.segmentConsumption ?: 0.0).toFloat()
                    val x = if (validPoints.size == 1) {
                        paddingX + drawWidth / 2f
                    } else {
                        paddingX + (idx.toFloat() / (validPoints.size - 1)) * drawWidth
                    }
                    val y = paddingY + drawHeight - (((cons - chartMin) / range) * drawHeight).toFloat()
                    Offset(x, y)
                }

                if (points.size >= 2) {
                    // Fill gradient under curve
                    val fillPath = Path().apply {
                        moveTo(points.first().x, height - paddingY)
                        lineTo(points.first().x, points.first().y)
                        for (i in 1 until points.size) {
                            val p1 = points[i - 1]
                            val p2 = points[i]
                            val controlX1 = p1.x + (p2.x - p1.x) / 2
                            val controlX2 = p1.x + (p2.x - p1.x) / 2
                            cubicTo(controlX1, p1.y, controlX2, p2.y, p2.x, p2.y)
                        }
                        lineTo(points.last().x, height - paddingY)
                        close()
                    }

                    drawPath(
                        path = fillPath,
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                primaryColor.copy(alpha = 0.35f),
                                primaryColor.copy(alpha = 0.02f)
                            )
                        )
                    )

                    // Line path
                    val linePath = Path().apply {
                        moveTo(points.first().x, points.first().y)
                        for (i in 1 until points.size) {
                            val p1 = points[i - 1]
                            val p2 = points[i]
                            val controlX1 = p1.x + (p2.x - p1.x) / 2
                            val controlX2 = p1.x + (p2.x - p1.x) / 2
                            cubicTo(controlX1, p1.y, controlX2, p2.y, p2.x, p2.y)
                        }
                    }

                    drawPath(
                        path = linePath,
                        color = primaryColor,
                        style = Stroke(width = 6f)
                    )
                }

                // Point dots
                for (pt in points) {
                    drawCircle(
                        color = Color.White,
                        radius = 8f,
                        center = pt
                    )
                    drawCircle(
                        color = primaryColor,
                        radius = 5f,
                        center = pt
                    )
                }
            }

            // Legend labels
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = DateUtils.formatDateShort(validPoints.first().record.date, lang = lang),
                    style = MaterialTheme.typography.labelSmall,
                    color = onSurfaceVariant
                )
                Text(
                    text = "${AppStrings.avgConsumption(lang)}: ${DateUtils.formatNumber(avgConsumption, lang = lang)} ${AppStrings.unitL100km(lang)}",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Medium,
                    color = secondaryColor
                )
                Text(
                    text = DateUtils.formatDateShort(validPoints.last().record.date, lang = lang),
                    style = MaterialTheme.typography.labelSmall,
                    color = onSurfaceVariant
                )
            }
        }
    }
}
