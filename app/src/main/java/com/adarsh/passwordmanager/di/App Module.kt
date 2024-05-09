package com.adarsh.passwordmanager.di

import android.app.Application
import androidx.room.Room
import com.adarsh.passwordmanager.data.data_source.CredentialDB
import com.adarsh.passwordmanager.data.repository.CredentialRepositoryImpl
import com.adarsh.passwordmanager.domain.repo.CredentialRepository
import com.adarsh.passwordmanager.domain.use_cases.AddCredential
import com.adarsh.passwordmanager.domain.use_cases.CredentialUseCase
import com.adarsh.passwordmanager.domain.use_cases.DeleteCredential
import com.adarsh.passwordmanager.domain.use_cases.GetCredential
import com.adarsh.passwordmanager.persentation.credential.components.GetCredentials
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): CredentialDB {
        return Room.databaseBuilder(
            app,
            CredentialDB::class.java,
            CredentialDB.DB_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideCredentialRepository(db: CredentialDB): CredentialRepository {
        return CredentialRepositoryImpl(db.credentialDAO)
    }

    @Provides
    @Singleton
    fun provideCredentialUseCases(repository: CredentialRepository): CredentialUseCase {
        return CredentialUseCase(
            getCredentials = GetCredentials(repository),
            deleteCredential = DeleteCredential(repository),
            addCredential = AddCredential(repository),
            getIndividualCredential = GetCredential(repository)
        )
    }

}