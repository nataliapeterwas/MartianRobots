import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

internal class CommandsTest{
    private val robot = Robot()
    private val grid = Grid()

    @Test
    fun `class MoveRightCommand correctly change direction from N to E`(){
        //given
        robot.robotDirection = Direction.N

        //when
        MoveRightCommand(robot).execute()

        //then
        robot.robotDirection shouldBeEqualTo Direction.E
    }

    @Test
    fun `class MoveLeftCommand correctly change direction from W to S`(){
        //given
        robot.robotDirection = Direction.W

        //when
        MoveLeftCommand(robot).execute()

        //then
        robot.robotDirection shouldBeEqualTo Direction.S
    }

    @Test
    fun `class MoveForwardCommand correctly change his position when his direction is S`(){
        //given
        grid.setGrid(10, 10)
        robot.robotDirection = Direction.S
        robot.setRobotPosition(1, 3, Direction.S, grid)

        //when
        MoveForwardCommand(robot, grid).execute()

        //then
        robot.robotPosition shouldBeEqualTo Position(1,2)
    }

    @Test
    fun `class MoveForwardCommand doing nothing when robot's position is polluted`(){
        //given
        grid.setGrid(10, 10)
        robot.robotDirection = Direction.S
        robot.setRobotPosition(0, 0, Direction.S, grid)
        grid.pollutedList.add(Position(0,-1))

        //when
        MoveForwardCommand(robot, grid).execute()

        //then
        robot.robotPosition shouldBeEqualTo Position(0,0)
    }

    @Test
    fun `class MoveForwardCommand makes that robot loses when he went outside grid`(){
        //given
        grid.setGrid(10, 10)
        robot.setRobotPosition(0, 0, Direction.S, grid)

        //when
        MoveForwardCommand(robot, grid).execute()

        //then
        robot.robotStatus shouldBeEqualTo Status.LOST
    }
}