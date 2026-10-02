package ua.edu.znu.geoquizcomposeedu.educational.turbine

import app.cash.turbine.test
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class TurbineFlowTest {
    @Test
    fun continuouslyCollect() = runTest {
        val dataSource = DataSourceImpl()
        val repository = MyRepository(dataSource)

        repository.scores().test {
            // Make calls that will trigger value changes only within test{}
            dataSource.emit(1)
            assertEquals(10, awaitItem())

            dataSource.emit(2)
            awaitItem() // Ignore items if needed, can also use skip(n)

            dataSource.emit(3)
            assertEquals(30, awaitItem())
        }
    }
}