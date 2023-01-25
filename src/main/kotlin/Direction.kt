enum class Direction(val letter: String, val arrow: String) {
    N("N", "↑ "),
    S("S", "↓ "),
    E("E", "→ "),
    W("W", "← ")
}

//Direction.values().firstOrNull { it.letter == "String z config text" }