package search
/*
Description
Now, let's make our search a little more complex. Let's write a program that performs multiple searches in multiple text lines.

Objectives
Write a program that reads text lines from the standard input and processes single-word queries. The program must output all lines that contain the string from the query. For this stage, this should include the case where the query string appears as a substring of one of the text lines. For example, the query "bc" should be found in a line containing "abcd".

You may choose what the text represents in your project. For example, each line may describe:

a person represented by the first name, the last name, and optionally an email;

an address of a building represented by the country, city, state, street, and zip code;

a book represented by its ISBN, title, author/authors, publisher, and so on.

You can use any of these options or come up with your own, because your search algorithm should work regardless of what the text actually represents.

Here is an example of an input line. It contains three items: the first name, the last name, and an email address.

Elsa Sanders elsa@gmail.com
In this example, all items are separated by spaces.

The search should be case-insensitive and ignore all extra spaces.

First, the user should input a number N, which is the number of data lines they are going to enter next.
 Then the user enters N lines with data. After that, the user enters a number M, which is the number of search queries.
  After each query, the program should print the information it managed to find among the data. You can see this
   searching process in the example below.
 */

class Stage2 {
    private val data = mutableListOf<String>()

    init {
        data.addAll(getInputs())
    }
    private fun performSearch(data: List<String>) {
        println("Enter the number of search queries:")
        val n = readln().toInt()
        println()

        repeat(n) {
            println("Enter data to search people:")
            val input = readln().lowercase()
            val foundData = mutableListOf<String>()

            data.forEach {
                if (it.contains(input, ignoreCase = true)) {
                    foundData.add(it)
                }
            }
            if (foundData.isNotEmpty()) {
                println()
                println("People found:")
                foundData.forEach { println(it) }
            } else {
                println("No matching people found.")
            }
            println()
        }
    }

    fun run() {
        performSearch(data)
    }
}

fun main() {
    Stage2().run()
}