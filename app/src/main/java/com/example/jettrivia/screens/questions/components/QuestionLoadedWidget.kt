package com.example.jettrivia.screens.questions.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jettrivia.data.models.response.QuestionItemModel
import com.example.jettrivia.screens.common_widgets.DottedLine
import com.example.jettrivia.utils.AppColors

@Composable
fun QuestionLoadedWidget(
    items: List<QuestionItemModel>,
    currentQuestionIndex: Int,
    selectedChoiceIndex: Int? = null,
    onChoiceSelected: ((index: Int) -> Unit)? = null,
    onNextPressed: () -> Unit,
    isAnswerCorrect: Boolean?,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        QuestionTracker(
            questionsTotalNumber = items.size,
            questionsAnswered = (currentQuestionIndex) + 1,
        )
        Spacer(modifier = Modifier.height(16.dp))
        DottedLine()
        Spacer(modifier = Modifier.height(16.dp))
        QuestionWidget(
            question = items[currentQuestionIndex],
            selectedChoiceIndex = selectedChoiceIndex,
            onChoiceSelected = onChoiceSelected,
            isAnswerCorrect,
        )
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
            ElevatedButton(
                onClick = onNextPressed,
                shape = RoundedCornerShape(5.dp),
                elevation = ButtonDefaults.elevatedButtonElevation(5.dp)
            ) {
                Text(text = "Next Question", fontSize = 18.sp, color = AppColors.mBlack)
            }
        }
    }
}