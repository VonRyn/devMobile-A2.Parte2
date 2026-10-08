package com.example.githubui

import org.junit.Assert.*
import org.junit.Test

class GithubViewModelTest {
    @Test fun historicoRegistraCicloDaIssueEPreservaDadosAntigos() {
        val model = GithubViewModel()
        model.salvarRepositorio(null, "primeiro", "", "", false)
        model.salvarRepositorio(null, "segundo", "", "", false)
        val primeiro = model.repositorios[0].id
        val segundo = model.repositorios[1].id
        model.adicionarIssue(primeiro, "Original", "Descrição original")
        val id = model.issues.single().id
        model.editarIssue(id, segundo, "Atualizada", "Descrição nova")
        model.alternarIssue(id)
        model.alternarIssue(id)
        model.excluirIssue(id)
        val eventos = model.atividades.filter { it.issue != null }
        assertEquals(listOf(AcaoAtividade.EXCLUIDO, AcaoAtividade.REABERTO, AcaoAtividade.CONCLUIDO, AcaoAtividade.EDITADO, AcaoAtividade.CRIADO), eventos.map { it.acao })
        assertEquals("Original", eventos.last().issue?.titulo)
        assertEquals("Descrição original", eventos.last().issue?.descricao)
        assertEquals("primeiro", eventos.last().repositorio.nome)
        assertEquals("segundo", eventos.first().repositorio.nome)
        assertEquals(true, eventos[2].issue?.concluida)
        assertEquals(false, eventos[1].issue?.concluida)
        assertTrue(model.issues.isEmpty())
        assertEquals(model.atividades.size, model.atividades.map { it.id }.distinct().size)
        assertTrue(model.atividades.zipWithNext().all { (a, b) -> a.id > b.id })
    }

    @Test fun historicoNaoDuplicaEdicaoSemMudancasNemRegistraOperacoesInvalidas() {
        val model = GithubViewModel()
        model.salvarRepositorio(null, "app", "", "", false)
        val repoId = model.repositorios.single().id
        model.adicionarIssue(repoId, "Original", "Descrição")
        val id = model.issues.single().id
        val antes = model.atividades.toList()
        model.editarIssue(id, repoId, " Original ", "Descrição")
        model.editarIssue(id, repoId, " ", "")
        model.adicionarIssue(999, "Inválida", "")
        model.adicionarIssue(repoId, " ", "")
        model.excluirIssue(999)
        model.alternarIssue(999)
        assertEquals(antes, model.atividades.toList())
    }

    @Test fun excluirRepositorioRegistraExclusaoDasIssuesRelacionadas() {
        val model = GithubViewModel()
        model.salvarRepositorio(null, "app", "", "", false)
        val repoId = model.repositorios.single().id
        model.adicionarIssue(repoId, "Primeira", "")
        model.adicionarIssue(repoId, "Segunda", "")
        model.excluirRepositorio(repoId)
        val excluidos = model.atividades.filter { it.acao == AcaoAtividade.EXCLUIDO }
        assertEquals(3, excluidos.size)
        assertNull(excluidos.first().issue)
        assertEquals(setOf("Primeira", "Segunda"), excluidos.mapNotNull { it.issue?.titulo }.toSet())
        assertTrue(model.issues.isEmpty())
    }

    @Test fun editarIssuePreservaIdEConclusaoAoTrocarRepositorio() {
        val model = GithubViewModel()
        model.salvarRepositorio(null, "primeiro", "", "", false)
        model.salvarRepositorio(null, "segundo", "", "", false)
        val primeiro = model.repositorios[0].id
        val segundo = model.repositorios[1].id
        model.adicionarIssue(primeiro, "Título antigo", "Descrição antiga")
        val id = model.issues.single().id
        model.alternarIssue(id)
        assertNull(model.editarIssue(id, segundo, " Novo título ", " Nova descrição "))
        assertEquals(Issue(id, segundo, "Novo título", "Nova descrição", true), model.issues.single())
        model.excluirRepositorio(primeiro)
        assertEquals(id, model.issues.single().id)
    }

    @Test fun editarIssueInvalidaNaoAlteraDados() {
        val model = GithubViewModel()
        model.salvarRepositorio(null, "app", "", "", false)
        val repoId = model.repositorios.single().id
        model.adicionarIssue(repoId, "Original", "Descrição")
        val original = model.issues.single()
        assertNotNull(model.editarIssue(original.id, repoId, " ", ""))
        assertNotNull(model.editarIssue(original.id, 999, "Novo", ""))
        assertNotNull(model.editarIssue(999, repoId, "Novo", ""))
        assertNotNull(model.editarIssue(original.id, repoId, "a".repeat(101), ""))
        assertNotNull(model.editarIssue(original.id, repoId, "Novo", "a".repeat(351)))
        assertEquals(original, model.issues.single())
    }

    @Test fun validarPrimeiraEtapaNaoCriaRepositorioENemAtividade() {
        val model = GithubViewModel()
        assertNull(model.validarRepositorio(null, "novo", "Descrição"))
        assertTrue(model.repositorios.isEmpty())
        assertTrue(model.atividades.isEmpty())
        assertNotNull(model.salvarRepositorio(null, "a".repeat(101), "", "", false))
        assertNotNull(model.salvarRepositorio(null, "novo", "a".repeat(351), "", false))
        assertTrue(model.repositorios.isEmpty())
        assertNull(model.salvarRepositorio(null, "a".repeat(100), "a".repeat(350), "Kotlin", true))
        assertEquals(1, model.atividades.size)
        assertTrue(model.repositorios.single().privado)
    }

    @Test fun historicoPreservaDadosEOrdenaEventosMaisRecentesPrimeiro() {
        val model = GithubViewModel()
        assertTrue(model.atividades.isEmpty())
        model.salvarRepositorio(null, "original", "Descrição inicial", "Kotlin", false)
        val id = model.repositorios.single().id
        model.salvarRepositorio(id, "renomeado", "Descrição nova", "Java", true)
        model.excluirRepositorio(id)
        assertEquals(listOf(AcaoAtividade.EXCLUIDO, AcaoAtividade.EDITADO, AcaoAtividade.CRIADO), model.atividades.map { it.acao })
        assertEquals("original", model.atividades.last().repositorio.nome)
        assertEquals("Descrição inicial", model.atividades.last().repositorio.descricao)
        assertEquals("renomeado", model.atividades.first().repositorio.nome)
        assertTrue(model.repositorios.isEmpty())
    }

    @Test fun historicoNaoRegistraFalhasNemEdicoesSemAlteracao() {
        val model = GithubViewModel()
        model.salvarRepositorio(null, "", "", "", false)
        model.excluirRepositorio(999)
        assertTrue(model.atividades.isEmpty())
        model.salvarRepositorio(null, "app", "", "", false)
        val id = model.repositorios.single().id
        model.salvarRepositorio(id, "app", "", "", false)
        model.salvarRepositorio(null, "app", "", "", false)
        model.salvarRepositorio(999, "outro", "", "", false)
        assertEquals(1, model.atividades.size)
    }

    @Test fun repositoriosComecamVaziosEValidamNome() {
        val model = GithubViewModel()
        assertTrue(model.repositorios.isEmpty())
        assertNotNull(model.salvarRepositorio(null, "", "", "", false))
        assertNull(model.salvarRepositorio(null, " app-kotlin ", "Descrição", "Kotlin", false))
        assertEquals("app-kotlin", model.repositorios.single().nome)
        assertNotNull(model.salvarRepositorio(null, "APP-KOTLIN", "", "", false))
        assertEquals(1, model.repositorios.size)
    }
    @Test fun editarPreservaIdentidadeEExcluirRemoveSomenteIssuesVinculadas() {
        val model = GithubViewModel()
        model.salvarRepositorio(null, "primeiro", "", "", false)
        model.salvarRepositorio(null, "segundo", "", "", false)
        val primeiro = model.repositorios[0].id
        val segundo = model.repositorios[1].id
        assertFalse(model.adicionarIssue(primeiro, " ", ""))
        assertFalse(model.adicionarIssue(999, "Inválida", ""))
        assertTrue(model.adicionarIssue(primeiro, "Navegação", "Conectar telas"))
        model.adicionarIssue(segundo, "Outra", "")
        model.alternarIssue(model.issues[0].id)
        assertTrue(model.issues[0].concluida)
        assertNull(model.salvarRepositorio(primeiro, "renomeado", "Nova descrição", "Kotlin", true))
        assertEquals(primeiro, model.repositorios[0].id)
        assertTrue(model.repositorios[0].privado)
        model.excluirRepositorio(primeiro)
        assertEquals(segundo, model.repositorios.single().id)
        assertEquals(segundo, model.issues.single().repositorioId)
        model.salvarRepositorio(null, "terceiro", "", "", false)
        assertTrue(model.repositorios.last().id > segundo)
    }
}

