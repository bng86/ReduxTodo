package tw.andyang.todo.module

import org.koin.dsl.module.module
import tw.andyang.domain.model.FakeData
import tw.andyang.domain.redux.Redux
import tw.andyang.todo.MainPresenter
import tw.andyang.todo.MainView

val appModule = module {
    single { FakeData() }
}
val reduxModule = module {
    single { Redux(get()) }
}
val presenterModule = module {
    factory { (view: MainView) -> MainPresenter(view) }
}