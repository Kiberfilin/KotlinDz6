package ru.cyber_eagle_owl.kotlindz6.adapters.viewholders.utils4holders

import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import dto.Event
import dto.Post
import kotlinx.android.synthetic.main.add_post_item.view.*
import kotlinx.android.synthetic.main.event_post_item.view.*
import kotlinx.android.synthetic.main.post_item.view.*
import kotlinx.android.synthetic.main.repost_item.view.*
import kotlinx.android.synthetic.main.video_post_item.view.*
import ru.cyber_eagle_owl.kotlindz6.R

class PostFiller {
    private var itemView: View? = null

    //region Post views
    private lateinit var likeCountIcon: ImageButton
    private lateinit var sharedCountIcon: ImageButton
    private lateinit var createdDate: TextView
    private lateinit var contentText: TextView
    private lateinit var authorTV: TextView
    private lateinit var likeCountText: TextView
    private lateinit var commentCountText: TextView
    private lateinit var sharedCountText: TextView
    private lateinit var commentCountIcon: ImageButton
    //endregion

    //region Event views
    private lateinit var postLocationIcon: ImageButton
    private lateinit var postAddress: TextView
    //endregion

    //region Add post views
    private lateinit var addPoster: ImageView
    //endregion

    //region Video post views
    lateinit var videoPreview: ImageButton
    //endregion

    fun initForPost(itemView: View) {
        this.itemView = itemView
        with(itemView) {
            likeCountIcon = postLikeCountIcon
            commentCountIcon = postCommentCountIcon
            sharedCountIcon = postSharedCountIcon
            createdDate = postCreatedDate
            contentText = postContentText
            authorTV = postAuthorTV
            likeCountText = postLikeCountText
            commentCountText = postCommentCountText
            sharedCountText = postSharedCountText
        }
    }

    fun initForRepost(itemView: View) {
        this.itemView = itemView
        with(itemView) {
            likeCountIcon = repostLikeCountIcon
            commentCountIcon = repostCommentCountIcon
            sharedCountIcon = repostSharedCountIcon
            createdDate = repostCreatedDate
            contentText = repostContentText
            authorTV = repostAuthorTV
            likeCountText = repostLikeCountText
            commentCountText = repostCommentCountText
            sharedCountText = repostSharedCountText
        }
    }

    fun initForEvent(itemView: View) {
        this.itemView = itemView
        with(itemView) {
            likeCountIcon = eventPostLikeCountIcon
            commentCountIcon = eventPostCommentCountIcon
            sharedCountIcon = eventPostSharedCountIcon
            createdDate = eventPostCreatedDate
            contentText = eventPostContentText
            authorTV = eventPostAuthorTV
            likeCountText = eventPostLikeCountText
            commentCountText = eventPostCommentCountText
            sharedCountText = eventPostSharedCountText
            postLocationIcon = eventPostLocationIcon
            postAddress = eventPostAddress
        }
    }

    fun initForAddPost(itemView: View) {
        this.itemView = itemView
        with(itemView) {
            createdDate = addPostCreatedDate
            contentText = addPostContentText
            authorTV = addPostAuthorTV
            addPoster = addPostPictureIv
        }
    }

    fun initForVideoPost(itemView: View) {
        this.itemView = itemView
        with(itemView) {
            likeCountIcon = videoPostLikeCountIcon
            commentCountIcon = videoPostCommentCountIcon
            sharedCountIcon = videoPostSharedCountIcon
            createdDate = videoPostCreatedDate
            contentText = videoPostContentText
            authorTV = videoPostAuthorTV
            likeCountText = videoPostLikeCountText
            commentCountText = videoPostCommentCountText
            sharedCountText = videoPostSharedCountText
            videoPreview = videoPostVideoPreviewImg
        }
    }

    fun fillPost(postModel: Post) {
        with(postModel) {
            createdDate.text = created
            contentText.text = content
            authorTV.text = author
            fillCountText(likeCountText, likeCount, likedByMe, itemView!!)
            fillCountText(commentCountText, commentCount, commentedByMe, itemView!!)
            fillCountText(sharedCountText, shareCount, sharedByMe, itemView!!)
            iconManagement(
                likeCountIcon,
                likedByMe,
                R.drawable.ic_favorite_active_24dp,
                R.drawable.ic_favorite_inactive_24dp
            )
            iconManagement(
                commentCountIcon,
                commentedByMe,
                R.drawable.ic_comment_active_24dp,
                R.drawable.ic_comment_inactive_24dp
            )
            iconManagement(
                sharedCountIcon,
                sharedByMe,
                R.drawable.ic_share_active_24dp,
                R.drawable.ic_share_inactive_24dp
            )
        }
    }

    fun fillAddPost(postModel: Post) {
        with(postModel) {
            createdDate.text = created
            contentText.text = content
            authorTV.text = author
            addPoster.setImageResource(R.drawable.herbalayf)
        }
    }

    fun fillEvent(event: Post) {
        fillPost(event)
        postAddress.text = (event as Event).address
    }

    fun fillVideoPost(videoPost: Post) {
        fillPost(videoPost)
        videoPreview.setImageResource(R.drawable.hqdefault)
    }

    private fun fillCountText(
        textView: TextView,
        count: Long,
        isTheTextColored: Boolean,
        view: View
    ) {
        if (count == 0L) {
            textView.visibility = View.INVISIBLE
        } else {
            textView.visibility = View.VISIBLE
        }
        with(textView) {
            text = count.toString()
            if (isTheTextColored) {
                setTextColor(ContextCompat.getColor(view.context, R.color.colorRed))
            } else {
                setTextColor(ContextCompat.getColor(view.context, R.color.colorTextSecondary))
            }
        }
    }

    private fun iconManagement(
        icon: ImageButton,
        isTheIconActive: Boolean,
        activeIcon: Int,
        inactiveIcon: Int
    ) {
        icon.setImageResource(if (isTheIconActive) activeIcon else inactiveIcon)
    }
}