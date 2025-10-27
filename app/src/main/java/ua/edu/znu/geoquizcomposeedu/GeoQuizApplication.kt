package ua.edu.znu.geoquizcomposeedu

import android.app.Application
import ua.edu.znu.geoquizcomposeedu.data.QuestionDatabase
import ua.edu.znu.geoquizcomposeedu.data.QuestionRepositoryImpl
import ua.edu.znu.geoquizcomposeedu.data.QuestionRepository

/**
 * Custom Application class to provide app-wide dependencies.
 */
class GeoQuizApplication : Application() {
    // expose repository as a single instance
    val questionRepository: QuestionRepository by lazy {
        QuestionRepositoryImpl
            .getInstance(
                QuestionDatabase.getInstance(this)
                    .questionDao()
            )
    }
}