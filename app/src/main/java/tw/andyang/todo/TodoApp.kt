package tw.andyang.todo

import android.app.Application
import org.koin.android.ext.android.startKoin
import tw.andyang.todo.module.appModule
import tw.andyang.todo.module.presenterModule
import tw.andyang.todo.module.reduxModule

class TodoApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule, reduxModule, presenterModule))
    }
}