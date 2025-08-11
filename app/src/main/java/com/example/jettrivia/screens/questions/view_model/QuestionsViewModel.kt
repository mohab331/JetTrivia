package com.example.jettrivia.screens.questions.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jettrivia.data.data_source.remote.toUserMessage
import com.example.jettrivia.data.models.response.QuestionItemModel
import com.example.jettrivia.domain.repository.BaseQuestionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionsViewModel @Inject constructor(
    private val repo: BaseQuestionRepository
) : ViewModel() {

    // ---------- Render state (long-lived) ----------
    private val _uiState = MutableStateFlow<QuestionTriviaEvent>(QuestionTriviaEvent.Idle)
    val uiState: StateFlow<QuestionTriviaEvent> = _uiState.asStateFlow()

    private val _selectedIndex = MutableStateFlow<Int?>(null)
    val selectedIndex: StateFlow<Int?> = _selectedIndex

    private val _currentQuestionIndex = MutableStateFlow(0)
    val currentQuestionIndex: StateFlow<Int> = _currentQuestionIndex

    private val _isAnswerCorrect = MutableStateFlow<Boolean?>(null)
    val isAnswerCorrect: StateFlow<Boolean?> = _isAnswerCorrect

    // ---------- One-time actions (toasts, nav) ----------
    private val _uiActions = MutableSharedFlow<UiAction>()
    val uiActions = _uiActions.asSharedFlow()

    private val _scoreResultMap: HashMap<String, Boolean> = HashMap()

    fun getQuestions() {
        resetValues()
        viewModelScope.launch {
            _uiState.value = QuestionTriviaEvent.Loading
            val response = repo.getQuestionsList()
            val data = response.data
            val error = response.error
            if (data != null) {
                _uiState.value = QuestionTriviaEvent.Success(data.take(5))
            } else {
                _uiState.value = QuestionTriviaEvent.Error(error?.toUserMessage() ?: "Unknown error")
            }
        }
    }

    private fun resetValues() {
        _currentQuestionIndex.value = 0
        _selectedIndex.value = null
        _isAnswerCorrect.value = null
        _scoreResultMap.clear()
    }

    fun selectAnswer(index: Int) {
        if (_selectedIndex.value == index) return
        _selectedIndex.value = index
        _isAnswerCorrect.value = null
    }

    fun onNextPressed(items: List<QuestionItemModel>) {
        val selected = _selectedIndex.value
        if (selected == null) {
            viewModelScope.launch { _uiActions.emit(UiAction.NoSelection("Please select an answer")) }
            return
        }

        val currentIdx = _currentQuestionIndex.value
        val question = items.getOrNull(currentIdx) ?: return
        val choice = question.choices?.getOrNull(selected)
        val correct = question.answer.equals(choice, ignoreCase = true)
        _isAnswerCorrect.value = correct
        viewModelScope.launch {
            if (correct) {
                _uiActions.emit(UiAction.ShowCorrectToast(question))
                val next = currentIdx + 1
                if (next < items.size) {
                    _currentQuestionIndex.value = next
                    _selectedIndex.value = null
                    _isAnswerCorrect.value = null
                } else {
                    _uiActions.emit(UiAction.Finished)
                    }
            } else {
                _uiActions.emit(UiAction.ShowWrongToast(question))
            }
        }
    }
    fun calculateScorePercentage(): Int {
        val totalQuestions:Int = _scoreResultMap.size
        if (totalQuestions == 0) return 0
        val correctCount = _scoreResultMap.values.count { it }
        return ((correctCount.toDouble() / totalQuestions) * 100).toInt()
    }

    fun addQuestionToScoreMap(questionItemModel: QuestionItemModel,correct:Boolean){
        questionItemModel.question.takeIf { it?.isNotBlank() == true }?.let { id ->
            if (!_scoreResultMap.containsKey(id)) _scoreResultMap[id] = correct
        }
    }
}

