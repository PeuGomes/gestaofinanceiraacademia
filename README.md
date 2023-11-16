# Sistema de Gestão de Academia

Este é um projeto de sistema de gestão de academia desenvolvido em Spring Boot(MVC). Projeto de TCC da Universidade.

## Configuração do Ambiente

Antes de executar o projeto, certifique-se de ter as seguintes ferramentas instaladas:

- **Java JDK 17**: [Download JDK](https://www.oracle.com/java/technologies/downloads/#java17)
- **IntelliJ IDEA**: [Download IntelliJ IDEA](https://www.jetbrains.com/idea/)
- **MySQL**: [Download MySQL](https://dev.mysql.com/downloads/)

## Configuração do Banco de Dados

As configurações do banco de dados podem ser ajustadas no arquivo `src/main/resources/application.properties`. Modifique as informações de conexão conforme necessário.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/AcademiaHardcore
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
```
## Executando o Projeto

O aplicativo estará disponível em [http://localhost:8080](http://localhost:8080). Pagina Inicial.

## Dependências do Maven

Este projeto utiliza as seguintes dependências Maven:

```xml
<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-thymeleaf</artifactId>
		</dependency>
		<dependency>
			<groupId>com.itextpdf</groupId>
			<artifactId>itextpdf</artifactId>
			<version>5.5.13</version>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
			<version>3.1.0</version>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>com.mysql</groupId>
			<artifactId>mysql-connector-j</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<version>3.1.0</version>
			<scope>test</scope>
		</dependency>
	</dependencies>
```

## Endpoints

- `/clientes/formulario`: Endpoint para cadastro de clientes.
- `/clientes/atualizar`: Endpoint para atualização do cadastro de cliente.
- `/admin/login`: Endpoint para login do admin.
- `/admin/dashboard`: Endpoint para o admin ter acesso a tudo que é permitido ele fazer ou ver.
- `/admin/adicionar`: Endpoint para o admin cadastrar modalidades.
- `/admin/cadastrar-planos`: Endpoint para o admin cadastrar planos.
- `/admin/cadastrar`: Endpoint para o admin poder cadastrar o cliente que vai direto a Academia, o admin tem acesso privilegiado.
- `/admin/atualizar`: Endpoint para o admin poder atualizar o cadastro do cliente, o admin tem acesso privilegiado.
- `/admin/listar`: Endpoint para o admin ver toda lista de clientes cadastrado e tambem quem fez o pagamento e quem não fez, o admin tem acesso privilegiado.
- `/admin/pagamentos`: Endpoint para o admin cadastrar o pagamento do cliente que foi na Academia e pagou, gerar o comprovante de pagamento, o admin tem acesso privilegiado.
- `/admin/pagamentos/10/atualizar`: Endpoint para o admin atualizar o pagamento do cliente que foi na Academia e pagou após a data de vencimento, o admin tem acesso privilegiado.
- `/admin/excluir`: Endpoint para o admin excluir o cliente, o admin tem acesso privilegiado.

## Estrutura do Projeto

- `src/main/java/br/com/sistemagestaoacademia/gestaofinanceiraacademia`: Código-fonte Java.
- `src/main/resources`: Recursos, incluindo arquivos de configuração e modelos Thymeleaf.

## Tecnologias Utilizadas

- **Spring Boot**: Framework Java para criação de aplicativos baseados em Java.
- **Thymeleaf**: Motor de modelos para Java, incluindo suporte para integração com JavaScript.
- **iTextPDF**: Biblioteca para criação de PDFs em Java.
- **MySQL**: Sistema de gerenciamento de banco de dados relacional.
- **Spring Web**: Módulo do Spring para desenvolvimento web.
- **Spring Data JPA**: Módulo do Spring para integração com JPA (Java Persistence API).
- **MySQL Connector/J**: Driver JDBC para MySQL.
- **Spring Boot DevTools**: Ferramentas para desenvolvimento rápido.
- **Spring Boot Starter Validation**: Suporte para validação no Spring Boot.
- **Spring Boot Starter Test**: Suporte para testes no Spring Boot.
- **JavaScript (com Thymeleaf)**: Utilizado para aprimorar a experiência do usuário no lado do cliente.
- **Bootstrap**: Framework front-end para design responsivo.
- **CSS**: Linguagem de estilização para melhorar o layout e a apresentação.

## Ainda séra feito melhorias no projeto

Mais adiante....

## Autor

Pedro Ivo Barreto Gomes
