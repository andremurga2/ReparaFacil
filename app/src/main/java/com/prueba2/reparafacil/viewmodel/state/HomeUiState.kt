package com.prueba2.reparafacil.viewmodel.state

data class HomeUiState(
    val welcomeMessage: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)
