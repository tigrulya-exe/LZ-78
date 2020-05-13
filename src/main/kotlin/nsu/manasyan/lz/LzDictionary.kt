package nsu.manasyan.lz
class LzDictionary {
    /**
     * Первый незанятый номер в словаре (текущий индекс)
     */
    private var currentIndex = 1

    /**
     * Словарь для кодирования
     * Ключ - слово, значение - его позиция в словаре
     */
     private val reverseDictionary = mutableMapOf<String, Int>()

    /**
     * Получить номер слова в словаре по слову
     */
    fun getIndex(string: String): Int?{
        return reverseDictionary[string]
    }

    /**
     * Положить слово в словарь. Если такое слово уже присутствовало в словаре, то вернуть null
     */
    fun put(string: String): Int? {
        if(reverseDictionary.containsKey(string)){
            return null
        }

        reverseDictionary[string] = currentIndex++
        val previousPosition = reverseDictionary[string.dropLast(1)]
        return previousPosition ?: 0
    }
}

