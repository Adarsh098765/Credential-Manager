package com.adarsh.passwordmanager.persentation.credential.components

import com.adarsh.passwordmanager.domain.model.Credential
import com.adarsh.passwordmanager.domain.repo.CredentialRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetCredentials(
    private val repository: CredentialRepository
) {
    operator fun invoke(

    ): Flow<List<Credential>> {
        return repository.getCredential().map { notes ->

            notes.sortedByDescending { it.website }
        }
    }
}