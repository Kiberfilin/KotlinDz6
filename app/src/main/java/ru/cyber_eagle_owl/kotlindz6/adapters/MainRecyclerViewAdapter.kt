package ru.cyber_eagle_owl.kotlindz6.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxrelay2.BehaviorRelay
import dto.Post
import dto.posttypes.PostType
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import ru.cyber_eagle_owl.kotlindz6.R
import ru.cyber_eagle_owl.kotlindz6.adapters.viewholders.*
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException

class MainRecyclerViewAdapter :
    RecyclerView.Adapter<BaseViewHolder>() {

    internal val bag = CompositeDisposable()
    internal var allPosts = BehaviorRelay.createDefault(ArrayList<Post>())

    init {
        allPosts.observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                notifyDataSetChanged()
            }.addTo(bag)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when (viewType) {
            PostType.POST.value -> PostViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false)
            )
            PostType.EVENT.value -> EventViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.event_post_item, parent, false)
            )
            RepostType.POST_REPOST.value -> RepostViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.repost_item, parent, false)
            )
            RepostType.EVENT_REPOST.value -> RepostViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.repost_item, parent, false)
            )
            RepostType.VIDEO_REPOST.value -> RepostViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.repost_item, parent, false)
            )
            PostType.ADD.value -> AddViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.add_post_item, parent, false)
            )
            PostType.VIDEO.value -> VideoViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.video_post_item, parent, false)
            )
            else -> throw IllegalArgumentException("Такого PostType (viewType = $viewType) не существует!")
        }

    override fun getItemCount() = allPosts.value.size

    override fun getItemId(position: Int) = allPosts.value[position].id

    override fun getItemViewType(position: Int) = when (allPosts.value[position].postType) {
        PostType.POST -> PostType.POST.value
        PostType.EVENT -> PostType.EVENT.value
        PostType.REPOST -> when (allPosts.value[position].source?.postType) {
            PostType.POST -> RepostType.POST_REPOST.value
            PostType.EVENT -> RepostType.EVENT_REPOST.value
            PostType.VIDEO -> RepostType.VIDEO_REPOST.value
            else -> throw IllegalStateException("Неверный source")
        }
        PostType.ADD -> PostType.ADD.value
        PostType.VIDEO -> PostType.VIDEO.value
        else -> throw IllegalStateException("Неверный тип поста")
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        with(holder) {
            bind(allPosts.value[position]) { position -> notifyItemChanged(position) }
        }
    }
}

enum class RepostType(val value: Int) {
    POST_REPOST(21), EVENT_REPOST(22), VIDEO_REPOST(23)
}