#!/bin/bash

c_name="localhost"
c_csr="${c_name}.csr"
c_key="${c_name}.key"
c_crt="${c_name}.crt"

cert_directory="/tmp/jdocker-registry/"

echo "Creating directory ${cert_directory}"

mkdir -p "${cert_directory}"

echo "Generating certs..."

# openssl req -nodes -newkey rsa:2048 -keyout $c_key -out $cert_directory/$c_csr
# openssl x509 -req -in $cert_directory/$c_csr -signkey $cert_directory/$c_key -out $cert_directory/$c_crt -subj "/CN=SE"

KEY_NAME="${cert_directory}/${c_name}"
CSR_CONFIG="registry.cnf"

openssl req -config $CSR_CONFIG -new -newkey rsa:2048 -nodes -keyout ${KEY_NAME}.key -out ${KEY_NAME}.csr
openssl x509 -req -days 365 -in ${KEY_NAME}.csr -signkey ${KEY_NAME}.key -out ${KEY_NAME}.crt

docker run -d -p 5000:5000 --restart=always --name registry \
  -v `pwd`/auth:/auth \
  -e "REGISTRY_AUTH=htpasswd" \
  -e "REGISTRY_AUTH_HTPASSWD_REALM=Registry Realm" \
  -e REGISTRY_AUTH_HTPASSWD_PATH=/auth/htpasswd \
  -v /tmp/jdocker-registry/:/certs \
  -e REGISTRY_HTTP_TLS_CERTIFICATE=/certs/localhost.crt \
  -e REGISTRY_HTTP_TLS_KEY=/certs/localhost.key \
  registry:2

