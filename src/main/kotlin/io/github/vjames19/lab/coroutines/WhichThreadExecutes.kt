package io.github.vjames19.lab.coroutines

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking

/**
 * Created by victor.reventos on 6/28/17.
 */
fun main(args: Array<String>) = runBlocking<Unit> {
    val jobs = (1..100_000).map {
        launch(CommonPool) {
            delay(1000L)
            println("From thread ${Thread.currentThread()}")
        }
    }

    jobs.forEach { it.join() }
}