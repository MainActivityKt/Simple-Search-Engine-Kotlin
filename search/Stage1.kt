package search

class Stage1 {
    private var listOfWords = emptyList<String>()
    fun run() {
        listOfWords = getWords()
        val word = readln()
        searchWord(word)
    }

    private fun getWords(): List<String> {
        return readln().split(" ")
    }

    private fun searchWord(keyword: String) {
        listOfWords.indexOf(keyword).apply {
            println(if (this == -1) "Not found" else this + 1)
        }
    }
}
fun main() {
    /*
    When run, takes a list of space-separated strings from terminal as input in the first line, and
    a string as the next input in the second line.
    Prints out the position of given word in the list of strings if it is present
     */
    Stage1().run()
}