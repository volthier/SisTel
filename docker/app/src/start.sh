echo "[******] Adding new Hosts to /etc/hosts";
bash /tmp/src/addHosts.sh

echo "[******] starting wildfly";
/opt/jboss/wildfly/bin/standalone.sh -b 0.0.0.0
