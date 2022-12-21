enum class Direction(letter: String) {
    N("N"),
    S("S"),
    E("E"),
    W("W");

    fun printArrow(direction: Direction): String{
        return when (direction) {
            N -> "↑ "
            S -> "↓ "
            E -> "→ "
            W -> "← "
        }
    }
}