package com.adarsh.passwordmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.fragment.app.FragmentActivity
import com.adarsh.passwordmanager.persentation.credential.CredentialScreen
import com.adarsh.passwordmanager.ui.theme.PasswordManagerTheme
import com.adarsh.passwordmanager.utils.BiometricPromptManager
import com.adarsh.passwordmanager.utils.rememberImeState
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    val biometricPrompt  by lazy {
        BiometricPromptManager(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PasswordManagerTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CredentialScreen(biometricPromptManager = biometricPrompt)
                }

            }
        }
    }
}

