package tw.andyang.domain.redux.reducer

import org.junit.Assert.assertEquals
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import tw.andyang.domain.model.Todo
import tw.andyang.domain.redux.TodoAction
import tw.andyang.domain.redux.TodoState

object DeleteTodoReducerTest : Spek({
    val todo1 = Todo(id = "1", text = "text")
    val todo2 = Todo(id = "2", text = "text")
    val currentState = TodoState(listOf(todo1, todo2))
    val action = TodoAction.DeleteTodo("1")
    val target by memoized { DeleteTodoReducer(action) }
    describe("delete todo") {
        it("have 2 todo id = 1 and 2 when delete id = 1 todo then got id = 2 todo") {
            val actual = target.newState(currentState)
            val excepted = currentState.copy(listOf(todo2))
            assertEquals(excepted, actual)
        }
    }
})