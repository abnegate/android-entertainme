package com.jakebarnby.entertainme.ui.composable

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.jakebarnby.entertainme.model.ContentType
import com.jakebarnby.entertainme.model.Suggestion
import com.jakebarnby.entertainme.ui.theme.EntertainMeTheme

@Composable
@ExperimentalMaterialApi
fun Suggestion(
    suggestion: State<Suggestion?>,
    onClickAccept: () -> Unit = {},
    onClickRetry: () -> Unit = {},
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        elevation = 8.dp,
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(suggestion.value!!.imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = suggestion.value!!.name,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Row(
            verticalAlignment = Alignment.Bottom
        ) {
            Column(
                Modifier.background(
                    color = MaterialTheme.colors.surface,
                    shape = RoundedCornerShape(16.dp)
                )
            ) {
                Text(
                    suggestion.value!!.name,
                    Modifier.padding(16.dp, 16.dp, 16.dp, 4.dp),
                    style = MaterialTheme.typography.h5
                )
                Text(
                    suggestion.value!!.trail,
                    Modifier.padding(16.dp, 4.dp, 16.dp, 0.dp),
                    style = MaterialTheme.typography.body1
                )
                AcceptRejectOutlinedButtonRow(
                    modifier = Modifier.padding(16.dp),
                    acceptText = "I'll try this!",
                    rejectText = "Suggest another",
                    onClickAccept = onClickAccept,
                    onClickReject = onClickRetry
                )
            }
        }
    }
}

@Composable
fun AcceptRejectOutlinedButtonRow(
    modifier: Modifier = Modifier,
    acceptText: String,
    rejectText: String,
    onClickAccept: () -> Unit = {},
    onClickReject: () -> Unit = {},
) {
    Row(
        modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Button(
            onClick = onClickAccept,
            modifier = Modifier.weight(1f).padding(end = 8.dp),
            shape = CircleShape,
        ) {
            Text(acceptText)
        }
        OutlinedButton(
            onClick = onClickReject,
            modifier = Modifier.weight(1f).padding(start = 8.dp),
            shape = CircleShape,
            border = BorderStroke(1.dp, MaterialTheme.colors.primary),
        ) {
            Text(rejectText)
        }
    }
}


@Preview(name = "Light")
@Preview(name = "Dark", uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
@ExperimentalMaterialApi
fun SuggestionCardPreview() {
    EntertainMeTheme {
        Suggestion(derivedStateOf {
            Suggestion(
                name = "The Matrix",
                type = ContentType.Movie,
                imageUrl = "https://techcrunch.com/wp-content/uploads/2014/12/matrix.jpg?w=1390&crop=1",
                trail = "Because you watched similar movies in the past 3 months for at least 30 minutes, we think you might like this one."
            )
        })
    }
}