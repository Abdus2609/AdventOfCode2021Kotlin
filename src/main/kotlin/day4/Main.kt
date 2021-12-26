package day4

import java.io.BufferedReader
import java.io.File
import java.util.*
import kotlin.collections.ArrayList

class BingoBoard(strings: List<String>) {

    var board: Array<Array<Int>> = Array(5) { Array(5) { 0 } }
    var allNums: ArrayList<Int> = arrayListOf()

    init {
        populate(strings)
    }

    fun populate(strings: List<String>) {
        strings.forEach {
            val nums: List<String> = it.split(" ")
            for (i in 0 until 5) {
                val num: Int = Integer.parseInt(nums[i])
                board[strings.indexOf(it)][i] = num
                allNums.add(num)
            }
        }
    }

    private fun getRowsAndCols(): ArrayList<List<Int>> {
        val rowsAndCols: ArrayList<List<Int>> = arrayListOf()

        for (i in board.indices) {
            rowsAndCols.add(board[i].toList())
            rowsAndCols.add(board.map { it[i] })
        }

        return rowsAndCols
    }

    fun checkWinner(chosenNumbers: ArrayList<Int>): Optional<List<Int>> {
        val rowsAndCols: ArrayList<List<Int>> = getRowsAndCols()

        rowsAndCols.forEach {
            if (chosenNumbers.containsAll(it)) {
                return Optional.of(it)
            }
        }

        return Optional.empty()
    }
}

fun partOne(filename: String): Int {
    val br: BufferedReader = File(filename).bufferedReader()

    val bingoNumbers: List<Int> = br.readLine().split(",").map(Integer::parseInt)

    val boards: ArrayList<BingoBoard> = arrayListOf()

    var index: Int = 0
    var strings: ArrayList<String> = arrayListOf()

    br.forEachLine {
        if (it.isNotEmpty()) {
            if (index == 0) {
                strings = arrayListOf()
            }

            strings.add(index++, it)

            if (index == 5) {
                boards.add(BingoBoard(strings))
                index = 0
            }
        }
    }

    var sumUnmarked: Int = 0

    val chosenNumbers: ArrayList<Int> = arrayListOf()
    bingoNumbers.forEach { n ->
        chosenNumbers.add(n)

        val winningBoards: List<BingoBoard> = boards.filter {
            it.checkWinner(chosenNumbers).isPresent
        }

        if (winningBoards.isNotEmpty()) {
            val winningBoard: BingoBoard = winningBoards[0]
            val unmarked: List<Int> = winningBoard.allNums.filter { !chosenNumbers.contains(it) }

            sumUnmarked += unmarked.sum()
            return sumUnmarked * n
        }
    }

    return 0
}

fun partTwo(filename: String): Int {
    val br: BufferedReader = File(filename).bufferedReader()

    val bingoNumbers: List<Int> = br.readLine().split(",").map(Integer::parseInt)

    val boards: ArrayList<BingoBoard> = arrayListOf()

    var index: Int = 0
    var strings: ArrayList<String> = arrayListOf()

    br.forEachLine {
        if (it.isNotEmpty()) {
            if (index == 0) {
                strings = arrayListOf()
            }

            strings.add(index++, it)

            if (index == 5) {
                boards.add(BingoBoard(strings))
                index = 0
            }
        }
    }

    var sumUnmarked: Int
    var lastWinningScore: Int = 0

    val chosenNumbers: ArrayList<Int> = arrayListOf()
    bingoNumbers.forEach { n ->
        chosenNumbers.add(n)

        val winningBoards: List<BingoBoard> = boards.filter {
            it.checkWinner(chosenNumbers).isPresent
        }

        winningBoards.forEach { b ->
            val unmarked: List<Int> = b.allNums.filter { !chosenNumbers.contains(it) }

            sumUnmarked = unmarked.sum()
            lastWinningScore = sumUnmarked * n
            boards.remove(b)
        }
    }

    return lastWinningScore
}

fun main() {
    val filename = "day4/input.txt"
    println(partOne(filename))
    println(partTwo(filename))
}

