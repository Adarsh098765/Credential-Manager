package com.adarsh.passwordmanager.data.repository

import com.adarsh.passwordmanager.data.data_source.CredentialDAO
import com.adarsh.passwordmanager.domain.model.Credential
import com.adarsh.passwordmanager.domain.repo.CredentialRepository
import com.adarsh.passwordmanager.utils.decrypt
import com.adarsh.passwordmanager.utils.encrypt
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CredentialRepositoryImpl(
    private val dao: CredentialDAO
) : CredentialRepository {

    override fun getCredential(): Flow<List<Credential>> {
        return dao.getCredentials().map { credentials ->
            credentials.map { credential ->
                credential.copy(password = decrypt(credential.password))
            }
        }
    }

    override suspend fun getCredentialByWebsite(id: String): Credential? {
        val credential = dao.getCredentialByWebsite(id)
        credential?.password?.let { encryptedPassword ->
            return credential.copy(password = decrypt(encryptedPassword))
        }
        return null
    }

    override suspend fun insertCredential(credential: Credential) {
        val encryptedPassword = encrypt(credential.password)
        dao.insertCredential(credential.copy(password = encryptedPassword))
    }

    override suspend fun deleteCredential(credential: Credential) {
        dao.deleteCredential(credential)
    }
}