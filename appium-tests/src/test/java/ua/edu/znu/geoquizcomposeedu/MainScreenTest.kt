package ua.edu.znu.geoquizcomposeedu

import io.appium.java_client.AppiumBy
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MainScreenTest : BaseTest() {
    @Test
    fun ifFirstQuestionDisplayed() {
        val firstQuestionText = "Канберра - це столиця Австралії"
        val firstQuestionWebElement =
            driver.findElement(AppiumBy.xpath("//*[@resource-id='QuestionText']"))
        assertEquals(
            firstQuestionText,
            firstQuestionWebElement.text,
            "Питання не відображається на головному екрані"
        )
    }

    @Test
    fun checkIfSecondQuestionDisplayedAfterClickingNextButton() {
        val secondQuestionText = "Тихий океан більший, аніж Атлантичний океан"
        val nextButton =
            driver.findElement(AppiumBy.xpath("//*[@resource-id='NextQuestionButton']"))
        nextButton.click()
        val secondQuestionWebElement =
            driver.findElement(AppiumBy.xpath("//*[@resource-id='QuestionText']"))
        assertEquals(
            secondQuestionText,
            secondQuestionWebElement.text,
            "Друге питання не відображається на головному екрані"
        )
    }

    @Test
    fun checkIfFirstQuestionDisplayedAfterClickingNextButtonOnLastQuestion() {
        val firstQuestionText = "Канберра - це столиця Австралії"
        val nextButton =
            driver.findElement(AppiumBy.xpath("//*[@resource-id='NextQuestionButton']"))
        repeat(5) { nextButton.click() }
        nextButton.click()
        val firstQuestionWebElement =
            driver.findElement(AppiumBy.xpath("//*[@resource-id='QuestionText']"))
        assertEquals(
            firstQuestionText,
            firstQuestionWebElement.text,
            "Після натискання кнопки 'Next' на останньому питанні, перше питання не відображається на головному екрані"
        )
    }
}