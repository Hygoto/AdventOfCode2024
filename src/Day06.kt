fun main() {
    fun part1(input: List<String>): Int {
        val d = mapOf('^' to 0, '>' to 1, 'v' to 2, '<' to 3)
        val map = mutableListOf<MutableList<Char>>()
        val guard = MutableList(3) {0}
        for (line in input) {
            map.add(line.toMutableList())
            val g = map.last().indexOfFirst { d.containsKey(it) }
            if (g != -1) {
                guard[0] = map.lastIndex
                guard[1] = g
                guard[2] = d[map.last()[g]]!!
            }
        }
        while (guard[0] >= 0 && guard[1] >= 0 && guard[0] < map.size && guard[1] < map[0].size) {
            map[guard[0]][guard[1]] = 'x'
            when (guard[2]) {
                0 -> {
                    if (map.getOrElse(guard[0]-1) {mutableListOf(' ')}.getOrElse(guard[1]) {' '} == '#') guard[2] = (guard[2] + 1) % 4
                    else guard[0] -= 1
                }
                1 -> {
                    if (map.getOrElse(guard[0]) {mutableListOf(' ')}.getOrElse(guard[1]+1) {' '} == '#') guard[2] = (guard[2] + 1) % 4
                    else guard[1] += 1
                }
                2 -> {
                    if (map.getOrElse(guard[0]+1) {mutableListOf(' ')}.getOrElse(guard[1]) {' '} == '#') guard[2] = (guard[2] + 1) % 4
                    else guard[0] += 1
                }
                3 -> {
                    if (map.getOrElse(guard[0]) {mutableListOf(' ')}.getOrElse(guard[1]-1) {' '} == '#') guard[2] = (guard[2] + 1) % 4
                    else guard[1] -= 1
                }
            }
        }
        return map.sumOf { it.count { it == 'x' } }
    }

    fun move(guard: MutableList<Int>, map: MutableList<MutableList<String>>, startPos: Pair<Int, Int>, o: Boolean): Int {
        var count = 0
        while (guard[0] >= 0 && guard[1] >= 0 && guard[0] < map.size && guard[1] < map[0].size) {
            map[guard[0]][guard[1]] += guard[2].toString()
            when (guard[2]) {
                0 -> {
                    if (map.getOrElse(guard[0]-1) {mutableListOf("")}.getOrElse(guard[1]) {""} == "#") guard[2] = (guard[2] + 1) % 4
                    else if (o && guard[0]-1 to guard[1] != startPos && guard[0]-1 >= 0 && map[guard[0]-1][guard[1]].length == 1) {
                        val newMap = map.map { it.toMutableList() }.toMutableList()
                        newMap[guard[0]-1][guard[1]] = "#"
                        count += move(mutableListOf(guard[0], guard[1], (guard[2]+1)%4), newMap, startPos, false)
                        guard[0] -= 1
                    }
                    else guard[0] -= 1
                }
                1 -> {
                    if (map.getOrElse(guard[0]) {mutableListOf("")}.getOrElse(guard[1]+1) {""} == "#") guard[2] = (guard[2] + 1) % 4
                    else if (o && guard[0] to guard[1]+1 != startPos && guard[1]+1 < map[0].size && map[guard[0]][guard[1]+1].length == 1) {
                        val newMap = map.map { it.toMutableList() }.toMutableList()
                        newMap[guard[0]][guard[1]+1] = "#"
                        count += move(mutableListOf(guard[0], guard[1], (guard[2]+1)%4), newMap, startPos, false)
                        guard[1] += 1
                    }
                    else guard[1] += 1
                }
                2 -> {
                    if (map.getOrElse(guard[0]+1) {mutableListOf("")}.getOrElse(guard[1]) {""} == "#") guard[2] = (guard[2] + 1) % 4
                    else if (o && guard[0]+1 to guard[1] != startPos && guard[0]+1 < map.size && map[guard[0]+1][guard[1]].length == 1) {
                        val newMap = map.map { it.toMutableList() }.toMutableList()
                        newMap[guard[0]+1][guard[1]] = "#"
                        count += move(mutableListOf(guard[0], guard[1], (guard[2]+1)%4), newMap, startPos, false)
                        guard[0] += 1
                    }
                    else guard[0] += 1
                }
                3 -> {
                    if (map.getOrElse(guard[0]) {mutableListOf("")}.getOrElse(guard[1]-1) {""} == "#") guard[2] = (guard[2] + 1) % 4
                    else if (o && guard[0] to guard[1]-1 != startPos && guard[1]-1 >= 0 && map[guard[0]][guard[1]-1].length == 1) {
                        val newMap = map.map { it.toMutableList() }.toMutableList()
                        newMap[guard[0]][guard[1]-1] = "#"
                        count += move(mutableListOf(guard[0], guard[1], (guard[2]+1)%4), newMap, startPos, false)
                        guard[1] -= 1
                    }
                    else guard[1] -= 1
                }
            }
            if (map.getOrElse(guard[0]) {mutableListOf("")}.getOrElse(guard[1]) {""}.contains(guard[2].toString())) return 1
        }
        return count
    }

    fun part2(input: List<String>): Int {
        val d = mapOf("^" to 0, ">" to 1, "v" to 2, "<" to 3)
        val map = mutableListOf<MutableList<String>>()
        val guard = MutableList(3) {0}
        for (line in input) {
            map.add(line.toMutableList().map { it.toString() }.toMutableList())
            val g = map.last().indexOfFirst { d.containsKey(it) }
            if (g != -1) {
                guard[0] = map.lastIndex
                guard[1] = g
                guard[2] = d[map.last()[g]]!!
            }
        }
        val startPos = guard[0] to guard[1]
        val count = move(guard.toMutableList(), map.map { it.toMutableList() }.toMutableList(), startPos, true)
        return count
    }

    // Or read a large test input from the `src/Day06_test.txt` file:
    val testInput = readInput("Day06_test")
    part1(testInput).println()
    part2(testInput).println()

    // Read the input from the `src/Day06.txt` file.
    val input = readInput("Day06")
    part1(input).println()
    part2(input).println()
}
