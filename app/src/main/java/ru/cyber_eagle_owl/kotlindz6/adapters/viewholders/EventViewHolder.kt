package ru.cyber_eagle_owl.kotlindz6.adapters.viewholders

import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.ImageButton
import androidx.core.content.ContextCompat.startActivity
import dto.Coordinates
import dto.Event
import dto.Post
import kotlinx.android.synthetic.main.event_post_item.view.*

class EventViewHolder(view: View) :
    PostViewHolder(view) {

    private lateinit var postLocationIcon: ImageButton

    override fun initViews() {
        with(itemView) {
            likeCountIcon = eventPostLikeCountIcon
            commentCountIcon = eventPostCommentCountIcon
            sharedCountIcon = eventPostSharedCountIcon
            postLocationIcon = eventPostLocationIcon
        }
    }

    override fun setOnClickListeners(
        post: Post,
        updateItem: (itemPosition: Int) -> Unit
    ) {
        super.setOnClickListeners(post, updateItem)
        postLocationIcon.setOnClickListener { onLocationTaped(post) }
    }

    private fun onLocationTaped(post: Post) {
        val (lat, lng) = (post as Event).coordinates
            ?: Coordinates()
        val intent = Intent().apply {
            action = Intent.ACTION_VIEW
            data = Uri.parse("geo:$lat,$lng")
        }

        if (intent.resolveActivity(itemView.context.packageManager) != null) {
            startActivity(itemView.context, intent, null)
        }
    }

    override fun bind(post: Post, updateItem: (itemPosition: Int) -> Unit) {
        setOnClickListeners(post, updateItem)
        with(postFiller) {
            initForEvent(itemView)
            fillEvent(post)
        }
    }
}