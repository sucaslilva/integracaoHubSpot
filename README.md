# 🚀 Integração com HubSpot

Este repositório contém uma API REST desenvolvida em **Spring Boot** para integração com o **HubSpot CRM**, permitindo autenticação via **OAuth 2.0**, criação e listagem de contatos, além do recebimento de notificações via **webhooks**.

## 🔗 Endpoints Disponíveis

| Endpoint | Versão |
|-------|---------|
|OAuth 2.0 | 1.0|
|Login | 1.0|
|Criar Contato | 1.0|
|Buscar todos os Contatos | 1.0|
|Webhook Contatos | 1.0|

## ⚙️ Como Executar o Projeto

Para utilizar a API devemos seguir os passos abaixo:

- Clone o repositório
- Configure as variáveis de ambiente que estão comentadas no `application.properties`
- Abra o arquivo hubSpot em sua IDE de preferência (Indicado o IntelliJ)
- Execute o comando `mvn spring-boot:run`

Se ainda não contém os dados para configurar as varíaveis de ambiente, crie uma conta de desenvolvedor no HubSpot: - [HubSpot Developer Account](https://developers.hubspot.com/).

Logo após crie seu aplicativo e tenha acesso as chaves necessárias para essa aplicação funcionar.

Dentro do repositório há um arquivo JSON que pode ser utilizado no Postman contendo os endpoints afim de agilizar os testes da API.

## 📝 Requisitos

- Java 17+
- Maven 3.8+
- Conta de desenvolvedor no HubSpot → [Criar Conta](https://developers.hubspot.com/)
