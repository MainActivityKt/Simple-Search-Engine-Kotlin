package search
import java.io.File
import kotlin.system.exitProcess


/*
Description
Now your program can successfully search for all matching lines, and the search is case- and space-insensitive. There is one problem though: you need to check each line to find out whether it contains the query string.

To optimize your program, you can use a data structure called an Inverted Index. It maps each word to all positions/lines/documents in which the word occurs. As a result, when we receive a query, we can immediately find the answer without any comparisons.

Objectives
In this stage, build an inverted index at the start of the program and then use the index for searching operations. You can implement it using the Map class. It connects an item with a list (or set) of indexes belonging to the lines that contain the item.

Suppose you have the following list of lines:

0: Katie Jacobs
1: Erick Harrington harrington@gmail.com
2: Myrtle Medina
3: Erick Burgess
For these lines, the inverted index will look like this:

Katie -> [0]
Jacobs -> [0]
Erick -> [1, 3]
Harrington -> [1]
harrington@gmail.com -> [1]
Myrtle -> [2]
Medina -> [2]
Burgess -> [3]
The order of pairs is not important. If you are searching for Erick, you can immediately get the target fields using this mapping.

Note that the Inverted Index is not intended to search for parts of a word, it is only used to search for full words.
 */


class OptimizedSearchEngine(private val data: List<String>) {
    private lateinit var input: String
    private var menu = StringBuilder().apply {
        appendLine("=== ${Title.MENU.title} ===")
        appendLine("1. Find a person")
        appendLine("2. Print all people")
        append("0. Exit")
    }

    private val invertedIndexMap = mutableMapOf<String, MutableSet<Int>>()

    init {
        data.forEachIndexed { i, row ->
            row.split(" ").forEach { word ->
                if (invertedIndexMap.containsKey(word.lowercase())) {
                    invertedIndexMap[word.lowercase()]!!.add(i)
                } else {
                    invertedIndexMap[word.lowercase()] = mutableSetOf(i)
                }
            }

        }
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
            appendLine("=== ${Title.LIST_PEOPLE.title} ===")
            data.forEach { appendLine(it) }
        }
        println(result)
    }

    private fun performSearch() {
        println("Enter a name or email to search all matching people.")
        val keyword = readln()
        val results = mutableListOf<String>()

        if (invertedIndexMap.containsKey(keyword.lowercase())) {
            invertedIndexMap[keyword.lowercase()]!!.forEach {
                results.add(data[it])
            }
        }
        if (results.isEmpty()) {
            println("No matching people found.")
        } else {
            println("${results.size} person found:")
            results.forEach {
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
        val searchEngine = OptimizedSearchEngine(data)
        searchEngine.start()
    }
}