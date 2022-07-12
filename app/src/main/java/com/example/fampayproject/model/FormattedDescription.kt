package com.example.fampayproject.model

data class FormattedDescription(
    val align: String,
    val entities: List<Entity>,
    val text: String
)