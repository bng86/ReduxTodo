package tw.andyang.domain.redux.reducer

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Assert.assertEquals
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import tw.andyang.domain.model.FakeData
import tw.andyang.domain.redux.TodoState

object GenerateDataReducerTest : Spek({
    val currentState = TodoState()
    val fakeData by memoized { mock<FakeData>() }
    val target by memoized { GenerateDataReducer(fakeData) }
    describe("generate fake data") {
        it("generate data append under state todos") {
            whenever(fakeData.generateFakeData()).thenReturn(FakeData.todos)
            val actual = target.newState(currentState)
            val excepted = currentState.copy(todos = FakeData.todos)
            assertEquals(excepted, actual)
        }
    }
})