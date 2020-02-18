package client

import dto.*
import io.ktor.client.HttpClient
import io.ktor.client.features.json.GsonSerializer
import io.ktor.client.features.json.JsonFeature
import io.ktor.http.ContentType
import io.ktor.util.KtorExperimentalAPI
import ru.cyber_eagle_owl.kotlindz6.RuntimeTypeAdapterFactory

@KtorExperimentalAPI
object Api {
    const val url =
        "https://raw.githubusercontent.com/Kiberfilin/gsonConverterForDz6/master/output.json"

    val client = HttpClient {
        install(JsonFeature) {
            acceptContentTypes = listOf(
                ContentType.Text.Plain,
                ContentType.Application.Json
            )
            serializer = GsonSerializer {
                registerTypeAdapterFactory(
                    RuntimeTypeAdapterFactory.of(Post::class.java, "postType", true)
                        .registerSubtype(Post::class.java, "POST")
                        .registerSubtype(Event::class.java, "EVENT")
                        .registerSubtype(Repost::class.java, "REPOST")
                        .registerSubtype(Add::class.java, "ADD")
                        .registerSubtype(Video::class.java, "VIDEO")
                )

            }
        }
    }
}