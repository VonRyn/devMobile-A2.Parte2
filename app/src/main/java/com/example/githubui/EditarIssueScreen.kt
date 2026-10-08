package com.example.githubui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun EditarIssueScreen(model: GithubViewModel, id: Int, onFechar: () -> Unit, onSalvo: () -> Unit) {
    val issue = model.issues.find { it.id == id }
    if (issue == null) {
        Column(Modifier.padding(20.dp)) {
            Text("Issue não encontrada.")
            TextButton(onClick = onFechar) { Text("Voltar") }
        }
    } else FormularioIssue(model, issue.repositorioId, issue, onFechar) { onSalvo() }
}

@Preview(name = "Editar issue", showBackground = true, widthDp = 393, heightDp = 820)
@Composable
private fun EditarIssueScreenPreview() {
    GithubPreview { model -> EditarIssueScreen(model, id = 1, onFechar = {}, onSalvo = {}) }
}
