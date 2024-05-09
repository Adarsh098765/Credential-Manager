package com.adarsh.passwordmanager.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "credential")
data class Credential(
    @PrimaryKey
    val website: String,
    val username: String,
    val password: String,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

class InvalidCredentialException(message:String): Exception(message)