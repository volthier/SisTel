# SisTel
Sistema de telefonia

## Como desenvolver

1. Suba um serviço de banco postgres à sua escolha

Caso queira um banco rapido, sugerimos o container de postgres usado no salic :D

```
docker run --name db-postgres -p 5432:5432 -e POSTGRES_PASSWORD=root -e POSTGRES_USER=postgres -e PGDATA=/var/lib/postgresql/data -v [pasta data local]:[pasta data container] -d culturagovbr/salic-db

```

2. Criar um database e um schema chamados dbditel

3. Subir o container do nosso repositorio docker-sistel - https://github.com/culturagovbr/docker-sistel

4. Efetuar os passo-a-passo do repositorio [docker sistel](https://github.com/culturagovbr/docker-sistel).
