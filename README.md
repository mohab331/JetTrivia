# JetTrivia 🎯

A trivia quiz app built with **Jetpack Compose**, **MVVM**, **Hilt**, and **Retrofit + Gson**.  
It demonstrates StateFlow for reactive UI, SharedFlow for one-time events, and a clean, lifecycle-aware architecture.

---

## ✨ Features
- Fetch trivia questions from a JSON endpoint (`world.json`) using Retrofit + Gson
- Clean **MVVM** pattern with Hilt Dependency Injection
- **StateFlow** for UI state and **SharedFlow** for one-time actions (toasts, navigation)
- Question selection and correctness feedback
- Animated circular **Score Screen** (percentage only)
- Lifecycle-safe flow collection with `collectAsState`

---

## 🧱 Tech Stack
- **Kotlin**
- **Jetpack Compose**
- **MVVM Architecture**
- **Hilt** for DI
- **Retrofit** + **Gson**
- **Kotlin Coroutines** + **StateFlow** / **SharedFlow**

---

## 📱 Screens
1. **Trivia Home**
   - Displays fetched questions
   - Lets the user select an answer
   - Shows correctness feedback via Toast
   - Tracks score
2. **Score Screen**
   - Displays percentage score in an animated circular indicator
   - Shows performance message
   - “Play Again” and “Home” buttons

