package com.example.prtica_viewmodel_listarolvel.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class UserModel (
    @StringRes val number: Int,
    @StringRes val first_name: Int,
    @StringRes val last_name: Int,
    @DrawableRes val avatar: Int
)