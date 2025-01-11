package com.opn3r.android.texi.feature.auth.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class LoginValue(
    val id: String = "",
    val password: String = ""
)

class LoginViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(LoginValue())
    val uiState = _uiState.asStateFlow()

    fun updateId(id: String) {
        _uiState.update { it.copy(id = id) }
    }

    fun updatePassword(password: String) {
        _uiState.update { it.copy(password = password) }
    }
}
