package ua.edu.znu.geoquizcomposeedu

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class QuestionListScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testQuestionListScreen() {
        val questions = listOf(
            "Канберра - це столиця Австралії",
            "Тихий океан більший, аніж Атлантичний океан",
            "Суецький канал поєднує Червоне море та Індійський океан"
        )
        val listButtonText = "Питання"
        val questionListScreenText = "Текст питання"
        composeTestRule.onNodeWithText(listButtonText).performClick()
        // Wait for the Question List screen to be displayed
        composeTestRule.waitUntil(timeoutMillis = 5_000) {
            composeTestRule.onAllNodesWithText(questionListScreenText).fetchSemanticsNodes()
                .isNotEmpty()
        }
        composeTestRule.onAllNodesWithText(questions[0]).assertCountEquals(1)
        composeTestRule.onAllNodesWithText(questions[1]).assertCountEquals(1)
        composeTestRule.onAllNodesWithText(questions[2]).assertCountEquals(1)
    }
}