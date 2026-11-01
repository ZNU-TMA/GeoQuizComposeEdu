package ua.edu.znu.geoquizcomposeedu

import androidx.compose.ui.semantics.LiveRegionMode
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher.Companion.expectValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import ua.edu.znu.geoquizcomposeedu.util.TestTags

@RunWith(Parameterized::class)
class MainScreenCheckAnswerParameterizedTest(
    private val index: Int,
    private val questionText: String,
    private val userAnswer: String,
    private val message: String
) {
    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "Test {index}: question={1}, answer={2}")
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

    @Test
    fun checkIfAnswer(): Unit = with(composeTestRule) {
        // Every test starts with a new Activity and ViewModel, so the index is 0 again.
        // Questions 2 and 3 are about the Suez Canal, which is the third question.
        if (index >= 2) {
            waitUntil(timeoutMillis = 5_000) {
                onAllNodesWithText("Далі").fetchSemanticsNodes().isNotEmpty()
            }
            repeat(2) { onNodeWithTag(TestTags.NEXT_QUESTION_BUTTON).performClick() }
        }
        // Wait for the question to be displayed
        waitUntil(timeoutMillis = 5_000) {
            onAllNodesWithText(questionText).fetchSemanticsNodes().isNotEmpty()
        }
        // Click the button
        onNodeWithText(userAnswer).performClick()

        // Wait for the Snackbar message text to be displayed
        waitUntil(timeoutMillis = 5_000) {
            onAllNodesWithText(message).fetchSemanticsNodes()
                .isNotEmpty()
        }
//        onNode(
//            expectValue(
//                SemanticsProperties.LiveRegion, LiveRegionMode.Polite
//            ) and hasText(message)
//        ).assertIsDisplayed()
        // Check if the right Snackbar message is displayed
        onNodeWithText(message).assertIsDisplayed()
    }
}