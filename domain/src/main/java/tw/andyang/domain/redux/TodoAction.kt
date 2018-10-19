package tw.andyang.domain.redux

import tw.andyang.domain.model.Todo

sealed class TodoAction {
    data class AddTodo(val todo: Todo) : TodoAction()
    data class UpdateTodo(val id: String, val text: String, val completed: Boolean) : TodoAction()
    data class DeleteTodo(val id: String) : TodoAction()
    data class CheckAllTodo(val completed: Boolean) : TodoAction()
}