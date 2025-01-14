# TabelaFipe - Projeto Java Spring Boot

Bem-vindo ao projeto **TabelaFipe**! Este repositório tem como objetivo facilitar consultas à tabela FIPE, oferecendo funcionalidades para acessar dados sobre marcas, modelos e veículos. 

---

## 🚗 **Descrição do Projeto**
A aplicação foi desenvolvida em **Java** utilizando o framework **Spring Boot** e é capaz de integrar-se a uma API externa para obter informações detalhadas sobre:
- Marcas de veículos
- Modelos associados a cada marca
- Dados específicos de veículos

---

## ✨ **Funcionalidades**
- **🔍 Consulta de Marcas**: Busca todas as marcas cadastradas na tabela FIPE.
- **🚙 Consulta de Modelos**: Retorna os modelos relacionados a uma marca especificada.
- **📊 Consulta de Veículos**: Oferece detalhes específicos de um veículo com base no código selecionado.

---

## 🛠 **Tecnologias Utilizadas**
Este projeto utiliza as seguintes ferramentas e bibliotecas:

- **Java 17**: Linguagem principal do projeto.
- **Spring Boot 3.x**: Framework para criação de aplicações robustas e escaláveis.
- **Maven**: Gerenciador de dependências e automação de build.
- **HttpClient**: Biblioteca para comunicação com APIs REST.

---

## 📦 **Dependências Principais**
As dependências estão especificadas no arquivo `pom.xml`. Aqui estão algumas delas:

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

---

## 📂 **Estrutura do Projeto**

```plaintext
TabelaFipe/
|-- src/main/java/br/com/suna/TabelaFipe
|   |-- TabelaFipeApplication.java       # Classe principal do projeto
|   |-- main/Main.java                   # Classe de entrada para execução
|   |-- model/                           # Modelos de dados
|       |-- DadosMarca.java              # Modelo para marcas
|       |-- DadosModelo.java             # Modelo para modelos
|       |-- DadosVeiculo.java            # Modelo para veículos
|   |-- service/                         # Lógica de negócio e serviços
|       |-- ConsumoApi.java              # Consumo da API externa
|       |-- ConverteDados.java           # Conversão e manipulação de dados
|-- src/main/resources/
|   |-- application.properties           # Configurações do projeto
|-- pom.xml                              # Configuração do Maven
```

---

## 🌐 **Integração com a API**
A classe `ConsumoApi` é responsável por integrar-se à API da tabela FIPE, utilizando a biblioteca `HttpClient`. Aqui está um exemplo de como o método `obterDados` funciona:

```java
public String obterDados(String endereco) {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(endereco))
            .build();
    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    return response.body();
}
```

Exemplo de requisição:
```java
ConsumoApi api = new ConsumoApi();
String dados = api.obterDados("https://api.fipe.org.br/marcas");
System.out.println(dados);
```

---

## 🚀 **Como Rodar o Projeto**

### ⚙️ **Pré-requisitos**
- **JDK 17** ou superior instalado.
- **Maven** instalado e configurado.

### Passos para Execução
1. **Clone o repositório:**
   ```bash
   git clone <URL-do-repositorio>
   ```

2. **Navegue até o diretório do projeto:**
   ```bash
   cd TabelaFipe
   ```

3. **Compile o projeto:**
   ```bash
   ./mvnw clean install
   ```

4. **Inicie a aplicação:**
   ```bash
   ./mvnw spring-boot:run
   ```

5. **Adendo:**
   Caso prefira é possível fazer o projeto rodar no IntelliJ, ao abrir o projeto no Software faça o seguinte caminho:
   
   src>main>java>br.com.suna.TabelaFipe

   Ao abrir o pacote clique na classe TabelaFipeApplication e aperte o ícone verde de "play" para rodar.

   ![Captura de tela 2025-01-13 233353](https://github.com/user-attachments/assets/40e57844-b5f2-40d7-b5f9-14b1381b2c04)

---

## 🤝 **Contribuições**
Contribuições são muito bem-vindas! Sinta-se à vontade para abrir _issues_ ou enviar _pull requests_ com melhorias, correções ou novas funcionalidades.

---

## 📜 **Licença**
Este projeto é licenciado sob a Licença MIT. Consulte o arquivo `LICENSE` para mais detalhes.

