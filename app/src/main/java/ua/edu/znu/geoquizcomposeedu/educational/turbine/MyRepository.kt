package ua.edu.znu.geoquizcomposeedu.educational.turbine

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Repository class that interacts with the DataSource to provide data to the application.
 */
class MyRepository(private val datasource: DataSource) {
    /**
     * Returns a Flow of integer values from the DataSource, each multiplied by 10.
     */
    fun scores(): Flow<Int> {
        return datasource.counts().map { it * 10 }
    }
}