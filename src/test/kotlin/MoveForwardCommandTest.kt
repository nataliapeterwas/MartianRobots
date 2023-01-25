import io.mockk.*
import org.junit.jupiter.api.Test

internal class MoveForwardCommandTest {
    private val robot = mockk<Robot>()
    private val grid = mockk<Grid>()
    private val sut = MoveForwardCommand(robot, grid)

    @Test
    fun `class MoveForwardCommand correctly change his position when his direction is S`() {
        // given
        every { grid.height } returns 10
        every { grid.width } returns 10
        every { robot.robotDirection } returns Direction.S
        every { robot.robotPosition } returns Position(1, 3)
        justRun { robot.updatePosition(1, 2)  }

        // when
        sut.execute()

        // then
        verify { robot.updatePosition(1, 2) }

    }

    @Test
    fun `class MoveForwardCommand doing nothing when robot's position is polluted`() {
        // given
        every { grid.height } returns 10
        every { grid.width } returns 10
        every { robot.robotDirection } returns Direction.S
        every { grid.hasPositionInPollutedList(0, -1) } returns true
        every { robot.robotPosition } returns Position(0, 0)

        // when
        sut.execute()

        // then
        verify(exactly = 0) { robot.updatePosition(any(), any()) }
    }

    @Test
    fun `class MoveForwardCommand makes that robot loses when he went outside grid`() {
        // given
        every { grid.height } returns 10
        every { grid.width } returns 10
        every { grid.pollutedList } returns mutableListOf()
        every { robot.robotDirection } returns Direction.S
        every { robot.robotPosition } returns Position(0, 0)
        every { robot.robotStatus } returns RobotStatus.ALIVE
        justRun { robot.setRobotPosition(any(), any()) }
        every { grid.hasPositionInPollutedList(0, -1) } returns false
        justRun { grid.addPositionToPollutedList(0, -1) }
        justRun { robot.updateRobotStatus(RobotStatus.LOST) }

        // when
        sut.execute()

        // then
        verifyOrder {
            grid.addPositionToPollutedList(0, -1)
            robot.updateRobotStatus(RobotStatus.LOST)
        }
    }
}