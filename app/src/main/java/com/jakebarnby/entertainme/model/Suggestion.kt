package com.jakebarnby.entertainme.model

data class Suggestion(
    val name: String,
    val type: ContentType,
    val imageUrl: String,
    val trail: String,
)