package search

fun main() {
    /*
    When run, takes a list of space-separated strings from terminal as input in the first line, and
    a string as the next input in the second line.
    Prints out the position of given word in the list of strings if it is present
     */
    val listOfWords = readln().split(" ")
    val word = readln()
    listOfWords.indexOf(word).apply {
        println(if (this == -1) "Not found" else this + 1)
    }
}