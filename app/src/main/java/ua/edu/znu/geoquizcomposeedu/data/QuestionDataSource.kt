package ua.edu.znu.geoquizcomposeedu.data

/**
 * Simple provider of the initial question list.
 * All state management and updates are centralized in the repository,
 * ensuring a single source of truth and observable state for your UI.
 *
 * DEPRECATED: This class is now deprecated in favor of using a Room database
 * with QuestionDao and QuestionRepository for better data management and persistence.
 */
class QuestionDataSource {

    // Now list is mutable to allow removal/addition/update of questions
    private val questions = mutableListOf(
        Question(questionText = "Канберра - це столиця Австралії", answer = true),
        Question(questionText = "Тихий океан більший, аніж Атлантичний океан", answer = true),
        Question(questionText = "Суецький канал поєднує Червоне море та Індійський океан", answer = false),
        Question(questionText = "Витік річки Ніл знаходиться в Єгипті", answer = false),
        Question(questionText = "Амазонка є найдовшою річкою у Америці", answer = true),
        Question(questionText = "Озеро Байкал є найстарішим та найглибшим прісноводним озером", answer = true),
    )

    fun getQuestions(): List<Question> = questions

    /* Mutable operations on the question list */
    fun addQuestion(question: Question) {
        if (!questions.contains(question)) {
            questions.add(question)
        }
    }

    fun updateQuestion(updatedQuestion: Question) {
        val index = questions.indexOfFirst { it.id == updatedQuestion.id }
        if (index != -1) {
            questions[index] = updatedQuestion
        }
    }

    fun removeQuestion(question: Question) {
        questions.removeIf { it.id == question.id }
    }
}