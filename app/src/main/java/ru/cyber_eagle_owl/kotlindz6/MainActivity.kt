package ru.cyber_eagle_owl.kotlindz6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import client.Api
import dto.*
import kotlin.collections.ArrayList
import io.ktor.client.request.get
import io.ktor.util.KtorExperimentalAPI
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import ru.cyber_eagle_owl.kotlindz6.adapters.MainRecyclerViewAdapter

class MainActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    private lateinit var postsAdapter: MainRecyclerViewAdapter

    @KtorExperimentalAPI
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*fun testData(): ArrayList<Post> = arrayListOf(
            Post(
                34, "CATS", "All your base are belong to us", "1992",
                likeCount = 3,
                commentCount = 1,
                shareCount = 0,
                likedByMe = true,
                commentedByMe = true,
                sharedByMe = false,
                postType = PostType.POST
            ),
            Event(
                323, "CATS", "Event: All your base are belong to us", "1992",
                likeCount = 3,
                commentCount = 31,
                shareCount = 0,
                likedByMe = true,
                commentedByMe = false,
                sharedByMe = false,
                postType = PostType.EVENT,
                address = "Shimizu, Suginami City, Tokyo, Japan",
                coordinates = Coordinates(35.7135292, 139.6134291)
            ),
            Post(
                314, "CATS", "Repost 1 All your base are belong to us", "1992",
                likeCount = 3,
                commentCount = 1,
                shareCount = 0,
                likedByMe = true,
                commentedByMe = false,
                sharedByMe = false,
                postType = PostType.REPOST,
                source = Post(
                    234, "CATS", "Source post for repost. All your base are belong to us", "1992",
                    likeCount = 3,
                    commentCount = 1,
                    shareCount = 0,
                    likedByMe = true,
                    commentedByMe = false,
                    sharedByMe = false,
                    postType = PostType.POST
                )
            ),
            Add(
                348032,
                "CATS",
                "Реклама: All your base are belong to us",
                "1992",
                postType = PostType.ADD,
                url = "https://duckduckgo.com/?q=herbalife&atb=v127-3bd&ia=web"

            ),
            Post(
                3, "CATS", "Repost 2 All your base are belong to us", "1992",
                likeCount = 3,
                commentCount = 1,
                shareCount = 0,
                likedByMe = true,
                commentedByMe = false,
                sharedByMe = false,
                postType = PostType.REPOST,
                source = Event(
                    323,
                    "CATS",
                    "Source event post for repost. Event: All your base are belong to us",
                    "1992",
                    likeCount = 3,
                    commentCount = 31,
                    shareCount = 0,
                    likedByMe = true,
                    commentedByMe = false,
                    sharedByMe = false,
                    postType = PostType.EVENT,
                    address = "Shimizu, Suginami City, Tokyo, Japan",
                    coordinates = Coordinates(35.7135292, 139.6134291)
                )
            ), Post(
                34, "CATS", "All your base are belong to us", "1992",
                likeCount = 3,
                commentCount = 1,
                shareCount = 0,
                likedByMe = true,
                commentedByMe = true,
                sharedByMe = false,
                postType = PostType.POST
            ),
            Event(
                323, "CATS", "Event: All your base are belong to us", "1992",
                likeCount = 3,
                commentCount = 31,
                shareCount = 0,
                likedByMe = true,
                commentedByMe = false,
                sharedByMe = false,
                postType = PostType.EVENT,
                address = "Shimizu, Suginami City, Tokyo, Japan",
                coordinates = Coordinates(35.7135292, 139.6134291)
            ),
            Post(
                314, "CATS", "Repost 1 All your base are belong to us", "1992",
                likeCount = 3,
                commentCount = 1,
                shareCount = 0,
                likedByMe = true,
                commentedByMe = false,
                sharedByMe = false,
                postType = PostType.REPOST,
                source = Post(
                    234, "CATS", "Source post for repost. All your base are belong to us", "1992",
                    likeCount = 3,
                    commentCount = 1,
                    shareCount = 0,
                    likedByMe = true,
                    commentedByMe = false,
                    sharedByMe = false,
                    postType = PostType.POST
                )
            ),
            Post(
                34, "CATS", "All your base are belong to us", "1992",
                likeCount = 3,
                commentCount = 1,
                shareCount = 0,
                likedByMe = true,
                commentedByMe = true,
                sharedByMe = false,
                postType = PostType.POST
            ),
            Post(
                3, "CATS", "Repost 2 All your base are belong to us", "1992",
                likeCount = 3,
                commentCount = 1,
                shareCount = 0,
                likedByMe = true,
                commentedByMe = false,
                sharedByMe = false,
                postType = PostType.REPOST,
                source = Event(
                    323,
                    "CATS",
                    "Source event post for repost. Event: All your base are belong to us",
                    "1992",
                    likeCount = 3,
                    commentCount = 31,
                    shareCount = 0,
                    likedByMe = true,
                    commentedByMe = false,
                    sharedByMe = false,
                    postType = PostType.EVENT,
                    address = "Shimizu, Suginami City, Tokyo, Japan",
                    coordinates = Coordinates(35.7135292, 139.6134291)
                )
            ), Video(
                34, "CATS", "All your base are belong to us", "1992",
                likeCount = 25,
                commentCount = 8,
                shareCount = 12,
                likedByMe = true,
                commentedByMe = true,
                sharedByMe = false,
                postType = PostType.VIDEO,
                url = "https://www.youtube.com/watch?v=jQE66WA2s-A"
            ),
            Event(
                323, "CATS", "Event: All your base are belong to us", "1992",
                likeCount = 3,
                commentCount = 31,
                shareCount = 0,
                likedByMe = true,
                commentedByMe = false,
                sharedByMe = false,
                postType = PostType.EVENT,
                address = "Shimizu, Suginami City, Tokyo, Japan",
                coordinates = Coordinates(35.7135292, 139.6134291)
            ),
            Post(
                3, "CATS", "Repost Video All your base are belong to us", "1992",
                likeCount = 3,
                commentCount = 1,
                shareCount = 0,
                likedByMe = true,
                commentedByMe = false,
                sharedByMe = false,
                postType = PostType.REPOST,
                source = Video(
                    34, "CATS", "All your base are belong to us", "1992",
                    likeCount = 25,
                    commentCount = 8,
                    shareCount = 12,
                    likedByMe = true,
                    commentedByMe = true,
                    sharedByMe = false,
                    postType = PostType.VIDEO,
                    url = "https://www.youtube.com/watch?v=jQE66WA2s-A"
                )
            ),
            Post(
                314, "CATS", "Repost 1 All your base are belong to us", "1992",
                likeCount = 3,
                commentCount = 1,
                shareCount = 0,
                likedByMe = true,
                commentedByMe = false,
                sharedByMe = false,
                postType = PostType.REPOST,
                source = Post(
                    234, "CATS", "Source post for repost. All your base are belong to us", "1992",
                    likeCount = 3,
                    commentCount = 1,
                    shareCount = 0,
                    likedByMe = true,
                    commentedByMe = false,
                    sharedByMe = false,
                    postType = PostType.POST
                )
            ),
            Post(
                34, "CATS", "All your base are belong to us", "1992",
                likeCount = 3,
                commentCount = 1,
                shareCount = 0,
                likedByMe = true,
                commentedByMe = true,
                sharedByMe = false,
                postType = PostType.POST
            ),
            Post(
                3, "CATS", "Repost 2 All your base are belong to us", "1992",
                likeCount = 3,
                commentCount = 1,
                shareCount = 0,
                likedByMe = true,
                commentedByMe = false,
                sharedByMe = false,
                postType = PostType.REPOST,
                source = Event(
                    323,
                    "CATS",
                    "Source event post for repost. Event: All your base are belong to us",
                    "1992",
                    likeCount = 3,
                    commentCount = 31,
                    shareCount = 0,
                    likedByMe = true,
                    commentedByMe = false,
                    sharedByMe = false,
                    postType = PostType.EVENT,
                    address = "Shimizu, Suginami City, Tokyo, Japan",
                    coordinates = Coordinates(35.7135292, 139.6134291)
                )
            ), Post(
                34, "CATS", "All your base are belong to us", "1992",
                likeCount = 3,
                commentCount = 1,
                shareCount = 0,
                likedByMe = true,
                commentedByMe = true,
                sharedByMe = false,
                postType = PostType.POST
            ),
            Event(
                323, "CATS", "Event: All your base are belong to us", "1992",
                likeCount = 3,
                commentCount = 31,
                shareCount = 0,
                likedByMe = true,
                commentedByMe = false,
                sharedByMe = false,
                postType = PostType.EVENT,
                address = "Shimizu, Suginami City, Tokyo, Japan",
                coordinates = Coordinates(35.7135292, 139.6134291)
            ),
            Post(
                314, "CATS", "Repost 1 All your base are belong to us", "1992",
                likeCount = 3,
                commentCount = 1,
                shareCount = 0,
                likedByMe = true,
                commentedByMe = false,
                sharedByMe = false,
                postType = PostType.REPOST,
                source = Post(
                    234, "CATS", "Source post for repost. All your base are belong to us", "1992",
                    likeCount = 3,
                    commentCount = 1,
                    shareCount = 0,
                    likedByMe = true,
                    commentedByMe = false,
                    sharedByMe = false,
                    postType = PostType.POST
                )
            ),
            Post(
                34, "CATS", "All your base are belong to us", "1992",
                likeCount = 3,
                commentCount = 1,
                shareCount = 0,
                likedByMe = true,
                commentedByMe = true,
                sharedByMe = false,
                postType = PostType.POST
            ),
            Post(
                3, "CATS", "Repost 2 All your base are belong to us", "1992",
                likeCount = 3,
                commentCount = 1,
                shareCount = 0,
                likedByMe = true,
                commentedByMe = false,
                sharedByMe = false,
                postType = PostType.REPOST,
                source = Event(
                    323,
                    "CATS",
                    "Source event post for repost. Event: All your base are belong to us",
                    "1992",
                    likeCount = 3,
                    commentCount = 31,
                    shareCount = 0,
                    likedByMe = true,
                    commentedByMe = false,
                    sharedByMe = false,
                    postType = PostType.EVENT,
                    address = "Shimizu, Suginami City, Tokyo, Japan",
                    coordinates = Coordinates(35.7135292, 139.6134291)
                )
            ), Post(
                34, "CATS", "All your base are belong to us", "1992",
                likeCount = 3,
                commentCount = 1,
                shareCount = 0,
                likedByMe = true,
                commentedByMe = true,
                sharedByMe = false,
                postType = PostType.POST
            ),
            Event(
                323, "CATS", "Event: All your base are belong to us", "1992",
                likeCount = 3,
                commentCount = 31,
                shareCount = 0,
                likedByMe = true,
                commentedByMe = false,
                sharedByMe = false,
                postType = PostType.EVENT,
                address = "Shimizu, Suginami City, Tokyo, Japan",
                coordinates = Coordinates(35.7135292, 139.6134291)
            ),
            Post(
                314, "CATS", "Repost 1 All your base are belong to us", "1992",
                likeCount = 3,
                commentCount = 1,
                shareCount = 0,
                likedByMe = true,
                commentedByMe = false,
                sharedByMe = false,
                postType = PostType.REPOST,
                source = Post(
                    234, "CATS", "Source post for repost. All your base are belong to us", "1992",
                    likeCount = 3,
                    commentCount = 1,
                    shareCount = 0,
                    likedByMe = true,
                    commentedByMe = false,
                    sharedByMe = false,
                    postType = PostType.POST
                )
            ),
            Post(
                34, "CATS", "All your base are belong to us", "1992",
                likeCount = 3,
                commentCount = 1,
                shareCount = 0,
                likedByMe = true,
                commentedByMe = true,
                sharedByMe = false,
                postType = PostType.POST
            ),
            Post(
                3, "CATS", "Repost 2 All your base are belong to us", "1992",
                likeCount = 3,
                commentCount = 1,
                shareCount = 0,
                likedByMe = true,
                commentedByMe = false,
                sharedByMe = false,
                postType = PostType.REPOST,
                source = Event(
                    323,
                    "CATS",
                    "Source event post for repost. Event: All your base are belong to us",
                    "1992",
                    likeCount = 3,
                    commentCount = 31,
                    shareCount = 0,
                    likedByMe = true,
                    commentedByMe = false,
                    sharedByMe = false,
                    postType = PostType.EVENT,
                    address = "Shimizu, Suginami City, Tokyo, Japan",
                    coordinates = Coordinates(35.7135292, 139.6134291)
                )
            ),
            Post(
                4, "CATS", "All your base are belong to us", "1992",
                likeCount = 3,
                commentCount = 1,
                shareCount = 0,
                likedByMe = true,
                commentedByMe = false,
                sharedByMe = false,
                postType = PostType.POST
            )
        )
        */

        //prepareRecyclerView(testData())
        progressBar.visibility = View.VISIBLE
        launch {
            val list = withContext(Dispatchers.IO){
                Api.client.get<ArrayList<Post>>(Api.url)
            }
            Log.d("dddddd", "$list")
            prepareRecyclerView(list)
        }
    }

    private fun prepareRecyclerView(posts: ArrayList<Post>) {
        postsAdapter = MainRecyclerViewAdapter()
        with(mainRv) {
            layoutManager = LinearLayoutManager (this@MainActivity)
            adapter = postsAdapter
            (adapter as MainRecyclerViewAdapter).allPosts.accept(posts)
            progressBar.visibility = View.GONE
        }
    }

    override fun onStop() {
        postsAdapter.bag.clear()
        super.onStop()
    }
}
