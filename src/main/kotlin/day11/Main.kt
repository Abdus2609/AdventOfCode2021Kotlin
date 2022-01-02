package day11

import java.io.BufferedReader
import java.io.File

fun partOne(filename: String): Int {

    val br: BufferedReader = File(filename).bufferedReader()

    var numFlashes = 0
    val rows: ArrayList<List<Char>> = arrayListOf()

    br.forEachLine {
        rows.add(it.toList())
    }

    val grid: Array<Array<Int>> = Array(10) { Array(10) { 0 } }
    populate(grid, rows)

    for (i in 0 until 100) {
        grid.forEach { r ->
            r.indices.forEach {
                r[it]++
            }
        }

        val visited: Array<Array<Boolean>> = Array(10) { Array(10) { false } }

        while (!noFlashesNeeded(grid)) {
            numFlashes += flash(grid, visited)
        }
    }

    return numFlashes
}

fun partTwo(filename: String): Int {

    val br: BufferedReader = File(filename).bufferedReader()

    val rows: ArrayList<List<Char>> = arrayListOf()

    br.forEachLine {
        rows.add(it.toList())
    }

    val grid: Array<Array<Int>> = Array(10) { Array(10) { 0 } }
    populate(grid, rows)

    var step = 0
    while (!allFlashed(grid)) {
        step++
        grid.forEach { r ->
            r.indices.forEach {
                r[it]++
            }
        }

        val visited: Array<Array<Boolean>> = Array(10) { Array(10) { false } }

        while (!noFlashesNeeded(grid)) {
            flash(grid, visited)
        }
    }

    return step
}

fun allFlashed(grid: Array<Array<Int>>): Boolean = grid.none { r -> r.any { it != 0 } }

fun flashAdjacent(i: Int, j: Int, grid: Array<Array<Int>>, visited: Array<Array<Boolean>>) {

    if (i > 0) {
        if (!visited[i - 1][j]) {
            grid[i - 1][j]++
        }
        if (j > 0 && !visited[i - 1][j - 1]) {
            grid[i - 1][j - 1]++
        }
        if (j < 9 && !visited[i - 1][j + 1]) {
            grid[i - 1][j + 1]++
        }
    }

    if (j > 0) {
        if (!visited[i][j - 1]) {
            grid[i][j - 1]++
        }
        if (i < 9 && !visited[i + 1][j - 1]) {
            grid[i + 1][j - 1]++
        }
    }

    if (j < 9) {
        if (!visited[i][j + 1]) {
            grid[i][j + 1]++
        }
        if (i < 9 && !visited[i + 1][j + 1]) {
            grid[i + 1][j + 1]++
        }
    }

    if (i < 9 && !visited[i + 1][j]) {
        grid[i + 1][j]++
    }
}

fun noFlashesNeeded(grid: Array<Array<Int>>): Boolean = grid.none { r -> r.any { it > 9 } }

fun flash(grid: Array<Array<Int>>, visited: Array<Array<Boolean>>): Int {
    var count = 0

    grid.indices.forEach { r ->
        grid[r].indices.forEach {
            if (grid[r][it] > 9 && !visited[r][it]) {
                grid[r][it] = 0
                visited[r][it] = true
                count++
                flashAdjacent(r, it, grid, visited)
            }
        }
    }

    return count
}

fun populate(grid: Array<Array<Int>>, rows: ArrayList<List<Char>>) {
    for (i in 0 until 10) {
        for (j in 0 until 10) {
            grid[i][j] = rows[i][j].digitToInt()
        }
    }
}

fun main() {
    val filename = "src/main/kotlin/day11/input.txt"
    println(partOne(filename))
    println(partTwo(filename))
}