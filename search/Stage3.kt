package search

import kotlin.system.exitProcess


class SearchEngineWithMenu() {
    private lateinit var input: String
    private var data: List<String> = getInputs()
    private var menu = StringBuilder().apply {
        appendLine("=== ${Title.MENU.title} ===")
        appendLine("1. Find a person")
        appendLine("2. Print all people")
        append("0. Exit")
    }

    fun run() {
        printMenu()
        getNewInput()
        while (true) {
            processInput()
            printMenu()
            getNewInput()
        }
    }

    private fun getNewInput() {
        input = readln()
    }

    private fun processInput() {
        val option = input.toIntOrNull()
        println()
        if (option != null && option in 0..2) {
            when (option) {
                1 -> performSearch()
                2 -> printAllData()
                0 -> {
                    println("Bye!")
                    exitProcess(0)
                }
            }

        } else {
            println("Incorrect option! Try again.")
        }
    }

    private fun printAllData() {
        val result = StringBuilder().apply {
            appendLine("=== ${Title.LIST_PEOPLE.title} ===")
            data.forEach { appendLine(it) }
        }
        println(result)
    }

    private fun performSearch() {
        println("Enter a name or email to search all suitable people.")
        val keyword = readln()
        data.forEach {
            if (it.contains(keyword, ignoreCase = true)) {
                println(it)
            }
        }
        println()
    }

    private fun printMenu() {
        println(menu)
    }
}



fun main() {
    val searchEngine = SearchEngineWithMenu()
    searchEngine.run()
}