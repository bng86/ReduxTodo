package tw.andyang.domain.redux

import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import tw.andyang.domain.model.Todo

object ReduxTest : Spek({
    val redux by memoized { Redux() }
    val todo = Todo(id = "1", text = "text", completed = false)
    val newTodo = Todo(id = "1", text = "new text", completed = true)
    describe("redux") {
        val testObserver = redux.actionDispatcher().test()
        it("test dispatcher TodoAction.AddTodo, TodoAction.UpdateTodo, TodoAction.DeleteTodo") {
            redux.dispatcher(TodoAction.AddTodo(todo))
            testObserver.assertValueAt(0, TodoState(listOf(todo)))
            redux.dispatcher(TodoAction.UpdateTodo("1", "new text", true))
            testObserver.assertValueAt(1, TodoState(listOf(newTodo)))
            redux.dispatcher(TodoAction.DeleteTodo("1"))
            testObserver.assertValueAt(2, TodoState(emptyList()))
        }
    }
})