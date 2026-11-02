package ua.edu.znu.geoquizcomposeedu

import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.options.UiAutomator2Options
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import java.net.URI
import java.time.Duration

/**
 * Base class for Appium tests.
 */
abstract class BaseTest {
    // Захищене поле драйвера, яке буде доступне в самих тестах
    protected lateinit var driver: AndroidDriver

    @BeforeEach
    fun setUp() {
        val capabilities = UiAutomator2Options()
            .setPlatformName("Android")
            .setAutomationName("UiAutomator2")
            .setDeviceName("Medium Phone")
            .setApp(
                java.io.File(
                    System.getProperty("user.dir"),
                    "../app/build/outputs/apk/debug/app-debug.apk"
                ).canonicalPath
            )
            // Налаштування для Jetpack Compose та стабільності
            .setNoReset(false)
            .setNewCommandTimeout(Duration.ofSeconds(60))

        // Підключення до локального Appium-сервера
        val serverUrl = URI.create("http://127.0.0.1:4723").toURL()
        driver = AndroidDriver(serverUrl, capabilities)

        // Неявне очікування (якщо не використовуємо плагін element-wait)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10))
    }

    @AfterEach
    fun tearDown() {
        // Закриваємо сесію драйвера, щоб не перевантажувати емулятор
        if (::driver.isInitialized) {
            driver.quit()
        }
    }
}