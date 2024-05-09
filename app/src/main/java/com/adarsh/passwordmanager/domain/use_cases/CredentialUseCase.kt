package com.adarsh.passwordmanager.domain.use_cases

import com.adarsh.passwordmanager.persentation.credential.components.GetCredentials

data class CredentialUseCase(
    val getCredentials: GetCredentials,
    val deleteCredential: DeleteCredential,
    val addCredential: AddCredential,
    val getIndividualCredential: GetCredential
)