package day2

import java.io.BufferedReader
import java.io.File

fun partOne(filename: String): Int {
    var horizontal: Int = 0
    var depth: Int = 0

    val br: BufferedReader = File(filename).bufferedReader()

    br.forEachLine {
        val elems: List<String> = it.split(" ")
        val action: String = elems[0]
        val value: Int = elems[1].toInt()
        when (action) {
            "forward" -> horizontal += value
            "down" -> depth += value
            "up" -> depth -= value
        }
    }

    return horizontal * depth
}

fun partTwo(filename: String): Int {
    var horizontal: Int = 0
    var depth: Int = 0
    var aim: Int = 0

    val br: BufferedReader = File(filename).bufferedReader()

    br.forEachLine {
        val elems: List<String> = it.split(" ")
        val action: String = elems[0]
        val value: Int = elems[1].toInt()
        when (action) {
            "forward" -> {
                horizontal += value
                depth += aim * value
            }
            "down" -> aim += value
            "up" -> aim -= value
        }
    }

    return horizontal * depth
}

fun main(args: Array<String>) {
    println(partTwo("day2/input.txt"))
}