package day5

import java.io.BufferedReader
import java.io.File
import kotlin.math.*

class Line(private val start: Point, private val end: Point) {

    fun addToGrid(grid: Array<Array<Int>>) {
        if (start.x == end.x || start.y == end.y) {
            for (i in min(start.y, end.y) until max(start.y, end.y) + 1) {
                for (j in min(start.x, end.x) until max(start.x, end.x) + 1) {
                    grid[i][j]++
                }
            }
        } else {
            val dx = if (start.x < end.x) 1 else -1
            val dy = if (start.y < end.y) 1 else -1

            for (i in 0 until abs(start.y - end.y) + 1) {
                grid[start.y + (dy * i)][start.x + (dx * i)]++
            }
        }
    }
}

class Point(val x : Int, val y: Int)

fun partOne(filename: String): Int {

    val br: BufferedReader = File(filename).bufferedReader()

    val lines: ArrayList<Line> = arrayListOf()

    br.forEachLine {
        val points: List<String> = it.split(" -> ")
        val p1Coords: List<String> = points[0].split(",")
        val p2Coords: List<String> = points[1].split(",")

        val p1 = Point(Integer.parseInt(p1Coords[0]), Integer.parseInt(p1Coords[1]))
        val p2 = Point(Integer.parseInt(p2Coords[0]), Integer.parseInt(p2Coords[1]))

        if (p1.x == p2.x || p1.y == p2.y) {
            lines.add(Line(p1, p2))
        }
    }

    val grid: Array<Array<Int>> = Array(1000) { Array(1000) { 0 } }

    lines.forEach { it.addToGrid(grid) }

    return grid.sumOf { row -> row.count { it > 1 } }
}

fun partTwo(filename: String): Int {

    val br: BufferedReader = File(filename).bufferedReader()

    val lines: ArrayList<Line> = arrayListOf()

    br.forEachLine {
        val points: List<String> = it.split(" -> ")
        val p1Coords: List<String> = points[0].split(",")
        val p2Coords: List<String> = points[1].split(",")

        val p1 = Point(Integer.parseInt(p1Coords[0]), Integer.parseInt(p1Coords[1]))
        val p2 = Point(Integer.parseInt(p2Coords[0]), Integer.parseInt(p2Coords[1]))

        if (p1.x == p2.x || p1.y == p2.y || abs(p1.x - p2.x) == abs(p1.y - p2.y)) {
            lines.add(Line(p1, p2))
        }
    }

    val grid: Array<Array<Int>> = Array(1000) { Array(1000) { 0 } }

    lines.forEach { it.addToGrid(grid) }

    return grid.sumOf { row -> row.count { it > 1 } }
}

fun main() {
    val filename = "day5/input.txt"
    println(partOne(filename))
    println(partTwo(filename))
}
