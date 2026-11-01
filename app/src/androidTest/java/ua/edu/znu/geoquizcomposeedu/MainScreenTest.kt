package ua.edu.znu.geoquizcomposeedu

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import org.junit.Rule
import org.junit.Test
import ua.edu.znu.geoquizcomposeedu.util.TestTags

/**
 * MainScreen functionality tests.
 */
class MainScreenTest {

    // Create a test rule with the MainActivity as the host for the Compose content
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun ifFirstQuestionDisplayed() {
        val text = "Канберра - це столиця Австралії"
        //        composeTestRule.onRoot(useUnmergedTree = true).printToLog("MY_TAG")
        composeTestRule.onRoot().printToLog("MY_TAG")
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    // ARRANGE - ACT - ASSERT pattern
    @Test
    fun checkIfSecondQuestionDisplayedAfterClickingNextButton(): Unit = with(composeTestRule) {
        // ARRANGE
//        val firstQuestionText = "Канберра - це столиця Австралії"
        val secondQuestionText = "Тихий океан більший, аніж Атлантичний океан"
        // Wait for the first question to be displayed
//        waitUntil(timeoutMillis = 5_000) {
//            onAllNodesWithText(firstQuestionText).fetchSemanticsNodes().isNotEmpty()
//        }
        // ACT: Click the "Next" button to go to the second question
//        onNodeWithTag(TestTags.NEXT_QUESTION_BUTTON).performClick()
        onNodeWithContentDescription("Click to go to the next question").performClick()
        // Wait for the second question to be displayed
//        waitUntil(timeoutMillis = 5_000) {
//            onAllNodesWithText(secondQuestionText).fetchSemanticsNodes()
//                .isNotEmpty()
//        }
        // ASSERT: Check if the second question is displayed
        onNodeWithText(secondQuestionText).assertIsDisplayed()
    }

    @Test
    fun checkIfFirstQuestionDisplayedAfterClickingNextButtonOnLastQuestion(): Unit =
        with(composeTestRule) {
            val firstQuestionText = "Канберра - це столиця Австралії"
//            val lastQuestionText = "Озеро Байкал є найстарішим та найглибшим прісноводним озером"
            // Wait for the first question to be displayed
//            waitUntil(timeoutMillis = 5_000) {
//                onAllNodesWithText(firstQuestionText).fetchSemanticsNodes()
//                    .isNotEmpty()
//            }
            // Click the "Next" button 5 times to go to the last question
            repeat(5) { onNodeWithTag(TestTags.NEXT_QUESTION_BUTTON).performClick() }
            // Wait for the last question to be displayed
//            waitUntil(timeoutMillis = 5_000) {
//                onAllNodesWithText(lastQuestionText).fetchSemanticsNodes()
//                    .isNotEmpty()
//            }
            // Click the "Next" button again to go back to the first question
            onNodeWithTag(TestTags.NEXT_QUESTION_BUTTON).performClick()
            // Wait for the first question to be displayed again
//            waitUntil(timeoutMillis = 5_000) {
//                onAllNodesWithText(firstQuestionText).fetchSemanticsNodes()
//                    .isNotEmpty()
//            }
            // Check if the first question is displayed again
            onNodeWithText(firstQuestionText).assertIsDisplayed()
        }
}