package de.tuotuo.kotlin_jike.imageprocessor

import indi.zeroornull.de.tuotuo.kotlin_jike.imageprocessor.BASE_PATH
import indi.zeroornull.de.tuotuo.kotlin_jike.imageprocessor.Image
import indi.zeroornull.de.tuotuo.kotlin_jike.imageprocessor.crop
import indi.zeroornull.de.tuotuo.kotlin_jike.imageprocessor.loadImage
import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals

class TestImageProcessor {

    /**
     * 待实现的单元测试
     */
    @Test
    fun testFlipHorizontal() {

    }

    /**
     * 待实现的单元测试
     */
    @Test
    fun testFlipVertical() {

    }

    /**
     * 待实现的单元测试
     */
    @Test
    fun testCrop() {
        val image = loadImage(File("${BASE_PATH}android.png"))
        val height = image.height() / 2
        val width = image.width() / 2
        val target = loadImage(File("${BASE_PATH}android_half_crop.png"))
        val crop = image.crop(0, 0, width, height)
        checkImageSame(crop, target)
    }

    private fun checkImageSame(picture: Image, expected: Image) {
        assertEquals(picture.height(), expected.height())
        assertEquals(picture.width(), expected.width())
        for (row in 0 until picture.height()) {
            for (column in 0 until picture.width()) {
                val actualPixel = picture.getPixel(row, column)
                val expectedPixel = expected.getPixel(row, column)
                assertEquals(actualPixel, expectedPixel)
            }
        }
    }
}