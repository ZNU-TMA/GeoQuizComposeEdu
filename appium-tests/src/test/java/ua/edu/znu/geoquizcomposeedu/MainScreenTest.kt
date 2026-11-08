package ua.edu.znu.geoquizcomposeedu

import io.appium.java_client.AppiumBy
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import ua.edu.znu.geoquizcomposeedu.util.TestTags


class MainScreenTest : BaseTest() {
    @Test
    fun ifFirstQuestionDisplayed() {
        val firstQuestionText = "Канберра - це столиця Австралії"
        // Compose Testing API: onNodeWithText(firstQuestionText).assertIsDisplayed()
        //        val firstQuestionWebElement =
//            driver.findElement(AppiumBy.xpath("//*[@text='$firstQuestionText']"))
        val firstQuestionWebElement =
            driver.findElement(AppiumBy.accessibilityId(TestTags.QUESTION_TEXT))
//        val firstQuestionWebElement =
//            driver.findElement(AppiumBy.xpath("//*[@resource-id='QuestionText']"))
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
            driver.findElement(AppiumBy.accessibilityId(TestTags.NEXT_QUESTION_BUTTON))
        nextButton.click()
        val secondQuestionWebElement =
            driver.findElement(AppiumBy.accessibilityId(TestTags.QUESTION_TEXT))
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
            driver.findElement(AppiumBy.accessibilityId(TestTags.NEXT_QUESTION_BUTTON))
        repeat(5) { nextButton.click() }
        nextButton.click()
        val firstQuestionWebElement =
            driver.findElement(AppiumBy.accessibilityId(TestTags.QUESTION_TEXT))
        assertEquals(
            firstQuestionText,
            firstQuestionWebElement.text,
            "Після натискання кнопки 'Next' на останньому питанні, перше питання не відображається на головному екрані"
        )
    }
}