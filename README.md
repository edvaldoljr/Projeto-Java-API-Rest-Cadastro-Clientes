# **Projeto API Rest Cadastro Clientes** 

![]()

 API de cadastro de clientes que utiliza o banco de dados H2, o JPA e o Lombok.

Esta API permite que você registre clientes com suas informações básicas, incluindo nome, e-mail e CPF. O banco de dados H2 é utilizado para armazenar esses dados de maneira segura e organizada, enquanto o JPA é responsável por fazer a comunicação entre a API e o banco de dados.

O Lombok é uma biblioteca Java que facilita a escrita de código, tornando-o mais conciso e legível. Com o Lombok, é possível criar classes e métodos com poucas linhas de código, o que ajuda a reduzir a complexidade do código e aumenta a produtividade.

Em resumo, esta API de cadastro de clientes é uma solução eficiente e fácil de usar para registrar informações de clientes. Com o uso do banco de dados H2, JPA e Lombok, você pode ter certeza de que seus dados estão seguros e bem organizados, além de ter um código limpo e legível.

## Para utilizar a API de cadastro de clientes, alguns requisitos são necessários:

- Banco de Dados H2: É necessário ter instalado e configurado o banco de dados H2 para armazenar as informações de clientes.
- JPA (Java Persistence API): É necessário ter conhecimento e habilidade na utilização da JPA, pois será utilizada para gerenciar as operações de persistência de dados.
- Lombok: É necessário ter conhecimento e habilidade na utilização do Lombok, pois será utilizado para gerar código automaticamente, tornando o desenvolvimento mais rápido e eficiente.
- Conhecimento de Java: É necessário ter conhecimento e habilidade na linguagem de programação Java, pois será utilizada para desenvolver a API.

Além disso, é importante que se tenha uma boa compreensão dos conceitos de programação orientada a objetos e de persistência de dados.

------

# **Desenvolvimento**

## Class ApiApplication

A classe ApiApplication é a classe principal da aplicação, responsável por iniciar a execução do projeto. Ela possui a anotação @SpringBootApplication, que indica que é uma classe de configuração principal do Spring Boot, e automaticamente configura as configurações necessárias para que a aplicação funcione corretamente.

O método main é o método principal da classe, responsável por iniciar a aplicação. Ele chama o método estático run da classe SpringApplication, passando como parâmetros a própria classe ApiApplication e os argumentos passados pelo usuário na linha de comando. Esse método é responsável por criar um contexto do Spring, gerenciar os beans e iniciar o servidor embutido.

```java
// Importação da classe SpringApplication da biblioteca Spring Boot para iniciar a aplicação.
import org.springframework.boot.SpringApplication;
// Importação da classe SpringBootApplication da biblioteca Spring Boot para configuração automática da aplicação.
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Anotação para indicar que essa classe é a classe principal da aplicação e que ela possui configurações de automação.
@SpringBootApplication
public class ApiApplication {

// Método principal que será executado quando a aplicação for iniciada.
public static void main(String[] args) {
	// Método da classe SpringApplication para iniciar a aplicação.
	SpringApplication.run(ApiApplication.class, args);
}
}


```

## class Cliente

Este é um exemplo de uma classe Java "Cliente" que representa uma entidade de banco de dados. É usado o framework JPA (Java Persistence API) para mapear a classe Java para uma tabela no banco de dados. A anotação @Entity é usada para indicar que a classe é uma entidade de banco de dados. A anotação @Id indica que o atributo id é a chave primária da tabela. A anotação @GeneratedValue com strategy = GenerationType.AUTO é usada para gerar automaticamente o valor da chave primária. A anotação @Column é usada para especificar o nome da coluna no banco de dados correspondente a cada atributo da classe. A anotação @Data, @AllArgsConstructor, @NoArgsConstructor e @Builder são do framework Lombok, usado para gerar métodos getter, setter e construtores automaticamente.

```java
package br.com.maddytc.cliente.entity;
/**
 * entity é onde vamos guardar nossas entidades do banco de dados.
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

//@Data para criar os métodos getter e setter e o To String automaticamente.
@Data
//@AllArgsConstructor Para criar o construtor com as propriedades que criarmos de cliente.
@AllArgsConstructor
//@NoArgsConstructor Para criar um construtor vazio ou seja sem argumentos.
@NoArgsConstructor
//@Builder Para nos ajudar na criação de objetos Cliente.
@Builder
//@Entity Para informa que é uma entidade de banco de dados.
@Entity

public class Cliente implements Serializable {

    //Atributos Cliente
    //Como é uma entidade temos que definir qual dos atributos será o id então usamos a anotação @Id
    //Para que não seja obrigatório ficarmos, gerando Id a cada novo Cliente vamos fazer a seguinte anotação @GeneratedValue com a estratégia (strategy = GenerationType.AUTO).
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //No atributo nome vamos adicionar o nome da coluna do banco com a marcação @Column e colocar nullable = false para que o campo nao seja vazio tem que estar preenchido
    @Column(name =  "nome", nullable = false)
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "cpf")
    private String cpf;
}
```

## class ClienteRepository

Este é um exemplo de uma interface "ClienteRepository" que estende o JpaRepository da biblioteca Spring Data JPA. Ela fornece acesso aos dados da entidade "Cliente" no banco de dados. A interface estende JpaRepository<Cliente, Long>, indicando que está trabalhando com entidades "Cliente" e a chave primária é do tipo Long. Isso significa que você pode acessar e manipular dados "Cliente" no banco de dados sem escrever consultas SQL manualmente, usando métodos já definidos na JpaRepository ou criando novos métodos personalizados.

```java
package br.com.maddytc.cliente.repository;

import br.com.maddytc.cliente.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
```

## class ClienteService

Este é o código para uma classe de serviço em Spring Boot que gerencia a lógica de negócios de clientes. A classe possui métodos para salvar um cliente, listar todos os clientes, buscar um cliente por ID, e excluir um cliente por ID. A classe também usa o repositório ClienteRepository para acessar a base de dados. Os métodos são anotados com @Autowired para serem injetados automaticamente no contexto da aplicação.

```java
package br.com.maddytc.cliente.service;

import br.com.maddytc.cliente.entity.Cliente;
import br.com.maddytc.cliente.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * service servirá para adicionarmos nossas regras de negócio
 */

//Como é uma classe de serviço vamos fazer a seguinte anotação @Service

@Service
public class ClienteService {

    //Nessa classe vamos precisar do nosso repositório
    //Vamos anotar com @Autowired
    @Autowired
    private ClienteRepository clienteRepository;

    //Vamos criar nosso primeiro método Salvar que servirá para salvar um cliente como atualizar podemos verificar na implementação
    public Cliente salvar(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    //Segundo método
    //Atraves do médoto findAll teremos uma lista de todos os clientes cadastrados na base de dados
    public List<Cliente> listaCliente(){
        return clienteRepository.findAll();
    }

    //Terceiro método vai retornar um Optional de Cliente e vamos o chamar de buscar por id
    public Optional<Cliente> buscarId(Long id) {
        return clienteRepository.findById(id);
    }

    //Quarto método esse método não irá retornar nenhum valor vai apenas servi vara excluir o id
    public void removerPorId(Long id) {
        clienteRepository.deleteById(id);
    }

    /**
     * Com tudo criado agora vamos desenvolver nossa classe controller
     */
}
```

## class ClienteController

Essa é uma classe de um controlador REST, responsável por gerenciar as requisições HTTP de clientes na aplicação. A classe é anotada com @RestController e @RequestMapping("/cliente"), indicando que é um controlador e que suas rotas começam com "/cliente".

A classe depende do ClienteService e do ModelMapper, que são injetados através da anotação @Autowired. O controlador possui métodos para criar um novo cliente (salvar), listar todos os clientes (listaCliente), buscar um cliente por ID (buscarClientePorId), excluir um cliente (removerCliente) e atualizar um cliente (atualizarCliente).

Cada método é anotado com o verbo HTTP adequado (ex: @PostMapping, @GetMapping) e o tipo de resposta HTTP esperado (ex: @ResponseStatus(HttpStatus.CREATED)). Alguns métodos possuem parâmetros com anotações específicas, como @PathVariable e @RequestBody.

```java
package br.com.maddytc.cliente.http.controller;

import br.com.maddytc.cliente.entity.Cliente;
import br.com.maddytc.cliente.service.ClienteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

//Para dizermos que essa classe é nosso controller precisamos da seguinte anotação @RestController
//Depois precisamos definir nosso rota de clientes com a anotação @ResquestMapping.

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    //Vamos criar nosso primeiro método e vamos usar nosso ClienteService
    //Vamos injetar as dependencias com a anotação @Autowired
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ModelMapper modelMapper;

    //Agora criando o primeiro método
    //Vamos utilizar o vervo @PostMapping
    @PostMapping
    //Cada requisição seja realizada com sucesso vamos retornar um Status @ResponseStatus com os parametro (HttpStatus.CREATED)
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente salvar(@RequestBody Cliente cliente) {
        //Através do clienteService vamos salvar o cliente através do parametro
        return clienteService.salvar(cliente);
        //Mas para isso funcionar precisamos informar qual é o verbo http que vamos utilizar nesse método "Vamos utilizar o vervo @PostMapping"
    }

    //Nesse próximo método vamos retornar uma lista de clientes
    //Precisamos também declarar o verbo http @GetMapping @ResponseStatus(HttpStatus.OK) agora vamos ter a lista de clientes cadastrada na base
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Cliente> listaCliente() {
        return clienteService.listaCliente();
    }

    //Agora vamos consultar um cliente por id
    //Como também é uma consulta iremos usar @GetMapping mas como o método retorna um id temos que informa no Path
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cliente buscarClientePorId(@PathVariable("id") Long id) {
        return clienteService.buscarId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado na base de dados! ")); //Caso o cliente não exista
    }

    //Método remover cliente
    //Verbo http @DeleteMapping
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) //NO_CONTENTE sem conteudo
    public void removerCliente(@PathVariable("id") Long id) {
        clienteService.buscarId(id)
                .map(cliente -> {
                    clienteService.removerPorId(cliente.getId());
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado na base de dados! "));
    }

    //Método para atualização do cliente
    //Para atualização de cliente vamos utilizar o verbo @Put
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarCliente(@PathVariable("id") Long id, @RequestBody Cliente cliente) {
        clienteService.buscarId(id)
                .map(clienteBase -> {
                    /**
                     * vamos usar um recurso do model map para atualizar o cliente da base como
                     * ainda não temos o model map em nosso pom.xml vamos adicionar
                     * <dependency>
                     * 			<groupId>org.modelmapper</groupId>
                     * 			<artifactId>modelmapper</artifactId>
                     * 			<version>2.3.5</version>
                     * 		</dependency>
                     */
                    modelMapper.map(cliente, clienteBase);
                    clienteService.salvar(clienteBase);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado na base de dados! "));
    }
}
```

# **Configurando Swagger**

## SwaggerConfig

Este é o arquivo de configuração do Swagger na classe "SwaggerConfig". A anotação "@Configuration" indica que esta é uma classe de configuração do Spring. A anotação "@Bean" indica que o método abaixo irá criar um bean gerenciado pelo Spring. O método "swaggerEditoraApi()" cria uma instância de Docket para a documentação da API usando o tipo de documentação "DocumentationType.SWAGGER_2". O método "select()" define que todas as rotas e todas as classes de controladores serão incluídas na documentação da API. A documentação da API pode ser acessada em "http://localhost:8080/swagger-ui/index.html".

```java
package br.com.maddytc.cliente.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
    // http://localhost:8080/swagger-ui/index.html
    @Bean
    public Docket swaggerEditoraApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
}
```

# **Configurando Banco de Dados H2**

## aplication.yml

Este é um trecho de configuração do Spring Boot para o banco de dados H2. Ele configura a URL de conexão com o banco de dados em memória (jdbc:h2:mem:testdb), o nome de usuário (sa) e a senha (não definido), além do driver (org.h2.Driver) para a conexão. Além disso, ele configura a plataforma de banco de dados como H2 (org.hibernate.dialect.H2Dialect) e habilita a exibição e formatação de SQL. Finalmente, ele habilita a consola H2 (acessível em /h2) para fins de teste.

```java
spring:

  datasource:
    url: jdbc:h2:mem:testdb
    username: sa
    password:
    driverClassName: org.h2.Driver

  jpa:
    database-platform: org.hibernate.dialect.H2Dialect
    properties.hibernate.show_sql: true
    properties.hibernate.format_sql: true

  h2:
    console:
      enabled: true
      path: /h2
```

# ⭐️ **Deixe um Star** ⭐️

Obrigado por conferir meu repository! É muito gratificante saber que alguém está interessado no meu trabalho. Se você gostou do que viu, deixar um star seria uma grande ajuda no meu crescimento e me motivaria a continuar fazendo projetos. O apoio de pessoas como você é fundamental para que eu possa seguir evoluindo e produzindo conteúdos cada vez melhores. Obrigado mais uma vez e espero que você possa acompanhar meus futuros projetos!