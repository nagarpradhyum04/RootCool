package com.example.rootcool

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun UploadCard(
    title: String,
    imageUri: Uri?,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .width(160.dp)
            .height(170.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    onClick()
                }
                .padding(12.dp),

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = title,
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            if (imageUri != null) {

                AsyncImage(
                    model = imageUri,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "✅ Added",
                    color = MaterialTheme.colorScheme.primary
                )

            } else {

                Text(
                    text = "📷 Tap To Add",
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}