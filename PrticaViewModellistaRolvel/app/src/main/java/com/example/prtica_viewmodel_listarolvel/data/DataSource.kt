package com.example.prtica_viewmodel_listarolvel.data

import com.example.prtica_viewmodel_listarolvel.R
import com.example.prtica_viewmodel_listarolvel.model.UserModel

class DataSource {
    companion object {
        fun loadUsers(): List<UserModel> {
            return listOf(
                UserModel(R.string.user1_number, R.string.user1_first_name, R.string.user1_last_name, R.drawable.avatar_1),
                UserModel(R.string.user2_number, R.string.user2_first_name, R.string.user2_last_name, R.drawable.avatar_1),
                UserModel(R.string.user3_number, R.string.user3_first_name, R.string.user3_last_name, R.drawable.avatar_1),
                UserModel(R.string.user4_number, R.string.user4_first_name, R.string.user4_last_name, R.drawable.avatar_1),
                UserModel(R.string.user5_number, R.string.user5_first_name, R.string.user5_last_name, R.drawable.avatar_1),
                UserModel(R.string.user6_number, R.string.user6_first_name, R.string.user6_last_name, R.drawable.avatar_1),
                UserModel(R.string.user7_number, R.string.user7_first_name, R.string.user7_last_name, R.drawable.avatar_1),
                UserModel(R.string.user8_number, R.string.user8_first_name, R.string.user8_last_name, R.drawable.avatar_1),
                UserModel(R.string.user9_number, R.string.user9_first_name, R.string.user9_last_name, R.drawable.avatar_1),
                UserModel(R.string.user10_number, R.string.user10_first_name, R.string.user10_last_name, R.drawable.avatar_1)
            )
        }
    }
}