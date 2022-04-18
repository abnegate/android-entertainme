package com.jakebarnby.entertainme.ui.composable.screen

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jakebarnby.entertainme.AccountsViewModel
import com.jakebarnby.entertainme.R
import com.jakebarnby.entertainme.model.Account
import com.jakebarnby.entertainme.ui.theme.EntertainMeTheme

@Composable
fun Accounts() {

    val vm = viewModel<AccountsViewModel>()
    val accounts = listOf(
        Account(
            name = "Netflix",
            iconResource = R.drawable.ic_launcher_foreground,
            userId = "esalmon@gmail.com",
            isConnected = true,
            authKey = "",
        ),
        Account(
            name = "Hulu",
            iconResource = R.drawable.ic_launcher_foreground,
            userId = "jakebarnby",
            isConnected = true,
            authKey = "",
        ),
        Account(
            name = "Amazon",
            iconResource = R.drawable.ic_launcher_foreground,
            userId = "jakebarnby",
            isConnected = false,
            authKey = "",
        ),
        Account(
            name = "YouTube",
            iconResource = R.drawable.ic_launcher_foreground,
            userId = "jakeb994@gmail.com",
            isConnected = false,
            authKey = "",
        ),
    )

    AccountList(accounts)
}

@Composable
fun AccountList(accounts: List<Account>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(accounts) { account ->
            key(account.name) {
                AccountRow(account)
            }
        }
    }
}

@Composable
fun AccountRow(account: Account) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(account.iconResource),
            contentDescription = account.name,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .align(Alignment.CenterVertically)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(verticalArrangement = Arrangement.Center) {
            Text(account.name, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(4.dp))
            Text(
                account.userId,
                style = MaterialTheme.typography.subtitle2,
                overflow = TextOverflow.Ellipsis
            )
        }
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.End,
        ) {
            AnimatedVisibility(account.isConnected) {
                OutlinedButton(
                    onClick = {},
                    shape = CircleShape,
                    border = BorderStroke(1.dp, MaterialTheme.colors.primary),
                ) {
                    Text("Disconnect")
                    Spacer(Modifier.width(8.dp))
                    Icon(
                        Icons.Filled.Cancel,
                        contentDescription = "cancel"
                    )
                }
            }
            AnimatedVisibility(!account.isConnected) {
                Button(
                    onClick = {},
                    shape = CircleShape,
                ) {
                    Text("Connect")
                }
            }
        }
    }
}

@Preview(name = "Light", showBackground = true)
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
@ExperimentalAnimationApi
@ExperimentalMaterialApi
fun AccountsPreview() {
    EntertainMeTheme {
        val accounts = listOf(
            Account(
                name = "Netflix",
                iconResource = R.drawable.ic_launcher_foreground,
                userId = "esalmon@gmail.com",
                isConnected = true,
                authKey = "",
            ),
            Account(
                name = "Hulu",
                iconResource = R.drawable.ic_launcher_foreground,
                userId = "jakebarnby",
                isConnected = true,
                authKey = "",
            ),
            Account(
                name = "Amazon",
                iconResource = R.drawable.ic_launcher_foreground,
                userId = "jakebarnby",
                isConnected = false,
                authKey = "",
            ),
            Account(
                name = "YouTube",
                iconResource = R.drawable.ic_launcher_foreground,
                userId = "jakeb994@gmail.com",
                isConnected = false,
                authKey = "",
            ),
        )
        AccountList(accounts)
    }
}


