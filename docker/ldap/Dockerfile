# Configuração Default Simples
# docker run --name isam-openldap --detach ibmcom/isam-openldap:latest

# Configuração Avançada
#docker run --hostname isam.openldap --name isam.openldap \
#--detach \
#--publish 636:636 \
#--volume /var/lib/ldap \
#--volume /etc/ldap/slapd.d \
#--volume /var/lib/ldap.secAuthority \
#--env LDAP_DOMAIN=gov.br \
#--env LDAP_ADMIN_PASSWORD=123456 \
#--env LDAP_CONFIG_PASSWORD=123456 \
#ibmcom/isam-openldap:latest

############################################################################################################################
#            Fonte de Informações                                                                                          #
# https://www.ibm.com/support/knowledgecenter/en/SSPREK_9.0.6/com.ibm.isam.doc/admin/concept/con_docker_user_registry.html #
#                                                                                                                          #
############################################################################################################################
#
#       MORE :   https://github.com/osixia/docker-openldap
#                https://hub.docker.com/r/osixia/openldap
###########################################################################################################################
FROM ibmcom/isam-openldap:latest
