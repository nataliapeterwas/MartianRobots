import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test
import parser.GridConfigParser

internal class GridConfigConfigParserTest{
    @Test
    fun `gridSizeParser throws exception when we pass incorrect gridSize (it length is 2)`() {
        // given
        val input = "66"

        // when
        val actual = { GridConfigParser().parse(input)}

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "Incorrect grid size"
    }

    @Test
    fun `gridSizeParser throws exception when we pass incorrect gridSize (it has blank space at beginning)`() {
        // given
        val input = " 66"

        // when
        val actual = { GridConfigParser().parse(input)}

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "Incorrect grid size"
    }

    @Test
    fun `GridSizeParser transforms string '30 5' to grid width and grid height`() {
        // given
        val input = "30 5"

        // when
        val actual = GridConfigParser().parse(input)

        // then
        actual.first shouldBeEqualTo 30
        actual.second shouldBeEqualTo 5
    }

    @Test
    fun `GridSizeParser throws exception when width is 60 (it is too big)`() {
        // given
        val input = "60 5"

        // when
        val actual = { GridConfigParser().parse(input)}

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "Grid is rectangle: 51>x>0 and 51>y>0"
    }
}