package ru.cyber_eagle_owl.kotlindz6.adapters.viewholders

import android.content.Intent
import android.view.View
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import dto.Post
import kotlinx.android.synthetic.main.post_item.view.*
import ru.cyber_eagle_owl.kotlindz6.adapters.viewholders.utils4holders.PostFiller

open class PostViewHolder(view: View) :
    BaseViewHolder(view) {

    protected val postFiller = PostFiller()

    lateinit var likeCountIcon: ImageButton
    lateinit var commentCountIcon: ImageButton
    lateinit var sharedCountIcon: ImageButton

    init {
        this.initViews()
    }

    protected open fun initViews() {
        with(itemView) {
            likeCountIcon = postLikeCountIcon
            commentCountIcon = postCommentCountIcon
            sharedCountIcon = postSharedCountIcon
        }
    }

    protected open fun setOnClickListeners(
        post: Post,
        updateItem: (itemPosition: Int) -> Unit
    ) {
        likeCountIcon.setOnClickListener { onLikeTaped(post, updateItem) }
        commentCountIcon.setOnClickListener { onCommentTaped(post, updateItem) }
        sharedCountIcon.setOnClickListener { onShareTaped(post, itemView, updateItem) }
    }

    private fun onLikeTaped(post: Post, updateItem: (itemPosition: Int) -> Unit) {
        if (adapterPosition != RecyclerView.NO_POSITION) {
            with(post) {
                likedByMe = !likedByMe
                if (likedByMe) likeCount++ else likeCount--
                updateItem(adapterPosition)
            }
        }
    }

    private fun onCommentTaped(
        post: Post,
        updateItem: (itemPosition: Int) -> Unit
    ) {
        if (adapterPosition != RecyclerView.NO_POSITION) {
            with(post) {
                commentedByMe = !commentedByMe
                if (commentedByMe) commentCount++ else commentCount--
                updateItem(adapterPosition)
            }
        }
    }

    private fun onShareTaped(
        post: Post,
        itemView: View,
        updateItem: (itemPosition: Int) -> Unit
    ) {
        if (adapterPosition != RecyclerView.NO_POSITION) {
            val intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(
                    Intent.EXTRA_TEXT, """
                                ${post.author} (${post.created})
    
                                ${post.content}
                            """.trimIndent()
                )
                type = "text/plain"
            }
            if (!post.sharedByMe) post.shareCount++
            post.sharedByMe = true
            updateItem(adapterPosition)
            itemView.context.startActivity(intent)
        }
    }

    override fun bind(post: Post, updateItem: (itemPosition: Int) -> Unit) {
        setOnClickListeners(post, updateItem)
        postFiller.initForPost(itemView)
        postFiller.fillPost(post)
    }
}