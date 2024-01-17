package search


fun getInputs(): MutableList<String> {
    println("Enter the number of people:")
    val data = mutableListOf<String>()
    val size = readln().toInt()
    println("Enter all people:")
    repeat(size) {
        data.add(readln())
    }
    println()
    return data
}

enum class Title(val title: String) {
    MENU("Menu"),
    LIST_PEOPLE("List of people")

}

enum class Strategy {
    ALL, ANY, NONE
}