package com.example.superheroes.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Hero(
    @StringRes
    var nameRes: Int,
    @StringRes
    var descriptionRes: Int,
    @DrawableRes
    var imageRes:  Int
)
