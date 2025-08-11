package com.example.jettrivia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph
import com.example.jettrivia.navigation.TriviaNavGraph
import com.example.jettrivia.screens.questions.TriviaHomeScreen
import com.example.jettrivia.screens.questions.view_model.QuestionsViewModel
import com.example.jettrivia.ui.theme.JetTriviaTheme
import com.example.jettrivia.utils.AppColors
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetTriviaTheme {
                TriviaNavGraph()
            }
        }
    }
}