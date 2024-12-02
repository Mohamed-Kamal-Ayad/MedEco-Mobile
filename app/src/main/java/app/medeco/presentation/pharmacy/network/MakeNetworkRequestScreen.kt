package app.medeco.presentation.pharmacy.network

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.medeco.R
import app.medeco.domain.model.pharmacy.PharmacyBranch
import app.medeco.presentation.common.AutoCompleteTextField
import app.medeco.presentation.common.MainButton
import app.medeco.presentation.common.MainTextField
import app.medeco.presentation.common.MainTopAppBar
import app.medeco.presentation.ui.theme.MedEcoTheme

@Composable
fun MakeNetworkRequestScreen(
    state: PharmacyNetworkUiState,
    modifier: Modifier = Modifier,
    onEvent: (PharmacyNetworkEvent) -> Unit
) {
    var description by remember {
        mutableStateOf("")
    }
    var selectedBranch: PharmacyBranch? by remember {
        mutableStateOf(null)
    }
    var branchText by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    LaunchedEffect(state.branches) {
        selectedBranch = state.branches.firstOrNull()
        branchText = state.branches.firstOrNull()?.address ?: ""
    }
    LaunchedEffect(state) {
        if (state.navigateUp) {
            Toast.makeText(context, context.getString(R.string.request_success), Toast.LENGTH_SHORT).show()
            onEvent(PharmacyNetworkEvent.NavigateUp)
        }
        if (state.error != null) {
            Toast.makeText(context, state.error, Toast.LENGTH_SHORT).show()
        }
    }
    Scaffold(
        modifier = modifier,
        topBar = {
            MainTopAppBar(
                title = stringResource(R.string.make_request),
                onNavigateUp = {
                    onEvent(PharmacyNetworkEvent.NavigateUp)
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues).padding(12.dp)
        ) {
            AutoCompleteTextField(
                text = branchText,
                hint = stringResource(R.string.branch),
                onTextChange = {
                    branchText = it
                },
                options = state.branches,
                onOptionSelected = {
                    branchText = it.address
                    branchText = it.address
                },
                optionName = { it.address }
            )
            Spacer(Modifier.height(12.dp))
            MainTextField(
                value = description,
                hint = stringResource(R.string.description),
                onValueChange = { description = it},
                modifier = Modifier.fillMaxWidth().heightIn(min = 200.dp)
            )
            Spacer(Modifier.height(12.dp))
            MainButton(
                text = stringResource(R.string.confirm),
                loading = state.loading,
                onClick = {
                    selectedBranch?.let { branch ->
                        onEvent(PharmacyNetworkEvent.MakeRequest(description, branch.id))
                    }
                }
            )
        }
    }
}
@Composable
@Preview(showBackground = true)
fun MakeNetworkRequestScreenPreview() {
    MedEcoTheme {
        MakeNetworkRequestScreen(
            state = PharmacyNetworkUiState(

            ),
            onEvent = {}
        )
    }
}

