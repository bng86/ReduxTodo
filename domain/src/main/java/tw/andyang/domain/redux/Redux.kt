package tw.andyang.domain.redux

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import tw.andyang.domain.model.FakeData
import tw.andyang.domain.redux.reducer.*

class Redux(val fakeData: FakeData) {
    private val actionDispatcher = PublishSubject.create<TodoAction>()
    private var currentState: TodoState = TodoState()

    fun actionDispatcher(): Observable<TodoState> {
        return actionDispatcher
            .map { action ->
                val reducer: TodoReducer = when (action) {
                    is TodoAction.AddTodo -> AddTodoReducer(action)
                    is TodoAction.UpdateTodo -> UpdateTodoReducer(action)
                    is TodoAction.DeleteTodo -> DeleteTodoReducer(action)
                    is TodoAction.CheckAllTodo -> CheckAllReducer(action)
                    TodoAction.GenerateData -> GenerateDataReducer(fakeData)
                }
                reducer.newState(currentState.clone())
            }
            .doAfterNext { newState -> currentState = newState.clone() }
    }

    fun dispatcher(todoAction: TodoAction) {
        actionDispatcher.onNext(todoAction)
    }
}