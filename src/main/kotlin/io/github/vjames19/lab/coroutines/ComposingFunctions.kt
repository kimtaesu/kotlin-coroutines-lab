package io.github.vjames19.lab.coroutines

/**
 * Created by victor.reventos on 6/29/17.
 */
import kotlinx.coroutines.experimental.*
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    doSequential()
    doAsync()
    runBlocking { doAsyncSuspend() }
}

suspend fun doSomethingOne(): Int {
    delay(1000L) // pretend we are doing something useful here
    return 13
}

suspend fun doSomethingTwo(): Int {
    delay(1000L) // pretend we are doing something useful here, too
    return 29
}

// The result type of asyncSomethingUsefulOne is Deferred<Int>
fun asyncSomethingOne(): Deferred<Int> = async(CommonPool) {
    doSomethingUsefulOne()
}

// The result type of asyncSomethingUsefulTwo is Deferred<Int>
fun asyncSomethingTwo(): Deferred<Int> = async(CommonPool)  {
    doSomethingUsefulTwo()
}


fun doSequential() {
    runBlocking {
        val one = doSomethingOne()
        val two = doSomethingTwo()

        println("sequential ${one + two}")
    }
}

fun  doAsync() {
    val one = asyncSomethingOne()
    val two = asyncSomethingTwo()

    runBlocking {
        println("async ${one.await() + two.await()}")
    }
}

suspend fun doAsyncSuspend() {
    val one = asyncSomethingOne()
    val two = asyncSomethingTwo()

    println("doAsyncSuspend ${one.await() + two.await()}")
}