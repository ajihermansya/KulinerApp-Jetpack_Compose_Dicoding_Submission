package com.example.kulinerapp.home.navigation_fragment

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kulinerapp.R

@Composable
@Preview
fun ScreenProfile() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize(),
        content = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(16.dp)
            ) {

                Image(
                    painter = painterResource(R.drawable.profile_ajii),
                    contentDescription = null,
                    modifier = Modifier
                        .size(200.dp)
                        .clip(CircleShape)
                        .border(4.dp, Color.White, CircleShape)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Mohamad Aji Hermansya",
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onBackground
                )
                Text(
                    text = "ajihermansya14@gmail.com",
                    color = MaterialTheme.colors.onBackground.copy(alpha = 0.8f)
                )
            }
        }
    )
}
