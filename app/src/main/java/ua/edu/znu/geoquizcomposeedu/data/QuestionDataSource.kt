package ua.edu.znu.geoquizcomposeedu.data

class QuestionDataSource {

    // Now list is mutable to allow removal/addition/update of questions
    private val questions = mutableListOf(
        Question(1, questionText="Канберра - це столиця Австралії", answer = true),
        Question(2, questionText="Тихий океан більший, аніж Атлантичний океа", answer = true),
        Question(3, questionText="Суецький канал поєднує Червоне море та Індійський океан", answer = false),
        Question(4, questionText="Витік річки Ніл знаходиться в Єгипті", answer = false),
        Question(5, questionText="Амазонка є найдовшою річкою у Америці", answer = true),
        Question(6, questionText="Озеро Байкал є найстарішим та найглибшим прісноводним озером", answer = true),
    )

    fun getQuestions(): List<Question> = questions

    fun getQuestionByIndex(index: Int): Question = questions[index]
}