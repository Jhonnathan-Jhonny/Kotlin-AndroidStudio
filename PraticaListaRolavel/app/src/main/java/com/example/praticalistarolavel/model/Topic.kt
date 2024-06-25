package com.example.praticalistarolavel.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val name: Int,
    val courdesNumber: Int,
    @DrawableRes val imagem: Int
)
