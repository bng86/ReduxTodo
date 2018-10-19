package tw.andyang.todo.extension

import io.reactivex.disposables.Disposable
import tw.andyang.todo.MainView

fun Disposable.bind(view: MainView) {
    view.bind(this)
}