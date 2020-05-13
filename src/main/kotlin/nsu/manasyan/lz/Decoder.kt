package nsu.manasyan.lz

import java.io.BufferedWriter
import java.io.DataInputStream
import java.io.EOFException
import java.io.File

class Decoder {
    private val dictionary = mutableListOf("")

    fun decodeFile(inputFileName: String, outputFileName: String) {
        DataInputStream(Encoder::class.java.getResourceAsStream(inputFileName).buffered()).use { dataInputStream ->
            File(outputFileName).outputStream().bufferedWriter().use { writer ->
                decode(dataInputStream, writer)
            }
        }
    }

    private fun decode(dataInputStream: DataInputStream, writer: BufferedWriter) {
        var position: Int
        var char: Char
        var word: String
        try {
            while (true) {
                position = dataInputStream.readInt()
                char = dataInputStream.readChar()
                word = dictionary[position] + char
                dictionary.add(word)
                writer.write(word)
            }
        } catch (eof: EOFException){

        }
    }
}