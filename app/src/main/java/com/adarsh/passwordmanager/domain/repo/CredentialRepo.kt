package com.adarsh.passwordmanager.domain.repo

import com.adarsh.passwordmanager.domain.model.Credential
import kotlinx.coroutines.flow.Flow

interface CredentialRepository {

    fun getCredential(): Flow<List<Credential>>

    suspend fun getCredentialByWebsite(id: String): Credential?

    suspend fun insertCredential(note: Credential)

    suspend fun deleteCredential(note: Credential)
}