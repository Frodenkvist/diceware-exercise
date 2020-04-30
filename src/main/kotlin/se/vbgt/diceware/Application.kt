package se.vbgt.diceware

fun main() {
    val wordThrows = (1..6).map {
        (1..5).map {
            Dice.rollDice()
        }
    }.toTypedArray()

    val specialCharacterThrows = (1..4).map { Dice.rollDice() }

    val words = DiceWareUtil.mapWords(*wordThrows).values.mapIndexed { i, s ->
        if(i != specialCharacterThrows[0]) {
            s
        } else {
            s.replaceCharAt(specialCharacterThrows[1] - 1 % s.length, DiceWareUtil.randomLetter(specialCharacterThrows[2], specialCharacterThrows[3]))
        }
    }

    println(words.joinToString())
}

fun String.replaceCharAt(index: Int, char: Char): String {
    require(index in 0 until length)

    if(index == 0) {
        return char + substring(1, length)
    }

    if(index == length - 1) {
        return substring(0, length - 1) + char
    }

    return substring(0, index) + char + substring(index + 1, length)
}