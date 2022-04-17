package trans.rights.client.util

import kotlin.math.abs

fun Double.round(increment: Double): Double {
    return abs(this - this.roundFloor(increment)).coerceAtMost(abs(this - this.roundCeil(increment)))
}

fun Double.roundCeil(increment: Double): Double {
    return this.roundFloor(increment) + increment
}

fun Double.roundFloor(increment: Double): Double {
    return this - (this % increment)
}