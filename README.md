# Intense API

## 📝 Visão Geral

Esta é a API backend do projeto **Intense**, uma revista de moda universitária. A API é responsável por toda a lógica de negócio, incluindo a geração de mensagens personalizadas utilizando a IA generativa do Google Gemini, além de gerenciar futuras funcionalidades do site.

Construída com foco em boas práticas, manutenibilidade e escalabilidade, seguindo uma arquitetura limpa e organizada.

---

## ✨ Funcionalidades Principais

-   **Geração de Mensagens Dinâmicas**: Endpoint que recebe o nome de um usuário e retorna uma frase de efeito criativa e única, gerada pela IA do Google Gemini.
-   **Configuração de Ambientes**: Separação clara entre ambientes de `desenvolvimento` e `produção` utilizando Perfis do Spring.
-   **Resiliência**: Implementação de uma estratégia de *Retry com Backoff Exponencial* para lidar com instabilidades na API do Gemini.
-   **Validação e Tratamento de Erros**: Endpoints protegidos com validação de entrada e um handler global de exceções para respostas de erro padronizadas.

---

## 🛠️ Stack Tecnológica

-   **Java 17**
-   **Spring Boot 3**
-   **Maven**: Gerenciador de dependências.
-   **Google Gemini API**: Para a geração de texto com IA.
-   **Lombok**: Para redução de código boilerplate.
-   **Spring Retry**: Para resiliência em chamadas externas.

---

## 🚀 Como Rodar Localmente

Para executar este projeto no seu ambiente de desenvolvimento, siga os passos abaixo.

### Pré-requisitos

-   **Java (JDK)** na versão 17 ou superior.
-   **Maven** instalado e configurado no seu `PATH`.
-   Uma **Chave de API** do [Google AI Studio](https://aistudio.google.com/app/apikey).

### Configuração

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/isaquemsf/intense-api.git
    cd intense-api
    ```

2.  **Crie e configure o arquivo de ambiente:**
    -   Na pasta `src/main/resources`, crie um arquivo chamado `application-dev.yml` (se ele não existir).
    -   Garanta que a variável da chave da API está configurada. Você pode usar uma variável de ambiente ou colocar a chave diretamente (apenas para testes locais):

    ```yaml
    # src/main/resources/application-dev.yml

    gemini:
      api-key: "SUA_CHAVE_API_DO_GEMINI_AQUI" 
      # ... outras configurações ...
    ```
    *Para configurar como variável de ambiente (recomendado), defina a variável `GEMINI_API_KEY` no seu sistema.*

### Execução

1.  **Use o Maven para compilar e rodar o projeto:**
    ```bash
    mvn spring-boot:run
    ```

2.  A API estará disponível em `http://localhost:8080`.

---

## Endpoints da API

### Gerar Mensagem

-   **`POST /api/messages/generate`**
-   **Descrição:** Gera uma nova mensagem personalizada.
-   **Body (JSON):**
    ```json
    {
      "name": "NomeDoUsuario"
    }
    ```
-   **Resposta de Sucesso (200 OK):**
    ```json
    {
      "message": "NomeDoUsuario, sua autenticidade é o look mais raro da estação. - Intense."
    }
    ```
-   **Resposta de Erro (400 Bad Request):**
    ```json
    {
      "timestamp": "...",
      "status": 400,
      "errors": ["O nome é obrigatório e não pode estar em branco."],
      "path": "/api/messages/generate"
    }
    ```
---
