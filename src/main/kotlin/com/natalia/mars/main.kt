package com.natalia.mars

import com.natalia.mars.di.module
import org.koin.core.context.startKoin

fun main() {
    startKoin {
        modules(module)
    }
    val mainApp = MainApp()

//
//    val s2 = """
//        5 3
//        3 2 N
//        FRRFLLFFRRFLL
//    """.trimIndent()
//
//    val s4 = """
//        9 9
//        3 3 N
//        FFF
//    """.trimIndent()
//
//    val s3 = """
//        5 3
//        0 3 W
//        LLFFFLFLFL
//    """.trimIndent()
//
//    val x = """
//             5 3
//             0 0 S
//             FFFFFF
//        """.trimIndent()
}
