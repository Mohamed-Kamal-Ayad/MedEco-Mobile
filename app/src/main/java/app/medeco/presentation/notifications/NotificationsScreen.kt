package app.medeco.presentation.notifications

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.medeco.R
import app.medeco.data.remote.pharmacy.model.GetNetworkRequestsDto
import app.medeco.data.remote.pharmacy.model.NetworkRequestSender
import app.medeco.domain.model.pharmacy.Pharmacy
import app.medeco.domain.model.pharmacy.PharmacyBranch
import app.medeco.presentation.common.BasicNotificationCard
import app.medeco.presentation.common.MainTopAppBar
import app.medeco.presentation.pharmacy.network.NetworkRequestCard
import app.medeco.presentation.ui.theme.MedEcoTheme

@Composable
fun NotificationsScreen(
    state: NotificationsUiState,
    onEvent: (NotificationsScreenEvent) -> Unit,
    onNavigateUp: () -> Unit,
) {
    Scaffold(
        topBar = {
            MainTopAppBar(
                title = stringResource(R.string.notifications),
                onNavigateUp = onNavigateUp
            )
        },
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(12.dp)
        ) {
            items(state.notifications) { notification ->
                when (notification) {
                    is BasicNotification -> BasicNotificationCard(content = notification.content)
                    is NetworkRequestNotification -> NetworkRequestCard(data = notification.request) {
                        onEvent(NotificationsScreenEvent.ApproveRequest(notification.request))
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun NotificationsScreenPreview() {
    MedEcoTheme {
        NotificationsScreen(
            state = NotificationsUiState(
                notifications = listOf(
                    NetworkRequestNotification(
                        request = GetNetworkRequestsDto(
                            id = 1,
                            description = "4x Panadol Extra\n3x Panadol Cold + Flu",
                            sender = NetworkRequestSender(
                                branch = PharmacyBranch(
                                    id = 7787,
                                    phone = "1234",
                                    lat = null,
                                    lng = null,
                                    pharmacy = null
                                ), pharmacy = Pharmacy(
                                    hotline = "cum",
                                    id = 2056,
                                    name = "El Ezaby pharmacy",
                                    logo = null,
                                    branches = listOf()
                                )
                            )

                        )
                    ),
                    BasicNotification(
                        content = "This is a test basic notification content in notifications screen"
                    )
                )
            ),
            {},
            {}
        )
    }
}