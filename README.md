# 📘 Documentação Técnica — Gerador de Desafios

## 1. Visão Geral

O **Gerador de Desafios** é uma aplicação web construída com **Spring Boot**, seguindo rigorosamente o padrão **MVC (Model–View–Controller)** e utilizando o **Strategy Pattern** para permitir a criação dinâmica de diferentes tipos de desafios lógicos e matemáticos.

O sistema foi projetado para funcionar como um **fluxo de jogo**, onde cada desafio é gerado dinamicamente, apresentado ao usuário e validado conforme sua própria regra interna.

---

## 2. Objetivos do Projeto

* Gerar desafios de forma automática e aleatória
* Permitir múltiplos tipos de desafios sem alterar o fluxo principal
* Separar claramente responsabilidades (MVC)
* Facilitar manutenção, expansão e testes
* Simular um fluxo de jogo interativo

---

## 3. Arquitetura Utilizada

### 3.1 Padrão MVC

```
Controller → Service → Domain → View
```

* **Controller**: recebe requisições HTTP e controla o fluxo
* **Service**: orquestra regras de negócio
* **Domain**: contém a lógica dos desafios (Strategy)
* **View**: interface do usuário (Thymeleaf)

---

## 4. Organização de Pacotes

```
com.example.gerador_problemas
│
├── controller
│   └── DesafioController
|   └── MainController
│
├── service
│   ├── DesafioService
│   ├── GeradorDesafios
│   └── tipos
│       ├── NumParesDesafio
│       ├── SequenciaDesafio
│       └── LogicaInversa
│
├── domain
│   ├── Desafio (interface)
|   └── Feedback
│   └── dto
│       └── DesafioDTO
│
│
└── util

resources
|
├── static
|   └──style.css
|   └── js
|       └── tema.js
|
├── templates
│   └── desafio.html
|   └── index.html
|   └── resultado.html
```

---

## 5. Domain Layer (Modelo)

### 5.1 Interface `Desafio`

Representa o **contrato** para qualquer tipo de desafio.

Responsabilidades:

* Gerar um desafio
* Validar a resposta do usuário
* Informar seu tipo

```java
public interface Desafio {
    DesafioDTO gerar();
    boolean validar(int resposta, DesafioDTO dto);
    String getTipo();
}
```

---

### 5.2 Implementações (`Strategy Pattern`)

Cada classe em `service.tipos` representa uma estratégia independente.

Exemplo:

* `NumParesDesafio`
* `SequenciaDesafio`
* `LogicaInversa`

Essas classes:

* São anotadas com `@Service`
* São automaticamente registradas pelo Spring
* Podem ser adicionadas sem alterar código existente

---

### 5.3 `DesafioDTO`

Objeto de transferência de dados responsável por levar informações do desafio para a View.

Campos comuns:

* `titulo`
* `pergunta`
* `tentativa`
* `tipo`

O DTO **não contém lógica de negócio**.

---

## 6. Service Layer (Regra de Negócio)

### 6.1 `GeradorDesafios`

Classe central do sistema.

Responsabilidades:

* Receber todas as implementações de `Desafio`
* Sortear um desafio aleatoriamente
* Manter o estado do desafio atual
* Validar respostas


---

### 6.2 `DesafioService`

Camada intermediária entre Controller e Gerador.

Responsabilidades:

* Iniciar o jogo
* Delegar validação
* Fornecer feedback


---

## 7. Controller Layer

### 7.1 `DesafioController`

Responsável por:

* Receber requisições HTTP
* Enviar dados para a View
* Controlar navegação

Fluxos principais:

* Exibir desafio atual
* Submeter resposta
* Gerar novo desafio

---

## 8. View Layer (Thymeleaf)

A interface utiliza **Thymeleaf**, integrando-se diretamente ao modelo.

```html
<h2 th:text="'Desafio ' + ${desafio.titulo}">Desafio</h2>
<p th:text="${desafio.pergunta}"></p>
```

O HTML é desacoplado da lógica de negócio.

---

## 9. Fluxo Completo do Sistema

```
Usuário → Controller → Service → Gerador → Desafio
                                  ↓
                               DesafioDTO
                                  ↓
                                View
```

---

## 10. Extensibilidade do Sistema

Para adicionar um novo desafio:

1. Criar uma nova classe que implemente `Desafio`
2. Anotar com `@Service`
3. Implementar `gerar()`, `validar()` e `getTipo()`

Nenhuma outra parte do sistema precisa ser alterada.

---

## 11. Boas Práticas Aplicadas

* ✔ MVC bem definido
* ✔ Strategy Pattern
* ✔ Inversão de Controle (Spring)
* ✔ DTO para transporte de dados
* ✔ Baixo acoplamento
* ✔ Alta coesão

---

## 12. Possíveis Evoluções

* Persistência por sessão
* Pontuação e ranking
* API REST
* Front-end SPA
* Modo competitivo

---

## 13. Conclusão

Este projeto demonstra domínio de:

* Arquitetura de software
* Padrões de projeto
* Spring Boot
* Organização profissional de código

---

## 14. Como executar
* Requisitos:

        -- Java 17 instalado. 
        -- Use o Maven instalado ou o Maven Wrapper incluído no projeto.

* Executar com Maven Wrapper (Windows):

```
mvnw.cmd spring-boot:run
```

* Executar com Maven Wrapper (Linux/macOS):

```
./mvnw spring-boot:run
```
* Gerar o JAR e executar:

Windows:
```
mvnw.cmd clean package -DskipTests
java -jar target/gerador-problemas-0.0.1-SNAPSHOT.jar
```

Linux/macOS:
```
./mvnw clean package -DskipTests
java -jar target/gerador-problemas-0.0.1-SNAPSHOT.jar

```
* Executar no IDE:

Importe o projeto como Maven e execute a classe `com.example.gerador_problemas.GeradorProblemasApplication` como aplicação Java.

Acessar a aplicação: abra http://localhost:8080 no navegador após a inicialização.