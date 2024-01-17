package search
import java.io.File
import kotlin.system.exitProcess
 /*
 Description
Now let's Improve your search engine to make it support complex queries containing word sequences and use several strategies that determine how to match data.

Objectives
In this stage, your program should be able to use such searching strategies as ALL, ANY, and NONE.

Take, for example, these six sample lines:

Dwight Joseph djo@gmail.com
Rene Webb webb@gmail.com
Katie Jacobs
Erick Harrington harrington@gmail.com
Myrtle Medina
Erick Burgess
If the strategy is ALL, the program should print lines containing all the words from the query.
Query:

Harrington Erick
Result:

Erick Harrington harrington@gmail.com
If the strategy is ANY, the program should print the lines containing at least one word from the query.
Query:

Erick Dwight webb@gmail.com
Result:

Erick Harrington harrington@gmail.com
Erick Burgess
Dwight Joseph djo@gmail.com
Rene Webb webb@gmail.com
If the strategy is NONE, the program should print the lines that do not contain any words from the query at all.
Query:

djo@gmail.com ERICK
Result:

Katie Jacobs
Myrtle Medina
Rene Webb webb@gmail.com
All the listed operations should be implemented with an inverted index, and the results should not contain any duplicates.

Do not forget to use methods to decompose your program.
  */
enum class Title(val title: String) {
    MENU("Menu"),
    LIST_PEOPLE("List of people")
}

enum class Strategy {
    ALL, ANY, NONE
}

class StrategizedSearchEngineWithMenu(private val data: List<String>) {
    private lateinit var input: String
    private var menu = StringBuilder().apply {
        appendLine("=== ${Title.MENU.title} ===")
        appendLine("1. Find a person")
        appendLine("2. Print all people")
        append("0. Exit")
    }

    private val mapOfData = mutableMapOf<String, MutableSet<Int>>()
    init {
        data.forEachIndexed { i, row ->
            row.split(" ").forEach { word ->
                if (mapOfData.containsKey(word.lowercase())) {
                    mapOfData[word.lowercase()]!!.add(i)
                } else {
                    mapOfData[word.lowercase()] = mutableSetOf(i)
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
        println("Select a matching strategy: ALL, ANY, NONE")
        val strategy = when(readln()) {
            "ALL" -> Strategy.ALL
            "ANY" -> Strategy.ANY
            else -> Strategy.NONE
        }
        println()
        println("Enter a name or email to search all matching people.")
        val keyword = readln()
        val results = getResults(strategy, keyword)
        println()
        if (results.isEmpty()) {
            println("No matching people found.")
        } else {
            println("${results.size} persons found:")
            results.forEach {
                println(it)
            }
        }
        println()
    }

    private fun getResults(strategy: Strategy, keyword: String): Set<String> {
        val results = mutableSetOf<String>()

        val words = keyword.split(" ") .map { it.lowercase() } //  [Harrington, Erick]
        keyword.split(" ").forEach { word ->
            if (strategy != Strategy.NONE) {
                if (mapOfData.containsKey(word)) {
                    mapOfData[word]!!.forEach { i ->
                        if (strategy == Strategy.ALL && data[i].split(" ").all { it.lowercase() in words }) {
                            results.add(data[i])
                        } else {
                            results.add(data[i])
                        }
                    }
                }
            } else {
                mapOfData.keys.filterNot { it == word }.forEach { key ->
                    mapOfData[key]!!.forEach { i ->
                        if (data[i].split(" ").none { it.lowercase() in words }) {
                            results.add(data[i])
                        }
                    }
                }
            }
        }
        return results
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
        val searchEngine = StrategizedSearchEngineWithMenu(data)
        searchEngine.start()
    }
}