package com.easygautam.ipe.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.easygautam.ipe.BR
import java.util.*

open class RecyclerViewAdapter<T>(private val rowLayout: Int) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var onItemClickListener: OnItemClickListener<T>? = null

    private var list: MutableList<T>? = null

    init {
        this.list = ArrayList()
    }

    fun getList(): List<T>? {
        return list
    }

    fun setList(list: List<T>?) {
        this.list!!.clear()
        if (list != null)
            this.list!!.addAll(list)

        notifyDataSetChanged()
    }

    fun addItem(model: T) {
        list!!.add(model)
        notifyItemInserted(list!!.indexOf(model))
    }

    fun remove(model: T) {
        list!!.remove(model)
        notifyItemRemoved(list!!.indexOf(model))
    }

    fun updateItem(model: T, index: Int) {
        list!![index] = model
        notifyItemChanged(index)
    }

    fun clearItems() {
        this.list!!.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val bind = DataBindingUtil
            .bind<ViewDataBinding>(
                LayoutInflater.from(parent.context)
                    .inflate(rowLayout, parent, false)
            )
        return ViewHolder(Objects.requireNonNull<ViewDataBinding>(bind))
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val holder = viewHolder as ViewHolder<*>
        val model = list!![position]
        holder.binding.setVariable(BR.model, model)
        holder.binding.setVariable(BR.index, list!!.indexOf(model))
        holder.binding.setVariable(BR.itemClickListener, onItemClickListener)
        holder.binding.executePendingBindings()

    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    private inner class ViewHolder<V : ViewDataBinding> constructor(val binding: V) :
        RecyclerView.ViewHolder(binding.root)
}

interface OnItemClickListener<T> {
    fun onItemClick(view: View?, model: T)
}