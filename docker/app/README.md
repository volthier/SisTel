# docker-sistel
Dockerfile e afins do container do sistel 

## Como rodar

1. Construa a imagem do docker, chame ela de sistel :D
```
docker build -t sistel .
```
2. Rode o container à partir da imagem sistel (container vai chamar sistel também)
```
docker run -it --name sistel -p 80:8080 -e ENVDB=$ENVDB -e USERDB=$USERDB -e PASSDB=$PASSDB -e URLLDAP=$URLLDAP -e LDAPBASE=$LDAPBASE -e USERLDAPDN=$USERLDAPDN -e USERLDAP=$USERLDAP -e PASSLDAP=$PASSLDAP -e ENVMAILHOST=$ENVMAILHOST -e ENVMAILPORT=$ENVMAILPORT -e ENVMAILPROTOCOL=$ENVMAILPROTOCOL -e ENVSMTPAUTH=$ENVSMTPAUTH -e ENVTLSENABLE=$ENVTLSENABLE -e ENVMAILDEBUG=$ENVMAILDEBUG -e ENVMAILTIMEOUT=$ENVMAILTIMEOUT -v [DIRETORIO-WAR]:/opt/jboss/wildfly/standalone/deployments/ sistel
```

Obs: as variáveis de ambiente acima precisam ser definidas, ENVDB = host do banco de dados

## Conexão com bancos

Atualmente basta setar a variável ENVDB com o ip ou o nome do host, lembrando que se for o nome, ainda será necessário adicionar o mapeamento de host no arquivo src/addHosts.sh

## Log

localização do log dentro do container: /opt/jboss/wildfly/standalone/log/server.log
