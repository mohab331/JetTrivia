package com.example.jettrivia.navigation

sealed class Screens(val route: String) {
    object TriviaHomeScreen : Screens("trivia_home")
    /// Required parameters → in {}
    /// Optional parameters → using ?key=value format
    object ScoreScreen : Screens("score")
}