package nsu.manasyan.lz

import java.io.DataOutputStream
import java.io.File

/**
 * Класс кодировщика
 */
class Encoder {

    /**
     * Словарь для кодирования
     */
    private val dictionary = LzDictionary()

    /**
     * Соответсвует wk в алгоритме из лекций
     */
    private var currentBuf: String = ""

    /**
     * Функция кодирования файла - точка входа в кодировщика
     * @param inputFileName - имя файла, контент которого надо закодировать
     * @param outputFileName - имя выходного файла
     */
    fun encodeFile(inputFileName: String, outputFileName: String) {
        File(inputFileName).bufferedReader().use {
            DataOutputStream(File(outputFileName).outputStream()).use { outputDataStream ->
                it.lines().forEach {
                    // кодируем каждую строчку, строчки подгружаются из файла "лениво"
                    encodeBlock(it, outputDataStream)
                }
                flushRemainingBuf(outputDataStream)
            }
        }
    }

    /**
     * Закодировать блок (в данной реализиции строку)
     */
    private fun encodeBlock(block: String, outputDataStream: DataOutputStream) {
        for (char in block) {
            currentBuf += char
            // если в словаре не встречался currentBuf, то
            dictionary.put(currentBuf)?.let {
                // пишем индекс слова в словаре
                outputDataStream.writeInt(it)
                // пишем символ, соответсвующий k из алгоритма из лекций
                outputDataStream.writeChar(char.toInt())
                // очищаем буффер
                currentBuf = ""
            }
        }
    }

    /**
     * Если в конце остались данные в буфере, то пишем их в выходной файл
     */
    private fun flushRemainingBuf(outputDataStream: DataOutputStream) {
        if ("" == currentBuf) {
            return
        }

        outputDataStream.writeInt(dictionary.getIndex(currentBuf)!!)
    }
}