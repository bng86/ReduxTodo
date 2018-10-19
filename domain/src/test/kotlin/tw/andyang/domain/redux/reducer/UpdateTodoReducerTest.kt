package tw.andyang.domain.redux.reducer

import org.junit.Assert.assertEquals
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import tw.andyang.domain.model.Todo
import tw.andyang.domain.redux.TodoAction
import tw.andyang.domain.redux.TodoState

object UpdateTodoReducerTest : Spek({
    val todo = Todo(id = "1", text = "text", completed = false)
    val currentState = TodoState(listOf(todo))
    val action = TodoAction.UpdateTodo("1", "new text", true)
    val target by memoized { UpdateTodoReducer(action) }

    describe("update todo") {
        it("when find todo id = 1 then update text = new text and completed = true") {
            val actual = target.newState(currentState)
            val excepted = currentState.copy(todos = listOf(todo.copy(
                text = "new text", completed = true
            )))
            assertEquals(excepted, actual)
        }
    }
})