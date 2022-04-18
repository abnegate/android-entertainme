package com.jakebarnby.entertainme.model

import androidx.annotation.DrawableRes

data class Account(
    val name: String,
    val userId: String,
    val isConnected: Boolean,
    val authKey: String,
    @DrawableRes val iconResource: Int,
)
