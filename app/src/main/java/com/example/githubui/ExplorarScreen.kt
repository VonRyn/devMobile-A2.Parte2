package com.example.githubui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.Lightbulb
import androidx.compose.material.icons.outlined.Reorder
import androidx.compose.material.icons.outlined.WbSunny
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ExplorarScreen() {
    Surface(color = Color.White) {
        Column(Modifier.fillMaxSize()) {

            Text(
                text = "Explorar",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Ink,
                modifier = Modifier.padding(start = 20.dp, top = 20.dp, bottom = 4.dp)
            )

            SectionHeader(title = "Descubra") {
                Icon(Icons.Filled.MoreHoriz, null, tint = Muted, modifier = Modifier.size(22.dp))
            }

            MenuRow(Icons.Outlined.WbSunny, Red, "Repositórios em Alta")
            MenuRow(Icons.Outlined.Lightbulb, Indigo, "Listas Incríveis")

            SectionHeader(title = "Atividade") {
                Icon(Icons.Outlined.Reorder, null, tint = Ink, modifier = Modifier.size(22.dp))
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 8.dp),
                verticalAlignment = Alignment.Top
            ) {
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .background(Color(0xFFF0F3F6), CircleShape)
                        .border(1.dp, Line, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Filled.Person,
                        contentDescription = null,
                        tint = Blue,
                        modifier = Modifier.size(18.dp)
                    )
                }
                Spacer(Modifier.width(12.dp))
                Text(
                    text = buildAnnotatedString {
                        pushStyle(SpanStyle(color = Ink, fontWeight = FontWeight.Bold))
                        append("mfrickss")
                        pop()
                        append(" classificou um repositório com estrela")
                    },
                    fontSize = 14.sp,
                    color = Muted,
                    lineHeight = 19.sp
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 8.dp)
                    .border(1.dp, Line, RoundedCornerShape(8.dp))
                    .padding(14.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    BadgeIcon(icon = Icons.Outlined.Book, tint = Accent, modifier = Modifier.size(22.dp))
                    Spacer(Modifier.width(10.dp))
                    Text(
                        text = "mattpocock / skills",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Ink
                    )
                }

                Spacer(Modifier.height(10.dp))
                Text(
                    text = "Skills for real Enginners.",
                    fontSize = 13.sp,
                    color = Muted
                )

                Spacer(Modifier.height(14.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Filled.Star,
                        contentDescription = null,
                        tint = Ink,
                        modifier = Modifier.size(15.dp)
                    )
                    Spacer(Modifier.width(6.dp))
                    Text(text = "246,7k", fontSize = 13.sp, color = Ink)
                }

                Spacer(Modifier.height(14.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(BorderStroke(1.dp, Line), RoundedCornerShape(6.dp))
                        .padding(vertical = 12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Filled.StarBorder,
                            contentDescription = null,
                            tint = Ink,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(
                            text = "ESTRELA",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium,
                            color = Ink
                        )
                    }
                }
            }

            Spacer(Modifier.weight(1f))
            BottomBar()
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 720)
@Composable
private fun ExplorarScreenPreview() {
    MaterialTheme { ExplorarScreen() }
}
