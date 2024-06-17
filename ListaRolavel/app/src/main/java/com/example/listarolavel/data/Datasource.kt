package com.example.listarolavel.data

import com.example.listarolavel.R
import com.example.listarolavel.model.Affirmation

class Datasource {
    fun loadAffirmations(): List<Affirmation> {
        return listOf(
            Affirmation(R.string.affirmation1, R.drawable.img_1),
            Affirmation(R.string.affirmation2, R.drawable.img_2),
            Affirmation(R.string.affirmation3, R.drawable.img_3),
            Affirmation(R.string.affirmation4, R.drawable.img_4),
            Affirmation(R.string.affirmation5, R.drawable.img_5),
            Affirmation(R.string.affirmation6, R.drawable.img_6),
            Affirmation(R.string.affirmation7, R.drawable.img_7),
            Affirmation(R.string.affirmation8, R.drawable.img_8),
            Affirmation(R.string.affirmation9, R.drawable.img_9),
            Affirmation(R.string.affirmation10, R.drawable.img_10))
    }
}