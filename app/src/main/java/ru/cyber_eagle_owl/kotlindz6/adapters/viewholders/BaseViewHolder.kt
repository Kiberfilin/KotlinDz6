package ru.cyber_eagle_owl.kotlindz6.adapters.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import dto.Post

abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(post: Post, updateItem: (itemPosition: Int) -> Unit)
}