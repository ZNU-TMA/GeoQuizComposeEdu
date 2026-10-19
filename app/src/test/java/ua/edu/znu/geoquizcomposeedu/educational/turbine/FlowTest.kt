package ua.edu.znu.geoquizcomposeedu.educational.turbine

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class FlowTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun continuouslyCollect() = runTest {
        val dataSource = DataSourceImpl()
        val repository = MyRepository(dataSource)

        val values = mutableListOf<Int>()
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            repository.scores().toList(values)
        }

        dataSource.emit(1)
        assertEquals(10, values[0]) // Assert on the list contents

        dataSource.emit(2)
        dataSource.emit(3)
        assertEquals(30, values[2])

        assertEquals(3, values.size) // Assert the number of items collected
    }
}