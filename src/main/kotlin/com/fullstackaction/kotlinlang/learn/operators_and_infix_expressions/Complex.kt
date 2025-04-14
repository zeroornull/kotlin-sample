package indi.zeroornull.com.fullstackaction.kotlinlang.learn.operators_and_infix_expressions

class Complex(var real: Double, var imaginary: Double) {
    // 复数相加：实部与虚部分别相加
    operator fun plus(other: Complex): Complex {
        return Complex(real + other.real, imaginary + other.imaginary)
    }

    // 复数与整数相加，只处理实部
    operator fun plus(other: Int): Complex {
        return Complex(real + other, imaginary)
    }

    // 复数使用()时，求弦长（三角形的斜边长度）
    operator fun invoke(): Double {
        return Math.hypot(real, imaginary)
    }

    override fun toString(): String {
        return "$real + ${imaginary}i"
    }
}

fun main() {
    val c1 = Complex(3.0, 4.0)
    val c2 = Complex(2.0,7.5)
    println(c1 + c2)
    println(c1 + 2)
    println(c1())
}