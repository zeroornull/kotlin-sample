package indi.zeroornull.de.tuotuo.kotlin_jike.kthttp.annotations

@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class Field(val value: String)