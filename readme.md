# 📦 Estoque Backend API REST

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot)
![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)

---

## ✨ Visão Geral

Uma robusta API RESTful para gerenciamento de estoque, desenvolvida com Java e o framework Spring Boot. Este backend foi projetado para oferecer controle total sobre produtos e categorias, com foco em:

-   **CRUD Completo:** Manipulação de Categorias e Produtos.
-   **Controle de Estoque:** Validações transacionais inteligentes para entradas e saídas.
-   **Relatórios Gerenciais:** Visibilidade sobre o desempenho do seu estoque.
-   **Integridade de Dados:** Validações rigorosas para garantir a qualidade das informações.

---

## 🛠️ Tecnologias Principais

| Categoria      | Tecnologia              | Descrição                                         |
| :------------- | :---------------------- | :------------------------------------------------ |
| **Linguagem** | Java 21+                | Base de toda a aplicação.                         |
| **Framework** | Spring Boot 3.x         | Microframework para desenvolvimento rápido de APIs. |
| **Banco de Dados** | MySQL                   | Armazenamento relacional de dados.                |
| **ORM** | Spring Data JPA / Hibernate | Facilita a interação com o banco de dados.        |
| **Build** | Maven                   | Gerenciamento de dependências e build do projeto. |
| **Utilitários** | Lombok                  | Redução de código boilerplate (getters/setters).  |
| **API** | REST (JSON)             | Padrão de comunicação leve e escalável.           |

---

## ⚙️ Configuração Local e Requisitos

Para colocar este backend em funcionamento na sua máquina, siga os passos abaixo:

### Pré-requisitos:

-   **Java JDK 21+**
-   **Apache Maven**
-   **MySQL Server** (rodando localmente, preferencialmente na porta 3306)
-   Um cliente MySQL (Ex: MySQL Workbench, DBeaver)

### 🧱 Estrutura do Banco de Dados

A aplicação espera a existência de um banco de dados chamado **`estoque_db`**. O Hibernate configurado criará as tabelas automaticamente se elas não existirem (`ddl-auto=update`).

**Modelagem das Tabelas:**

-   `categoria`: (`id`, `nome`, `tamanho`, `embalagem`)
-   `produto`: (`id`, `nome`, `preco_unitario`, `quantidade_estoque`, `quantidade_minima`, `quantidade_maxima`, `categoria_id` - **FK**)
-   `movimentacao`: (`id`, `produto_id` - **FK**, `quantidade_movimentada`, `tipo_movimentacao`, `data_hora`)

### 🔑 Configuração da Conexão com o Banco de Dados

Edite o arquivo `src/main/resources/application.properties` com as credenciais do seu MySQL local:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/estoque_db?createDatabaseIfNotExist=true&serverTimezone=UTC
spring.datasource.username=root # Seu usuário do MySQL
spring.datasource.password=sua_senha_aqui # Sua senha do MySQL

# Configurações do Hibernate (Mantenha para criação automática de tabelas)
spring.jpa.hibernate.ddl-auto=update
server.error.include-message=always # Exibe mensagens de erro úteis
```
## 🚀 Como Rodar a Aplicação

Para executar o backend, siga estes passos no seu terminal:

### 🧩 1. Clone o Repositório
Baixe o código do GitHub para sua máquina:

git clone https://github.com/Alejandroo19/estoque-backend.git

### 📂 2. Navegue até a Pasta do Projeto

cd estoque-backend

### ⚙️ 3. Construa e Instale as Dependências (Maven)
Este comando baixa todas as bibliotecas necessárias e compila o projeto:

mvn clean install

### ▶️ 4. Execute o Servidor Spring Boot

Via Terminal (recomendado):

mvn spring-boot:run

O servidor será iniciado na porta 8080.

Via IDE:
Execute o método main() na classe EstoqueBackendApplication.java.

---

## 🌐 Endpoints da API (Guia para o Frontend)

A API REST roda na porta padrão 8080, e todos os endpoints são prefixados com /api.

### 🧱 Módulos Principais

| Módulo | Endpoint Base | Métodos | Descrição |
|--------|----------------|----------|------------|
| **Categorias** | /api/categorias | GET, POST, PUT, DELETE | Gerenciamento completo de categorias de produtos |
| **Produtos** | /api/produtos | GET, POST, PUT, DELETE | Gerenciamento de produtos (requer categoria válida) |
| **Estoque** | /api/movimentacoes | POST | Registra entrada ou saída de estoque, com validação de saldo |
| **Relatórios** | /api/relatorios/* | GET | Conjunto de relatórios gerenciais de estoque |

---

### 📊 Relatórios Gerenciais Disponíveis (Endpoints GET)

| Relatório | Rota do Endpoint |
|------------|------------------|
| Lista de Preços | /api/relatorios/lista-precos |
| Balanço Físico/Financeiro | /api/relatorios/balanco |
| Estoque Crítico | /api/relatorios/estoque-critico |
| Produtos por Categoria | /api/relatorios/por-categoria |
| Rotatividade de Produto | /api/relatorios/rotatividade |
