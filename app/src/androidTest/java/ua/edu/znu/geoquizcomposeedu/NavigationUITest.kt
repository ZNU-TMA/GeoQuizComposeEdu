package ua.edu.znu.geoquizcomposeedu

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class NavigationUITest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun checkIfQuesionListScreenDisplayedAfterClickingQuestionsButtonOnNavigationBar() {
        val listButtonText = "Питання"
        val questionListScreenText = "Текст питання"
        // Click the "List" button
        composeTestRule.onNodeWithText(listButtonText).performClick()
        // Wait for the Question List screen to be displayed
        composeTestRule.waitUntil(timeoutMillis = 5_000) {
            composeTestRule.onAllNodesWithText(questionListScreenText).fetchSemanticsNodes()
                .isNotEmpty()
        }
        // Check if the Question List screen is displayed
        composeTestRule.onNodeWithText(questionListScreenText).assertIsDisplayed()
    }

    @Test
    fun checkIfMainScreenDisplayedAfterClickingMainButtonOnNavigationBar() {
        val listButtonText = "Питання"
        val questionListScreenText = "Текст питання"
        val mainButtonText = "Тест"
        val firstQuestionText = "Канберра - це столиця Австралії"
        // Click the "List" button
        composeTestRule.onNodeWithText(listButtonText).performClick()
        // Wait for the Question List screen to be displayed
        composeTestRule.waitUntil(timeoutMillis = 5_000) {
            composeTestRule.onAllNodesWithText(questionListScreenText).fetchSemanticsNodes()
                .isNotEmpty()
        }
        // Check if the Question List screen is displayed
        composeTestRule.onNodeWithText(questionListScreenText).assertIsDisplayed()
        // Click the "Main" button
        composeTestRule.onNodeWithText(mainButtonText).performClick()
        // Wait for the Main screen to be displayed
        composeTestRule.waitUntil(timeoutMillis = 5_000) {
            composeTestRule.onAllNodesWithText(firstQuestionText).fetchSemanticsNodes()
                .isNotEmpty()
        }
        // Check if the Main screen is displayed
        composeTestRule.onNodeWithText(firstQuestionText).assertIsDisplayed()
    }
}