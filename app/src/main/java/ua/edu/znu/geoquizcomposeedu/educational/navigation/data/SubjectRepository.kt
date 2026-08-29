package ua.edu.znu.geoquizcomposeedu.educational.navigation.data

object SubjectRepository {
    val subjects = mutableListOf(
        Subject(text = "Subject 1", value = true, category = CategoryRepository.categories[0]),
        Subject(text = "Subject 2", value = false, category = CategoryRepository.categories[0]),
        Subject(text = "Subject 3", value = true, category = CategoryRepository.categories[1]),
        Subject(text = "Subject 4", value = false, category = CategoryRepository.categories[2]),
    )
}