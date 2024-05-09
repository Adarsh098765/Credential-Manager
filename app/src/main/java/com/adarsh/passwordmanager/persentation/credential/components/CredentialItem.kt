package com.adarsh.passwordmanager.persentation.credential.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adarsh.passwordmanager.domain.model.Credential

@Composable
fun CredentialItem(
    modifier: Modifier = Modifier,
    credential: Credential,
    itemClicked: (Credential) -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp, horizontal = 10.dp)
            .clickable {
                itemClicked.invoke(credential)
            },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(50.dp)
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = credential.website,
                fontSize = 20.sp,
                fontFamily = FontFamily.SansSerif,
            )
            Spacer(modifier = modifier.padding(start = 10.dp))
            Text(
                text = "*".repeat(credential.password.length),
                fontSize = 18.sp,
                letterSpacing = 1.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Gray.copy(alpha = 0.5f)
            )
            Spacer(modifier = modifier.weight(1f))
            Image(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = ""
            )
        }
    }
}