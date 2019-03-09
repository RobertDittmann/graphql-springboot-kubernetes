#!/bin/bash

#rebuild project
mvn clean install

#start minikube
minikube start --memory 4096 --cpus 4

#build docker image in Kubernetes docker
eval $(minikube docker-env)
docker build -f Dockerfile -t docker-graphql-springboot .

#enable ingress locally
kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/master/deploy/mandatory.yaml
minikube addons enable ingress

#build application on Kubernetes
#build application on Kubernetes
kubectl apply -f k8s/namespace.yaml
kubectl apply -f k8s/

#install LinkerD
curl -sL https://run.linkerd.io/install | sh

#ADD LinkerD to env variables
export PATH=$PATH:$HOME/.linkerd2/bin

#install linkerD on cluster
linkerd install | kubectl apply -f -

#validate linkerD
linkerd check

#add linkerD to deployments
kubectl get -n graphql deployments/docker-graphql-springboot -o yaml | linkerd inject - | kubectl apply -f -

sleep 60

##open H2
open "http://$(minikube ip)/h2-console"

#open graphiql
open "http://$(minikube ip)/graphiql"

#open graphql schema
open "http://$(minikube ip)/graphql/schema.json"

#open linkerD dashboard
linkerd dashboard