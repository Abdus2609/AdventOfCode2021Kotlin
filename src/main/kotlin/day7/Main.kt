package day7

import java.io.BufferedReader
import java.io.File
import kotlin.math.abs

fun partOne(filename: String): Int {

    val br: BufferedReader = File(filename).bufferedReader()

    val positions: ArrayList<Int> = arrayListOf()

    br.forEachLine { l ->
        val words: List<String> = l.split(",")
        words.forEach { positions.add(Integer.parseInt(it)) }
    }

    var minFuel = Integer.MAX_VALUE
    val maxPos = positions.maxOrNull()

    for (i in 0 until maxPos!!) {
        var sum = 0
        positions.forEach { sum += abs(it - i) }
        minFuel = if (sum < minFuel) sum else minFuel
    }

    return minFuel
}

fun partTwo(filename: String): Int {

    val br: BufferedReader = File(filename).bufferedReader()

    val positions: ArrayList<Int> = arrayListOf()

    br.forEachLine { l ->
        val words: List<String> = l.split(",")
        words.forEach { positions.add(Integer.parseInt(it)) }
    }

    var minFuel = Integer.MAX_VALUE
    val maxPos = positions.maxOrNull()

    for (i in 0 until maxPos!!) {
        var sum = 0
        positions.forEach {
            val diff = abs(it - i)
            sum += (diff * (diff + 1)) / 2
        }

        minFuel = if (sum < minFuel) sum else minFuel
    }

    return minFuel
}

fun main() {
    val filename = "src/main/kotlin/day7/input.txt"
    println(partOne(filename))
    println(partTwo(filename))
}