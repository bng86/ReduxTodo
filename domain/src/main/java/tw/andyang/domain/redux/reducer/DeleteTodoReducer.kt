package tw.andyang.domain.redux.reducer

import tw.andyang.domain.redux.TodoAction
import tw.andyang.domain.redux.TodoReducer
import tw.andyang.domain.redux.TodoState

class DeleteTodoReducer(private val action: TodoAction.DeleteTodo) : TodoReducer {
    override fun newState(currentState: TodoState): TodoState {
        val todos = currentState.todos
            .filter { it.id != action.id }
        return currentState.copy(todos = todos)
    }
}
