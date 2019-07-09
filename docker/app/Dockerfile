FROM jboss/wildfly

USER root

RUN echo "[ Step 01 ] - Copying files to Image ***** ***** ***** "
COPY ./src /tmp/src

RUN chmod +x -R /tmp/src/

RUN echo "[ Step 02 ] - Exposing ports  ***** ***** ***** "
EXPOSE 8080

#Desablitar Welcome Content WildFly
CMD ["EAP_HOME/bin/jboss-cli.sh","/profile=default/subsystem=web/virtual-server=default-host:write-attribute(name=enable-welcome-root,value=false)"]

# habilitar para criar usuário admin no container
#RUN /opt/jboss/wildfly/bin/add-user.sh admin Admin#70365

#habilitar para a interface de administração funcionar
#CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]

# habilitar caso queira adicionar o arquivo na mao, ao invés de mapear o volume (bom para CIs etc)
#ADD war/Sistel.war /opt/jboss/wildfly/standalone/deployments/

# mapeamento de volume fracassado
#VOLUME ["/home/chris/projetos/docker-sistel/war" , "/opt/jboss/wildfly/standalone/deployments/"]

RUN echo "[ Step 03 ] - Begin of Actions inside Image ***** ***** ***** "

CMD /tmp/src/start.sh
