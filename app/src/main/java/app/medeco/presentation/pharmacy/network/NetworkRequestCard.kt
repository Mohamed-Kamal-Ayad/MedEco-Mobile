package app.medeco.presentation.pharmacy.network

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import app.medeco.R
import app.medeco.data.remote.pharmacy.model.GetNetworkRequestsDto
import app.medeco.data.remote.pharmacy.model.NetworkRequestSender
import app.medeco.domain.model.pharmacy.Pharmacy
import app.medeco.domain.model.pharmacy.PharmacyBranch
import app.medeco.presentation.common.SmallOutlinedButton
import app.medeco.presentation.ui.theme.MedEcoTheme

@Composable
fun NetworkRequestCard(
    data: GetNetworkRequestsDto,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.weight(1.2f)
            ) {
                Text(
                    text = data.sender.pharmacy.name,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                    modifier = Modifier.padding(end = 8.dp)
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = data.description,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Spacer(Modifier.height(12.dp))
                SmallOutlinedButton(
                    text = stringResource(R.string.approve_request),
                    onClick = onClick
                )
            }
            AsyncImage(
                model = data.sender.pharmacy.logo,
                contentDescription = null,
                modifier = Modifier
                    .weight(1f)
                    .padding(
                        end = 12.dp,
                        start = 24.dp
                    )
                    .aspectRatio(1f)
                    .clip(CircleShape),
                contentScale = ContentScale.FillBounds,
            )
        }
    }
}


@Preview
@Composable
fun NetworkRequestCardPreview() {
    MedEcoTheme {
        NetworkRequestCard(
            data = GetNetworkRequestsDto(
                id = 0,
                description = "4x Panadol Extra",
                NetworkRequestSender(
                    branch = PharmacyBranch(
                        id = 5242,
                        phone = "(944) 626-8772",
                        lat = null,
                        lng = null,
                        pharmacy = null
                    ),
                    pharmacy = Pharmacy(
                        hotline = "12345",
                        id = 2585,
                        name = "El Ezaby Pharmacy",
                        logo = "https://image.winudf.com/v2/image1/Y29tLm9yY2h0ZWNoLmVsZXphYnlfaWNvbl8xNTY3MDE1NjExXzAyOQ/icon.png",
                        branches = listOf()
                    )
                )
            ),
            onClick = {}
        )
    }
}
