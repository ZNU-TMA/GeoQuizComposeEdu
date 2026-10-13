package ua.edu.znu.geoquizcomposeedu

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

/**
 * MainScreen functionality tests.
 */
class MainScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun ifFirstQuestionDisplayed() {
        val text = "Канберра - це столиця Австралії"
        // Questions are loaded from Room asynchronously, so wait for them to appear
        composeTestRule.waitUntil(timeoutMillis = 5_000) {
            composeTestRule.onAllNodesWithText(text).fetchSemanticsNodes().isNotEmpty()
        }
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test
    fun checkIfSecondQuestionDisplayedAfterClickingNextButton() {
        val next = composeTestRule.onNodeWithText("Далі")
        val firstQuestionText = "Канберра - це столиця Австралії"
        val secondQuestionText = "Тихий океан більший, аніж Атлантичний океан"
        // Wait for the first question to be displayed
        composeTestRule.waitUntil(timeoutMillis = 5_000) {
            composeTestRule.onAllNodesWithText(firstQuestionText).fetchSemanticsNodes().isNotEmpty()
        }
        // Click the "Next" button
        next.performClick()
        // Wait for the second question to be displayed
        composeTestRule.waitUntil(timeoutMillis = 5_000) {
            composeTestRule.onAllNodesWithText(secondQuestionText).fetchSemanticsNodes()
                .isNotEmpty()
        }
        // Check if the second question is displayed
        composeTestRule.onNodeWithText(secondQuestionText).assertIsDisplayed()
    }

    @Test
    fun checkIfFirstQuestionDisplayedAfterClickingNextButtonOnLastQuestion() {
        val next = composeTestRule.onNodeWithText("Далі")
        val firstQuestionText = "Канберра - це столиця Австралії"
        val lastQuestionText = "Озеро Байкал є найстарішим та найглибшим прісноводним озером"
        // Wait for the first question to be displayed
        composeTestRule.waitUntil(timeoutMillis = 5_000) {
            composeTestRule.onAllNodesWithText(firstQuestionText).fetchSemanticsNodes().isNotEmpty()
        }
        // Click the "Next" button 5 times to go to the last question
        repeat(5) { next.performClick() }
        // Wait for the last question to be displayed
        composeTestRule.waitUntil(timeoutMillis = 5_000) {
            composeTestRule.onAllNodesWithText(lastQuestionText).fetchSemanticsNodes()
                .isNotEmpty()
        }
        // Click the "Next" button again to go back to the first question
        next.performClick()
        // Wait for the first question to be displayed again
        composeTestRule.waitUntil(timeoutMillis = 5_000) {
            composeTestRule.onAllNodesWithText(firstQuestionText).fetchSemanticsNodes().isNotEmpty()
        }
        // Check if the first question is displayed again
        composeTestRule.onNodeWithText(firstQuestionText).assertIsDisplayed()
    }
}