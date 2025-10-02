package ua.edu.znu.geoquizcomposeedu.data

import ua.edu.znu.geoquizcomposeedu.R

class QuestionDataSource {

    // Now list is mutable to allow removal/addition/update of questions
    fun getQuestionBank() = mutableListOf(
        Question(1, textResId = R.string.question_australia, answer = true),
        Question(2, textResId = R.string.question_oceans, answer = true),
        Question(3, textResId = R.string.question_mideast, answer = false),
        Question(4, textResId = R.string.question_africa, answer = false),
        Question(5, textResId = R.string.question_americas, answer = true),
        Question(6, textResId = R.string.question_asia, answer = true)
    )
}