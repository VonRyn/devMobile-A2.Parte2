package com.example.githubui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DetalhesRepositorioScreen(
    model: GithubViewModel,
    id: Int,
    onEditar: (Int) -> Unit,
    onIssues: (Int) -> Unit,
    onExcluido: () -> Unit,
    onVoltar: () -> Unit,
    onNovaIssue: (Int) -> Unit = onIssues
) {
    val repo = model.repositorios.find { it.id == id }
    var menu by remember { mutableStateOf(false) }
    var confirmar by remember { mutableStateOf(false) }
    var estrela by rememberSaveable(id) { mutableStateOf(false) }
    var notificacoes by rememberSaveable(id) { mutableStateOf(false) }
    var mostrarCodigo by rememberSaveable(id) { mutableStateOf(false) }
    Column(Modifier.fillMaxSize().background(Color.White)) {
        Row(Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 6.dp), verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onVoltar) { Icon(Icons.AutoMirrored.Filled.ArrowBack, "Voltar", tint = Ink) }
            Spacer(Modifier.weight(1f))
            if (repo != null) {
                IconButton(onClick = { onNovaIssue(id) }) { Icon(Icons.Outlined.AddCircleOutline, "Nova issue", tint = LinkBlue) }
                Box {
                    IconButton(onClick = { menu = true }) { Icon(Icons.Outlined.MoreVert, "Opções do repositório", tint = LinkBlue) }
                    DropdownMenu(expanded = menu, onDismissRequest = { menu = false }) {
                        DropdownMenuItem(text = { Text("Editar repositório") }, onClick = { menu = false; onEditar(id) })
                        DropdownMenuItem(text = { Text("Excluir repositório") }, onClick = { menu = false; confirmar = true })
                    }
                }
            }
        }
        if (repo == null) {
            Text("Repositório não encontrado.", color = Muted, modifier = Modifier.padding(18.dp))
        } else {
            Column(Modifier.fillMaxWidth().weight(1f).verticalScroll(rememberScrollState())) {
                Column(Modifier.fillMaxWidth().background(Color(0xFFFAFAFA)).padding(18.dp), verticalArrangement = Arrangement.spacedBy(18.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(Modifier.size(22.dp).background(Color(0xFFF0F1F3), CircleShape), contentAlignment = Alignment.Center) {
                            Icon(Icons.Outlined.Person, null, tint = Muted, modifier = Modifier.size(18.dp))
                        }
                        Spacer(Modifier.width(6.dp))
                        Text("<user_name>", color = Muted, fontSize = 16.sp)
                    }
                    Text(repo.nome, color = Color.Black, fontSize = 26.sp, fontWeight = FontWeight.Bold)
                    if (repo.descricao.isNotBlank()) Text(repo.descricao, color = Ink, fontSize = 16.sp)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(if (repo.privado) Icons.Outlined.Lock else Icons.Outlined.Public, null, tint = Muted, modifier = Modifier.size(20.dp))
                        Spacer(Modifier.width(8.dp))
                        Text(if (repo.privado) "Privado" else "Público", color = Muted, fontSize = 16.sp)
                    }
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Icon(Icons.Outlined.StarBorder, null, tint = Muted, modifier = Modifier.size(20.dp))
                        Text(if (estrela) "1 estrela" else "0 estrela", color = Muted, fontSize = 16.sp)
                        Spacer(Modifier.width(4.dp))
                        Icon(Icons.Outlined.AccountTree, null, tint = Muted, modifier = Modifier.size(20.dp))
                        Text("0 bifurcação", color = Muted, fontSize = 16.sp)
                    }
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        OutlinedButton(onClick = { estrela = !estrela }, modifier = Modifier.weight(1f).height(52.dp),
                            shape = RoundedCornerShape(6.dp), border = BorderStroke(1.dp, Line), contentPadding = PaddingValues(8.dp),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = Ink)) {
                            Icon(if (estrela) Icons.Outlined.Star else Icons.Outlined.StarBorder, null, modifier = Modifier.size(20.dp))
                            Spacer(Modifier.width(4.dp))
                            Text(if (estrela) "ESTRELADO" else "ESTRELA", fontSize = 13.sp)
                        }
                        OutlinedButton(onClick = {}, enabled = false, modifier = Modifier.size(width = 58.dp, height = 52.dp),
                            shape = RoundedCornerShape(6.dp), border = BorderStroke(1.dp, Line), contentPadding = PaddingValues(0.dp)) {
                            Icon(Icons.Outlined.AccountTree, "Bifurcação indisponível", tint = Color(0xFFB8BABD), modifier = Modifier.size(20.dp))
                        }
                        OutlinedButton(onClick = { notificacoes = !notificacoes }, modifier = Modifier.size(width = 58.dp, height = 52.dp),
                            shape = RoundedCornerShape(6.dp), border = BorderStroke(1.dp, Line), contentPadding = PaddingValues(0.dp)) {
                            Icon(if (notificacoes) Icons.Outlined.NotificationsActive else Icons.Outlined.NotificationsNone,
                                if (notificacoes) "Desativar notificações" else "Ativar notificações", tint = if (notificacoes) LinkBlue else Ink)
                        }
                    }
                }
                HorizontalDivider(color = Line.copy(alpha = 0.5f))
                Row(Modifier.fillMaxWidth().clickable { onIssues(id) }.padding(horizontal = 18.dp, vertical = 16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Box(Modifier.size(36.dp).background(Color(0xFF67C65D), RoundedCornerShape(4.dp)), contentAlignment = Alignment.Center) {
                        Icon(Icons.Outlined.Adjust, null, tint = Color.White, modifier = Modifier.size(24.dp))
                    }
                    Text("Issues", color = Ink, fontSize = 17.sp, modifier = Modifier.weight(1f).padding(start = 14.dp))
                    Text(model.issues.count { it.repositorioId == id && !it.concluida }.toString(), color = Muted, fontSize = 16.sp)
                }
                HorizontalDivider(color = Line.copy(alpha = 0.5f))
                Row(Modifier.padding(horizontal = 26.dp, vertical = 18.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Outlined.AccountTree, null, tint = Muted, modifier = Modifier.size(20.dp))
                    Column(Modifier.padding(start = 20.dp)) {
                        Text("Branch Atual", color = Muted, fontSize = 14.sp)
                        Text("main", color = Ink, fontSize = 16.sp, fontFamily = FontFamily.Monospace)
                    }
                }
                Row(Modifier.fillMaxWidth().clickable { mostrarCodigo = !mostrarCodigo }.padding(horizontal = 18.dp, vertical = 12.dp), verticalAlignment = Alignment.CenterVertically) {
                    Box(Modifier.size(36.dp).background(Color(0xFFF0F1F6), RoundedCornerShape(4.dp)), contentAlignment = Alignment.Center) {
                        Icon(Icons.Outlined.Code, null, tint = Muted, modifier = Modifier.size(24.dp))
                    }
                    Text("Código", color = Ink, fontSize = 17.sp, modifier = Modifier.padding(start = 14.dp))
                }
                if (mostrarCodigo) Text("Nenhum arquivo.", color = Muted, modifier = Modifier.padding(horizontal = 18.dp, vertical = 12.dp))
            }
        }
    }
    if (confirmar && repo != null) ConfirmarExclusao("Excluir ${repo.nome}?", "O repositório e suas issues serão removidos.", { confirmar = false }) {
        model.excluirRepositorio(id)
        confirmar = false
        onExcluido()
    }
}

@Preview(name = "Repositório acessado", showBackground = true, widthDp = 393, heightDp = 820)
@Composable
private fun DetalhesRepositorioScreenPreview() {
    GithubPreview { model ->
        Column(Modifier.fillMaxSize().background(Color.White)) {
            Box(Modifier.weight(1f)) {
                DetalhesRepositorioScreen(model, id = 1, onEditar = {}, onIssues = {}, onExcluido = {}, onVoltar = {})
            }
            BottomBar()
        }
    }
}
