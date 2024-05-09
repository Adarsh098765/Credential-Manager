package com.adarsh.passwordmanager.domain.use_cases

import com.adarsh.passwordmanager.domain.model.Credential
import com.adarsh.passwordmanager.domain.model.InvalidCredentialException
import com.adarsh.passwordmanager.domain.repo.CredentialRepository

class AddCredential(
    private val repository: CredentialRepository
) {
    @Throws(InvalidCredentialException::class)
    suspend operator fun invoke(credential: Credential) {
        if (credential.website.isBlank()) {
            throw InvalidCredentialException("Website of the Credential can't be Empty!")
        }
        if (credential.username.isBlank()) {
            throw InvalidCredentialException("Username of the Credential can't be Empty!")
        }
        if (credential.password.isBlank()) {
            throw InvalidCredentialException("Password of the Credential can't be Empty!")
        }

        //If the above three conditions don't meet, then we can't insert the Credential
        repository.insertCredential(credential)
    }
}