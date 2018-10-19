package tw.andyang.todo

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_add_todo.view.*
import tw.andyang.domain.model.Todo

class MainActivity : AppCompatActivity(), MainView {

    private val presenter = MainPresenter(this)
    private val compositeDisposable = CompositeDisposable()
    private val adapter = TodoListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        adapter.onCompletedChanged = { todo -> presenter.updateTodo(todo) }
        adapter.onEditCallBack = { todo -> showEditDialog(todo) }
        presenter.initialize()

        checkboxAll.setOnClickListener {
            presenter.checkAll(checkboxAll.isChecked)
        }
    }

    private fun showEditDialog(todo: Todo) {
        AlertDialog.Builder(this).apply {
            val view = LayoutInflater.from(this@MainActivity).inflate(R.layout.dialog_add_todo, null)
            setView(view)
            val editTodo = view.editTodo
            editTodo.setText(todo.text)
            editTodo.setSelection(todo.text.length)
            setTitle(R.string.add_todo)
            setPositiveButton(R.string.update) { dialog, _ ->
                if (editTodo.text.isNullOrEmpty()) {
                    Toast.makeText(this@MainActivity, R.string.hint_add_todo, Toast.LENGTH_LONG).show()
                } else {
                    presenter.updateTodo(todo.copy(text = editTodo.text.toString()))
                    dialog.dismiss()
                }
            }
            setNeutralButton(R.string.delete) { dialog, _ ->
                presenter.deleteTodo(todo.id)
                dialog.dismiss()
            }
            setNegativeButton(R.string.cancel) { dialog, _ ->
                dialog.dismiss()
            }
        }.show()
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_todo, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.add -> {
                AlertDialog.Builder(this).apply {
                    val view = LayoutInflater.from(this@MainActivity).inflate(R.layout.dialog_add_todo, null)
                    setView(view)
                    val editTodo = view.editTodo
                    setTitle(R.string.add_todo)
                    setPositiveButton(R.string.add) { dialog, _ ->
                        if (editTodo.text.isNullOrEmpty()) {
                            Toast.makeText(this@MainActivity, R.string.hint_add_todo, Toast.LENGTH_LONG).show()
                        } else {
                            presenter.addTodo(editTodo.text.toString())
                            dialog.dismiss()
                        }
                    }
                    setNegativeButton(R.string.cancel) { dialog, _ ->
                        dialog.dismiss()
                    }
                }.show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun updateTodo(todos: List<Todo>) {
        adapter.refresh(todos)
    }

    override fun bind(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }
}

interface MainView {
    fun bind(disposable: Disposable)
    fun updateTodo(todos: List<Todo>)
}