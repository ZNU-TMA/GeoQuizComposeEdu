package ua.edu.znu.geoquizcomposeedu.educational.junit

/**
 * The base amount of the scholarship.
 */
const val BASE_SCHOLARSHIP = 100

/**
 * Calculation of the scholarship taking into account the increasing coefficient.
 */
class ScholarshipCalculation {

    /**
     * Calculation of a scholarship with an increasing coefficient.
     *
     * @param stepUpCoef - the increasing coefficient value
     * @return scholarship amount with increasing coefficient
     */
    fun scholarshipCalculate(stepUpCoef: Double): Double {
        return BASE_SCHOLARSHIP * stepUpCoef
    }

    /**
     * Calculation of the increasing coefficient based on GPA.
     *
     * @param gpa - the GPA value
     * @return the increasing coefficient
     */
    fun stepUpCoefCalc(gpa: Int): Double {
        try {
            Thread.sleep(80)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        return when (gpa) {
            3 -> 1.0
            4 -> 1.3
            5 -> 1.5
            else -> throw IllegalArgumentException("GPA must be between 3 and 5")
        }
    }
}
