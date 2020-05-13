package nsu.manasyan.lz

import java.io.DataOutputStream
import java.io.File

class Encoder {
    // словарь (ключ - позиция, значение - символ входного алфавита)

    private val dictionary = LzDictionary()

    private var currentBuf: String = ""

    fun encodeBlock(block: String, outputDataStream: DataOutputStream) {
        for (char in block) {
            currentBuf += char
            dictionary.put(currentBuf)?.let {
                outputDataStream.writeInt(it)
                outputDataStream.writeChar(char.toInt())
                currentBuf = ""
            }
        }
    }

    fun encodeFile(inputFileName: String, outputFileName: String) {
        Encoder::class.java.getResourceAsStream(inputFileName).bufferedReader().use { reader ->
            DataOutputStream(File(outputFileName).outputStream()).use { outputDataStream ->
                reader.lines().forEach {
                    encodeBlock(it + "\n", outputDataStream)
                }
                flushRemainingBuf(outputDataStream)
            }
        }
    }

    private fun flushRemainingBuf(outputDataStream: DataOutputStream) {
        if ("" == currentBuf) {
            return
        }

        outputDataStream.writeInt(0)
        outputDataStream.writeChar(currentBuf.last().toInt())
    }
}

