Perfeito. Abaixo está a **documentação completa do projeto**, organizada como **MVC (Model–View–Controller)**, **adaptada exatamente ao que você construiu** (Spring Boot + desafios automáticos + estado + view dinâmica).

Pode colar direto como **`README.md`** do projeto.

---

# 🎮 Projeto — Jogo de Desafios Lógicos (Arquitetura MVC)

## 📌 Visão Geral

Este projeto é um **jogo educacional de lógica**, desenvolvido em **Java com Spring Boot**, que gera **desafios automaticamente**, valida respostas de forma progressiva e apresenta feedback dinâmico ao usuário.

A aplicação segue rigorosamente o padrão **MVC (Model–View–Controller)**, separando responsabilidades e facilitando manutenção, testes e evolução do sistema.

---

## 🧠 Conceito Central

* O sistema **gera desafios dinamicamente**
* A **lógica nunca fica na view**
* A view **não sabe como a resposta é validada**
* O desafio pode **mudar de regra conforme tentativas**
* O usuário interage apenas com perguntas e feedback

---

## 🏗️ Arquitetura MVC

```
src/main/java
└── com.example.jogo
    ├── controller
    │   └── DesafioController.java
    │
    ├── service
    │   ├── DesafioService.java
    │   ├── Desafio.java
    │   └── NumParesDesafio.java
    │
    ├── model
    │   └── DesafioDTO.java
    │
    └── util
        └── Feedback.java
```

```
src/main/resources
├── templates
│   ├── challenge.html
│   └── result.html
│
└── static
    ├── css
    │   └── style.css
    └── js
        └── tema.js
```

---

## 🧩 MODEL (Dados e Estado)

### 📦 `DesafioDTO`

Responsável por **transportar dados do desafio** entre Service e View.

```java
public class DesafioDTO {

    private final String descricao;
    private int tentativa = 1;

    public DesafioDTO(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getTentativa() {
        return tentativa;
    }

    public void incrementarTentativa() {
        this.tentativa++;
    }
}
```

### Responsabilidades do Model

✔ Armazenar estado do desafio
✔ Controlar número de tentativas
✔ Não conter regras de validação complexas
✔ Não conhecer View ou Controller

---

## 🧠 SERVICE (Regras de Negócio)

### 🔌 Interface `Desafio`

Define o **contrato** de qualquer desafio do sistema.

```java
public interface Desafio {
    DesafioDTO gerar();
    boolean validar(int resposta, DesafioDTO dto);
}
```

---

### 🔢 `NumParesDesafio`

Implementação concreta de um desafio com **dificuldade progressiva**.

```java
@Service
public class NumParesDesafio implements Desafio {

    @Override
    public DesafioDTO gerar() {
        return new DesafioDTO(
            "Digite um número que satisfaça a regra atual."
        );
    }

    @Override
    public boolean validar(int resposta, DesafioDTO dto) {

        int tentativa = dto.getTentativa();
        boolean valido;

        if (tentativa <= 2) {
            valido = resposta % 2 == 0;
        } else {
            valido = resposta % 4 == 0;
        }

        dto.incrementarTentativa();
        return valido;
    }
}
```

📌 A regra **muda silenciosamente**, sem alterar a pergunta.

---

### 🧠 `DesafioService`

Gerencia o **estado atual do jogo**.

```java
@Service
public class DesafioService {

    @Autowired
    private NumParesDesafio desafio;

    private DesafioDTO desafioAtual;

    @PostConstruct
    public void init() {
        this.desafioAtual = desafio.gerar();
    }

    public String submit(int resposta) {

        boolean valido = desafio.validar(resposta, desafioAtual);

        if (valido) {
            return Feedback.sucesso(desafioAtual.getTentativa());
        } else {
            return Feedback.falha(desafioAtual.getTentativa());
        }
    }

    public String getDescricao() {
        return desafioAtual.getDescricao();
    }

    public void novoDesafio() {
        this.desafioAtual = desafio.gerar();
    }
}
```

### Responsabilidades do Service

✔ Centralizar lógica de negócio
✔ Manter estado do desafio
✔ Validar respostas
✔ Nunca acessar HTML ou requisições HTTP

---

## 🎯 CONTROLLER (Fluxo da Aplicação)

### `DesafioController`

Responsável por **ligar a View ao Service**.

```java
@Controller
public class DesafioController {

    @Autowired
    private DesafioService service;

    @GetMapping("/desafio")
    public String desafio(Model model) {
        model.addAttribute("descricao", service.getDescricao());
        return "challenge";
    }

    @PostMapping("/desafio")
    public String responder(
        @RequestParam int resposta,
        Model model
    ) {
        String resultado = service.submit(resposta);
        model.addAttribute("resultado", resultado);
        return "result";
    }
}
```

### Responsabilidades do Controller

✔ Receber requisições HTTP
✔ Enviar dados para a View
✔ Nunca conter regras de negócio
✔ Nunca validar lógica

---

## 🎨 VIEW (Interface do Usuário)

### 🧾 `challenge.html`

* Exibe a descrição do desafio
* Coleta resposta do usuário
* Não conhece regras internas

### 🧾 `result.html`

* Exibe feedback do sistema
* Não valida resposta

---

## 🎨 Tema & UX

* Tema Dark como padrão (`:root`)
* Modo Light e Caótico via JS
* Estado do tema salvo em `localStorage`
* Easter eggs e ícones dinâmicos

---

## ✅ Benefícios da Arquitetura

✔ MVC bem definido
✔ Fácil adicionar novos desafios
✔ Sem acoplamento entre camadas
✔ Lógica invisível ao usuário
✔ Ideal para jogos educacionais
✔ Escalável para API REST

---

## 🚀 Possíveis Evoluções

* Gerador automático de desafios
* Modo 😈 com regras instáveis
* Ranking de usuários
* Histórico de tentativas
* API REST
* Persistência em banco
* Desafios “sem resposta correta”

---

## 🧠 Conclusão

Este projeto não é apenas um jogo — é um **sistema de decisão**, onde:

> o usuário interage com efeitos
> mas nunca vê a causa

Uma aplicação ideal para:

* ensino de lógica
* experimentos cognitivos
* jogos educacionais
* UX comportamental

---

Se quiser, no próximo passo posso:

* revisar o README como **documento acadêmico**
* transformar isso em **TCC prático**
* adicionar **diagrama MVC**
* criar **novo desafio plugável**
* evoluir para **REST API**

👉 Qual o próximo passo?
