package app.medeco.presentation.pharmacy.nav

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import app.medeco.presentation.assistant.AssistantScreen
import app.medeco.presentation.assistant.AssistantViewModel
import app.medeco.presentation.common.Screen
import app.medeco.presentation.notifications.NotificationsScreen
import app.medeco.presentation.notifications.NotificationsViewModel
import app.medeco.presentation.pharmacy.branch.ManageBranchesScreen
import app.medeco.presentation.pharmacy.branch.ManageBranchesViewModel
import app.medeco.presentation.pharmacy.network.MakeNetworkRequestScreen
import app.medeco.presentation.pharmacy.network.PharmacyNetworkEvent
import app.medeco.presentation.pharmacy.network.PharmacyNetworkViewModel
import app.medeco.presentation.user.profile.faq.FAQScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

fun NavGraphBuilder.pharmacyNestedGraph(navController: NavHostController) {
    navigation<Screen.PharmacyGraph>(
        startDestination = Screen.PharmacyMainScreen
    ) {
        composable<Screen.PharmacyMainScreen> {
            PharmacyMainScreen(navController)
        }
        composable<Screen.ManageBranchesScreen> {
            val viewModel = koinViewModel<ManageBranchesViewModel>()
            val state by viewModel.uiState.collectAsState()
            val pharmacyId = it.toRoute<Screen.ManageBranchesScreen>().pharmacyId
            ManageBranchesScreen(
                state = state,
                pharmacyId = pharmacyId,
                onEvent = viewModel::onEvent
            )
        }
        composable<Screen.RequestMedicineNetworkScreen> {
            val viewModel = koinViewModel<PharmacyNetworkViewModel>()
            val state by viewModel.uiState.collectAsState()
            MakeNetworkRequestScreen(
                state = state,
                onEvent = {
                    when(it) {
                        PharmacyNetworkEvent.NavigateUp -> navController.navigateUp()
                        else -> viewModel.onEvent(it)
                    }
                }
            )
        }
        composable<Screen.NotificationsScreen> {
            val viewModel = koinViewModel<NotificationsViewModel>()
            val state by viewModel.uiState.collectAsState()
            NotificationsScreen(
                state = state,
                onEvent = viewModel::onEvent,
                onNavigateUp = navController::navigateUp
            )
        }
        composable<Screen.SmartAssistantScreen> {
            val viewModel = koinViewModel<AssistantViewModel>()
            val state by viewModel.uiState.collectAsState()
            AssistantScreen(
                state = state,
                messages = viewModel.messages,
                onEvent = viewModel::onEvent
            )
        }
        composable<Screen.FAQScreen> {
            FAQScreen(
                onNavigateUp = navController::navigateUp
            )
        }
    }
}