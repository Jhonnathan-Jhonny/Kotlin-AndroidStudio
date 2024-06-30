package com.example.materialdesign.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.materialdesign.R

data class DataDog(
    @DrawableRes val imageResourceId: Int,
    @StringRes val name: Int,
    val age: Int,
    @StringRes val hobbies: Int
)

val listDog = listOf(
    DataDog(R.drawable.koda, R.string.dog_name_1, 2, R.string.dog_description_1),
    DataDog(R.drawable.lola, R.string.dog_name_2, 16, R.string.dog_description_2),
    DataDog(R.drawable.frankie, R.string.dog_name_3, 2, R.string.dog_description_3),
    DataDog(R.drawable.nox, R.string.dog_name_4, 8, R.string.dog_description_4),
    DataDog(R.drawable.faye, R.string.dog_name_5, 8, R.string.dog_description_5),
    DataDog(R.drawable.bella, R.string.dog_name_6, 14, R.string.dog_description_6),
    DataDog(R.drawable.moana, R.string.dog_name_7, 2, R.string.dog_description_7),
    DataDog(R.drawable.tzeitel, R.string.dog_name_8, 7, R.string.dog_description_8),
    DataDog(R.drawable.leroy, R.string.dog_name_9, 4, R.string.dog_description_9)
)