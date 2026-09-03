package com.example.githubui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.RadioButtonUnchecked
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//Cores

internal val Accent = Color(0xFF1BB8D8)
internal val LinkBlue = Color(0xFF1E88E5)
internal val Ink = Color(0xFF1F2328)
internal val Muted = Color(0xFF6E7781)
internal val Line = Color(0xFFD8DEE4)
internal val Green = Color(0xFF2DA44E)
internal val Blue = Color(0xFF2F81F7)
internal val Magenta = Color(0xFFD62BC9)
internal val Orange = Color(0xFFF08A24)
internal val Yellow = Color(0xFFE3B341)
internal val Red = Color(0xFFE5484D)
internal val Indigo = Color(0xFF6366F1)


@Composable
internal fun BadgeIcon(
    icon: ImageVector,
    tint: Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(30.dp)
            .border(1.5.dp, tint, RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = tint,
            modifier = Modifier.size(17.dp)
        )
    }
}

//Menu
@Composable
internal fun MenuRow(
    icon: ImageVector,
    tint: Color,
    label: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BadgeIcon(icon = icon, tint = tint)
        Spacer(Modifier.width(16.dp))
        Text(
            text = label,
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            color = Ink
        )
    }
}

//Header
@Composable
internal fun SectionHeader(
    title: String,
    modifier: Modifier = Modifier,
    trailing: (@Composable () -> Unit)? = null
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            color = Ink
        )
        Spacer(Modifier.weight(1f))
        trailing?.invoke()
    }
}

//Navbar
@Composable
internal fun BottomBar() {
    Column {
        Divider(color = Line, thickness = 1.dp)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 14.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            BottomBarItem(Icons.Outlined.Home, "Home")
            BottomBarItem(Icons.Outlined.Email, "Inbox")
            BottomBarItem(Icons.Outlined.Explore, "Explorar")
            BottomBarItem(Icons.Outlined.RadioButtonUnchecked, "Copilot")
        }
    }
}

@Composable
private fun BottomBarItem(icon: ImageVector, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Ink,
            modifier = Modifier.size(24.dp)
        )
        Spacer(Modifier.height(4.dp))
        Text(text = label, fontSize = 11.sp, color = Ink)
    }
}
