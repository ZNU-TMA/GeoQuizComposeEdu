package ua.edu.znu.geoquizcomposeedu.educational.mockk

class UserFacade(private val userService: UserService) {
    fun greetUser(): String {
        val userName = userService.getUserName()
//        val userName = "Petro"
        return "Hello, $userName!"
    }

    fun setUserName(name: String) {
        userService.setUserName(name)
    }
}