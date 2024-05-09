package com.adarsh.passwordmanager.persentation.credential

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissState
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.adarsh.passwordmanager.domain.model.Credential
import com.adarsh.passwordmanager.persentation.credential.components.AddCredentialBottomSheet
import com.adarsh.passwordmanager.persentation.credential.components.CredentialDetailsBottomSheet
import com.adarsh.passwordmanager.persentation.credential.components.CredentialItem
import com.adarsh.passwordmanager.utils.BiometricPromptManager
import com.adarsh.passwordmanager.utils.rememberImeState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CredentialScreen(
    modifier: Modifier = Modifier,
    viewModel: CredentialViewModel = hiltViewModel(),
    biometricPromptManager: BiometricPromptManager
) {
    val imeState = rememberImeState()
    val scrollState = rememberScrollState()
    val snackBarHostState = remember {
        SnackbarHostState()
    }

    LaunchedEffect(key1 = imeState.value) {
        if (imeState.value) {
            scrollState.animateScrollTo(scrollState.maxValue, tween(300))
        }
    }
    val showBottomSheet = remember { mutableStateOf(false) }
    var credential = remember { mutableStateOf<Credential?>(null) }
    val credentialList by viewModel.credentialList.collectAsStateWithLifecycle(initialValue = emptyList())
    val snackbarState by viewModel.snackbarState.collectAsStateWithLifecycle()

    val authenticatedState = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        biometricPromptManager.showBiometricPrompt(
            title = "Biometric Authentication",
            description = "Authenticate to view your credentials"
        )

//        biometricPromptManager.promptResults.collect { result ->
//            when (result) {
//                is BiometricPromptManager.BiometricResult.AuthenticationSuccess -> {
//                    authenticatedState.value = true
//                }
//                else -> {
//                    authenticatedState.value = false
//                }
//            }
//        }
    }

    if (snackbarState != null) {
        LaunchedEffect(key1 = snackbarState) {
            snackBarHostState.showSnackbar(
                message = snackbarState.toString(),
                duration = SnackbarDuration.Long
            )
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        floatingActionButton = {
            FloatingButtonScaffold(
                onAddClick = {
                    credential.value = null
                    showBottomSheet.value = true
                }
            )
        },
        topBar = {
            TopAppBar(
                title = { Text(text = "Password Manager") },
            )
        },
        modifier = modifier
    ) { paddingValues ->

        if (credentialList.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    text = "Add your credentials here",
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    textAlign = TextAlign.Center
                )
            }
        } else {
            CredentialListScreen(
                paddingValues = paddingValues,
                credentialList,
                onDeleteCredential = {
                    viewModel.deleteCredential(it)
                },
                onEditCredential = {
                    credential.value = it
                    showBottomSheet.value = true
                },
                scrollState,

                )
        }


        if (showBottomSheet.value) {
            AddCredentialBottomSheet(
                credential = credential.value,
                onAddCredential = { website, username, password ->
                    viewModel.addCredential(website, username, password)
                    showBottomSheet.value = false
                },
                onDismissRequest = { showBottomSheet.value = false },
            )
        }
    }
}


@Composable
fun CredentialListScreen(
    paddingValues: PaddingValues,
    credentialList: List<Credential>,
    onDeleteCredential: (Credential) -> Unit, // Callback for deleting a credential
    onEditCredential: (Credential) -> Unit, // Callback for editing a credential
    scrollState: ScrollState,
) {
    val showBottomSheet = remember { mutableStateOf(false) }
    val selectedCredential = remember { mutableStateOf<Credential?>(null) }

    LazyColumn(
        modifier = Modifier
            .padding(5.dp),
        contentPadding = paddingValues,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(credentialList) { credential ->
            SwipeToDeleteContainer(
                item = credential,
                onDelete = { onDeleteCredential(credential) }
            ) {
                CredentialItem(
                    credential = credential,
                    itemClicked = {
                        showBottomSheet.value = true
                        selectedCredential.value = credential
                    }
                )
            }
        }
    }

    if (showBottomSheet.value && selectedCredential.value != null) {
        CredentialDetailsBottomSheet(
            credential = selectedCredential.value!!,
            onCloseBottomSheet = {
                showBottomSheet.value = false
                selectedCredential.value = null
            },
            onDeleteCredential = {
                onDeleteCredential(it)
                showBottomSheet.value = false
                selectedCredential.value = null
            },
            onEditCredential = {
                onEditCredential.invoke(it)
                showBottomSheet.value = false
            }

        )
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwipeToDeleteContainer(
    item: Credential,
    onDelete: () -> Unit,
    content: @Composable (Credential) -> Unit
) {
    var isRemoved by remember { mutableStateOf(false) }
    val state = rememberDismissState(
        confirmValueChange = {
            if (it == DismissValue.DismissedToStart) {
                isRemoved = true
                true
            } else {
                false
            }
        }
    )

    LaunchedEffect(key1 = isRemoved) {
        if (isRemoved) {
            onDelete()
        }
    }

    SwipeToDismiss(
        state = state,
        background = {
            DeleteBackground(swipeDismissState = state)
        },
        dismissContent = {
            if (!isRemoved) {
                content(item)
            }
        },
        directions = setOf(DismissDirection.EndToStart)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteBackground(
    swipeDismissState: DismissState
) {
    val color = if (swipeDismissState.dismissDirection == DismissDirection.EndToStart) {
        Color.Red
    } else Color.Transparent

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color)
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = null,
            tint = Color.White
        )
    }
}


@Composable
fun FloatingButtonScaffold(
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FloatingActionButton(
        onClick = onAddClick,
        containerColor = MaterialTheme.colorScheme.primary,
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add Credential"
        )
    }
}


