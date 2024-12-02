package app.medeco.presentation.user

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import app.medeco.presentation.user.donate.map.NearbyPharmaciesScreen
import app.medeco.presentation.assistant.AssistantScreen
import app.medeco.presentation.assistant.AssistantViewModel
import app.medeco.presentation.common.Screen
import app.medeco.presentation.user.donate.DonationDataScreen
import app.medeco.presentation.user.donate.approve.ApproveDonationScreen
import app.medeco.presentation.user.donate.approve.ApproveDonationViewModel
import app.medeco.presentation.user.donate.donation_data.DonationDataViewModel
import app.medeco.presentation.user.donate.donation_data.DonationEvent
import app.medeco.presentation.user.donate.map.NearbyPharmaciesViewModel
import app.medeco.presentation.user.profile.faq.FAQScreen
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.userNestedGraph(navController: NavHostController) {
    navigation<Screen.UserGraph>(
        startDestination = Screen.UserMainScreen
    ) {
        composable<Screen.UserMainScreen>(
            enterTransition = { fadeIn(tween(0)) },
            exitTransition = { fadeOut(tween(0)) },
            popEnterTransition = { fadeIn(tween(0)) },
            popExitTransition = { fadeOut(tween(0)) },
        ) {
            UserMainScreen(navController)
        }
        composable<Screen.SmartAssistantScreen> {
            val viewModel = koinViewModel<AssistantViewModel>()
            val state by viewModel.uiState.collectAsState()
            AssistantScreen(
                state = state,
                messages = viewModel.messages,
                onEvent = {
                    viewModel.onEvent(it)
                }
            )
        }
        composable<Screen.FAQScreen> {
            FAQScreen(
                onNavigateUp = {
                    navController.navigateUp()
                }
            )
        }
        composable<Screen.NearbyPharmaciesScreen> {
            val viewModel = koinViewModel<NearbyPharmaciesViewModel>()
            val state by viewModel.uiState.collectAsState()
            NearbyPharmaciesScreen(
                state = state,
                onNavigate = {
                    navController.navigate(it)
                }
            )
        }
        composable<Screen.DonationDataScreen> {
            val viewModel = koinViewModel<DonationDataViewModel>()
            val state by viewModel.uiState.collectAsState()
            val args = it.toRoute<Screen.DonationDataScreen>()
            DonationDataScreen(
                state = state,
                branchId = args.branchId,
                pharmacyName = args.pharmacyName,
                onEvent = {
                    if (it == DonationEvent.Done) {
                        navController.navigate(Screen.UserMainScreen) {
                            popUpTo(Screen.UserHomeScreen) { inclusive = true }
                        }
                    } else viewModel.onEvent(it)
                }
            )
        }
        composable<Screen.ApproveDonationScreen> {
            val viewModel = koinViewModel<ApproveDonationViewModel>()
            val state by viewModel.uiState.collectAsState()
            ApproveDonationScreen(
                state = state,
                onEvent = viewModel::onEvent
            )
        }
    }
}