package ua.edu.znu.geoquizcomposeedu

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextReplacement
import org.junit.Rule
import org.junit.Test

class QuestionEditScreen {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun checkQuestionTextCanBeEditedFromQuestionList() {
        val listButtonText = "Питання"
        val questionListScreenText = "Текст питання"
        // Select the second question in the list for editing because the first question
        // may be deleted in the testCheckQuestionCanBeDeletedFromQuestionList test
        val editedQuestionText = "Тихий океан більший, аніж Атлантичний океан"
        val updatedQuestionText = "Тихий океан є найбільшим океаном на Землі"
        val updateButtonText = composeTestRule.activity.getString(R.string.update_question)
        // Click the "List" button
        composeTestRule.onNodeWithText(listButtonText).performClick()
        // Wait for the Question List screen to be displayed
        composeTestRule.waitUntil(timeoutMillis = 5_000) {
            composeTestRule.onAllNodesWithText(questionListScreenText).fetchSemanticsNodes()
                .isNotEmpty()
        }
        // Click on the first question in the list
        composeTestRule.onNodeWithText(editedQuestionText).performClick()
        // Replace the question text and save the edit
        composeTestRule.onNodeWithText(editedQuestionText).assertIsDisplayed()
        composeTestRule.onNodeWithText(editedQuestionText)
            .performTextReplacement(updatedQuestionText)
        composeTestRule.onNodeWithText(updateButtonText).performClick()
        // Verify the updated text appears in the question list after saving
        composeTestRule.waitUntil(timeoutMillis = 5_000) {
            composeTestRule.onAllNodesWithText(updatedQuestionText).fetchSemanticsNodes()
                .isNotEmpty()
        }
        composeTestRule.onNodeWithText(updatedQuestionText).assertIsDisplayed()
    }

    @Test
    fun checkQuestionCanBeDeletedFromQuestionList() {
        val listButtonText = "Питання"
        val questionListScreenText = "Текст питання"
        val firstQuestionText = "Канберра - це столиця Австралії"
        val deleteButtonText = composeTestRule.activity.getString(R.string.remove_question)
        // Click the "List" button
        composeTestRule.onNodeWithText(listButtonText).performClick()
        // Wait for the Question List screen to be displayed
        composeTestRule.waitUntil(timeoutMillis = 5_000) {
            composeTestRule.onAllNodesWithText(questionListScreenText).fetchSemanticsNodes()
                .isNotEmpty()
        }
        // Click on the first question in the list
        composeTestRule.onNodeWithText(firstQuestionText).performClick()
        // Click the delete button
        composeTestRule.onNodeWithText(deleteButtonText).performClick()
        // Verify the question is removed from the question list after deletion
        composeTestRule.waitUntil(timeoutMillis = 5_000) {
            composeTestRule.onAllNodesWithText(firstQuestionText).fetchSemanticsNodes().isEmpty()
        }
    }

    @Test
    fun checkIfNewQuestionCanBeAddedFromQuestionList() {
        val listButtonText = "Питання"
        val questionListScreenText = "Текст питання"
        val newQuestionText = "Нове питання для тестування"
        val addButtonText = composeTestRule.activity.getString(R.string.add_question)
        // Click the "List" button
        composeTestRule.onNodeWithText(listButtonText).performClick()
        // Wait for the Question List screen to be displayed
        composeTestRule.waitUntil(timeoutMillis = 5_000) {
            composeTestRule.onAllNodesWithText(questionListScreenText).fetchSemanticsNodes()
                .isNotEmpty()
        }
        // Click the "Add" button
        composeTestRule.onNodeWithContentDescription(addButtonText).performClick()
        // Wait for the Question Edit screen to be displayed
        composeTestRule.waitUntil(timeoutMillis = 5_000) {
            composeTestRule.onAllNodesWithText(addButtonText).fetchSemanticsNodes()
                .isNotEmpty()
        }
        // Replace the question text and save the new question
        composeTestRule.onNodeWithText("").performTextReplacement(newQuestionText)
        composeTestRule.onNodeWithText(addButtonText).performClick()
        // VWait for the new question to appear in the question list after saving
        composeTestRule.waitUntil(timeoutMillis = 5_000) {
            composeTestRule.onAllNodesWithText(newQuestionText).fetchSemanticsNodes()
                .isNotEmpty()
        }
        // Verify the new question appears in the question list after saving
        composeTestRule.onNodeWithText(newQuestionText).assertIsDisplayed()
    }

    @Test
    fun checkIfSnackbarIsShownWhenTryingToSaveEmptyQuestion() {
        val listButtonText = "Питання"
        val questionListScreenText = "Текст питання"
        val addButtonText = composeTestRule.activity.getString(R.string.add_question)
        val emptyQuestionSnackbarText = composeTestRule.activity.getString(R.string.question_text_empty)
        // Click the "List" button
        composeTestRule.onNodeWithText(listButtonText).performClick()
        // Wait for the Question List screen to be displayed
        composeTestRule.waitUntil(timeoutMillis = 5_000) {
            composeTestRule.onAllNodesWithText(questionListScreenText).fetchSemanticsNodes()
                .isNotEmpty()
        }
        // Click the "Add" button
        composeTestRule.onNodeWithContentDescription(addButtonText).performClick()
        // Wait for the Question Edit screen to be displayed
        composeTestRule.waitUntil(timeoutMillis = 5_000) {
            composeTestRule.onAllNodesWithText(addButtonText).fetchSemanticsNodes()
                .isNotEmpty()
        }
        // Try to save an empty question
        composeTestRule.onNodeWithText(addButtonText).performClick()
        // Verify that the snackbar is shown with the correct message
        composeTestRule.waitUntil(timeoutMillis = 5_000) {
            composeTestRule.onAllNodesWithText(emptyQuestionSnackbarText).fetchSemanticsNodes()
                .isNotEmpty()
        }
        composeTestRule.onNodeWithText(emptyQuestionSnackbarText).assertIsDisplayed()
    }
}
