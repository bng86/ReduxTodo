package tw.andyang.domain.redux.reducer

import org.junit.Assert.assertEquals
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import tw.andyang.domain.model.FakeData
import tw.andyang.domain.redux.TodoState

object GenerateDataReducerTest : Spek({
    val currentState = TodoState()
    val target by memoized { GenerateDataReducer() }
    describe("generate fake data") {
        it("") {
            val actual = target.newState(currentState)
            val excepted = currentState.copy(todos = FakeData.todos)
            assertEquals(excepted, actual)
        }
    }
})