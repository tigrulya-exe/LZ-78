package nsu.manasyan.lz

enum class ModeType {
    ENCODING,
    DECODING
}

data class Mode(val type: ModeType, val inputFileName: String, val outputFileName: String)

object ArgumentsResolver {
    private const val ARGS_COUNT = 3

    private const val MODE_INDEX = 0

    private const val ENCODE_MODE = "-e"

    private const val DECODE_MODE = "-d"

    fun resolve(args: Array<String>): Mode {
        if (args.size != ARGS_COUNT) {
            throw IllegalArgumentException("Wrong arguments count")
        }

        return when(args[MODE_INDEX]){
            ENCODE_MODE -> Mode(ModeType.ENCODING, args[1], args[2])
            DECODE_MODE -> Mode(ModeType.DECODING, args[1], args[2])
            else ->  throw IllegalArgumentException("Wrong mode position")
        }
    }

    fun getUsage() = "Usage:\nLZ.jar [-e | -d] inputFileName.txt outputFileName.txt\nOnly text files!"
}