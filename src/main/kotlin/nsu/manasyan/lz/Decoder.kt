package nsu.manasyan.lz

import java.io.BufferedWriter
import java.io.DataInputStream
import java.io.EOFException
import java.io.File

class Decoder {
    /**
     * В данном случае вместо словаря нам достаточно только списка
     */
    private val dictionary = mutableListOf("")

    /**
     * Функция декодирования файла и записи результата в outputFileName
     */
    fun decodeFile(inputFileName: String, outputFileName: String) {
        DataInputStream(File(inputFileName).inputStream().buffered()).use { dataInputStream ->
            File(outputFileName).outputStream().bufferedWriter().use { writer ->
                decode(dataInputStream, writer)
            }
        }
    }

    /**
     * Функция последовательного декодирования
     */
    private fun decode(dataInputStream: DataInputStream, writer: BufferedWriter) {
        var position: Int = 0
        var char: Char
        var word: String
        var lastWordMarker = false
        try {
            while (true) {
                lastWordMarker = false
                // читаем позицию из файла
                position = dataInputStream.readInt()
                lastWordMarker = true
                // читаем символ из файла
                char = dataInputStream.readChar()
                // получаем закодированное слово
                word = dictionary[position] + char
                // добавляем его в словарь
                dictionary.add(word)
                // пишем в выходной файл. Т.к. используется обертка над BufferedWriter, то
                // не будет системного вызова на запись при каждом write - запись на диск будет сделана только при
                // переполнении внутреннего буффера
                writer.write(word)
            }
        } catch (eof: EOFException) {
            // если исключение вызвано из-за последнего слова - добавляем его в выходной файл
            if (lastWordMarker) {
                word = dictionary[position]
                dictionary.add(word)
                writer.write(word)
            }
        }
    }
}