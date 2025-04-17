package indi.zeroornull.com.fullstackaction.kotlinlang.learn.data_class

/*
componentN 方法
大前端 es6 中有一个解构的语法特性我很是喜欢，通过类似 let {name,isCheck} = option 的写法就可以从 option 对象中提取出 name 和 isCheck 属性，
而不再是使用 let name = option.name 这种写法，解构的语法让代码得以更进一步的简化，那么作为后起之秀的 Kotlin 是否也有这种特性呢？
没错，Kotlin 也有（注意：两者还是有区别的），它就是 componentN 方法 ，我们直接来看代码：
*/

/*class User(val username: String, val age: Int) {
    operator fun component1(): String {
        return username
    }

    operator fun component2(): Int {
        return age
    }
}*/

/*
使用 val (arg1, arg2) in object 这种写法，就可以从 object 对象中提取出 component1() 和 component2() 的返回结果，
而且 componentN 方法 的返回值类型是没有限制的。
*/

/*
数据类(data class)
你可能会觉得 Kotlin 为了达到类似 "es6 解构" 的语法特性，对开发者的要求有点高啊，还要自己编写 componentN 方法 ，
是的，如果就只是这样的话，那很糗唉，还好，Kotlin 中的 数据类(data class) 就可以自动为对象生成 componentN 方法 ，
不仅如此，data class 还会默认生成 toString()、copy() 等方法：
*/

data class User(val username: String, val age: Int)

/*
可见，使用 data class 就无须自己手写 componentN 方法 了，抛开 "es6 解构" 语法，我们来看 数据类(data class) ，顾名思义， 
data class 被设计出来就是为了存储数据的，最核心最应该被关注的就是数据本身，所以在声明 data class 时，
我们只需要把成员属性写好，剩下的交给 data class ，它会自己处理好 toString()、copy() 等方法，componentN 方法 
也只是为了方便开发者提取其中的数据而已。

从 Java 开发者的角度来看，数据类(data class) 的诞生就是为了取代 JavaBean 的。

还记得 IntArray.withIndex() 吗？它的返回值 Iterable<IndexedValue<Int>> 中泛型类 IndexedValue 就是 data class：

// _Arrays.kt
/**
 * Returns a lazy [Iterable] of [IndexedValue] for each element of the original array.
 */
public fun IntArray.withIndex(): Iterable<IndexedValue<Int>> {
    return IndexingIterable { iterator() }
}

// IndexedValue.kt
/**
 * Data class representing a value from a collection or sequence, along with its index in that collection or sequence.
 *
 * @property value the underlying value.
 * @property index the index of the value in the collection or sequence.
 */
public data class IndexedValue<out T>(public val index: Int, public val value: T)
因此，在 for 循环中，才会有 (index, value) in intArray.withIndex() 这种写法，本质上就是使用了 data class 默认生成的 componentN 方法 ：


*/

/*
allOpen 和 noArg 插件
相比 JavaBean，虽然 data class 有很多好用的语法糖特性，但也有其不足的地方：
data class 默认 final，无法被继承。
data class 默认没有无参构造函数。
借助 Show Kotlin Bytecode 工具，将上面的 data class User 类反编译成 Java 代码，发现确实有这 2 点问题：

public final class User {
   @NotNull
   private final String username;
   private final int age;

   @NotNull
   public final String getUsername() {
      return this.username;
   }

   public final int getAge() {
      return this.age;
   }

   public User(@NotNull String username, int age) {
      super();
      this.username = username;
      this.age = age;
   }

   @NotNull
   public final String component1() {
      return this.username;
   }

   public final int component2() {
      return this.age;
   }

   @NotNull
   public final User copy(@NotNull String username, int age) {
      Intrinsics.checkParameterIsNotNull(username, "username");
      return new User(username, age);
   }

   @NotNull
   public String toString() {
      return "User(username=" + this.username + ", age=" + this.age + ")";
   }

   public int hashCode() { ... }

   public boolean equals(@Nullable Object var1) { ... }
}

在一些开发场景下(如数据库)，会要求数据类必须要有无参构造函数或可继承，这对 data class 本身来说是无解的，
但可以借助 allOpen 和 noArg 插件来解决这个问题，让 data class 在编译期增加无参构造器（noArg 插件），
并去除 final 限制（allOpen 插件），集成这 2 个插件的步骤如下：

1. 声明注解类
编写一个注解类，名字随便，后续用得到：

annotation class Poko

2. 配置 Gradle 脚本
在工程的 gradle 脚本文件中，配置 allOpen 和 noArg 插件：

buildscript {
    ext.kotlin_version = '1.3.72'
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
        // Step1. 依赖插件
        classpath "org.jetbrains.kotlin:kotlin-noarg:$kotlin_version"
        classpath "org.jetbrains.kotlin:kotlin-allopen:$kotlin_version"
    }
}

// Step2. 应用插件
apply plugin: "kotlin-noarg"
apply plugin: "kotlin-allopen"

// Step3. 配置插件
noArg {
    // invokeInitializers = true // ture：无参构造器中执行 init{} 代码；false(默认值)：不执行 init{} 代码
    annotation("com.charylin.kotlinlearn.annotation.Poko")
}
allOpen {
    annotation("com.charylin.kotlinlearn.annotation.Poko")
}
Gradle 脚本中，noArg 和 allOpen 配置项就用到了前面的注解类 Poko，只需要把注解类 Poko 的全路径配置进去就好了。

#3.使用注解类
在 data class 上使用注解类 Poko ：

@Poko
data class User(val username: String, val age: Int)
经过上述步骤便完成了 allOpen 和 noArg 插件的集成和使用，接下来就是 rebuild project，然后再反编译看看 User 类的 Java 代码：

public class User {
   ...
   public User(@NotNull String username, int age) {
      Intrinsics.checkParameterIsNotNull(username, "username");
      super();
      this.username = username;
      this.age = age;
   }

   public User() {
   }
}
可以看到，final 限制去除了，也多了一个无参构造器。前面也提到了，这 2 个插件是在编译期对 data class 进行修改，
也就是说，我们无法在编译期之前（也就是 coding 的时候）去使用该无参构造器或继承该 data class，不过呢，可以通过反射来处理这个问题。

*/


fun main() {
    val user = User("John", 18)
    println("username = ${user.username} , age = ${user.age}")// username = lqr , age = 18
    val (username, age) = user
    println("username = ${username}, age = $age")
    println(user)

    var intArray = intArrayOf(1, 2, 3, 4, 5)
    for ((index, value) in intArray.withIndex()) {
        println("index=$index,value=$value")
    }

}
