package com.adarsh.passwordmanager.domain.use_cases

import com.adarsh.passwordmanager.domain.model.Credential
import com.adarsh.passwordmanager.domain.repo.CredentialRepository

class DeleteCredential(
    private val repository: CredentialRepository
) {
    suspend operator fun invoke(credential: Credential) {
        repository.deleteCredential(credential)
    }
}