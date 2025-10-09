package ua.edu.znu.geoquizcomposeedu.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ua.edu.znu.geoquizcomposeedu.data.QuestionRepository

class ViewModelFactory(
    private val questionRepository: QuestionRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(questionRepository) as T
            }
            modelClass.isAssignableFrom(QuestionListViewModel::class.java) -> {
                QuestionListViewModel(questionRepository) as T
            }
            // Add other ViewModels here as needed
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}