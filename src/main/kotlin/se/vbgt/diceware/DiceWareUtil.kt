package se.vbgt.diceware

import java.nio.file.Files
import java.nio.file.Paths

object DiceWareUtil {
    private val wordMap = Files.readAllLines(Paths.get(javaClass.classLoader.getResource("se/vbgt/diceware/diceware.wordlist.asc").toURI()))
        .drop(2)
        .take(7775)
        .map {
            val split = it.trim().split("\\s".toRegex())
            split[0].map { c ->
                Character.getNumericValue(c)
            } to split[1]
        }.toMap()

    private val specialCharacterMatrix = arrayOf(
        arrayOf('~', '!', '#', '$', '%', '^'),
        arrayOf('&', '*', '(', ')', '-', '='),
        arrayOf('+', '[', ']', '\\', '{', '}'),
        arrayOf(':', ';', '"', '\'', '<', '>'),
        arrayOf('?', '/', '0', '1', '2', '3'),
        arrayOf('4', '5', '6', '7', '8', '9')
    )

    fun mapWords(vararg wordRoll: List<Int>): Map<List<Int>, String> {
        require(wordRoll.isNotEmpty())
        require(!wordRoll.any { it.size != 5 })

        return wordRoll.map {
            it to (wordMap[it] ?: throw IllegalArgumentException())
        }.toMap()
    }
    fun randomLetter(horizontalDice: Int, verticalDice: Int): Char = specialCharacterMatrix[verticalDice-1][horizontalDice-1]
}
