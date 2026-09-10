package ua.edu.znu.geoquizcomposeedu.educational.junit

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    ScholarshipCalculationTest::class,
    ScholarshipCalculationParameterizedTest::class,
    ScholarshipCalculationNegativeTest::class
)
class ScholarshipCalculationTestSuite