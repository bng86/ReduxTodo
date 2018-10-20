package tw.andyang.domain.redux.reducer

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import tw.andyang.domain.model.FakeData
import tw.andyang.domain.model.Todo
import tw.andyang.domain.redux.Redux
import tw.andyang.domain.redux.TodoAction
import tw.andyang.domain.redux.TodoState

object ReduxTest : Spek({
    val fakeData by memoized { mock<FakeData>() }
    val redux by memoized { Redux(fakeData) }
    val todo = Todo(id = "1", text = "text", completed = false)
    val newTodo = Todo(id = "1", text = "new text", completed = true)

    describe("redux") {
        it("test dispatcher TodoAction.AddTodo, TodoAction.UpdateTodo, TodoAction.DeleteTodo") {
            val testObserver = redux.actionDispatcher().test()
            redux.dispatcher(TodoAction.AddTodo(todo))
            testObserver.assertValueAt(0, TodoState(listOf(todo)))
            redux.dispatcher(TodoAction.CheckAllTodo(true))
            testObserver.assertValueAt(1, TodoState(listOf(todo.copy(completed = true))))
            redux.dispatcher(TodoAction.UpdateTodo("1", "new text", true))
            testObserver.assertValueAt(2, TodoState(listOf(newTodo)))
            redux.dispatcher(TodoAction.DeleteTodo("1"))
            testObserver.assertValueAt(3, TodoState(emptyList()))
        }
        it("generate fake data") {
            whenever(fakeData.generateFakeData()).thenReturn(FakeData.todos)
            val testObserver = redux.actionDispatcher().test()
            redux.dispatcher(TodoAction.GenerateData)
            testObserver.assertValue(TodoState(FakeData.todos))
        }
    }
})