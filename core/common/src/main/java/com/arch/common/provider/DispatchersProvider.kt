package com.arch.common.provider

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

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