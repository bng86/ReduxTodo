package tw.andyang.domain.model

class FakeData {
    companion object {
        val todos = listOf(
            Todo("做 Redux 投影片"),
            Todo("做 Spek 投影片"),
            Todo("TADSG"),
            Todo("Takuma 不上班")
        )
    }

    fun generateFakeData(): List<Todo> {
        return listOf(
            Todo("做 Redux 投影片"),
            Todo("做 Spek 投影片"),
            Todo("TADSG"),
            Todo("Takuma 不上班")
        )
    }
}