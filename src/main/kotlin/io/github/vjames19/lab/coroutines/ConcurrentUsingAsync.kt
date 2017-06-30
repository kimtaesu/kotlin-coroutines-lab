package io.github.vjames19.lab.coroutines

import kotlinx.coroutines.experimental.*
import kotlin.system.measureTimeMillis

/**
 * Created by victor.reventos on 6/28/17.
 */
fun main(args: Array<String>) = runBlocking<Unit> {
    val concurrentTime = measureTimeMillis { doSomethingConcurrent() }
    println("Completed concurrent in $concurrentTime ms")

    val sequentialTime = measureTimeMillis { doSomethingSequential() }
    println("Completed concurrent in $sequentialTime ms")
}

suspend fun doSomethingConcurrent() {
    val one = async(CommonPool) { doSomethingUsefulOne() }
    val two = async(CommonPool) { doSomethingUsefulTwo() }
    println("The answer is ${one.await() + two.await()}")

}

suspend fun doSomethingSequential() {
    val one = doSomethingUsefulOne()
    val two = doSomethingUsefulTwo()
    println("The answer is ${one + two}")
}

suspend fun doSomethingUsefulOne(): Int {
    delay(1000L) // pretend we are doing something useful here
    return 13
}

suspend fun doSomethingUsefulTwo(): Int {
    delay(1000L) // pretend we are doing something useful here, too
    return 29
}