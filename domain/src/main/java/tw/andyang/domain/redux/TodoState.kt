package tw.andyang.domain.redux

import tw.andyang.domain.model.Todo

data class TodoState(
    val todos: List<Todo> = emptyList()
)