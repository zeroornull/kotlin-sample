plugins {
    kotlin("jvm") version "2.1.20"
    kotlin("plugin.noarg") version "2.1.20"
    kotlin("plugin.allopen") version "2.1.20"
}

group = "indi.zeroornull"
version = "1.0-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    // noarg 插件配置
    noArg {
        annotation("indi.zeroornull.com.fullstackaction.kotlinlang.learn.data_class.Poko")
        invokeInitializers = true // 可选：是否在无参构造器中执行 init 块，默认为 false
    }
// allopen 插件配置
    allOpen {
        annotation("indi.zeroornull.com.fullstackaction.kotlinlang.learn.data_class.Poko")
    }
    jvmToolchain(8)

}
