package com.adarsh.passwordmanager.utils

import java.util.regex.Pattern


object AppUtil {
    fun getPasswordStrength(password: String): Int {
        val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"
        val pattern = Pattern.compile(passwordPattern)
        val matcher = pattern.matcher(password)

        return when {
            password.length < 6 -> 0 // Weak
            password.length < 8 || !matcher.find() -> 1 // Fair
            password.length < 12 || !matcher.find() -> 2 // Good
            else -> 3
        }
    }

    val generatePassword: () -> String = {
        val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9') + listOf('!', '@', '#', '$', '%', '^', '&', '*')
        (1..12)
            .map { kotlin.random.Random.nextInt(0, charPool.size) }
            .map(charPool::get)
            .joinToString("")
    }
}