package day1

import java.io.BufferedReader
import java.io.File

fun partOne(filename: String): Int {
    val br: BufferedReader = File(filename).bufferedReader()
    val ints: List<Int> = br.readLines().map { it.toInt() }

    return ints.windowed(2).count {(a, b) -> a < b}
}

fun partTwo(filename: String): Int {
    val br: BufferedReader = File(filename).bufferedReader()
    val ints: List<Int> = br.readLines().map { it.toInt() }

    return ints.windowed(4).count { it[0] < it[3] }
}

fun main(args: Array<String>) {
    println(partTwo("day1/input.txt"))
}