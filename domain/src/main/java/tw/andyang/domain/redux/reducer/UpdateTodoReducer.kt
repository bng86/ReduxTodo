package tw.andyang.domain.redux.reducer

import tw.andyang.domain.redux.TodoAction
import tw.andyang.domain.redux.TodoReducer
import tw.andyang.domain.redux.TodoState

class UpdateTodoReducer(private val action: TodoAction.UpdateTodo) : TodoReducer {
    override fun newState(currentState: TodoState): TodoState {
        currentState.todos.firstOrNull { it.id == action.id }?.apply {
            text = action.text
            completed = action.completed
        }
        return currentState
    }
}

