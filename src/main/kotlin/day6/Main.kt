package day6

import java.io.BufferedReader
import java.io.File

fun partOne(filename: String): Int {

    val br: BufferedReader = File(filename).bufferedReader()

    val fishes: ArrayList<Int> = arrayListOf()

    br.forEachLine { l ->
        val words: List<String> = l.split(",")
        words.forEach { fishes.add(Integer.parseInt(it)) }
    }

    for (i in 0 until 80) {
        val fishCopy: ArrayList<Int> = fishes.clone() as ArrayList<Int>
        fishCopy.forEachIndexed { index, element ->
            if (element == 0) {
                fishes.add(8)
                fishes[index] = 7
            }
            fishes[index]--
        }
    }

    return fishes.size
}

fun partTwo(filename: String): Long {

    val br: BufferedReader = File(filename).bufferedReader()

    val fishes: HashMap<Int, Long> = hashMapOf(0 to 0L, 1 to 0L, 2 to 0L, 3 to 0L, 4 to 0L,
        5 to 0L, 6 to 0L, 7 to 0L, 8 to 0L)

    br.forEachLine { l ->
        val words: List<String> = l.split(",")
        words.forEach {
            val num = Integer.parseInt(it)
            fishes[num] = fishes[num]!! + 1
        }
    }

    for (i in 0 until 256) {

        val numZeros = fishes[0]

        fishes.forEach {
            if (it.key == 0) return@forEach
            fishes[it.key - 1] = it.value
            fishes[it.key] = 0L
        }

        fishes[6] = fishes[6]!! + numZeros!!
        fishes[8] = numZeros
    }

    return fishes.values.sum()
}

fun main() {
    val filename = "day6/input.txt"
    println(partOne(filename))
    println(partTwo(filename))
}

