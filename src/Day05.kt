fun main() {
    fun part1(input: List<String>): Int {
        val rules = mutableMapOf<Int, MutableSet<Int>>()
        var i = 0
        while (input[i].isNotEmpty()) {
            val r = input[i].split("|").map(String::toInt)
            if (rules.containsKey(r[1])) rules[r[1]]!!.add(r[0])
            else rules[r[1]] = mutableSetOf(r[0])
            i++
        }
        val updates = input.subList(i + 1, input.size).map { it.split(",").map(String::toInt) }
        var sum = 0
        var a = false
        for (update in updates) {
            val p = mutableListOf<Int>()
            for (page in update) {
                if (rules.containsKey(page) && update.filter { it !in p }.any { it in rules[page]!! }) {
                    a = true
                    break
                }
                p.add(page)
            }
            if (!a) sum += update[update.size/2]
            a = false
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        val rules = mutableMapOf<Int, MutableSet<Int>>()
        var i = 0
        while (input[i].isNotEmpty()) {
            val r = input[i].split("|").map(String::toInt)
            if (rules.containsKey(r[1])) rules[r[1]]!!.add(r[0])
            else rules[r[1]] = mutableSetOf(r[0])
            i++
        }
        val updates = input.subList(i + 1, input.size).map { it.split(",").map(String::toInt) }
        val incorrect = mutableListOf<MutableList<Int>>()
        for (update in updates) {
            val p = mutableListOf<Int>()
            for (page in update) {
                if (rules.containsKey(page) && update.filter { it !in p }.any { it in rules[page]!! }) {
                    incorrect.add(update.toMutableList())
                    break
                }
                p.add(page)
            }
        }
        var sum = 0
        for (update in incorrect) {
            val u = update.toMutableList()
            for (i in u.indices) {
                var a = 0
                while (rules.containsKey(u[i-a]) && u.subList(0, i-a).any { it in rules[u[i-a]]!! }) {
                    val t = u[i-a]
                    u.removeAt(i-a)
                    u.add(i-a-1, t)
                    a++
                }

            }
            sum += u[u.size/2]
        }
        return sum
    }

    // Or read a large test input from the `src/Day05_test.txt` file:
    val testInput = readInput("Day05_test")
    part1(testInput).println()
    part2(testInput).println()

    // Read the input from the `src/Day05.txt` file.
    val input = readInput("Day05")
    part1(input).println()
    part2(input).println()
}
