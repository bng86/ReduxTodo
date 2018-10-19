package tw.andyang.todo

import tw.andyang.domain.model.Todo
import tw.andyang.domain.redux.Redux
import tw.andyang.domain.redux.TodoAction
import tw.andyang.todo.extension.bind

class MainPresenter(val view: MainView) {

    private val redux = Redux()

    fun initialize() {
        redux.actionDispatcher()
            .subscribe { state ->
                view.updateTodo(state.todos)
            }
            .bind(view)
    }

    fun addTodo(text: String) {
        redux.dispatcher(TodoAction.AddTodo(Todo(text)))
    }

    fun updateTodo(todo: Todo) {
        redux.dispatcher(TodoAction.UpdateTodo(todo.id, todo.text, todo.completed))
    }

    fun deleteTodo(id: String) {
        redux.dispatcher(TodoAction.DeleteTodo(id))
    }

    fun checkAll(completed: Boolean) {
        redux.dispatcher(TodoAction.CheckAllTodo(completed))
    }
}