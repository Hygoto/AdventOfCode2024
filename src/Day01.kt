import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        val l = mutableListOf<Int>()
        val r = mutableListOf<Int>()
        for (item in input) {
            val i = item.split("   ").map { it.toInt() }
            l.add(i[0])
            r.add(i[1])
        }
        val ls = l.sorted()
        val rs = r.sorted()
        var sum = 0
        for (i in 0..l.lastIndex) {
            sum += abs(ls[i] - rs[i])
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        val l = mutableListOf<Int>()
        val r = mutableListOf<Int>()
        for (item in input) {
            val i = item.split("   ").map { it.toInt() }
            l.add(i[0])
            r.add(i[1])
        }
        var sum = 0
        for (item in l) {
            sum += r.count { it == item } * item
        }
        return sum
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")
    part1(testInput).println()
    part2(testInput).println()

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
