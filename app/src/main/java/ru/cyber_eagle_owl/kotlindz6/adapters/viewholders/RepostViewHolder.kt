package ru.cyber_eagle_owl.kotlindz6.adapters.viewholders

import android.view.View
import dto.Post
import dto.posttypes.PostType
import kotlinx.android.synthetic.main.repost_item.view.*
import ru.cyber_eagle_owl.kotlindz6.R

class RepostViewHolder(view: View) : PostViewHolder(view) {
    override fun initViews() {
        with(itemView) {
            likeCountIcon = repostLikeCountIcon
            commentCountIcon = repostCommentCountIcon
            sharedCountIcon = repostSharedCountIcon
        }
    }

    override fun bind(post: Post, updateItem: (itemPosition: Int) -> Unit) {
        setOnClickListeners(post, updateItem)
        with(postFiller) {
            initForRepost(itemView)
            fillPost(post)
        }
        val sourceContainer = itemView.repostSourceContainer
        sourceContainer?.run {
            when (post.source?.postType) {
                PostType.POST -> {
                    layoutResource = R.layout.post_item
                    with(postFiller) {
                        initForPost(this@run.inflate())
                        fillPost(post.source)
                    }
                }
                PostType.EVENT -> {
                    layoutResource = R.layout.event_post_item
                    with(postFiller) {
                        initForEvent(this@run.inflate())
                        fillEvent(post.source)
                    }
                }
                PostType.VIDEO -> {
                    layoutResource = R.layout.video_post_item
                    with(postFiller) {
                        initForVideoPost(this@run.inflate())
                        fillVideoPost(post.source)
                    }
                }
                PostType.ADD -> throw java.lang.IllegalStateException("Нельзя репостить рекламу!")
                PostType.REPOST -> throw java.lang.IllegalStateException("Пока что нельзя репостить репосты!")
                else -> throw IllegalStateException("Неизвестный тип поста!")
            }
        }
    }
}