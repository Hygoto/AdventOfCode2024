fun main() {
    fun part1(input: List<String>): Int {
        val regex = "(?=(XMAS)|(SAMX))".toRegex()
        var count = 0
        var c = List(input[0].length) { "" }
        for (line in input) {
            count += regex.findAll(line).count()
            c = c.mapIndexed { index, value -> value + line[index]}
        }
        for (col in c) {
            count += regex.findAll(col).count()
        }
        for (i in 0..<input.size+input[0].length-1) {
            var d1 = ""
            var d2 = ""
            for (ii in input.indices) {
                if (i < input.size) {
                    d1 += input.getOrElse(i+ii) {" "}.getOrElse(ii) {' '}
                    d2 += input.getOrElse(input.size-1-i-ii) {" "}.getOrElse(ii) {' '}
                }
                else {
                    d1 += input.getOrElse(ii) {" "}.getOrElse(ii+i-input.size+1) {' '}
                    d2 += input.getOrElse(input.size-1-ii) {" "}.getOrElse(i+ii-input.size+1) {' '}
                }

            }
            count += regex.findAll(d1).count() + regex.findAll(d2).count()
        }
        return count
    }

    fun part2(input: List<String>): Int {
        var count = 0
        for (i in 1..input.size-2) {
            for (j in 1..input[0].length-2) {
                if (input[i][j] == 'A' && ((input[i-1][j-1] == 'M' && input[i+1][j+1] == 'S') || (input[i-1][j-1] == 'S' && input[i+1][j+1] == 'M')) && ((input[i-1][j+1] == 'M' && input[i+1][j-1] == 'S') || (input[i-1][j+1] == 'S' && input[i+1][j-1] == 'M'))) count++
            }
        }
        return count
    }

    // Or read a large test input from the `src/Day04_test.txt` file:
    val testInput1 = readInput("Day04_testp1")
    part1(testInput1).println()
    val testInput2 = readInput("Day04_testp2")
    part2(testInput2).println()

    // Read the input from the `src/Day04.txt` file.
    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}
