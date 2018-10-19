package tw.andyang.domain.redux.reducer

import tw.andyang.domain.model.FakeData
import tw.andyang.domain.redux.TodoReducer
import tw.andyang.domain.redux.TodoState

class GenerateDataReducer : TodoReducer {
    override fun newState(currentState: TodoState): TodoState {
        val todos = currentState.todos.toMutableList().apply {
            addAll(FakeData.todos)
        }
        return currentState.copy(todos = todos)
    }
}
