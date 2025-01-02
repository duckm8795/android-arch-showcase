package com.arch.testing

import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import kotlinx.coroutines.ExperimentalCoroutinesApi

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