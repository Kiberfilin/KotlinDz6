package ru.cyber_eagle_owl.kotlindz6.adapters.viewholders

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.core.content.ContextCompat
import dto.Add
import dto.Post
import kotlinx.android.synthetic.main.add_post_item.view.*

class AddViewHolder(view: View) : PostViewHolder(view) {
    private lateinit var addPost: View

    override fun initViews() {
        addPost = itemView.addPostItem
    }

    override fun setOnClickListeners(post: Post, updateItem: (itemPosition: Int) -> Unit) {
        addPost.setOnClickListener { onAddPostTaped(post) }
    }

    private fun onAddPostTaped(post: Post) {
        val intent = Intent().apply {
            action = Intent.ACTION_VIEW
            data = Uri.parse((post as Add).url)
        }

        if (intent.resolveActivity(itemView.context.packageManager) != null) {
            ContextCompat.startActivity(itemView.context, intent, null)
        }
    }

    override fun bind(post: Post, updateItem: (itemPosition: Int) -> Unit) {
        setOnClickListeners(post, updateItem)
        postFiller.run {
            initForAddPost(itemView)
            fillAddPost(post)
        }
    }
}