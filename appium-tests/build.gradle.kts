plugins {
    kotlin("jvm")
}

group = "ua.edu.znu.geoquizcomposeedu"
version = "1.0-SNAPSHOT"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

dependencies {
    // Автоматизація
    implementation(libs.appium.java.client)
    implementation(libs.selenium.java)
    testImplementation(libs.junit6.jupiter.engine)
    testImplementation(libs.junit6.jupiter.params)
    testRuntimeOnly(libs.junit6.platform.launcher)
}

tasks.test {
    useJUnitPlatform()

    testLogging {
        events("passed", "skipped", "failed")
        showStandardStreams = true
    }
}
