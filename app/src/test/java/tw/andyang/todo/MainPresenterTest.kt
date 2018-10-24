package tw.andyang.todo

import com.nhaarman.mockito_kotlin.argThat
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.koin.dsl.module.module
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.standalone.StandAloneContext.stopKoin
import org.spekframework.spek2.style.specification.describe
import tw.andyang.domain.model.Todo
import tw.andyang.domain.redux.Redux
import tw.andyang.domain.redux.TodoAction
import tw.andyang.domain.redux.TodoState

class MainPresenterTest : BaseSpek({
    val redux by memoized { mock<Redux>() }

    beforeEachTest {
        startHookSchedules()
        startKoin(listOf(
            module { factory { redux } }
        ))
    }

    describe("todo presenter") {
        val view by memoized { mock<MainView>() }
        val presenter by memoized { MainPresenter(view) }

        context("convert to TodoAction") {
            it("when add todo text convert to AddTodo") {
                presenter.addTodo("text")
                val argMatcher: TodoAction.AddTodo.() -> Boolean = {
                    todo.text == "text"
                }
                verify(redux).dispatcher(argThat(argMatcher))
            }
            it("when update todo convert to UpdateTodo") {
                presenter.updateTodo(Todo(id = "1", text = "new text", completed = false))
                verify(redux).dispatcher(TodoAction.UpdateTodo(id = "1", text = "new text", completed = false))
            }
            it("when delete todo convert to DeleteTodo") {
                presenter.deleteTodo("1")
                verify(redux).dispatcher(TodoAction.DeleteTodo("1"))
            }
            it("when check all todo convert to CheckAllTodo") {
                presenter.checkAll(true)
                verify(redux).dispatcher(TodoAction.CheckAllTodo(true))
            }
            it("when generate data todo convert to GenerateData") {
                presenter.generateData()
                verify(redux).dispatcher(TodoAction.GenerateData)
            }
        }
        context("initialize redux") {
            it("when action dispatcher dispatch action then update todo view") {
                whenever(redux.actionDispatcher()).thenReturn(Observable.just(TodoState(emptyList())))
                presenter.initialize()
                verify(view).updateTodo(emptyList())
            }
        }
    }

    afterEachTest {
        stopHookSchedules()
        stopKoin()
    }
})