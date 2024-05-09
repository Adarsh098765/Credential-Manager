package com.adarsh.passwordmanager.domain.use_cases

import com.adarsh.passwordmanager.domain.model.Credential
import com.adarsh.passwordmanager.domain.repo.CredentialRepository

class GetCredential(private val repository: CredentialRepository) {

    suspend operator fun invoke(id: String): Credential? {
        return repository.getCredentialByWebsite(id)
    }

}