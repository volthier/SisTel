[![Maintainability](https://api.codeclimate.com/v1/badges/acf4d9d69142d1d4c717/maintainability)](https://codeclimate.com/github/volthier/SisTel/maintainability)

# SisTel
Sistema de telefonia

## Como desenvolver

1. Instale o **Docker**.

  * [Tutorial](https://docs.docker.com/engine/installation/)

* Instale o **docker-compose**.

  * [Tutorial](https://docs.docker.com/compose/install/)

* Atualize as variáveis de ambiente nos arquivos `.env` para serem mapeadas para o container. Em cada arquivo contém uma breve descrição do conteúdo de cada variável.

  * Variáveis de ambiente do container da aplicação estão no arquivo `docker/app/app-variables.env`

  * Variáveis de ambiente do container de banco estão no arquivo `docker/postgres/db-variables.env`

* execute o comando:
  * `docker-compose up`

* Criar um database e um schema chamados dbditel.
