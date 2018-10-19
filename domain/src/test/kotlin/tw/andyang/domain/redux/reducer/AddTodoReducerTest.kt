package tw.andyang.domain.redux.reducer

import org.junit.Assert.assertEquals
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import tw.andyang.domain.model.Todo
import tw.andyang.domain.redux.TodoAction
import tw.andyang.domain.redux.TodoState

object AddTodoReducerSpec : Spek({
    val currentState = TodoState()
    val todo = Todo("text")
    val action  = TodoAction.AddTodo(todo)
    val target by memoized { AddTodoReducer(action) }

    describe("add todo") {
        it("when add 1 todo then todo state got 1 todo") {
            val actual = target.newState(currentState)
            val excepted = TodoState(listOf(todo))
            assertEquals(excepted, actual)
        }
    }
})