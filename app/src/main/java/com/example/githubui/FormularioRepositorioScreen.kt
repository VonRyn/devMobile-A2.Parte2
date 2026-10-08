package com.example.githubui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun EditarRepositorioScreen(model: GithubViewModel, id: Int, onFechar: () -> Unit, onSalvo: () -> Unit) {
    val repo = model.repositorios.find { it.id == id }
    if (repo == null) {
        Column(Modifier.padding(20.dp)) {
            Text("Repositório não encontrado.")
            TextButton(onClick = onFechar) { Text("Voltar") }
        }
    } else FormularioRepositorio(model, repo, onFechar, onSalvo)
}

@Preview(name = "Editar repositório", showBackground = true, widthDp = 393, heightDp = 820)
@Composable
private fun EditarRepositorioScreenPreview() {
    GithubPreview { model -> EditarRepositorioScreen(model, id = 1, onFechar = {}, onSalvo = {}) }
}
