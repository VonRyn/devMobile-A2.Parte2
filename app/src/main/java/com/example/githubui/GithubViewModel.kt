package com.example.githubui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

data class Repositorio(val id: Int, val nome: String, val descricao: String, val linguagem: String, val privado: Boolean)
data class Issue(val id: Int, val repositorioId: Int, val titulo: String, val descricao: String, val concluida: Boolean = false)

enum class AcaoRepositorio(val texto: String, val filtro: String) {
    CRIADO("criou um repositório", "Criados"),
    EDITADO("editou um repositório", "Editados"),
    EXCLUIDO("excluiu um repositório", "Excluídos")
}

data class AtividadeRepositorio(
    val id: Int,
    val repositorio: Repositorio,
    val acao: AcaoRepositorio,
    val instante: Long = System.currentTimeMillis()
)

// Estado compartilhado pelas telas. Não usa banco de dados nem arquivos.
class GithubViewModel : ViewModel() {
    val repositorios = mutableStateListOf<Repositorio>()
    val issues = mutableStateListOf<Issue>()
    val atividades = mutableStateListOf<AtividadeRepositorio>()
    private var proximoRepo = 1
    private var proximaIssue = 1
    private var proximaAtividade = 1

    private fun registrar(repo: Repositorio, acao: AcaoRepositorio) {
        atividades.add(0, AtividadeRepositorio(proximaAtividade++, repo, acao))
    }

    fun validarRepositorio(id: Int?, nome: String, descricao: String): String? {
        val nomeLimpo = nome.trim()
        if (nomeLimpo.isEmpty()) return "Informe o nome do repositório."
        if (nome.length > 100) return "O nome deve ter no máximo 100 caracteres."
        if (descricao.length > 350) return "A descrição deve ter no máximo 350 caracteres."
        if (!nomeLimpo.matches(Regex("[A-Za-z0-9._-]+"))) return "Use letras, números, ponto, hífen ou sublinhado no nome."
        if (repositorios.any { it.id != id && it.nome.equals(nomeLimpo, ignoreCase = true) }) return "Já existe um repositório com esse nome."
        return null
    }

    fun salvarRepositorio(id: Int?, nome: String, descricao: String, linguagem: String, privado: Boolean): String? {
        validarRepositorio(id, nome, descricao)?.let { return it }
        val nomeLimpo = nome.trim()
        if (id == null) {
            val repo = Repositorio(proximoRepo++, nomeLimpo, descricao.trim(), linguagem.trim(), privado)
            repositorios.add(repo)
            registrar(repo, AcaoRepositorio.CRIADO)
        }
        else {
            val indice = repositorios.indexOfFirst { it.id == id }
            if (indice < 0) return "Repositório não encontrado."
            val atualizado = Repositorio(id, nomeLimpo, descricao.trim(), linguagem.trim(), privado)
            if (repositorios[indice] != atualizado) {
                repositorios[indice] = atualizado
                registrar(atualizado, AcaoRepositorio.EDITADO)
            }
        }
        return null
    }
    fun excluirRepositorio(id: Int) {
        val repo = repositorios.find { it.id == id } ?: return
        registrar(repo, AcaoRepositorio.EXCLUIDO)
        repositorios.removeAll { it.id == id }
        issues.removeAll { it.repositorioId == id }
    }
    fun adicionarIssue(repoId: Int, titulo: String, descricao: String): Boolean {
        if (titulo.isBlank() || repositorios.none { it.id == repoId }) return false
        issues.add(Issue(proximaIssue++, repoId, titulo.trim(), descricao.trim()))
        return true
    }
    fun alternarIssue(id: Int) {
        val indice = issues.indexOfFirst { it.id == id }
        if (indice >= 0) issues[indice] = issues[indice].copy(concluida = !issues[indice].concluida)
    }

    fun editarIssue(id: Int, repositorioId: Int, titulo: String, descricao: String): String? {
        val indice = issues.indexOfFirst { it.id == id }
        if (indice < 0) return "Issue não encontrada."
        if (repositorios.none { it.id == repositorioId }) return "Selecione um repositório válido."
        if (titulo.isBlank()) return "Informe o título da issue."
        if (titulo.length > 100) return "O título deve ter no máximo 100 caracteres."
        if (descricao.length > 350) return "A descrição deve ter no máximo 350 caracteres."
        issues[indice] = issues[indice].copy(repositorioId = repositorioId, titulo = titulo.trim(), descricao = descricao.trim())
        return null
    }
}

