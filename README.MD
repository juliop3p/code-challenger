### Requisitos para rodar o projeto

* Docker
* JDK17
* Maven
* IntelliJ ou Eclipse (ou qualquer outra IDE de sua preferência)
* Postman ou Insomnia (ou qualquer outra ferramenta de sua preferência)

<br>

***

### Como rodar o projeto?

    * 1° Passo: executar o comando `docker-compose up` na raiz do projeto

    * 2° Passo: rodar o projeto insurance-policy manualmente na usa IDE preferida

    * 3° Passo: rodar o projeto insurance-quote manualmente na usa IDE preferida

<br>

***

### Como chamar os Endpoints?

Temos dois endpoints disponíveis:

- `POST`: Criar cotação `http://localhost:8080/quotes`

- `GET`: Consultar cotação `http://localhost:8080/quotes/{idCotacao}`

#### [Documentação da API aqui](https://documenter.getpostman.com/view/33003809/2sAXjKaCvX)

<small>Em anexo tem a collection do postman: Cotação de Seguros.postman_collection.json</small>

Produto Ativo: `http://localhost:1080/api/products/1b2da7cc-b367-4196-8a78-9cfeec21f587`

Produto Inativo: `http://localhost:1080/api/products/2b3da7cc-a678-4896-8a78-9cfeec21f589`

Oferta Ativa: `http://localhost:1080/api/offers/adc56d77-348c-4bf0-908f-22d402ee715c`

Oferta Ativa: `http://localhost:1080/api/offers/edc56d77-348c-4bf0-908f-22d402ee718c`

<br>

***

### Detalhes da solução

<details>
    <summary>1° Infraestrutura base</summary> <br>
    <p>O projeto começou com a construção da infra base, seguindo a seguinte ordem:</p>
    <ul>
        <li>Serviço de catálogo com MockServer</li>
        <li>Banco de Dados MongoDB</li>
        <li>Kafka</li>
    </ul>
</details>
<details>
    <summary>2° Desenvolvimento do domínio da aplicação</summary> <br>
    <p>Na segunda parte, foquei no desenvolvimento do domínio da aplicação usando DDD para alinhar a lógica do negócio
        com a estrutura do sistema, isolando as regras do domínio das demais partes do sistema.</p>
</details>
<details>
    <summary>3° Construção da camada de aplicação e comunicação com serviços/dados</summary> <br>
    <p>Nessa etapa, comecei a permitir a interação com o domínio através da camada de aplicação/api, construindo os
        controllers para acessar o domínio e, em seguida, implementei as classes de interação com o serviço de catálogo
        e de persistência.</p>
</details>
<details>
    <summary>4° Construção do serviço de apólice mockado</summary> <br>
    <p>Aqui, construí um serviço simples para receber o ID da cotação e, com isso, gerar um ID aleatório para enviar ao
        serviço de cotação via broker.</p>
</details>
<details>
    <summary>5° Comunicação com broker</summary> <br>
    <p>Por fim, foquei na comunicação com o broker, enviando a cotação realizada com sucesso e ouvindo o retorno com o
        ID da apólice.</p>
</details>

<br>

***

### Melhorias pendentes
    - [ ] Ajustar o docker-compose para rodar todos os projetos
    - [ ] Testes de unidade e integração
    - [ ] Cobertura de testes (Code Coverage)
    - [ ] Observabilidade (métricas, traces e logs)
