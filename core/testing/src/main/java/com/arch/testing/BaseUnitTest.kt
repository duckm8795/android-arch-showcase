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

/**
 * BaseUnitTest is an abstract class that provides a standardized setup for unit tests involving coroutines and
 * dependency injection. It ensures that tests run on a controlled coroutine dispatcher and facilitates mocking
 * of dependencies.
 *
 * Features:
 * - Configures a [UnconfinedTestDispatcher] as the main dispatcher to execute coroutines immediately in tests.
 * - Mocks a [DispatchersProvider] to return the test dispatcher for all coroutine contexts (IO, Default, Main, Immediate).
 * - Ensures proper cleanup of the main dispatcher after tests to avoid side effects.
 *
 * Usage:
 * - Extend this class in unit test files to inherit the coroutine testing setup.
 * - Override [setUp] or [tearDown] if additional setup or cleanup is needed.
 *
 * Properties:
 * - `dispatchersProvider`: A mocked instance of [DispatchersProvider] with all contexts returning the test dispatcher.
 *
 * Methods:
 * - [setUp]: Prepares the test environment by setting the main dispatcher and mocking the dispatchers provider.
 * - [tearDown]: Cleans up the test environment by resetting the main dispatcher.
 *
 * Example Usage:
 * ```kotlin
 * class ExampleUnitTest : BaseUnitTest() {
 *
 *     private lateinit var viewModel: ExampleViewModel
 *
 *     @Before
 *     override fun setUp() {
 *         super.setUp()
 *         // Additional setup
 *         viewModel = ExampleViewModel(dispatchersProvider)
 *     }
 *
 *     @Test
 *     fun testExampleFunction() = runTest {
 *         // Test logic here
 *     }
 * }
 * ```
 *
 * Notes:
 * - This class uses [ExperimentalCoroutinesApi] for testing coroutine-related functionality.
 */
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