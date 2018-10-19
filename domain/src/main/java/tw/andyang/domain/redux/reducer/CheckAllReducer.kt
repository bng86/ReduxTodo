package tw.andyang.domain.redux.reducer

import tw.andyang.domain.redux.TodoAction
import tw.andyang.domain.redux.TodoReducer
import tw.andyang.domain.redux.TodoState

class CheckAllReducer(private val action: TodoAction.CheckAllTodo) :
    TodoReducer {
    override fun newState(currentState: TodoState): TodoState {
        currentState.todos.forEach { todo ->
            todo.completed = action.completed
        }
        return currentState
    }
}
