# TODO List - ZG Hero (K1-T3) | Java Backend

Aplicação backend desenvolvida em **Java puro** como entrega do **ZG-Hero Project da trilha K1-T3 (Java)** do programa Acelera ZG.

O projeto consiste em um **gerenciador de tarefas (TODO List)** executado via terminal, implementando um **MVP funcional com operações CRUD** e organização por prioridade.

## Objetivo do Projeto

Implementar uma aplicação back-end em Java capaz de:

- Criar tarefas
- Listar tarefas
- Remover tarefas
- Atualizar tarefas
- Ordenar tarefas automaticamente por prioridade
- Filtrar listagens por:
    - Categoria
    - Prioridade
    - Status

O projeto foi desenvolvido sem utilização de frameworks (Spring, Micronaut, etc.), utilizando apenas recursos nativos da linguagem Java.

## Requisitos Atendidos

### Obrigatórios

- Tarefa contendo:
    - Nome
    - Descrição
    - Data de término
    - Nível de prioridade (1 a 5)
    - Categoria
    - Status (TODO, Doing, Done)
- CRD de tarefas via menu no terminal
- Listagem por:
    - Categoria
    - Prioridade
    - Status
- Rebalanceamento automático por prioridade ao inserir nova tarefa

### Opcionais Implementados

- Atualização de tarefa
- Validação de entrada do usuário

## Estrutura do Projeto

### model
Contém as classes de domínio da aplicação.

### service
Responsável pelas regras de negócio e manipulação das tarefas.

### ui
Responsável pela interação com o usuário via terminal.

## Tecnologias Utilizadas

- Java 17+ (compatível com versões modernas da linguagem)

---

## ▶️ Como Executar

### 1️⃣ Clone o repositório

```bash
git clone https://github.com/mirellesv/todo-list.git
```

### 2️⃣ Acesse o diretório
```bash
cd todo-list
```

### 3️⃣ Compile o projeto
```bash
javac Main.java
```

### 4️⃣ Execute
```bash
java Main
```