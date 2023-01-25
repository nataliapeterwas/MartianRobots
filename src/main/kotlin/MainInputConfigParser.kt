class MainInputConfigParser {
    fun parse(input: String): Triple<String, String, String> {
        require(input.isNotEmpty()) { "You must pass grid size, robot position and robot moves" }

        val splitMainInput = input.split("\n").toMutableList()

        require(splitMainInput.size == 3) { "Incorrect input" }

        val gridSize = splitMainInput[0]
        val robotPosition = splitMainInput[1]
        val moves = splitMainInput[2]

        return Triple(gridSize, robotPosition, moves)
    }
}