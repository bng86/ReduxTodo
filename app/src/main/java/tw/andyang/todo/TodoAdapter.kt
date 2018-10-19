package tw.andyang.todo

import android.support.v7.recyclerview.extensions.ListAdapter
import android.view.LayoutInflater
import android.view.ViewGroup
import tw.andyang.domain.model.Todo

class TodoAdapter : ListAdapter<Todo, TodoViewHolder>(
    TodoDiffCallBack()
) {

    var onCompletedChanged: (todo: Todo) -> Unit = { }
    var onEditCallBack: (todo: Todo) -> Unit = { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_todo, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        getItem(holder.adapterPosition).apply {
            holder.text.text = text
            holder.checkbox.isChecked = completed
            holder.checkbox.setOnClickListener {
                onCompletedChanged.invoke(copy(completed = holder.checkbox.isChecked))
            }
            holder.itemView.setOnClickListener {
                onEditCallBack.invoke(this)
            }
        }
    }
}