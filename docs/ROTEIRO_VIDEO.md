# Roteiro do vídeo — Trabalho 2

**Apresentador:** Christian Gustavo Von Ryn  
**Duração sugerida:** 5 a 7 minutos  
**Objetivo:** mostrar o aplicativo funcionando e explicar a evolução e as decisões do projeto.

As falas abaixo são uma sugestão para adaptar às próprias palavras. Os tempos são aproximados: priorize mostrar o resultado de cada ação com clareza.

## Preparação

- Grave a tela do aparelho ou emulador com sua voz. Se preferir, grave a voz depois.
- Tenha os prints antigos separados para a primeira cena. Eles devem ser do seu Trabalho 1, não do aplicativo oficial do GitHub.
- Comece com as listas vazias. Se necessário, encerre o processo do app e abra novamente antes de gravar. Não faça isso durante a demonstração, pois os dados estão em memória.
- Ative Não Perturbe para evitar notificações pessoais na gravação.
- Deixe o Android Studio aberto em `AppNavigation.kt` e `GithubViewModel.kt` para a explicação final.
- Faça uma passagem de ensaio seguindo a ordem abaixo. Depois reinicie os dados para a gravação definitiva.

### Dados de exemplo

| Item | Nome ou título | Descrição |
| --- | --- | --- |
| Repositório A | `app-faculdade` | Aplicativo para organizar as atividades da faculdade. |
| Repositório B | `projeto-teste` | Repositório de demonstração para testar a exclusão. |
| Issue A1, no A | Criar tela de login | Organizar os campos de e-mail e senha. |
| Issue A2, no A | Revisar navegação | Conferir os botões de voltar e os atalhos. |
| Issue B1, no B | Testar cadastro | Verificar os campos obrigatórios. |

A issue “Criar tela de login” é somente um exemplo de tarefa cadastrada; não significa que o aplicativo tenha uma tela de login.

## Cena 1 — Apresentação e Trabalho 1 | 0:00–0:40

**Mostrar:** prints antigos de Home, Explorar e Repositórios Principais. Depois abrir a Home atual.

**Fala sugerida:**

> Meu nome é Christian Gustavo Von Ryn e este é o meu Trabalho 2, um aplicativo Android feito em Kotlin com Jetpack Compose e inspirado no GitHub. No Trabalho 1, eu tinha três telas com dados fixos e foco na aparência. Agora as telas estão conectadas e os dados são cadastrados e alterados durante o uso. A Home ficou concentrada em repositórios e issues.

Se não tiver os prints antigos, explique essa diferença na fala sem apresentar imagens atuais como evidência da versão anterior.

**Print opcional:** Home atual com o menu + aberto, para `01-home.png`.

## Cena 2 — Cadastro e lista de repositórios | 0:40–1:30

**Ações:**

1. Na Home, tocar em + e escolher Criar repositório.
2. Preencher nome e descrição do repositório A.
3. Tocar em Avançar, selecionar Público, informar Kotlin e tocar em Criar.
4. Abrir Repositórios Principais e mostrar o item na lista.
5. Pelo + da lista, criar o repositório B, também com linguagem Kotlin.
6. Mostrar os dois itens cadastrados.

**Fala sugerida:**

> Separei o cadastro da listagem para manter a tela simples. O primeiro passo recebe nome e descrição; o segundo define a visibilidade e a linguagem. Ao salvar, o repositório aparece na lista. As informações ficam no ViewModel e a lista reativa atualiza a interface.

**Prints opcionais:** formulário preenchido (`02-criar-repositorio.png`) e lista com dois itens (`03-lista-repositorios.png`).

## Cena 3 — Detalhes e edição | 1:30–2:05

**Ações:**

1. Abrir o repositório A.
2. Mostrar o nome e a descrição corretos.
3. Abrir o menu de três pontos e escolher Editar repositório.
4. Alterar a descrição para “Aplicativo de estudos em Kotlin e Compose.”, tocar em Avançar e depois Salvar.
5. Mostrar a nova descrição nos detalhes.

**Fala sugerida:**

> Cada repositório tem um id. Quando toco na lista, a navegação passa esse id e os detalhes procuram o item correspondente. A edição mantém a identidade do repositório e atualiza seus campos, em vez de criar outro item.

**Print opcional:** edição preenchida (`05-editar-repositorio.png`).

## Cena 4 — Cadastro, lista e detalhes de issues | 2:05–3:10

**Ações:**

1. Nos detalhes do A, tocar no + para criar a issue A1.
2. Mostrar que o repositório A já está selecionado; preencher os campos e criar.
3. Pelo + da lista de issues, criar A2 no mesmo repositório.
4. Mostrar os dois itens e abrir A1.
5. Mostrar seu título, descrição e repositório. Tocar em Editar issue, alterar a descrição e salvar; conferir a atualização nos detalhes.
6. Marcar A1 como concluída.
7. Tocar em Ver repositório e mostrar que a contagem de issues abertas agora é 1.

**Fala sugerida:**

> A issue é o segundo tipo de item do aplicativo. Ela guarda o id do repositório ao qual pertence. Esse vínculo é a complexidade extra dos detalhes: o repositório conta as issues abertas e abre uma lista só com os seus itens. Eu tinha duas abertas; ao concluir uma, a contagem passou para uma.

**Prints opcionais:** criação (`06-criar-issue.png`), lista (`07-lista-issues.png`), issue concluída (`08-detalhes-issue.png`) e repositório com contagem atualizada (`04-detalhes-repositorio.png`).

## Cena 5 — Conferir o vínculo e remover itens das duas listas | 3:10–4:15

**Ações:**

1. Pela Home, abrir Repositórios Principais e acessar B.
2. Criar B1 pelo + dos detalhes.
3. Voltar a A e abrir Issues: mostrar que B1 não aparece nessa lista.
4. Abrir A2, tocar em Excluir issue e confirmar.
5. Mostrar A2 removida da lista e A1 ainda presente.
6. Abrir B, acessar o menu de três pontos e escolher Excluir repositório.
7. Cancelar uma vez para demonstrar a confirmação; repetir e confirmar.
8. Na Home, abrir Issues: mostrar que B1 foi removida junto com B e que A1 continua.

**Fala sugerida:**

> A lista aberta pelo repositório mostra apenas as issues relacionadas a ele. Posso excluir uma issue individualmente. Quando excluo um repositório, suas issues também são removidas, para não ficarem sem vínculo. As issues de outros repositórios continuam na lista.

**Print opcional:** confirmação de exclusão (`09-excluir.png`).

## Cena 6 — Histórico em Explorar | 4:15–4:45

**Ações:**

1. Tocar em Explorar na barra inferior.
2. Mostrar atividades de criação de A e B, edição de A e exclusão de B.
3. Usar o filtro para mostrar apenas excluídos e depois voltar a Todas.
4. Tocar em uma atividade de A para abrir seus detalhes.

**Fala sugerida:**

> Explorar virou o histórico das alterações nos repositórios. Cada registro guarda uma cópia dos dados no momento da ação. Assim, a exclusão do repositório B não apaga a atividade, e a edição do A não modifica seus registros antigos.

**Print opcional:** histórico (`10-historico.png`).

## Cena 7 — Organização do código e dificuldades | 4:45–5:45

**Mostrar:** no Android Studio, `AppNavigation.kt` com `Rotas` e `NavHost`; depois `GithubViewModel.kt` com as data classes, listas e operações.

**Fala sugerida:**

> Centralizei as rotas no AppNavigation para controlar as telas em um lugar só. O Scaffold organiza a estrutura, e os callbacks conectam os botões à navegação. Os detalhes recebem ids como argumentos.
>
> No GithubViewModel ficam as data classes e as listas mutableStateListOf. Isso permite compartilhar os mesmos dados entre cadastro, lista e detalhes. Os dados ficam em memória porque a persistência não faz parte desta etapa do trabalho.
>
> Durante os ajustes, foi necessário aproximar os layouts das referências e corrigir o botão + de Issues, que ficava desativado quando não havia repositórios. Também retirei as animações de transição para deixar a navegação direta.

Se quiser falar de uma dificuldade pessoal, acrescente aqui um relato verdadeiro: o que você não compreendia, como investigou e o que aprendeu. Não atribua uma dificuldade a outro integrante, pois o projeto é individual.

## Cena 8 — Encerramento | 5:45–6:05

**Mostrar:** voltar à Home ou à listagem de repositórios.

**Fala sugerida:**

> O projeto evoluiu de três telas visuais para dez telas conectadas, com cadastros, listas, detalhes e alterações reais. Demonstrei a criação e a remoção dos dois tipos de item, a relação entre repositórios e issues e o histórico de atividades. O código, a documentação e as evidências estão no repositório da entrega.

Use a última frase apenas depois de publicar o material. Se perguntarem sobre limites, explique que Inbox e Copilot permanecem visuais, os dados são locais e não há conexão com a API do GitHub.

## Como colocar o vídeo e os prints no README

1. Salvar o vídeo e enviar para uma plataforma aceita pelo professor.
2. Conferir se o link pode ser aberto por quem vai avaliar, sem solicitar acesso.
3. Substituir o campo “Vídeo de demonstração” do README pelo link real.
4. Se também usar prints, salvar em `docs/imagens/` com os nomes sugeridos e inserir os blocos Markdown de imagem.
5. Acrescentar as imagens antigas na seção “Projeto antigo — Trabalho 1”.
6. Conferir o README renderizado no GitHub antes da entrega.

## Conferência da gravação

- [ ] A apresentação identifica o autor e explica a evolução do Trabalho 1.
- [ ] O vídeo mostra adicionar e excluir repositórios e issues.
- [ ] Os detalhes exibem dados de itens diferentes, e não valores fixos.
- [ ] A contagem de issues abertas muda após concluir uma issue.
- [ ] Editar um repositório atualiza seus detalhes.
- [ ] A exclusão de um repositório remove somente suas próprias issues.
- [ ] A navegação por Home, Explorar e voltar foi mostrada.
- [ ] A fala explica rotas, ViewModel e listas reativas.
- [ ] As dificuldades relatadas correspondem ao que realmente ocorreu.
- [ ] O link e o áudio do vídeo foram conferidos.

