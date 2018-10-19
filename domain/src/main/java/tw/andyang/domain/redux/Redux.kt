package tw.andyang.domain.redux

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import tw.andyang.domain.redux.reducer.AddTodoReducer
import tw.andyang.domain.redux.reducer.CheckAllReducer
import tw.andyang.domain.redux.reducer.DeleteTodoReducer
import tw.andyang.domain.redux.reducer.UpdateTodoReducer

class Redux {
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
                }
                reducer.newState(currentState)
            }
            .doAfterNext { newState -> currentState = newState }
    }

    fun dispatcher(todoAction: TodoAction) {
        actionDispatcher.onNext(todoAction)
    }
}