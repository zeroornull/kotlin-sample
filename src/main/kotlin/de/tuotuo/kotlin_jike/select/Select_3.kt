package indi.zeroornull.de.tuotuo.kotlin_jike.select

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.select

fun main() = runBlocking {

    suspend fun getCacheInfo(productId: String): Product? {
        delay(100L)
        return Product(productId, 9.9)
    }

    suspend fun getNetworkInfo(productId: String): Product? {
        delay(2000L)
        return Product(productId, 9.8)
    }

    fun updateUI(product: Product) {
        println("${product.productId}==${product.price}")
    }

    val startTime = System.currentTimeMillis()
    val productId = "xxxId"

    val cacheDeferred = async {
        getCacheInfo(productId)
    }

    val latestDeferred = async {
        getNetworkInfo(productId)
    }

    val product = select<Product?> {
        //
        cacheDeferred.onAwait {
            it?.copy(isCache = true)
        }
        latestDeferred.onAwait {
            it?.copy(isCache = false)
        }
    }

    if (product != null) {
        updateUI(product)
        println("Time cost ${System.currentTimeMillis() - startTime}")
    }

    if (product?.isCache == true) {
        val latest = latestDeferred.await() ?: return@runBlocking
        updateUI(latest)
        println("Time cost :${System.currentTimeMillis() - startTime}")
    }

}

data class Product(val productId: String, val price: Double, val isCache: Boolean = false)