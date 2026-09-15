package ua.edu.znu.geoquizcomposeedu.data

import org.junit.runner.RunWith
import org.junit.runners.Suite

/**
 * Test suite for the QuestionRepositoryImpl class.
 * This suite includes both positive and negative test cases for the repository.
 */
@RunWith(Suite::class)
@Suite.SuiteClasses(
    QuestionRepositoryTest::class,
    NegativeQuestionRepositoryTest::class
)
class QuestionRepositoryTestSuite {
}