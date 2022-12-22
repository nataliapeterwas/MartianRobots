class MainInputParser(private val input: String){
    fun mainInput(): Triple<String, String, String> {
        require(input.isNotEmpty()) { "You must pass grid size, robot position and robot moves" }

        val splitMainInput = input.split("\n").toMutableList()

        require(splitMainInput.size == 3) { "Incorrect input" }

        val gridSize = splitMainInput[0]
        val robotPosition = splitMainInput[1]
        val moves = splitMainInput[2]

//        require(gridSize.split(" ").size == 2 && gridSize.toList()[1] == ' ') { "Incorrect grid size" }
//        require(robotPosition.split(" ").size == 3 && robotPosition.toList()[1] == ' ' && robotPosition.toList()[3] == ' ' && robotPosition.toList()[4].isLetter()) { "Incorrect robot position" }
//        require(moves.toList().all { it.isLetter() && it.isUpperCase() }) { "Incorrect moves" }

        return Triple(gridSize, robotPosition, moves)
    }
}