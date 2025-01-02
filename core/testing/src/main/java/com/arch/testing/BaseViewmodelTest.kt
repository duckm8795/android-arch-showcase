package com.arch.testing

import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import kotlinx.coroutines.ExperimentalCoroutinesApi
/**
 * `BaseViewModelTest` is an abstract class extending [BaseUnitTest], designed to provide additional setup and teardown
 * functionality specifically for testing ViewModel classes in a coroutine-friendly environment.
 *
 * Features:
 * - Builds on [BaseUnitTest] to configure coroutine dispatchers for ViewModel testing.
 * - Mocks the `SavedStateHandle` extension functions from `androidx.navigation.SavedStateHandleKt` to enable
 * testing of ViewModels that depend on `SavedStateHandle`.
 * - Ensures proper cleanup of mocked `SavedStateHandle` extensions to prevent interference with other tests.
 *
 * Methods:
 * - [setUp]:
 *   - Mocks `SavedStateHandle` extension functions by calling [mockkSavedStateHandle].
 *   - Calls the superclass [BaseUnitTest.setUp] for coroutine dispatcher setup.
 * - [tearDown]:
 *   - Unmocks `SavedStateHandle` extension functions by calling [unmockSavedStateHandle].
 *   - Calls the superclass [BaseUnitTest.tearDown] to reset the coroutine dispatcher.
 *
 * Private Helpers:
 * - [mockkSavedStateHandle]: Uses MockK to mock the static functions in `SavedStateHandleKt`.
 * - [unmockSavedStateHandle]: Uses MockK to unmock the static functions in `SavedStateHandleKt`.
 *
 * Usage:
 * - Extend this class when testing ViewModels that use `SavedStateHandle` and coroutines.
 *
 * Example:
 * ```kotlin
 * class ExampleViewModelTest : BaseViewModelTest() {
 *
 *     private lateinit var viewModel: ExampleViewModel
 *
 *     @Before
 *     override fun setUp() {
 *         super.setUp()
 *         viewModel = ExampleViewModel(savedStateHandle = mockk(), dispatchersProvider)
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
 * - This class ensures compatibility with `SavedStateHandle`-based ViewModels by leveraging MockK's static mocking capabilities.
 * - Proper mocking and unmocking ensure isolated test cases and prevent side effects across tests.
 */

abstract class BaseViewmodelTest: BaseUnitTest() {

    override fun setUp() {
        mockkSavedStateHandle()
        super.setUp()
    }

    override fun tearDown() {
        unmockSavedStateHandle()
        super.tearDown()
    }

    private fun mockkSavedStateHandle() = mockkStatic("androidx.navigation.SavedStateHandleKt")

    private fun unmockSavedStateHandle() = unmockkStatic("androidx.navigation.SavedStateHandleKt")
}