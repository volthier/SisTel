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

5. Setar as variavéis de ambiente listadas a baixo:

  5.1. Banco de Dados (Local/Docker)

    5.1.1 Chamada arquivo application.propreties
  
      5.1.1.1 Mysql
        spring.datasource.url= jdbc:mysql://${ENVDB}:3306/dbditel
        spring.datasource.username= ${USERDB}
        spring.datasource.password= ${PASSDB}

      5.1.1.2 Postegres
        spring.datasource.driver-class-name= org.postgresql.Driver
        spring.datasource.url= jdbc:postgresql://${ENVDB}:5432/dbditel
        spring.datasource.username= ${USERDB}
        spring.datasource.password= ${PASSDB}

	  5.1.2 Variáveis ambiente
        ENVDB = Ip da base
        USERDB = Usuário da base
        PASSDB = Senha da base

  5.2. Serviço Email

    5.2.1 Chamada arquivo application.propreties
      email.host= ${ENVMAILHOST}
      email.port= ${ENVMAILPORT}
      email.protocol= ${ENVMAILPROTOCOL}
      email.smtp.aut= ${ENVSMTPAUTH}
      email.tls.enable= ${ENVTLSENABLE}
      email.debug= ${ENVMAILDEBUG}
      email.timeout= ${ENVMAILTIMEOUT}

5.2.2  Variáveis ambiente

      ENVMAILHOST= mailapp.xxx.xxx.xxx
      ENVMAILPORT= 25
      ENVMAILPROTOCOL= smtp
      ENVSMTPAUTH= true
      ENVTLSENABLE= true
      ENVMAILDEBUG= false
      ENVMAILTIMEOUT= 50000

5.3. LDAP(Configuração de Autenticação e consulta)*

  5.3.1 Chamada arquivo application.propreties
  
      ldap.userDn= ${LDAPUSERDN}
      ldap.passDn= ${LDAPPASS}
      ldap.url= ${LDAPURL}
      ldap.base= ${LDAPBASE}
      ldap.user.search.filter= ${LDAPUSERSEARCHFILTER}
      ldap.user.search.base= ${LDAPUSERSEARCHBASE}

	5.3.2 Variáveis ambiente
      LDAPUSER= Usuario de login
      LDAPPASS= Senha 
      LDAPUSERDN= CN=Usuariologin,OU=XXXXX,OU=YYYYY,OU=ZZZZZ
      LDAPURL= ldap://000.000.000:389
      LDAPBASE= DC=XXXXX,DC = XXXX
      LDAPUSERSEARCHFILTER= XXXXXXXXXXXX
      LDAPUSERSEARCHBASE= XXXXXXXXXXXX
      
6. Configurar o SEI (Sistema Eletrônico de Informação) para comunicação via WEBSERVICE:

  6.1. Operações requisitadas

        -Gerar Procedimento
        -Listar Usuarios Com Permissões na Unidade
        -Consultar Documento
        -Consultar Procedimento
        -Incluir Documento
        -Incluir Documento em Bloco
        -Disposnibilizar Bloco
        -Gerar Bloco
        -Excluir BlocoRetirar 
        -Documento de Bloco
        -Cancelar Disponibilização de Bloco

  6.2. Chamada arquivo application.propreties

     sei.sistema= ${SEISISTEMA}
     sei.servico= ${SEISERVICO}

  6.3. Variáveis ambiente

      SEISISTEMA = REGISTRO NO SEI DA APLICAÇÃO
      SEISERVICO = REGISTRO NO SEI DA APLICAÇÃO
      SEIURL =  Url do sei a ser utilizado via WebService(Homolog, Treinamento , Produção ou etc.)
