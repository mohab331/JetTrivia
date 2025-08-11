package com.example.jettrivia.screens.score

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jettrivia.screens.questions.view_model.QuestionsViewModel
import com.example.jettrivia.utils.AppColors

@Composable
fun ScoreScreen(
    navController: NavController,
    questionsViewModel: QuestionsViewModel,
) {
    val percentage = questionsViewModel.calculateScorePercentage()
    val progress by animateFloatAsState(
        targetValue = (percentage.coerceIn(0, 100) / 100f),
        label = "scoreProgress"
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = AppColors.mDarkPurple
    ) { inner ->
        Box(
            modifier = Modifier
                .padding(inner)
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                shape = RoundedCornerShape(24.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Your Score", fontSize = 22.sp, fontWeight = FontWeight.SemiBold)

                    Spacer(Modifier.height(20.dp))

                    Box(contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(
                            progress = progress,
                            modifier = Modifier.size(180.dp),
                            strokeWidth = 14.dp
                        )
                        Text(
                            "${percentage.coerceIn(0, 100)}%",
                            fontSize = 34.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(Modifier.height(16.dp))

                    val message = when {
                        percentage >= 90 -> "Excellent 🎉"
                        percentage >= 70 -> "Great job! 🙌"
                        percentage >= 50 -> "Nice try 👍"
                        else -> "Keep practicing 💪"
                    }
                    Text(message, fontSize = 16.sp, color = Color.Gray)

                    Spacer(Modifier.height(24.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Button(
                            onClick = {navController.popBackStack()},
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(16.dp)
                        ) { Text("Play Again") }

                        OutlinedButton(
                            onClick = {navController.popBackStack()},
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(16.dp)
                        ) { Text("Home") }
                    }
                }
            }
        }
    }
}
