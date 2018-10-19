package tw.andyang.domain.redux.reducer

import org.junit.Assert.assertEquals
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import tw.andyang.domain.model.Todo
import tw.andyang.domain.redux.TodoAction
import tw.andyang.domain.redux.TodoState

object CheckAllReducerTest : Spek({
    val todo1 = Todo(id = "1", text = "text", completed = false)
    val todo2 = Todo(id = "2", text = "text", completed = true)
    val todo3 = Todo(id = "3", text = "text", completed = false)
    val currentState = TodoState(listOf(todo1, todo2, todo3))
    val target by memoized { CheckAllReducer(TodoAction.CheckAllTodo(true)) }

    describe("check all todos") {
        it("when check all got state all todo completed = true") {
            val actual = target.newState(currentState)
            val excepted = currentState.copy(
                todos = listOf(
                    todo1.copy(completed = true),
                    todo2.copy(completed = true),
                    todo3.copy(completed = true)
                )
            )
            assertEquals(excepted, actual)
        }
    }

})