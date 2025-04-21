package indi.zeroornull.de.tuotuo.kotlin_jike.kthttp

import com.google.gson.Gson
import indi.zeroornull.de.tuotuo.kotlin_jike.kthttp.annotations.Field
import indi.zeroornull.de.tuotuo.kotlin_jike.kthttp.annotations.GET
import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy
import kotlin.reflect.jvm.javaMethod


/**
 * 任何支持Get API
 * 以 GitHub Trendings API 为例：
 * https://trendings.herokuapp.com/repo?lang=java&since=weekly
 */
interface ApiServiceV2 {
    @GET("/repo")
    fun repos(
        @Field("lang") lang: String,
        @Field("since") since: String
    ): RepoList
}

object KtHttpV2 {
    private var okHttpClient: OkHttpClient = OkHttpClient()
    private var gson: Gson = Gson()
    var baseUrl = "https://trendings.herokuapp.com"

    inline fun <reified T> create(): T {
        return Proxy.newProxyInstance(
            T::class.java.classLoader,
            arrayOf(T::class.java)
        ) { proxy, method, args ->
            return@newProxyInstance method.annotations
                .filterIsInstance<GET>()
                .takeIf { it.size == 1 }
                ?.let { invoke("$baseUrl${it[0].value}", method, args) }
        } as T
    }

    fun invoke(url: String, method: Method, args: Array<out Any>): Any? = method
        .parameterAnnotations
        .takeIf { method.parameterAnnotations.size == args.size }
        ?.mapIndexed { index, it -> Pair(it, args[index]) }
        ?.fold(url, ::parseUrl)
        ?.let { Request.Builder().url(url).build() }
        ?.let { okHttpClient.newCall(it).execute().body?.string() }
        ?.let { gson.fromJson(it, method.genericReturnType) }

    private fun parseUrl(acc: String, pair: Pair<Array<Annotation>, Any>) =
        pair.first.filterIsInstance<Field>()
            .first()
            .let { field ->
                if (acc.contains("?")) {
                    "$acc&${field.value}=${pair.second}"
                } else {
                    "$acc?${field.value}=${pair.second}"
                }
            }

}

fun main() {
    val data: RepoList = KtHttpV2.create<ApiServiceV2>().repos(
        lang = "Kotlin",
        since = "weekly"
    )
    println(data)
}
