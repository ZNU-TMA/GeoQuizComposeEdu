package ua.edu.znu.geoquizcomposeedu.data

import androidx.annotation.StringRes

data class Question(
    @StringRes val textResId: Int, val answer: Boolean
)