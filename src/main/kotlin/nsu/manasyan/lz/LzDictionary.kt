package nsu.manasyan.lz

class LzDictionary {
    private var currentIndex = 1L

    private val dictionary = mutableMapOf<Long, String>()

    private val reverseDictionary = mutableMapOf<String, Long>()

    fun getIndex(string: String): Long? {
        return reverseDictionary[string]
    }

    fun put(string: String): Long? {
        if(reverseDictionary.containsKey(string)){
            return null
        }

        dictionary[currentIndex] = string
        reverseDictionary[string] = currentIndex++
        val previousPosition = reverseDictionary[string.dropLast(1)]
        return previousPosition ?: 0
    }
}