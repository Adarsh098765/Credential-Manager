package com.adarsh.passwordmanager.persentation.credential

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adarsh.passwordmanager.domain.model.Credential
import com.adarsh.passwordmanager.domain.use_cases.CredentialUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CredentialViewModel @Inject constructor(private val credentialUseCase: CredentialUseCase) :
    ViewModel() {

    private val _snackbarState = MutableStateFlow<String?>(null)
    val snackbarState: StateFlow<String?> = _snackbarState

    val credentialList = credentialUseCase.getCredentials()

    fun addCredential(website: String, username: String, password: String) {
        when {
            website.isEmpty() && username.isEmpty() && password.isEmpty() -> {
                _snackbarState.value = "Please fill all fields."
            }
            website.isEmpty() && username.isEmpty() -> {
                _snackbarState.value = "Please fill website and username."
            }
            website.isEmpty() && password.isEmpty() -> {
                _snackbarState.value = "Please fill website and password."
            }
            username.isEmpty() && password.isEmpty() -> {
                _snackbarState.value = "Please fill username and password."
            }
            website.isEmpty() -> {
                _snackbarState.value = "Please fill website."
            }
            username.isEmpty() -> {
                _snackbarState.value = "Please fill username."
            }
            password.isEmpty() -> {
                _snackbarState.value = "Please fill password."
            }
            password.length <= 8 -> {
                _snackbarState.value = "Password should be greater than 8 characters."
            }
            else -> {
                val newCredential = Credential(website = website, username = username, password = password)
                viewModelScope.launch {
                    try {
                        credentialUseCase.addCredential(newCredential)
                    } catch (e: Exception) {
                        // Handle the exception gracefully, such as showing an error message
                        Log.e("CredentialViewModel", "Error adding credential: ${e.message}", e)
                        // You can also emit a state using LiveData or StateFlow to notify the UI
                    }
                }
            }
        }
    }


    fun deleteCredential(credential: Credential) {
        viewModelScope.launch {
            try {
                credentialUseCase.deleteCredential(credential)
            } catch (e: Exception) {
                // Handle the exception gracefully, such as showing an error message
                Log.e("CredentialViewModel", "Error deleting credential: ${e.message}", e)
                // You can also emit a state using LiveData or StateFlow to notify the UI
            }
        }
    }
}
