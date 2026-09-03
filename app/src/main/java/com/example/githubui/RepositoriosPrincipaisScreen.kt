package com.example.githubui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RepositoriosScreen() {
    Surface(color = Color.White) {
        Column(Modifier.fillMaxSize()) {

            // Topo: voltar + título
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 20.dp, top = 20.dp, bottom = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                    tint = Ink,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(Modifier.width(14.dp))
                Text(
                    text = "Repositórios Principais",
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold,
                    color = Ink
                )
            }

            Row(
                modifier = Modifier
                    .padding(start = 20.dp, bottom = 12.dp)
                    .border(1.dp, Line, RoundedCornerShape(6.dp))
                    .padding(start = 12.dp, end = 6.dp, top = 5.dp, bottom = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Todas", fontSize = 13.sp, color = Ink)
                Icon(
                    Icons.Filled.ArrowDropDown,
                    contentDescription = null,
                    tint = Ink,
                    modifier = Modifier.size(20.dp)
                )
            }

            RepoRow(owner = "VonRyn", name = "VonRyn", avatarTint = Blue)
            RepoRow(owner = "Agencia WebTrip", name = "fatWintour.Books", avatarTint = Muted)
            RepoRow(owner = "VonRyn", name = "WintourDB", avatarTint = Blue)

            Spacer(Modifier.weight(1f))
            BottomBar()
        }
    }
}

@Composable
private fun RepoRow(owner: String, name: String, avatarTint: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(34.dp)
                .background(Color(0xFFF0F3F6), CircleShape)
                .border(1.dp, Line, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                Icons.Filled.Person,
                contentDescription = null,
                tint = avatarTint,
                modifier = Modifier.size(20.dp)
            )
        }
        Spacer(Modifier.width(14.dp))
        Column {
            Text(text = owner, fontSize = 12.sp, color = Muted)
            Text(
                text = name,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Ink
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 720)
@Composable
private fun RepositoriosScreenPreview() {
    MaterialTheme { RepositoriosScreen() }
}
