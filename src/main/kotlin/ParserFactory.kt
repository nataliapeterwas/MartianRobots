class ParserFactory {
    fun create(input: String, grid: Grid, robot: Robot) = Parser(input, grid, robot)
}