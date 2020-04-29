package se.vbgt.diceware

import kotlin.random.Random
import kotlin.random.nextInt

object Dice {
    /**
     * Simulate a six sided dice (values 1 - 6)
     */
    fun rollDice(): Int = Random.nextInt(1..6)
}