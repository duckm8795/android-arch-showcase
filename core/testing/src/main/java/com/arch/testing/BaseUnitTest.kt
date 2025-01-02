package com.arch.testing

import com.arch.common.provider.DispatchersProvider
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before

@OptIn(ExperimentalCoroutinesApi::class)
abstract class BaseUnitTest {

    private val testDispatcher = UnconfinedTestDispatcher()

    protected val dispatchersProvider: DispatchersProvider = mockk()

    @Before
    open fun setUp() {
        Dispatchers.setMain(testDispatcher)
        every { dispatchersProvider.io } returns testDispatcher
        every { dispatchersProvider.default } returns testDispatcher
        every { dispatchersProvider.main } returns testDispatcher
        every { dispatchersProvider.immediate } returns testDispatcher
    }

    @After
    open fun tearDown() {
        Dispatchers.resetMain()
    }
}