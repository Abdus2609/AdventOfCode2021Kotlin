package day10

import java.io.BufferedReader
import java.io.File
import java.util.*
import java.util.ArrayDeque

fun partOne(filename: String): Int {

    val br: BufferedReader = File(filename).bufferedReader()

    val scores: Map<Char, Int> = mapOf(')' to 3, ']' to 57, '}' to 1197, '>' to 25137)
    var score = 0

    br.forEachLine {
        val stack: ArrayDeque<Char> = ArrayDeque()
        val foundCorrupted: Optional<Char> = isCorrupted(it, stack)

        if (foundCorrupted.isPresent) {
            score += scores[foundCorrupted.get()]!!
        }
    }

    return score
}

fun partTwo(filename: String): Long {
    val br: BufferedReader = File(filename).bufferedReader()

    val scoreMap: Map<Char, Int> = mapOf('(' to 1, '[' to 2, '{' to 3, '<' to 4)
    val scores: ArrayList<Long> = arrayListOf()

    br.forEachLine { l ->
        val stack: ArrayDeque<Char> = ArrayDeque()
        val foundCorrupted: Optional<Char> = isCorrupted(l, stack)

        if (foundCorrupted.isEmpty) {
            var score: Long = 0
            stack.forEach {
                score += (4 * score) + scoreMap[it]!!
            }

            scores.add(score)
        }
    }

    scores.sort()

    return scores[scores.size / 2]
}

private fun isCorrupted(line: String, stack: ArrayDeque<Char>): Optional<Char> {
    val otherHalf: Map<Char, Char> = mapOf(')' to '(', ']' to '[', '}' to '{', '>' to '<')

    line.forEach {
        when (it) {
            '(', '[', '{', '<' -> {
                stack.push(it)
            }
            else -> {
                if (stack.peek() == otherHalf[it]) {
                    stack.pop()
                } else {
                    return Optional.of(it)
                }
            }
        }
    }

    return Optional.empty()
}

fun main() {
    val filename = "src/main/kotlin/day10/input.txt"
    println(partOne(filename))
    println(partTwo(filename))
}