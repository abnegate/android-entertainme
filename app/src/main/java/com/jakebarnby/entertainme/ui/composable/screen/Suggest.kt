package com.jakebarnby.entertainme.ui.composable.screen

import android.content.res.Configuration
import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jakebarnby.entertainme.SuggestViewModel
import com.jakebarnby.entertainme.ui.composable.BrainAnimation
import com.jakebarnby.entertainme.ui.composable.Suggestion
import com.jakebarnby.entertainme.ui.theme.EntertainMeTheme

@Composable
@ExperimentalAnimationApi
@ExperimentalMaterialApi
fun Suggest() {

    val vm = viewModel<SuggestViewModel>()

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val suggested = vm.suggested.collectAsState()
        val thinking = vm.thinking.collectAsState()

        AnimatedVisibility(thinking.value) {
            BrainAnimation(Modifier.size(150.dp))
        }

        AnimatedVisibility(
            !suggested.value && !thinking.value,
            //exit = fadeOut() + slideOutVertically { it * 2 }
        ) {
            Column {
                Text("Entertain Me")
                Spacer(Modifier.padding(16.dp))
                Button(vm::generateSuggestion, shape = RoundedCornerShape(50)) {
                    Text("Suggest", Modifier.padding(8.dp))
                }
            }
        }
        AnimatedVisibility(
            suggested.value,
            //enter = fadeIn() + slideInVertically { it * 2 }
        ) {
            Suggestion(
                suggestion = vm.suggestion.collectAsState(),
                onClickAccept = vm::consumeSuggestion,
                onClickRetry = vm::regenerateSuggestion
            )
        }
    }
}

@Preview(name = "Light", showBackground = true)
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
@ExperimentalAnimationApi
@ExperimentalMaterialApi
fun SuggestPreview() {
    EntertainMeTheme {
        Suggest()
    }
}