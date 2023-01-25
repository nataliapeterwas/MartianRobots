import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

internal class MoveRightCommandTest {
    private val robot = mockk<Robot>()
    private val sut = MoveRightCommand

    @Test
    fun `class MoveRightCommand correctly change direction from N to E`() {
        // given
        every { robot.robotDirection } returns Direction.N
        justRun { robot.robotDirection = any()}

        // when
        sut.execute(robot)

        // then
        verify { robot.robotDirection = Direction.E }
    }
}