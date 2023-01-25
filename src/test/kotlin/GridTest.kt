import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

internal class GridTest{
    private val sut = Grid()

    @Test
    fun `setGrid works correctly`() {
        //given

        //when
        sut.setSize(4, 6)

        //then
        sut.width shouldBeEqualTo 4
        sut.height shouldBeEqualTo 6
    }

    @Test
    fun `setGrid throws exception when width is bigger than 50`() {
        //given

        //when
        val actual = {sut.setSize(51, 6)}

        //then
        actual shouldThrow IllegalArgumentException::class withMessage "Grid is rectangle: 51>x>0 and 51>y>0"
    }

}