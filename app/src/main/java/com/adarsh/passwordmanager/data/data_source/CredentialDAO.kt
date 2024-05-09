package com.adarsh.passwordmanager.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.adarsh.passwordmanager.domain.model.Credential
import kotlinx.coroutines.flow.Flow


@Dao
interface CredentialDAO {

        @Query("SELECT * FROM credential")
        fun getCredentials(): Flow<List<Credential>>

        @Query("SELECT * FROM credential WHERE website = :website")
        suspend fun getCredentialByWebsite(website: String): Credential?

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertCredential(credential: Credential)

        @Delete
        suspend fun deleteCredential(credential: Credential)
}
