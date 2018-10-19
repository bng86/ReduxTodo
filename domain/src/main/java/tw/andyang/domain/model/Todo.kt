package tw.andyang.domain.model

import java.util.*

data class Todo(
    var text: String,
    var completed: Boolean = false,
    val id: String = UUID.randomUUID().toString()
)