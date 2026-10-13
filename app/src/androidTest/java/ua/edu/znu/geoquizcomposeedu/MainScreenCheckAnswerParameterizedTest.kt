package ua.edu.znu.geoquizcomposeedu

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class MainScreenCheckAnswerParameterizedTest(
    private val index: Int,
    private val questionText: String,
    private val userAnswer: String,
    private val message: String
) {
    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "Test {index}: question={0}, answer={1}")
        fun data(): Collection<Array<Any>> {
            return listOf(
                arrayOf(0, "Канберра - це столиця Австралії", "Так", "Правильно!"),
                arrayOf(1, "Канберра - це столиця Австралії", "Ні", "Неправильно!"),
                arrayOf(
                    2,
                    "Суецький канал поєднує Червоне море та Індійський океан",
                    "Ні",
                    "Правильно!"
                ),
                arrayOf(
                    3,
                    "Суецький канал поєднує Червоне море та Індійський океан",
                    "Так",
                    "Неправильно!"
                )
            )
        }
    }

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    val next = composeTestRule.onNodeWithText("Далі")

    @Test
    fun checkIfAnswer() {
        // Every test starts with a new Activity and ViewModel, so the index is 0 again.
        // Questions 2 and 3 are about the Suez Canal, which is the third question.
        if (index >= 2) {
            composeTestRule.waitUntil(timeoutMillis = 5_000) {
                composeTestRule.onAllNodesWithText("Далі").fetchSemanticsNodes().isNotEmpty()
            }
            repeat(2) { next.performClick() }
        }
        // Wait for the question to be displayed
        composeTestRule.waitUntil(timeoutMillis = 5_000) {
            composeTestRule.onAllNodesWithText(questionText).fetchSemanticsNodes().isNotEmpty()
        }
        // Click the button
        composeTestRule.onNodeWithText(userAnswer).performClick()
        // Wait for the Snackbar message text to be displayed
        composeTestRule.waitUntil(timeoutMillis = 5_000) {
            composeTestRule.onAllNodesWithText(message).fetchSemanticsNodes()
                .isNotEmpty()
        }
        // Check if the right Snackbar message is displayed
        composeTestRule.onNodeWithText(message).assertIsDisplayed()
    }
}