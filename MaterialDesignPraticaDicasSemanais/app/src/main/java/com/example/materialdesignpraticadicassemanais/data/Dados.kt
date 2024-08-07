package com.example.materialdesignpraticadicassemanais.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.materialdesignpraticadicassemanais.R

data class Dados (
    @StringRes val day: Int,
    @StringRes val text: Int,
    @DrawableRes val image: Int
)

val dataSourceList = listOf(
    Dados(R.string.segunda_feira, R.string.comece_a_semana_com_energia_e_determinacao, R.drawable.image_path_1),
    Dados(R.string.terca_feira, R.string.acredite_em_si_mesmo, R.drawable.image_path_2),
    Dados(R.string.quarta_feira, R.string.mantenha_o_foco, R.drawable.image_path_3),
    Dados(R.string.quinta_feira, R.string.tente_algo_novo, R.drawable.image_path_4),
    Dados(R.string.sexta_feira, R.string.celebre_conquistas, R.drawable.image_path_5),
    Dados(R.string.sabado, R.string.descanse_recarregue, R.drawable.image_path_6),
    Dados(R.string.domingo, R.string.aprecie_o_momento, R.drawable.image_path_7)
)