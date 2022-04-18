package com.jakebarnby.entertainme.ui.composable.screen

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.jakebarnby.entertainme.R

sealed class Screen(
    val route: String,
    val icon: ImageVector,
    @StringRes val resourceId: Int,
) {
    object Suggest : Screen("suggest", Icons.Filled.ChatBubble, R.string.suggest)
    object Accounts : Screen("settings", Icons.Filled.ManageAccounts, R.string.accounts)
}

val screens = listOf(
    Screen.Suggest,
    Screen.Accounts,
)