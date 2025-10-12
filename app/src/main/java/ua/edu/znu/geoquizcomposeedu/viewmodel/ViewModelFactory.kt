package ua.edu.znu.geoquizcomposeedu.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory<T : ViewModel>(
    private val modelClass: Class<T>,
    private val creator: () -> T
) : ViewModelProvider.Factory {
    override fun <U : ViewModel> create(modelClass: Class<U>): U {
        if (this.modelClass.isAssignableFrom(modelClass)) {
            @Suppress("UNCHECKED_CAST")
            return creator() as U
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}