package indi.zeroornull.de.tuotuo.kotlin_jike.coroutine

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun main() = runBlocking {
    val user = getUserInfo()
    println(user)


    val friendList = getFriendList(user)
    println(friendList)
    val feedList = getFeedList(friendList)
    println(feedList)
}

suspend fun getUserInfo(): String {
    withContext(Dispatchers.IO) {
        delay(1000L)
    }
    return "xxp"
}

suspend fun getFriendList(user: String): String {
    withContext(Dispatchers.IO) {
        delay(1000L)
    }
    return "Tom, Jack"
}

suspend fun getFeedList(list: String): String {
    withContext(Dispatchers.IO) {
        delay(1000L)
    }
    return "{FeedList...}"
}
