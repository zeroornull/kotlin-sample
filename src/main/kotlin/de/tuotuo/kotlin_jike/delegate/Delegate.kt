package indi.zeroornull.de.tuotuo.kotlin_jike.delegate

interface DB {
    fun save()
}

class SqlDB() : DB {
    override fun save() {
        println("save to sql")
    }
}

class GreenDaoDB() : DB {
    override fun save() {
        println("save to GreenDao")
    }
}

class UniversalDB(db: DB) : DB by db

fun main() {
    UniversalDB(SqlDB()).save()
    UniversalDB(GreenDaoDB()).save()
}