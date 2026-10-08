package com.example.githubui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

data class Repositorio(val id: Int, val nome: String, val descricao: String, val linguagem: String, val privado: Boolean)
data class Issue(val id: Int, val repositorioId: Int, val titulo: String, val descricao: String, val concluida: Boolean = false)

enum class AcaoAtividade(val texto: String, val filtro: String) {
    CRIADO("criou", "Criados"),
    EDITADO("editou", "Editados"),
    EXCLUIDO("excluiu", "Excluídos"),
    CONCLUIDO("concluiu", "Concluídas"),
    REABERTO("reabriu", "Reabertas")
}

data class Atividade(
    val id: Int,
    val repositorio: Repositorio,
    val acao: AcaoAtividade,
    val instante: Long = System.currentTimeMillis(),
    val issue: Issue? = null
)

// Estado compartilhado pelas telas. Não usa banco de dados nem arquivos.
class GithubViewModel : ViewModel() {
    val repositorios = mutableStateListOf<Repositorio>()
    val issues = mutableStateListOf<Issue>()
    val atividades = mutableStateListOf<Atividade>()
    private var proximoRepo = 1
    private var proximaIssue = 1
    private var proximaAtividade = 1

    private fun registrar(repo: Repositorio, acao: AcaoAtividade) {
        atividades.add(0, Atividade(proximaAtividade++, repo, acao))
    }

    private fun registrarIssue(issue: Issue, acao: AcaoAtividade) {
        val repo = repositorios.find { it.id == issue.repositorioId } ?: return
        atividades.add(0, Atividade(proximaAtividade++, repo, acao, issue = issue))
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
            registrar(repo, AcaoAtividade.CRIADO)
        }
        else {
            val indice = repositorios.indexOfFirst { it.id == id }
            if (indice < 0) return "Repositório não encontrado."
            val atualizado = Repositorio(id, nomeLimpo, descricao.trim(), linguagem.trim(), privado)
            if (repositorios[indice] != atualizado) {
                repositorios[indice] = atualizado
                registrar(atualizado, AcaoAtividade.EDITADO)
            }
        }
        return null
    }
    fun excluirRepositorio(id: Int) {
        val repo = repositorios.find { it.id == id } ?: return
        issues.filter { it.repositorioId == id }.forEach { excluirIssue(it.id) }
        registrar(repo, AcaoAtividade.EXCLUIDO)
        repositorios.removeAll { it.id == id }
    }
    fun adicionarIssue(repoId: Int, titulo: String, descricao: String): Boolean {
        if (titulo.isBlank() || repositorios.none { it.id == repoId }) return false
        val issue = Issue(proximaIssue++, repoId, titulo.trim(), descricao.trim())
        issues.add(issue)
        registrarIssue(issue, AcaoAtividade.CRIADO)
        return true
    }
    fun alternarIssue(id: Int) {
        val indice = issues.indexOfFirst { it.id == id }
        if (indice >= 0) {
            val atualizada = issues[indice].copy(concluida = !issues[indice].concluida)
            issues[indice] = atualizada
            registrarIssue(atualizada, if (atualizada.concluida) AcaoAtividade.CONCLUIDO else AcaoAtividade.REABERTO)
        }
    }

    fun excluirIssue(id: Int) {
        val issue = issues.find { it.id == id } ?: return
        registrarIssue(issue, AcaoAtividade.EXCLUIDO)
        issues.removeAll { it.id == id }
    }

    fun editarIssue(id: Int, repositorioId: Int, titulo: String, descricao: String): String? {
        val indice = issues.indexOfFirst { it.id == id }
        if (indice < 0) return "Issue não encontrada."
        if (repositorios.none { it.id == repositorioId }) return "Selecione um repositório válido."
        if (titulo.isBlank()) return "Informe o título da issue."
        if (titulo.length > 100) return "O título deve ter no máximo 100 caracteres."
        if (descricao.length > 350) return "A descrição deve ter no máximo 350 caracteres."
        val atualizada = issues[indice].copy(repositorioId = repositorioId, titulo = titulo.trim(), descricao = descricao.trim())
        if (issues[indice] != atualizada) {
            issues[indice] = atualizada
            registrarIssue(atualizada, AcaoAtividade.EDITADO)
        }
        return null
    }
}


