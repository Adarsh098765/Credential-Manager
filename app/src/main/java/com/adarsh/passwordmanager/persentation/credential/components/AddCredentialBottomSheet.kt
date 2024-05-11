package com.adarsh.passwordmanager.persentation.credential.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.adarsh.passwordmanager.R
import com.adarsh.passwordmanager.domain.model.Credential
import com.adarsh.passwordmanager.utils.AppUtil.generatePassword
import com.adarsh.passwordmanager.utils.AppUtil.getPasswordStrength
import com.google.accompanist.insets.navigationBarsWithImePadding

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun AddCredentialBottomSheet(
    credential: Credential? = null,
    onAddCredential: (String, String, String) -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val modalBottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)

    var website by remember { mutableStateOf(credential?.website ?: "") }
    var username by remember { mutableStateOf(credential?.username ?: "") }
    var password by remember { mutableStateOf(credential?.password ?: "") }

    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var isPasswordGeneratorVisible by rememberSaveable { mutableStateOf(false) }

    // Create a FocusRequester to request focus on the TextField when needed
    val websiteFocusRequester = remember { FocusRequester() }
    val usernameFocusRequester = remember { FocusRequester() }
    val passwordFocusRequester = remember { FocusRequester() }

    val clipboardManager = LocalClipboardManager.current
    val copyPasswordToClipboard: () -> Unit = {
        clipboardManager.setText(AnnotatedString(password))
    }

    ModalBottomSheet(
        onDismissRequest = { onDismissRequest() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
        containerColor = Color.White,
        modifier = Modifier
            .navigationBarsWithImePadding()
    ) {

        Column(
            modifier = modifier
                .padding(16.dp)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            TextField(
                value = website,
                onValueChange = { website = it },
                label = { Text("Website") },
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged { focusState ->
                        if (focusState.isFocused) {
                            websiteFocusRequester.requestFocus()
                        }
                    }
                    .focusRequester(websiteFocusRequester)
                    .padding(bottom = 8.dp),
                readOnly = credential != null
            )
            TextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username") },
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged { focusState ->
                        if (focusState.isFocused) {
                            usernameFocusRequester.requestFocus()
                        }
                    }
                    .focusRequester(usernameFocusRequester)
                    .padding(bottom = 8.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    singleLine = true,
                    placeholder = { Text("Password") },
                    modifier = Modifier
                        .weight(1f)
                        .onFocusChanged { focusState ->
                            if (focusState.isFocused) {
                                passwordFocusRequester.requestFocus()
                            }
                        }
                        .focusRequester(passwordFocusRequester),
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    trailingIcon = {
                        Row {
                            IconButton(
                                onClick = { passwordVisible = !passwordVisible },
                                modifier = Modifier.size(24.dp)
                            ) {
                                val image = if (passwordVisible)
                                    painterResource(id = R.drawable.show)
                                else painterResource(id = R.drawable.hide)

                                val description =
                                    if (passwordVisible) "Hide password" else "Show password"

                                Icon(
                                    painter = image,
                                    contentDescription = description,
                                    modifier = Modifier.size(16.dp)
                                )
                            }

                            IconButton(
                                onClick = { isPasswordGeneratorVisible = !isPasswordGeneratorVisible },
                                modifier = Modifier.size(24.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.password_generator),
                                    contentDescription = "Generate Password",
                                    modifier = Modifier.size(16.dp)
                                )
                            }

                            if (isPasswordGeneratorVisible) {
                                IconButton(
                                    onClick = {
                                        password = generatePassword()
                                        isPasswordGeneratorVisible = false
                                    },
                                    modifier = Modifier.size(24.dp)
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.generate),
                                        contentDescription = "Generate",
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                            }

                            IconButton(
                                onClick = copyPasswordToClipboard,
                                modifier = Modifier.size(24.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.copy),
                                    contentDescription = "Copy Password",
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    },
                )

                if (password.isNotEmpty()) {
                    Spacer(modifier = Modifier.width(8.dp))

                    val passwordStrengthMessage = when (getPasswordStrength(password)) {
                        0 -> "Weak"
                        1 -> "Fair"
                        2 -> "Good"
                        3 -> "Strong"
                        else -> ""
                    }
                    Text(
                        text = passwordStrengthMessage,
                        modifier = Modifier.padding(end = 8.dp),
                        color = when (getPasswordStrength(password)) {
                            0 -> Color.Red
                            1 -> Color.Yellow
                            2 -> Color(0xFFA7C957)
                            3 -> Color.Green
                            else -> Color.Black
                        }
                    )
                }
            }

            Button(
                onClick = { onAddCredential(website, username, password) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (credential != null) "Update Account" else "Add New Account")
            }
        }
    }
}