package day3

import java.io.BufferedReader
import java.io.File

fun partOne(filename: String): Int {
    val br: BufferedReader = File(filename).bufferedReader()

    val gsb: StringBuilder = StringBuilder()
    val esb: StringBuilder = StringBuilder()

    val nums: ArrayList<String> = arrayListOf()

    br.forEachLine { nums.add(it) }

    for (i in 0 until nums[0].length) {
        val numOnes: Int = nums.filter{ it[i] == '1' }.count()
        val numZeros: Int = nums.size - numOnes

        val max: Char = if (numOnes >= numZeros) '1' else '0'
        val min: Char = if (numOnes < numZeros) '1' else '0'

        gsb.append(max)
        esb.append(min)
    }

    val gamma: Int = gsb.toString().toInt(2)
    val epsilon: Int = esb.toString().toInt(2)

    return gamma * epsilon
}

fun partTwo(filename: String): Int {
    val br: BufferedReader = File(filename).bufferedReader()

    val oxygenList: ArrayList<String> = arrayListOf()
    val co2List: ArrayList<String> = arrayListOf()

    br.forEachLine {
        oxygenList.add(it)
        co2List.add(it)
    }

    var i: Int = 0
    while (oxygenList.size > 1) {
        val numOnes: Int = oxygenList.filter{ it[i] == '1'}.count()
        val numZeros: Int = oxygenList.size - numOnes

        val max: Char = if (numOnes >= numZeros) '1' else '0'
        oxygenList.removeIf { it[i] != max }

        i++
    }

    i = 0
    while (co2List.size > 1) {
        val numOnes: Int = co2List.filter{ it[i] == '1'}.count()
        val numZeros: Int = co2List.size - numOnes

        val min: Char = if (numOnes < numZeros) '1' else '0'
        co2List.removeIf { it[i] != min }

        i++
    }

    val oxygen: Int = oxygenList[0].toInt(2)
    val co2: Int = co2List[0].toInt(2)

    return oxygen * co2
}

fun main(args: Array<String>) {
    val filename: String = "day3/input.txt"
    println(partOne(filename))
    println(partTwo(filename))
}