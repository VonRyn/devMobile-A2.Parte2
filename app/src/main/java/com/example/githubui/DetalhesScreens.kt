package com.example.githubui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DetalhesIssueScreen(model: GithubViewModel, id: Int, onRepositorio: (Int) -> Unit, onExcluido: () -> Unit, onEditar: (Int) -> Unit) {
    val issue = model.issues.find { it.id == id }
    var confirmar by remember { mutableStateOf(false) }
    if (issue == null) { Text("Issue não encontrada.", modifier = Modifier.padding(20.dp)); return }
    val repo = model.repositorios.find { it.id == issue.repositorioId }
    LazyColumn(Modifier.fillMaxSize(), contentPadding = PaddingValues(20.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        item {
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text("Issue #${issue.id}", style = MaterialTheme.typography.labelLarge)
                    Text(issue.titulo, style = MaterialTheme.typography.headlineSmall)
                    Text(issue.descricao.ifBlank { "Sem descrição" })
                    Text("Repositório: ${repo?.nome ?: "Removido"}")
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(issue.concluida, { model.alternarIssue(id) })
                        Text(if (issue.concluida) "Concluída" else "Aberta")
                    }
                }
            }
        }
        item { Button(onClick = { onRepositorio(issue.repositorioId) }, enabled = repo != null) { Text("Ver repositório") } }
        item { OutlinedButton(onClick = { onEditar(id) }) { Text("Editar issue") } }
        item { OutlinedButton(onClick = { confirmar = true }) { Text("Excluir issue") } }
    }
    if (confirmar) ConfirmarExclusao("Excluir issue?", issue.titulo, { confirmar = false }) {
        model.issues.removeAll { it.id == id }
        confirmar = false
        onExcluido()
    }
}

@Preview(name = "Detalhes da issue", showBackground = true, widthDp = 360, heightDp = 720)
@Composable
private fun DetalhesIssueScreenPreview() {
    GithubPreview { model ->
        DetalhesIssueScreen(model, id = 2, onRepositorio = {}, onExcluido = {}, onEditar = {})
    }
}

