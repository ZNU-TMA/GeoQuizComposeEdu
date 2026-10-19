package ua.edu.znu.geoquizcomposeedu.educational.junit

import org.junit.Before
import org.junit.Test

class ScholarshipCalculationNegativeTest {
    lateinit var scholarship: ScholarshipCalculation

    @Before
    fun setUp() {
        scholarship = ScholarshipCalculation()
    }

    @Test(expected = IllegalArgumentException::class)
    fun stepUpCoefCalc_invalidGpa_throwsException() {
        val invalidGpa = 2
        scholarship.stepUpCoefCalc(invalidGpa)
    }

    @Test(timeout = 110)
    fun stepUpCoefCalc_executionTimeWithinLimit() {
        val gpa = 4
        scholarship.stepUpCoefCalc(gpa)
    }
}