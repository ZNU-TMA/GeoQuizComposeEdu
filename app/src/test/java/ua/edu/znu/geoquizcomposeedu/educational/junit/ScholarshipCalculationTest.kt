package ua.edu.znu.geoquizcomposeedu.educational.junit

import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ScholarshipCalculationTest {

    lateinit var scholarship : ScholarshipCalculation

    @Before
    fun setUp() {
        scholarship = ScholarshipCalculation()
    }

    @Test
    fun scholarshipCalculate() {
//        val scholarship = ScholarshipCalculation()
        val stepUpCoef = 1.3
        val expectedScholarship = 130.0
        val actualScholarship = scholarship.scholarshipCalculate(stepUpCoef)
        assertEquals(expectedScholarship, actualScholarship, 0.0)
    }

    @Test
    fun stepUpCoefCalc() {
//        val scholarship = ScholarshipCalculation()
        val gpa = 4
        val expectedCoef = 1.3
        val actualCoef = scholarship.stepUpCoefCalc(gpa)
        assertEquals(expectedCoef, actualCoef, 0.0)
    }
}