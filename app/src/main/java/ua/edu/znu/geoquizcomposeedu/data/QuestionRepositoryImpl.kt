package ua.edu.znu.geoquizcomposeedu.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlin.time.Duration.Companion.milliseconds

/* We should not use Log.d because it can cause issues in unit tests,
   especially when running on the JVM without Android.*/
//private const val TAG = "QuestionRepositoryImpl"

class QuestionRepositoryImpl private constructor(private val questionDataSource: QuestionDao) :
    QuestionRepository {
    // Singleton pattern provides a single instance of the repository with application
    companion object {
        @Volatile
        private var instance: QuestionRepositoryImpl? = null

        // Application-scoped CoroutineScope used for stateIn so we reuse a single scope
        // instead of creating a new short-lived scope each time. This keeps the flow
        // collection active for the lifetime of the application process.
        private val applicationScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

        fun getInstance(questionDao: QuestionDao): QuestionRepositoryImpl {
            return instance ?: synchronized(this) {
                instance ?: QuestionRepositoryImpl(questionDao).also { instance = it }
            }
        }

//        fun getInstance(): QuestionRepositoryImpl {
//            return INSTANCE ?: synchronized(this) {
//                INSTANCE ?: QuestionRepositoryImpl(QuestionDao()).also { INSTANCE = it }
//            }
//        }
    }

//    init {
//        // Log.d(TAG, "initialized QuestionRepositoryImpl")
//    }

    override fun getQuestionByIndex(index: Int): Question {
        // Accessing the questions from the StateFlow to ensure we get the latest data
        val list = getQuestionListState().value
        if (list.isEmpty()) {
            throw NoSuchElementException("Question list is empty")
        }
        if (index < 0 || index >= list.size) {
            throw IndexOutOfBoundsException("Index out of bounds")
        }
        return list[index]
    }

    override fun getQuestionBankSize() = getQuestionListState().value.size

    // Expose the Room Flow as a StateFlow so collectors (UI) get DB updates automatically.
    // Function stateIn converts a Room's Flow to a StateFlow, which holds the latest list of questions.
    // Use an application-scoped CoroutineScope so the stateIn collection lives for the
    // whole app process and emits DB updates to collectors reliably.
    private val questionsStateFlow: StateFlow<List<Question>> =
        questionDataSource.getQuestions()
            .stateIn(
                scope = applicationScope,
                started = SharingStarted.Eagerly,
                initialValue = emptyList()
            )

    override fun getQuestionListState(): StateFlow<List<Question>> {
        return questionsStateFlow
    }

    // inside QuestionRepositoryImpl
    override suspend fun addQuestion(question: Question) {
        // For suspend function use delay instead of Thread.sleep to avoid blocking the main thread
        delay(100.milliseconds) // Simulate a delay for testing purposes
        // Insert into DB and let the Room Flow emit the changed list
        questionDataSource.addQuestion(question)
        // kept for debugging/logging if needed; do not try to maintain a separate in-memory list
        // Log.d(
        //     TAG,
        //     "addQuestion: inserted=$question, totalAfter=${questionsStateFlow.value.size} (may not reflect the latest state yet)"
        // )
    }

    override suspend fun updateQuestion(updatedQuestion: Question) {
        // Update in data source; Room Flow will propagate changes
        questionDataSource.updateQuestion(updatedQuestion)
//        Log.d(
//            TAG,
//            "updateQuestion: updated=${updatedQuestion}, totalNow(before update propagates)=${questionsStateFlow.value.size}"
//        )
    }

    override suspend fun removeQuestion(question: Question) {
        // Remove from data source; Room Flow will propagate changes
        questionDataSource.removeQuestion(question)
        // Log.d(
        //     TAG,
        //     "removeQuestion: removed=${question}, totalAfter (may not reflect removal yet)=${questionsStateFlow.value.size}"
        // )
    }
}