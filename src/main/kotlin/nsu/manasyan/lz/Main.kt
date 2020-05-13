package nsu.manasyan.lz

fun main() {
    val encoder = Encoder()
    encoder.encodeFile("/in.txt", "out.txt")

    val decoder = Decoder()
    decoder.decodeFile("/out.txt", "decoded.txt")
}