package com.example.githubui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material.icons.outlined.Apartment
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material.icons.outlined.Dashboard
import androidx.compose.material.icons.outlined.Forum
import androidx.compose.material.icons.outlined.MergeType
import androidx.compose.material3.Divider
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
fun HomeScreen() {
    Surface(color = Color.White) {
        Column(Modifier.fillMaxSize()) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 16.dp, top = 20.dp, bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Home",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Ink
                )
                Spacer(Modifier.weight(1f))
                Row(horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                    Icon(Icons.Filled.Search, null, tint = Accent, modifier = Modifier.size(24.dp))
                    Icon(Icons.Filled.Refresh, null, tint = Accent, modifier = Modifier.size(24.dp))
                    Icon(Icons.Filled.AddCircleOutline, null, tint = Accent, modifier = Modifier.size(24.dp))
                    Icon(Icons.Filled.AccountCircle, null, tint = Accent, modifier = Modifier.size(24.dp))
                }
            }

            SectionHeader(title = "Meu Trabalho") {
                Icon(Icons.Filled.MoreHoriz, null, tint = Muted, modifier = Modifier.size(22.dp))
            }

            MenuRow(Icons.Outlined.Circle, Green, "Issues")
            MenuRow(Icons.Outlined.MergeType, Blue, "Solicitações de pull")
            MenuRow(Icons.Outlined.Forum, Magenta, "Discussões")
            MenuRow(Icons.Outlined.Dashboard, Ink, "Projetos")
            MenuRow(Icons.Outlined.Book, Ink, "Repositórios Principais")
            MenuRow(Icons.Outlined.Apartment, Orange, "Organizações")
            MenuRow(Icons.Filled.StarBorder, Yellow, "Classificado com estrela")

            Spacer(Modifier.height(8.dp))
            Divider(color = Line, thickness = 1.dp)

            SectionHeader(title = "Favoritos")

            Spacer(Modifier.weight(1f))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .border(1.dp, Line, RoundedCornerShape(6.dp))
                    .padding(vertical = 14.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "ADICIONAR AOS FAVORITOS",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    color = LinkBlue
                )
            }

            Spacer(Modifier.height(16.dp))
            BottomBar()
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 720)
@Composable
private fun HomeScreenPreview() {
    MaterialTheme { HomeScreen() }
}
