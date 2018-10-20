package tw.andyang.domain.redux

import tw.andyang.domain.model.Todo

data class TodoState(
    val todos: List<Todo> = emptyList()
) : Cloneable {

    public override fun clone(): TodoState {
        val newTodos = mutableListOf<Todo>().apply {
            todos.forEach {
                add(it.copy())
            }
        }
        return TodoState(newTodos)
    }
}