package nsu.manasyan.lz

class Encoder{
    // словарь (ключ - позиция, значение - символ входного алфавита)

    private val dictionary = LzDictionary()

    var output: String = ""

    fun encodeBlock(block: String){
        var currentDictionaryPosition = 0
        var currentBuf: String = ""
        for(char in block){
            currentBuf += char
            dictionary.put(currentBuf)?.let{
                output += "($it;$char)"
                currentBuf = ""
            }
        }
    }

//    fun encodeFile()
}

