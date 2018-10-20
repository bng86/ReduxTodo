package tw.andyang.domain.redux.reducer

import tw.andyang.domain.model.FakeData
import tw.andyang.domain.redux.TodoReducer
import tw.andyang.domain.redux.TodoState

class GenerateDataReducer(private val fakeData: FakeData) : TodoReducer {
    override fun newState(currentState: TodoState): TodoState {
        val todos = currentState.todos.toMutableList().apply {
            addAll(fakeData.generateFakeData())
        }
        return currentState.copy(todos = todos)
    }
}
