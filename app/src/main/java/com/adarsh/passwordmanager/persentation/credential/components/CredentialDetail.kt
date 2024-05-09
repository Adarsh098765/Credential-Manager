package com.adarsh.passwordmanager.persentation.credential.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adarsh.passwordmanager.R

data class AccountDetail(
    val label: String,
    val value: String
)

@Composable
fun CredentialDetail(
    modifier: Modifier = Modifier,
    accountDetail: AccountDetail,
    onPasswordVisibilityToggle: () -> Unit = {}
) {
    val showPassword = remember {
        mutableStateOf(false)
    }
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(9.dp)) {
        Text(text = accountDetail.label, color = Color.Gray.copy(alpha = 0.5f), fontSize = 14.sp)
        if (accountDetail.label == "Password") {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().clickable { onPasswordVisibilityToggle() }
            ) {
                Text(
                    text = if (showPassword.value) accountDetail.value else "*".repeat(accountDetail.value.length),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    if (showPassword.value) painterResource(id = R.drawable.show) else painterResource(
                        id = R.drawable.hide
                    ),
                    contentDescription = "Toggle password visibility",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(30.dp).padding(end = 10.dp).clickable {
                        showPassword.value = !showPassword.value
                    }
                )
            }
        } else {
            Text(text = accountDetail.value, fontSize = 17.sp, fontWeight = FontWeight.Bold)
        }
    }
}