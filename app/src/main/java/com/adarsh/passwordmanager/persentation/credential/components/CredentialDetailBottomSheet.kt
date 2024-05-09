package com.adarsh.passwordmanager.persentation.credential.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adarsh.passwordmanager.domain.model.Credential

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CredentialDetailsBottomSheet(
    credential: Credential,
    onCloseBottomSheet: () -> Unit,
    onDeleteCredential: (Credential) -> Unit = {},
    onEditCredential: (Credential) -> Unit = {}
) {

    val modalBottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)

    ModalBottomSheet(
        onDismissRequest = { onCloseBottomSheet() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
        containerColor = Color.White,
        modifier = Modifier
            .navigationBarsPadding()
            .padding(bottom = 30.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(bottom = 40.dp, start = 10.dp, end = 10.dp)
                .fillMaxHeight()
                .navigationBarsPadding()
                .fillMaxWidth(),
        ) {
            Text(
                text = "Account Details",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary.copy(0.9f)
            )
            Spacer(modifier = Modifier.height(30.dp))
            val accountType =
                AccountDetail(label = "Account Type", value = credential.website.orEmpty())
            val usernameEmail =
                AccountDetail(label = "Username/ Email", value = credential.username.orEmpty())
            val passwordValue =
                AccountDetail(label = "Password", value = credential.password.orEmpty())
            Column(
                verticalArrangement = Arrangement.spacedBy(20.dp), modifier = Modifier
                    .fillMaxWidth()
            ) {
                CredentialDetail(accountDetail = accountType)
                CredentialDetail(accountDetail = usernameEmail)
                CredentialDetail(accountDetail = passwordValue)

            }
            Spacer(modifier = Modifier.height(50.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        onEditCredential.invoke(credential)
                        onCloseBottomSheet.invoke()
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 8.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.Edit, contentDescription = "Edit")
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "Edit")
                    }
                }

                Button(
                    onClick = {
                        onDeleteCredential.invoke(credential)
                        onCloseBottomSheet.invoke()
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 8.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete")
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "Delete")
                    }
                }
            }


        }
    }
}