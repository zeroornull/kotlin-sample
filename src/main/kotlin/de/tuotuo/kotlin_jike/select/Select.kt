package indi.zeroornull.de.tuotuo.kotlin_jike.select

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.select

/*fun main() = runBlocking {
    suspend fun getCacheInfo(productId: String): Product? {
        delay(100L)
        return Product(productId, 9.9)
    }

    suspend fun getNetworkInfo(productId: String): Product? {
        delay(200L)
        return Product(productId, 9.8)
    }

    fun updateUI(product: Product) {
        println("${product.productId}==${product.price}")
    }

    val startTime = System.currentTimeMillis()
    val productId = "xxxId"

    val cacheInfo = getCacheInfo(productId)
    if (cacheInfo != null) {
        updateUI(cacheInfo)
        println("Time cost: ${System.currentTimeMillis() - startTime}")
    }

    val latestInfo = getNetworkInfo(productId)
    if (latestInfo != null) {
        updateUI(latestInfo)
        println("Time cost: ${System.currentTimeMillis() - startTime}")
    }

}*/

/*
fun main() = runBlocking {

    suspend fun getCacheInfo(productId: String): Product? {
        delay(100L)
        return Product(productId, 9.9)
    }

    suspend fun getNetworkInfo(productId: String): Product? {
        delay(200L)
        return Product(productId, 9.8)
    }

    fun updateUI(product: Product) {
        println("${product.productId}==${product.price}")
    }

    val startTime = System.currentTimeMillis()
    val productId = "xxxId"

    val product = select<Product?> {
        //
        async { getCacheInfo(productId) }.onAwait {
            it
        }
        async { getNetworkInfo(productId) }.onAwait {
            it
        }
    }

    if (product != null) {
        updateUI(product)
        println("Time cost ${System.currentTimeMillis() - startTime}")
    }

}

data class Product(val productId: String, val price: Double)*/
