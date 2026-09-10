package ua.edu.znu.geoquizcomposeedu.educational.junit

import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class ScholarshipCalculationParameterizedTest(
    private val gpa: Int,
    private val expectedCoef: Double
) {

    companion object{
        @JvmStatic
        @Parameterized.Parameters(name = "Test {index}: gpa={0}, expectedCoef={1}")
        fun data(): Collection<Array<Any>> {
            return listOf(
                arrayOf(4, 1.3),
                arrayOf(5, 1.5),
                arrayOf(3, 1.0)
            )
        }
    }

    lateinit var scholarship: ScholarshipCalculation

    @Before
    fun setUp() {
        scholarship = ScholarshipCalculation()
    }

   @Test
    fun stepUpCoefCalc_parameterizedTest() {
        val actualCoef = scholarship.stepUpCoefCalc(gpa)
        assertEquals(expectedCoef, actualCoef, 0.0)
    }

}