package nsu.manasyan.lz

class LzDictionary {
    private var currentIndex = 1

    private val dictionary = mutableMapOf<Int, String>()

    private val reverseDictionary = mutableMapOf<String, Int>()

//    fun get(index: Int): String {
//
//    }

    fun put(string: String): Int? {
        if(reverseDictionary.containsKey(string)){
            return null
        }

//        dictionary[currentIndex] = string
        reverseDictionary[string] = currentIndex++
        val previousPosition = reverseDictionary[string.dropLast(1)]
        return previousPosition ?: 0
    }
}