class Grid(
    var width: Int,
    var height: Int,
    val pollutedList: MutableList<Position> = mutableListOf()
) {
    fun hasPositionInPollutedList(x: Int, y: Int) = pollutedList.contains(Position(x, y))

    fun addPositionToPollutedList(x: Int, y: Int) {
        pollutedList.add(Position(x, y))
    }
}