package nsu.manasyan.lz

import java.lang.Exception

fun main(args: Array<String>) {
    try {
        val mode = ArgumentsResolver.resolve(args)
        when (mode.type) {
            ModeType.ENCODING -> {
                val encoder = Encoder()
                encoder.encodeFile(mode.inputFileName, mode.outputFileName)
            }
            ModeType.DECODING -> {
                val decoder = Decoder()
                decoder.decodeFile(mode.inputFileName, mode.outputFileName)
            }
        }
    } catch (exc: Exception){
        println("Something goes wrong :(\n${exc.localizedMessage}")
        println(ArgumentsResolver.getUsage())
    }
}