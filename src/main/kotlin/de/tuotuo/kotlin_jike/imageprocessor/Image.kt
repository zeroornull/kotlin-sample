package indi.zeroornull.de.tuotuo.kotlin_jike.imageprocessor

import java.awt.Color

class Image(private val pixels: Array<Array<Color>>) {
    fun height(): Int {
        return pixels.size
    }

    fun width(): Int {
        return pixels[0].size
    }

    fun getPixel(y: Int, x: Int): Color {
        return pixels[y][x]
    }

}