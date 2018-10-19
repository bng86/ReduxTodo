package tw.andyang.domain.redux.reducer

import tw.andyang.domain.redux.TodoAction
import tw.andyang.domain.redux.TodoReducer
import tw.andyang.domain.redux.TodoState

class AddTodoReducer(private val action: TodoAction.AddTodo) : TodoReducer {
    override fun newState(currentState: TodoState): TodoState {
        val todos = currentState.todos.toMutableList().apply { add(action.todo) }
        return currentState.copy(todos = todos)
    }
}