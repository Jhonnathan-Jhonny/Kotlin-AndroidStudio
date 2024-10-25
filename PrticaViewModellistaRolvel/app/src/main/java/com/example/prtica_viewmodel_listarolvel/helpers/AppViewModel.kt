package com.example.prtica_viewmodel_listarolvel.helpers

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.room.util.copy
import com.example.prtica_viewmodel_listarolvel.data.DataSource
import com.example.prtica_viewmodel_listarolvel.model.UserModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AppViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AppUiState())
    val uiState: StateFlow<AppUiState> = _uiState.asStateFlow()

    var userGuess by mutableStateOf("")
        private set

    private var usedWords: MutableSet<String> = mutableSetOf()
    private lateinit var currentWord: String

    init {
        resetGame()
    }

    private fun resetGame() {
        // Implemente sua lógica de redefinição de jogo aqui
    }

    private val _userList = MutableStateFlow(DataSource.loadUsers())
    val userList: StateFlow<List<UserModel>> = _userList

    // Função para alternar o botão expansivo de um item específico
    fun toggleButtonExpansive(userId: Int) {
        _userList.value = _userList.value.map { user ->
            if (user.number == userId) {
                user.copy(buttonExpansive = !user.buttonExpansive)
            } else user
        }
    }
}
