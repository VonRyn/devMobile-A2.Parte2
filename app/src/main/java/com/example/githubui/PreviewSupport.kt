package com.example.githubui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.githubui.ui.theme.GithubuiTheme

// Usado somente pelos @Preview. O aplicativo continua iniciando com listas vazias.
@Composable
internal fun GithubPreview(content: @Composable (GithubViewModel) -> Unit) {
    val model = remember {
        GithubViewModel().apply {
            salvarRepositorio(null, "meu-app", "Aplicativo de estudos com Kotlin e Jetpack Compose.", "Kotlin", false)
            salvarRepositorio(null, "anotacoes", "Anotações das aulas de desenvolvimento mobile.", "Markdown", true)
            adicionarIssue(1, "Criar navegação", "Conectar as telas do aplicativo com Navigation Compose.")
            adicionarIssue(1, "Revisar formulário", "Verificar os campos obrigatórios e as mensagens de erro.")
            alternarIssue(1)
        }
    }
    GithubuiTheme(darkTheme = false, dynamicColor = false) {
        Surface(modifier = Modifier.fillMaxSize()) {
            content(model)
        }
    }
}
