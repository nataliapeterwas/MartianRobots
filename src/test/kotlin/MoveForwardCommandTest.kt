import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.amshove.kluent.should
import org.amshove.kluent.`should be`
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

internal class MoveForwardCommandTest{
    private val robot= mockk<Robot>()
    private val grid = mockk<Grid>()
    private val sut = MoveForwardCommand(robot, grid)

    @Test
    fun `class MoveForwardCommand correctly change his position when his direction is S`(){
        // given
        every { grid.height } returns 10
        every { grid.width } returns 10
        every { robot.robotDirection } returns Direction.S
        every { robot.robotPosition } returns Position(1,3)
        justRun { robot.robotPosition = Position(1, 2) }

        // when
        sut.execute()

        // then
        verify { robot.robotPosition = Position(1,2) }
    }

    @Test
    fun `class MoveForwardCommand doing nothing when robot's position is polluted`(){
        // given
        every { grid.height } returns 10
        every { grid.width } returns 10
        every { robot.robotDirection } returns Direction.S
        every { robot.robotPosition } returns Position(0,0)
        every { grid.pollutedList.contains(Position(0, -1)) } returns true

        // when
        sut.execute()

        // then
        verify (exactly = 0){ robot.robotPosition = any()}
    }

    @Test
    fun `class MoveForwardCommand makes that robot loses when he went outside grid`(){
        // given
        every { grid.height } returns 10
        every { grid.width } returns 10
        every { grid.pollutedList } returns mutableListOf()
        every { robot.robotDirection } returns Direction.S
        every { robot.robotPosition } returns Position(0,0)
        every { robot.robotStatus } returns Status.ALIVE
        justRun { robot.setRobotPosition(any(), any()) }
        justRun { robot.robotStatus = Status.LOST }

        // when
        sut.execute()

        // then
        verify { robot.robotStatus = Status.LOST }
    }
}