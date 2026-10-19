package ua.edu.znu.geoquizcomposeedu.educational.turbine

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

/**
 * Implementation of the DataSource interface.
 */
class DataSourceImpl : DataSource {
    // A MutableSharedFlow to emit integer values.
    private val flow = MutableSharedFlow<Int>()

    /**
     * Suspend and emits an integer value to the MutableSharedFlow.
     */
    suspend fun emit(value: Int) = flow.emit(value)

    /**
     * Returns a Flow of integer values emitted by the MutableSharedFlow.
     */
    override fun counts(): Flow<Int> = flow
}