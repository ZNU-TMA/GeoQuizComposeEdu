package ua.edu.znu.geoquizcomposeedu

import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.options.UiAutomator2Options
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File
import java.net.URI
import java.time.Duration

/**
 * Simple Appium test for a Jetpack Compose application.
 * This test demonstrates how to set up an Appium driver
 * for an Android application built with Jetpack Compose.
 */
class FirstAppiumTest {

    private lateinit var driver: AndroidDriver

    @BeforeEach
    fun setUp() {
        val capabilities = UiAutomator2Options()
            .setPlatformName("Android")
            .setAutomationName("UiAutomator2")
            .setDeviceName("Medium Phone")
            .setApp(
                File(
                    System.getProperty("user.dir"),
                    "../app/build/outputs/apk/debug/app-debug.apk"
                ).canonicalPath
            )
            .setNoReset(false)

        // Підключення до локального Appium-сервера
        val serverUrl = URI.create("http://127.0.0.1:4723").toURL()
        driver = AndroidDriver(serverUrl, capabilities)
    }

    @Test
    fun sampleComposeTest() {
        // Тест запуститься, відкриє додаток та закриє його
        println("Додаток успішно запущено Appium-драйвером!")
    }

    @AfterEach
    fun tearDown() {
        if (::driver.isInitialized) {
            driver.quit()
        }
    }
}