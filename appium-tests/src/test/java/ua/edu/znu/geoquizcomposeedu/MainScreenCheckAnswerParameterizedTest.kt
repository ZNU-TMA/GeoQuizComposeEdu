package ua.edu.znu.geoquizcomposeedu

import io.appium.java_client.AppiumBy
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import ua.edu.znu.geoquizcomposeedu.util.TestTags
import java.time.Duration


class MainScreenCheckAnswerParameterizedTest : BaseTest() {

    @DisplayName("Use CSVSource to test the answer checking functionality on the main screen")
    @ParameterizedTest(name = "Test of answer checking with question index = {0}, question text = {1}, user answer = {2}, expected message = {3}")
    @CsvSource(
        value = [
            "0, Канберра - це столиця Австралії, Так, Правильно!",
            "1, Канберра - це столиця Австралії, Ні, Неправильно!",
            "2, Суецький канал поєднує Червоне море та Індійський океан, Ні, Правильно!",
            "3, Суецький канал поєднує Червоне море та Індійський океан, Так, Неправильно!"
        ]
    )
    fun checkIfAnswerParameterizedTest(
        index: Int,
        questionText: String,
        userAnswer: String,
        message: String
    ) {
        // Every test starts with a new Activity and ViewModel, so the index is 0 again.
        // Questions 2 and 3 are about the Suez Canal, which is the third question.
        if (index >= 2) {
            val nextButton =
                driver.findElement(AppiumBy.accessibilityId(TestTags.NEXT_QUESTION_BUTTON))
            repeat(2) { nextButton.click() }
        }
        // Wait for the question to be displayed
        val questionWebElement =
            driver.findElement(AppiumBy.accessibilityId(TestTags.QUESTION_TEXT))
        assert(questionWebElement.text == questionText) { "Expected question text '$questionText', but got '${questionWebElement.text}'" }

        // Click the button
        val answerButton =
            driver.findElement(AppiumBy.accessibilityId(if (userAnswer == "Так") TestTags.TRUE_BUTTON else TestTags.FALSE_BUTTON))
        answerButton.click()

        // Snackbar
        val snackbarMessage = WebDriverWait(driver, Duration.ofSeconds(5))
            .until(
                ExpectedConditions.visibilityOfElementLocated(
                    AppiumBy.xpath("//*[@text='$message']")
                )
            )

        assertEquals(message, snackbarMessage.text)
    }
}
