package ua.edu.znu.geoquizcomposeedu.data

import ua.edu.znu.geoquizcomposeedu.R

class QuestionDataSource {

    fun getQuestionBank(): List<Question> = listOf(
        Question(textResId = R.string.question_australia, answer = true),
        Question(textResId = R.string.question_oceans, answer = true),
        Question(textResId = R.string.question_mideast, answer = false),
        Question(textResId = R.string.question_africa, answer = false),
        Question(textResId = R.string.question_americas, answer = true),
        Question(textResId = R.string.question_asia, answer = true)
    )
}