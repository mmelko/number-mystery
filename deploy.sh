#!/bin/bash
# ==== install Fuse templates
oc login -u system:admin
BASEURL=https://raw.githubusercontent.com/jboss-fuse/application-templates/application-templates-2.1.fuse-000081-redhat-4
oc create -n openshift -f ${BASEURL}/fis-image-streams.json

#AMQ installation
oc create -n openshift -f \
 https://raw.githubusercontent.com/jboss-openshift/application-templates/ose-v1.4.8/jboss-image-streams.json
oc replace -n openshift -f \
 https://raw.githubusercontent.com/jboss-openshift/application-templates/ose-v1.4.8/jboss-image-streams.json

 oc -n openshift import-image jboss-amq-63:1.3

 for template in amq62-basic.json \
  amq62-ssl.json \
  amq63-persistent-ssl.json \
  amq62-persistent.json \
  amq63-basic.json \
  amq63-ssl.json \
  amq62-persistent-ssl.json \
  amq63-persistent.json;
  do
  oc create -n openshift -f \
  https://raw.githubusercontent.com/jboss-openshift/application-templates/ose-v1.4.8/amq/${template}
  oc replace -n openshift -f \
  https://raw.githubusercontent.com/jboss-openshift/application-templates/ose-v1.4.8/amq/${template}
  done


# ==== prepare services

oc login -u developer

#creating new project
oc new-project stability
oc new-app amq63-basic  -p APPLICATION_NAME=stability-broker -p MQ_PROTOCOL=openwire -p MQ_USERNAME=admin -p MQ_PASSWORD=admin
cd number-creator/ && mvn clean package fabric8:deploy
cd number-guesser/ && mvn clean package fabric8:deploy

# === install syndesis
syndesis install
