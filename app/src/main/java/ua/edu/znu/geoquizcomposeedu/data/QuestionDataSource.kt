package ua.edu.znu.geoquizcomposeedu.data

/**
 * Simple provider of the initial question list.
 * All state management and updates are centralized in the repository,
 * ensuring a single source of truth and observable state for your UI.
 */
class QuestionDataSource {

    // Now list is mutable to allow removal/addition/update of questions
    private val questions = mutableListOf(
        Question(questionText = "Канберра - це столиця Австралії", answer = true),
        Question(questionText = "Тихий океан більший, аніж Атлантичний океа", answer = true),
        Question(questionText = "Суецький канал поєднує Червоне море та Індійський океан", answer = false),
        Question(questionText = "Витік річки Ніл знаходиться в Єгипті", answer = false),
        Question(questionText = "Амазонка є найдовшою річкою у Америці", answer = true),
        Question(questionText = "Озеро Байкал є найстарішим та найглибшим прісноводним озером", answer = true),
    )

    fun getQuestions(): List<Question> = questions
}