package ru.cyber_eagle_owl.kotlindz6.adapters.viewholders

import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.ImageButton
import androidx.core.content.ContextCompat
import dto.Post
import dto.Video
import kotlinx.android.synthetic.main.video_post_item.view.*

class VideoViewHolder(view: View) :
    PostViewHolder(view) {
    lateinit var videoPreview: ImageButton

    override fun initViews() {
        with(itemView) {
            likeCountIcon = videoPostLikeCountIcon
            commentCountIcon = videoPostCommentCountIcon
            sharedCountIcon = videoPostSharedCountIcon
            videoPreview = videoPostVideoPreviewImg
        }
    }

    override fun setOnClickListeners(post: Post, updateItem: (itemPosition: Int) -> Unit) {
        super.setOnClickListeners(post, updateItem)
        videoPreview.setOnClickListener { onVideoPreviewTaped(post) }
    }

    private fun onVideoPreviewTaped(post: Post) {
        val intent = Intent().apply {
            action = Intent.ACTION_VIEW
            data = Uri.parse((post as Video).url)
        }

        if (intent.resolveActivity(itemView.context.packageManager) != null) {
            ContextCompat.startActivity(itemView.context, intent, null)
        }
    }

    override fun bind(post: Post, updateItem: (itemPosition: Int) -> Unit) {
        setOnClickListeners(post, updateItem)
        with(postFiller) {
            initForVideoPost(itemView)
            fillVideoPost(post)
        }
    }
}