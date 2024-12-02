import kotlin.math.sign

fun main() {
    fun part1(input: List<String>): Int {
        var count = 0
        for (line in input) {
            val report = line.split(" ").map { it.toInt() }
            val m = (report[0] - report[1]).sign
            var p = report[0]
            count++
            for (l in report.subList(1, report.size)) {
                if (p - l != m * 1 && p - l != m * 2 && p - l != m * 3) {
                    count--
                    break
                }

                p = l
            }
        }
        return count
    }

    fun safe(report: List<Int>, bad: Boolean): Int {
        val m = (report[0] - report[1]).sign
        if (m == 0) return 0
        var p = report[0]
        for (l in 1..<report.size) {
            if (p - report[l] != m * 1 && p - report[l] != m * 2 && p - report[l] != m * 3 && bad) return 0
            else if (p - report[l] != m * 1 && p - report[l] != m * 2 && p - report[l] != m * 3) {
                return (safe(report.filterIndexed { index, _ -> index != l }, true) + safe(report.filterIndexed { index, _ -> index != l-1 }, true)).sign

            }
            else p = report[l]
        }
        return 1
    }

    fun part2(input: List<String>): Int {
        var count = 0
        for (line in input) {
            val report = line.split(" ").map { it.toInt() }
            count += (safe(report, false) + safe(report.filterIndexed { index, _ -> index != 0 }, true)).sign

        }
        return count
    }

    // Or read a large test input from the `src/Day02_test.txt` file:
    val testInput = readInput("Day02_test")
    part1(testInput).println()
    part2(testInput).println()

    // Read the input from the `src/Day02.txt` file.
    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
