package day9

import java.io.BufferedReader
import java.io.File

fun partOne(filename: String): Int {

    val br: BufferedReader = File(filename).bufferedReader()

    val rows: ArrayList<List<Char>> = arrayListOf()

    br.forEachLine {
        rows.add(it.toList())
    }

    val grid: Array<Array<Int>> = Array(rows.size) { Array(rows[0].size) { 0 } }
    populate(grid, rows)

    val lowPoints: ArrayList<Int> = arrayListOf()

    for (i in 0 until rows.size) {
        for (j in 0 until rows[0].size) {
            if (isLowPoint(i, j, grid)) {
                lowPoints.add(grid[i][j])
            }
        }
    }

    return lowPoints.sum() + lowPoints.size
}

fun partTwo(filename: String): Int {

    val br: BufferedReader = File(filename).bufferedReader()

    val rows: ArrayList<List<Char>> = arrayListOf()

    br.forEachLine {
        rows.add(it.toList())
    }

    val grid: Array<Array<Int>> = Array(rows.size) { Array(rows[0].size) { 0 } }
    populate(grid, rows)

    val visited: Array<Array<Boolean>> = Array(grid.size) { Array(grid[0].size) { false } }

    val basins: ArrayList<Int> = arrayListOf()

    for (i in 0 until rows.size) {
        for (j in 0 until rows[0].size) {
            if (isLowPoint(i, j, grid)) {
                basins.add(getBasinSize(i, j, grid, visited))
            }
        }
    }

    basins.sortDescending()
    return basins[0] * basins[1] * basins[2]

}

private fun getBasinSize(i: Int, j: Int, grid: Array<Array<Int>>, visited: Array<Array<Boolean>>):
        Int {
    var sum = grid[i][j]
    visited[i][j] = true
    if (sum == 9) return 0

    sum = 1

    if (i > 0 && !visited[i - 1][j]) {
        sum += getBasinSize(i - 1, j, grid, visited)
    }

    if (i < grid.size - 1 && !visited[i + 1][j]) {
        sum += getBasinSize(i + 1, j, grid, visited)
    }

    if (j > 0 && !visited[i][j - 1]) {
        sum += getBasinSize(i, j - 1, grid, visited)
    }

    if (j < grid[0].size - 1 && !visited[i][j + 1]) {
        sum += getBasinSize(i, j + 1, grid, visited)
    }

    return sum
}

private fun isLowPoint(i: Int, j: Int, grid: Array<Array<Int>>): Boolean {
    val num = grid[i][j]
    val surrounding: ArrayList<Int> = arrayListOf()

    if (i > 0) {
        surrounding.add(grid[i - 1][j])
    }

    if (i < grid.size - 1) {
        surrounding.add(grid[i + 1][j])
    }

    if (j > 0) {
        surrounding.add(grid[i][j - 1])
    }

    if (j < grid[0].size - 1) {
        surrounding.add(grid[i][j + 1])
    }

    return surrounding.all { it > num }
}

private fun populate(grid: Array<Array<Int>>, rows: ArrayList<List<Char>>) {
    for (i in 0 until rows.size) {
        for (j in 0 until rows[0].size) {
            grid[i][j] = rows[i][j].digitToInt()
        }
    }
}

fun main() {
    val filename = "src/main/kotlin/day9/input.txt"
    println(partOne(filename))
    println(partTwo(filename))
}