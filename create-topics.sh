#!/bin/bash
# Espera o Kafka iniciar
sleep 30

# Cria o tópico
/kafka/bin/kafka-topics.sh --create --topic insurance-quote-received --bootstrap-server kafka:9092 --replication-factor 1 --partitions 1
/kafka/bin/kafka-topics.sh --create --topic insurance-policy-created --bootstrap-server kafka:9092 --replication-factor 1 --partitions 1

# Mantém o container em execução
exec /etc/confluent/docker/run
