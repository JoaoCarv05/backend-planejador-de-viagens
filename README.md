## ***Planejador de Viagens - Backend***

Este repositório contém o backend de uma aplicação de planejamento de viagens, criada para auxiliar na organização e gerenciamento de viagens, com funcionalidades voltadas para o cadastro da viagem, atividades e links importantes, além de possibilitar o gerenciamento de convidados e envio automatizado de e-mails de confirmação. Desenvolvido em Java com Spring Boot, o backend integra-se com microserviços e mensageria via Apache Kafka para assegurar comunicação eficiente e escalável entre as diferentes partes do sistema.

### Funcionalidades Principais:
- **Cadastro e Gerenciamento de Viagens:** Serviços REST para criar e gerenciar viagens.
- **Confirmação por E-mail:** Envio de e-mails para participantes e proprietário da viagem.
- **Mensageria com Kafka:** Utilização de tópicos Kafka para comunicação entre microserviços.
- **Estrutura de Microserviços:** Cada serviço possui seu próprio banco de dados, garantindo independência e escalabilidade.

### Tecnologias Utilizadas:
- **Linguagem:** Java (Spring Boot)
- **Mensageria:** Apache Kafka
- **Banco de Dados:** MySQL / PostgreSQL
- **Infraestrutura:** Docker, Google Cloud

