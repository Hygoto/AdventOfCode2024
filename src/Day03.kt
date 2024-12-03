fun main() {
    fun part1(input: List<String>): Int {
        var result = 0
        for (line in input) {

            result += "mul\\(\\d+,\\d+\\)".toRegex().findAll(line).fold(0) { acc, matched ->
                acc + "\\d+".toRegex().findAll(matched.value).map { it.value.toInt() }.fold(1) { bcc, matched2 -> bcc * matched2 }
            }
        }
        return result
    }

    fun part2(input: List<String>): Int {
        var result = 0
        var doo = true
        for (line in input) {
            result += "(mul\\(\\d+,\\d+\\))|(do\\(\\))|(don't\\(\\))".toRegex().findAll(line).fold(0) { acc, matched ->
                var t = 0
                if (matched.value == "do()") doo = true
                else if (matched.value == "don't()") doo = false
                else if (doo) t += "\\d+".toRegex().findAll(matched.value).map { it.value.toInt() }.fold(1) { bcc, matched2 -> bcc * matched2 }
                acc + t
            }
        }
        return result
    }

    // Or read a large test input from the `src/Day03_test.txt` file:
    val testInput = readInput("Day03_testp1")
    part1(testInput).println()
    val testInput2 = readInput("Day03_testp2")
    part2(testInput2).println()

    // Read the input from the `src/Day03.txt` file.
    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
