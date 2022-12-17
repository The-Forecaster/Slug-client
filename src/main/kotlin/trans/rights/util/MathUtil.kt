package trans.rights.util

import kotlin.math.abs

fun Double.round(increment: Double) = abs(this - this.roundFloor(increment)).coerceAtMost(abs(this - this.roundCeil(increment)))

fun Double.roundCeil(increment: Double) = this.roundFloor(increment) + increment

fun Double.roundFloor(increment: Double) = this - (this % increment)

fun Float.round(increment: Float) = abs(this - this.roundFloor(increment)).coerceAtMost(abs(this - this.roundCeil(increment)))

fun Float.roundCeil(increment: Float) = this.roundFloor(increment) + increment

fun Float.roundFloor(increment: Float) = this - (this % increment)