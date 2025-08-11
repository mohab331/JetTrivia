package com.example.jettrivia.screens.questions.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jettrivia.data.models.response.QuestionItemModel
import com.example.jettrivia.screens.common_widgets.BorderedRadioButton
import com.example.jettrivia.utils.AppColors

@Composable
fun QuestionWidget(
    question: QuestionItemModel?,
    selectedChoiceIndex: Int?,
    onChoiceSelected: ((index: Int) -> Unit)? = null,
    isAnswerCorrect: Boolean?,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = question?.question ?: "",
            fontSize = 20.sp,
            color = AppColors.mOffWhite,
            fontWeight = FontWeight.Medium,
        )
        Spacer(modifier = Modifier.height(8.dp))
        question?.choices?.forEachIndexed { index, option ->
            BorderedRadioButton(
                label = option,
                isSelected = (selectedChoiceIndex == index),
                showErrorBorder = isAnswerCorrect == false && selectedChoiceIndex == index,
                onPressed = {
                    if (onChoiceSelected != null) {
                        onChoiceSelected(index)
                    }
                },
            )
        }
    }
}