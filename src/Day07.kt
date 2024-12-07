fun main() {
    fun isPossible1(equation: List<Long>): Boolean {
        if (equation[0] < 0) return false
        var t = equation[0]
        for (i in 0..<equation.lastIndex-1) {
            if (t % equation[equation.lastIndex-i] != 0.toLong()) t -= equation[equation.lastIndex-i]
            else {
                return isPossible1(equation.subList(0, equation.lastIndex-i).mapIndexed { index, l -> if (index == 0) t/equation[equation.lastIndex-i]; else l }) ||
                        isPossible1(equation.subList(0, equation.lastIndex-i).mapIndexed { index, l -> if (index == 0) t-equation[equation.lastIndex-i]; else l })

            }
        }
        return t == equation[1]

    }

    fun part1(input: List<String>): Long {
        var sum: Long = 0
        for (line in input) {
            val equation = "\\d+".toRegex().findAll(line).map { it.value.toLong() }.toList()
            if (isPossible1(equation)) sum += equation[0]
        }
        return sum
    }

    fun isPossible2(equation: List<Long>): Boolean {
        if (equation[0] == equation[1] && equation.size == 2) return true
        if (equation[0] < equation[1] || equation.size == 2) return false
        val e0 = equation.toMutableList()
        e0[1] += e0[2]
        e0.removeAt(2)
        val e1 = equation.toMutableList()
        e1[1] *= e1[2]
        e1.removeAt(2)
        val e2 = equation.toMutableList()
        e2[1] = (e2[1].toString() + e2[2].toString()).toLong()
        e2.removeAt(2)

        return isPossible2(e0) || isPossible2(e1) || isPossible2(e2)

    }

    fun part2(input: List<String>): Long {
        var sum: Long = 0
        for (line in input) {
            val equation = "\\d+".toRegex().findAll(line).map { it.value.toLong() }.toList()
            if (isPossible2(equation)) sum += equation[0]
        }
        return sum
    }

    // Or read a large test input from the `src/Day07_test.txt` file:
    val testInput = readInput("Day07_test")
    part1(testInput).println()
    part2(testInput).println()

    // Read the input from the `src/Day07.txt` file.
    val input = readInput("Day07")
    part1(input).println()
    part2(input).println()
}
