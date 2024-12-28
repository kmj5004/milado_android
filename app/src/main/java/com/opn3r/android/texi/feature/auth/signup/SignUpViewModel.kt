package com.opn3r.android.texi.feature.auth.signup

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


data class SignUpState(
    val tel: String = "",
    val sec: Int = 300,
    val clicked: Boolean = false,
    val code: String = "",
    val code2: String = "",
    val code3: String = "",
    val code4: String = "",
    val code5: String = "",
    val code6: String = "",
    val name: String = "",
)

class SignUpViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(SignUpState())
    val uiState = _uiState.asStateFlow()

    fun updateTel(tel: String) {
        _uiState.update { it.copy(tel = tel) }
    }

    fun updateName(name: String) {
        _uiState.update { it.copy(name = name) }
    }

    fun updateCode(code: String) {
        if (code.length < 2) _uiState.update { it.copy(code = code) }
        if (code.length == 6) {
            PasteCode(code)
        }

    }

    fun updateCode2(code: String) {
        if (code.length < 2) _uiState.update { it.copy(code2 = code) }
        if (code.length == 6) {
            PasteCode(code)
        }
    }

    fun updateCode3(code: String) {
        if (code.length < 2) _uiState.update { it.copy(code3 = code) }
        if (code.length == 6) {
            PasteCode(code)
        }
    }

    fun updateCode4(code: String) {
        if (code.length < 2) _uiState.update { it.copy(code4 = code) }
        if (code.length == 6) {
            PasteCode(code)
        }
    }

    fun updateCode5(code: String) {
        if (code.length < 2) _uiState.update { it.copy(code5 = code) }
        if (code.length == 6) {
            PasteCode(code)
        }
    }

    fun updateCode6(code: String) {
        if (code.length < 2) _uiState.update { it.copy(code6 = code) }
        if (code.length == 6) {
            PasteCode(code)
        }
    }

    fun delSec(sec: Int) {
        if (_uiState.value.sec >= 0) _uiState.update { it.copy(sec = sec - 1) } else _uiState.update {
            it.copy(
                sec = 300
            )
        }
    }

    fun updateClicked(content: Boolean) {
        _uiState.update { it.copy(clicked = content) }
        if (content == false) _uiState.update { it.copy(sec = 300) }
    }

    fun PasteCode(code: String) {
        _uiState.update {
            it.copy(
                code = code[0].toString(),
                code2 = code[1].toString(),
                code3 = code[2].toString(),
                code4 = code[3].toString(),
                code5 = code[4].toString(),
                code6 = code[5].toString(),
            )
        }
    }
}