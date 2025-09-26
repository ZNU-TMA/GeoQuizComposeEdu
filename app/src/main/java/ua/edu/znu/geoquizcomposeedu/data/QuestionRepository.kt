package ua.edu.znu.geoquizcomposeedu.data

class QuestionRepository(val questionDataSource: QuestionDataSource){

    fun getQuestionByIndex(index: Int): Question {
        return questionDataSource.getQuestionBank()[index]
    }

    fun getQuestionBankSize() = questionDataSource.getQuestionBank().size

}