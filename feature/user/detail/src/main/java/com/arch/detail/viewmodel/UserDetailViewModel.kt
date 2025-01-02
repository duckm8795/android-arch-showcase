package com.arch.detail.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.arch.common.provider.DispatchersProvider
import com.arch.common.viewmodel.BaseViewModel
import com.arch.common.viewmodel.NoEvent
import com.arch.detail.nav.UserDetailRoute
import com.arch.detail.ui.UserDetailUiState
import com.arch.detail.ui.mapper.toUiState
import com.arch.domain.usecase.GetUserDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * `UserDetailViewModel` is a [HiltViewModel] that manages the state and logic for the User Detail screen.
 * It extends [BaseViewModel] to handle UI state updates and uses coroutine dispatchers for background processing.
 *
 * @param savedStateHandle The [SavedStateHandle] used to retrieve navigation arguments, such as the username.
 * @param dispatchersProvider A [DispatchersProvider] to ensure proper coroutine context usage.
 * @param getUserDetailUseCase A use case for fetching user details based on the provided username.
 *
 * Key Features:
 * - Initializes with [UserDetailUiState] to represent the default UI state.
 * - Extracts navigation arguments from [savedStateHandle] using `toRoute<UserDetailRoute>()`.
 * - Automatically triggers the retrieval of user details during initialization.
 *
 * Methods:
 * - [getUserDetails]:
 *   - Fetches user details using [getUserDetailUseCase].
 *   - Collects the results safely using [BaseViewModel.collectSafe], with error handling and loading state management.
 *   - Updates the UI state with the fetched user details by mapping the result to a [UserDetailUiState].
 *
 * Usage Example:
 * ```kotlin
 * @Composable
 * fun UserDetailScreen(viewModel: UserDetailViewModel = hiltViewModel()) {
 *     val uiState by viewModel.uiStateFlow.collectAsStateWithLifecycle()
 *     // Render the UI using uiState
 * }
 * ```
 *
 * Notes:
 * - The [init] block ensures that user details are fetched as soon as the ViewModel is created.
 * - Error handling in [getUserDetails] is currently a placeholder (`// handle later`) and should be customized as needed.
 */
@HiltViewModel
internal class UserDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val dispatchersProvider: DispatchersProvider,
    private val getUserDetailUseCase: GetUserDetailUseCase,
) : BaseViewModel<UserDetailUiState, NoEvent>(
    initialUiState = UserDetailUiState(),
) {

    private val routeData: UserDetailRoute by lazy {
        savedStateHandle.toRoute<UserDetailRoute>()
    }

    init {
        getUserDetails()
    }

    private fun getUserDetails() {
        getUserDetailUseCase(routeData.username).collectSafe(
            context = dispatchersProvider.io,
            onError = {
                // handle later
            },
            hasLoading = true,
        ) { userDetail ->
            updateUiState { userDetail.toUiState() }
        }
    }
}