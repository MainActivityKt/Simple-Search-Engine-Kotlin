package search
import java.io.File
import kotlin.system.exitProcess

/*
Description
Now, let's further modify the program and teach it to read the input data from a file.

Objectives
In this stage, your program should read data from a text file instead of the standard input. The file structure will depend on your text data type (personal information, address information, book information, and so on). See an example below:

Dwight Joseph djo@gmail.com
Rene Webb webb@gmail.com
Katie Jacobs
Erick Harrington harrington@gmail.com
Myrtle Medina
Erick Burgess
The program must process the command line argument --data followed by the name of the file with the data, for example, --data text.txt.

Note that the file should not include the total number of lines. All lines must be read only once, at the start of your program.
 */


class SearchEngineWithFile(private val fileData: List<String>) {
    private lateinit var input: String
    private var menu = StringBuilder().apply {
        appendLine("=== ${Titles.MENU.title} ===")
        appendLine("1. Find a person")
        appendLine("2. Print all people")
        append("0. Exit")
    }

    private fun printMenu() {
        println(menu)
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
            appendLine("=== ${Titles.LIST_PEOPLE.title} ===")
            fileData.forEach { appendLine(it) }
        }
        println(result)
    }

    private fun performSearch() {
        println("Enter a name or email to search all suitable people.")
        val keyword = readln()
        fileData.forEach {
            if (it.contains(keyword, ignoreCase = true)) {
                println(it)
            }
        }
        println()
    }

    fun start() {
        printMenu()
        getNewInput()
        while (true) {
            processInput()
            printMenu()
            getNewInput()
        }
    }
}


fun main(args: Array<String>) {
    if (args.contains("--data")) {
        val data = File(args.last()).readLines()
        val searchEngine = SearchEngineWithFile(data)
        searchEngine.start()
    }
}