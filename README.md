Projeto de API de Transferência Bancaria Simplificada

#  :star: About the project

Este projeto trata-se de um sistema para abertura de conta e realizando transferência.

### Diagrama de Classe do fluxo do desafio
![image](https://github.com/LysaKYoshikawa/contabancaria/assets/64383080/4f9a2c67-78dc-41c2-9308-f57700f71506)

## Sobre a arquitetura e organização do código:

Abordei o inicialmente um arquitetura hexogonal essa abordagem é centrada no domínio e busca separar claramente as preocupações relacionadas ao núcleo do negócio (domínio) das preocupações de infraestrutura e tecnologia.



## Organização e Planejamento 

Lendo o desafio preferi separar em tópicos importantes do projeto, gosto de fazer um diagrama para ficar mais visual minha ideia.
Os primeiros commits foram direcionados a construção da aplicação e fazendo as pricinpais validações. 

Separei para dentro do meu domain minhas classes de principais responsabilidades e cada uma com sua tabela no banco de dados H2. Alem de dividir responsabilidade fica melhor para numa proxima featura implementar um dash com Grafana e acompanhar os status do processo.

Tudo relacionado a criar usuario, fazer uma transação, criar uma conta, esta dentro do packege de services

Minhas requisições ou seja minhas chamadas e criação de rotas estão dentro do meu packege de controllers

Como o projeto em si tem muitos retornos de status, tentei fazer uma melhor tratativa de erro com messages claros e diretos.

## Payloads

Para criar um novo usuario/cliente entre na rota 

#### post : http://localhost:8080/client 

```payload
{
	"firstName": "Monalysa",
	"agency": "1553",
	"address": "Rua da contratada, 35",
	"document": "567.890.123-46",
	"email": "monalysa@example.com",
	"password": "152",
	"statusType": "COMMON",
	"balance": 4000
}
```
o response ja retornará:
```
{
	"id": 1,
	"firstName": "Monalysa",
	"agency": "1553",
	"address": "Rua Bela Cintra, 1234",
	"document": "567.890.123-46",
	"email": "monalysa@example.com",
	"password": "152",
	"accounts": [
		{
			"account": 1,
			"agency": "1553",
			"balance": 4000,
			"document": "567.890.123-46",
			"statusType": "COMMON",
			"id": 1
		}
	]
}
```
para adicionar uma nova conta:
#### post:http://localhost:8080/account

```
{
	"agency": "123",
	"document": "567.890.123-46",
	"statusType": "COMMON",
	"balance": 90
}

```
Seu response:
```
{
	"account": 2,
	"agency": "123",
	"balance": 90,
	"document": "567.890.123-46",
	"statusType": "COMMON",
	"id": 2
}

```
para listar todas as contas:
#### get:http://localhost:8080/client

Seu response:
```
[
	{
		"id": 1,
		"firstName": "Monalysa",
		"agency": "1553",
		"address": "Rua Bela Cintra, 1234",
		"document": "567.890.123-46",
		"email": "monalysa@example.com",
		"password": "152",
		"accounts": [
			{
				"account": 1,
				"agency": "1553",
				"balance": 4000.00,
				"document": "567.890.123-46",
				"statusType": "COMMON",
				"id": 1
			},
			{
				"account": 2,
				"agency": "123",
				"balance": 90.00,
				"document": "567.890.123-46",
				"statusType": "COMMON",
				"id": 2
			}
		]
	}
]
```
Para realizar transferencia todas as contas:
#### get:http://localhost:8080/trasaction

```
{
	"senderAccount": 1,
	"receiverAccount": 2,
	"value": 10
}

```
Seu response:
```
{
	"id": 1,
	"account": null,
	"amount": 10,
	"sender": {
		"account": 1,
		"agency": "1553",
		"balance": 3990.00,
		"document": "567.890.123-46",
		"statusType": "COMMON",
		"id": 1
	},
	"receiver": {
		"account": 2,
		"agency": "123",
		"balance": 100.00,
		"document": "567.890.123-46",
		"statusType": "COMMON",
		"id": 2
	},
	"timestamp": "2024-01-17T04:04:27.3705018"
}
```
### Insomia:
```
\contabancaria\contabancaria\src\main\java\com\yoshikawa\contabancaria\insomia\Insomnia_banckAccount
```

### Requesitos Tecnicos

- Spring Boot
- Java 17
- h2
- JPA
- maven
- Junit e Mockito



## Como executar o projeto
clone the repository $ git clone https://github.com/LysaKYoshikawa/contabancaria

- Ambiente de Java preparado segue alguns links para ajudar:
- https://www.devmedia.com.br/preparacao-do-ambiente-para-desenvolvimento-em-java/25188
- https://www.youtube.com/watch?v=M5HyVZJocjM

Utilize uma idea. Eu indico Intelijj

Abra o porjeto pela idea, instale as dependencia do Maven

Prepare o local para rodar o projeto 

![image](https://github.com/LysaKYoshikawa/contabancaria/assets/64383080/89ce32d9-45e8-43c7-8770-46a87a1453ae)

Execute o projeto e faça os testes de requisição. Indico Insomia ou Postman

# :pushpin: Author
Monalysa Klauck Yoshikawa
[Linkedin] : <https://www.linkedin.com/in/monalysa-yoshikawa/>
