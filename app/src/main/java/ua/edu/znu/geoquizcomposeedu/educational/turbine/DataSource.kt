package ua.edu.znu.geoquizcomposeedu.educational.turbine

import kotlinx.coroutines.flow.Flow

/**
 * DataSource interface for providing data to the application.
 */
interface DataSource  {
    fun counts(): Flow<Int>
}