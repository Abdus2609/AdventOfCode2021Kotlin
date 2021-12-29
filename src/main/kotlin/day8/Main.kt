package day8

import java.io.BufferedReader
import java.io.File

fun partOne(filename: String): Int {

    val br: BufferedReader = File(filename).bufferedReader()

    var numDigits = 0

    br.forEachLine { l ->
        val words: List<String> = l.split(" | ")[1].split(" ")
        words.forEach { numDigits += if (checkUniqueLength(it)) 1 else 0 }
    }

    return numDigits
}

private fun checkUniqueLength(word: String): Boolean {
    return (word.length in 2 until 5) || word.length == 7
}

fun partTwo(filename: String): Int {

    val br: BufferedReader = File(filename).bufferedReader()

    var sum = 0

    br.forEachLine { l ->
        val map: HashMap<Int, String> = hashMapOf()
        val allWords: ArrayList<String> = arrayListOf()
        val sections: List<String> = l.split(" | ")
        val output: List<String> = sections[1].split(" ")
        allWords.addAll(sections[0].split(" ") + output)

        allWords.forEach { decodeUnique(it, map) }
        allWords.filter { it.length == 6 }.forEach { decodeSix(it, map) }
        allWords.filter { it.length == 5 }.forEach { decodeFive(it, map) }

        var outputWord = ""
        output.forEach { outputWord += reverseLookup(it, map) }

        sum += Integer.parseInt(outputWord)
    }

    return sum
}

fun decodeUnique(word: String, map: HashMap<Int, String>) {
    val key: Int = when (word.length) {
        2 -> 1
        3 -> 7
        4 -> 4
        7 -> 8
        else -> return
    }

    map[key] = word
}

fun decodeSix(word: String, map: HashMap<Int, String>) {
    map[1]!!.forEach {
        if (!word.contains(it)) {
            map[6] = word
            return
        }
    }

    map[4]!!.forEach {
        if (!word.contains(it)) {
            map[0] = word
            return
        }
    }

    map[9] = word
}

fun decodeFive(word: String, map: HashMap<Int, String>) {
    word.forEach {
        if (!map[9]!!.contains(it)) {
            map[2] = word
            return
        }
    }

    map[1]!!.forEach {
        if (!word.contains(it)) {
            map[5] = word
            return
        }
    }

    map[3] = word
}

fun reverseLookup(value: String, map: HashMap<Int, String>): Int {
    map.keys.filter { map[it]!!.length == value.length }.forEach { n ->
        val word = map[n]
        var containsNum = true

        word!!.forEach {
            if (!value.contains(it)) {
                containsNum = false
                return@forEach
            }
        }

        if (containsNum) return n
    }

    return -1
}

fun main() {
    val filename = "src/main/kotlin/day8/input.txt"
    println(partOne(filename))
    println(partTwo(filename))
}