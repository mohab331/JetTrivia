package com.example.jettrivia.screens.common_widgets

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.unit.dp
import com.example.jettrivia.utils.AppColors

@Composable
fun DottedLine(
    color: Color = AppColors.mLightGray,
    thickness: Float = 2f,
    intervals: FloatArray = floatArrayOf(10f, 10f)
) {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(thickness.dp)
    ) {
        drawLine(
            color = color,
            start = Offset(0f, 0f),
            end = Offset(size.width, 0f),
            strokeWidth = thickness,
            pathEffect = PathEffect.dashPathEffect(intervals, 0f)
        )
    }
}