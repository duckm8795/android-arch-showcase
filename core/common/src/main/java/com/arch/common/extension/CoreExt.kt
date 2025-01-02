package com.arch.common.extension

/**
 * Extension function for nullable [Int] to provide a default value of zero.
 *
 * Usage:
 * - Safely unwraps a nullable [Int] by returning its value if not null.
 * - Returns `0` if the [Int] is null.
 *
 * Example:
 * ```kotlin
 * val nullableInt: Int? = null
 * val value = nullableInt.orZero() // value will be 0
 *
 * val nonNullInt: Int? = 5
 * val value = nonNullInt.orZero() // value will be 5
 * ```
 *
 * This function is particularly useful for avoiding null checks when dealing with nullable integers.
 */
fun Int?.orZero() = this ?: 0