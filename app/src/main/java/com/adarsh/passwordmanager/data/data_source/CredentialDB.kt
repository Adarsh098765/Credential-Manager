package com.adarsh.passwordmanager.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.adarsh.passwordmanager.domain.model.Credential


@Database(
    entities = [Credential::class],
    version = 1
)
abstract class CredentialDB : RoomDatabase() {
    abstract val credentialDAO: CredentialDAO

    companion object {
        const val DB_NAME = "credential_db"
    }
}
