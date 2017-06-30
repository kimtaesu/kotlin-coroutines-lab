package io.github.vjames19.lab.coroutines

/**
 * Created by victor.reventos on 6/28/17.
 */
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.channels.consumeEach
import kotlinx.coroutines.experimental.channels.produce

fun main(args: Array<String>) = runBlocking<Unit> {
    val numbers = produceNumbers()

    launch(CommonPool) {
        delay(3000)
        numbers.cancel()
    }

    numbers.consumeEach {
        println(it)
    }
}

fun produceNumbers() = produce(CommonPool) {
    var x = 1
    while (isActive) send(x++)
}