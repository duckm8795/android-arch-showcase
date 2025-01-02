package com.arch.common.provider

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * DispatchersProvider is an abstraction layer for providing coroutine dispatchers, allowing for better testability and flexibility.
 *
 * It defines the following dispatchers:
 * - [default]: For CPU-intensive work (e.g., data processing or calculations).
 * - [main]: For UI-related tasks (e.g., updating views).
 * - [io]: For I/O operations (e.g., file or network operations).
 * - [immediate]: For immediate execution on the main dispatcher without dispatching if already on the main thread.
 */
interface DispatchersProvider {
    val default: CoroutineDispatcher
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val immediate: CoroutineDispatcher
}

internal class DispatchersProviderImpl @Inject constructor() : DispatchersProvider {
    override val default: CoroutineDispatcher
        get() = Dispatchers.Default

    override val main: CoroutineDispatcher
        get() = Dispatchers.Main

    override val io: CoroutineDispatcher
        get() = Dispatchers.IO

    override val immediate: CoroutineDispatcher
        get() = Dispatchers.Main.immediate
}