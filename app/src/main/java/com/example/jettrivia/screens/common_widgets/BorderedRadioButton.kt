package com.example.jettrivia.screens.common_widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jettrivia.utils.AppColors

@Composable
fun BorderedRadioButton(
    isSelected: Boolean,
    label: String,
    showErrorBorder: Boolean,
    onPressed: () -> Unit,
) {
    val borderColor = when {
        showErrorBorder -> Color.Red
        isSelected -> AppColors.mLightBlue
        else -> AppColors.mLightGray
    }
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {
                onPressed()
            }
            .border(
                BorderStroke(3.dp, borderColor),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
    ) {
        RadioButton(
            selected = isSelected,
            onClick = {
                onPressed()
            },
            colors = RadioButtonDefaults.colors(
                selectedColor = AppColors.mLightBlue,
                unselectedColor = AppColors.mLightGray
            ),
        )
        Spacer(modifier = Modifier.width(2.dp))
        Text(
            text = label,
            fontSize = 18.sp,
            color = AppColors.mLightGray,
            fontWeight = FontWeight.Medium
        )
    }
}