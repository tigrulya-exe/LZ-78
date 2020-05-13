package nsu.manasyan.lz

fun main() {
    val encoder = Encoder()
    encoder.encodeBlock("abacababacabc")
    println(encoder.output)
}