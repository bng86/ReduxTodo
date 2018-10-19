package tw.andyang.domain.redux.reducer

import org.junit.Assert.assertEquals
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import tw.andyang.domain.model.Todo
import tw.andyang.domain.redux.TodoAction
import tw.andyang.domain.redux.TodoState

object AddTodoReducerSpec : Spek({
    val todo1 = Todo("1")
    val todo2 = Todo("2")
    val currentState = TodoState(todos = listOf(todo1))
    val action = TodoAction.AddTodo(todo2)
    val target by memoized { AddTodoReducer(action) }

    describe("add todo") {
        it("when origin 1 todo add 1 todo then todo state got 2 todo") {
            val actual = target.newState(currentState)
            val excepted = TodoState(listOf(todo1, todo2))
            assertEquals(excepted, actual)
        }
    }
})