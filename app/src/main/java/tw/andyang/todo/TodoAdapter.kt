package tw.andyang.todo

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.AppCompatCheckBox
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_todo.view.*
import tw.andyang.domain.model.Todo

class TodoAdapter : ListAdapter<Todo, TodoViewHolder>(TodoDiffCallBack()) {

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

class TodoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val text: AppCompatTextView = view.text
    val checkbox: AppCompatCheckBox = view.checkbox
}

class TodoDiffCallBack : DiffUtil.ItemCallback<Todo>() {
    override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
        return oldItem == newItem
    }
}