package src

import search.Stage1
import search.Stage2

const val MSG = "Please input the number of stage, 1 to 5:"
const val WRONG_INPUT = "Wrong input, please enter a number between 1 to 5:"

fun getStage(): Int {
    println(MSG)
    var input = -1
    while (input < 1) {
       try {
           readln().toInt().apply {
               require(this in 1..5)
               input = this
           }
       } catch (e: Exception) {
           println(WRONG_INPUT)
       }
    }
    return input
}

fun runStage(stageNumber: Int) {
    println("Running Stage$stageNumber.kt")
    when(stageNumber) {
        1 -> Stage1().run()
        2 -> Stage2().run()
    }
}
fun main(args: Array<String>) {
    val num = getStage()
    runStage(num)
}