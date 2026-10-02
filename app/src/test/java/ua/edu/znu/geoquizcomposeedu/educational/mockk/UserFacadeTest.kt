package ua.edu.znu.geoquizcomposeedu.educational.mockk

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.*
import org.junit.Test

class UserFacadeTest {

    // Create a mock UserService
    private val mockUserService = mockk<UserService>()

    @Test
    fun greetUser_returnsGreetingWithMockedUserName() {
        // Stub the getUserName method to return a specific value
        every { mockUserService.getUserName() } returns "John Doe"
        // Create a UserGreeting with the mock UserService
        val userFacade = UserFacade(mockUserService)
        // Call the method under test
        val result = userFacade.greetUser()
        // Verify that getUserName was called
        verify { mockUserService.getUserName() }
        // Assert the expected result
        assertEquals("Hello, John Doe!", result)
    }

    @Test
    fun setUserName_setsTheUserName() {
        // Stub the setUserName method to do nothing (Unit)
        every { mockUserService.setUserName(any()) } returns Unit

        // Create a UserGreeting with the mock UserService
        val userFacade = UserFacade(mockUserService)
        // Call the method under test
        userFacade.setUserName("Jane Doe")
        // Verify that setUserName was called with the correct argument
        verify { mockUserService.setUserName("Jane Doe") }
    }
}
