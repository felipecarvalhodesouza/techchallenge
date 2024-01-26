# Esse projeto é o artefato do primeiro tech challenge da Pós Tech da FIAP para o curso de Software Archtecture #

O objetivo é a criaçao de uma API para um sistema de auto atendimento de uma lanchonete.
Os fluxos criados na API para a primeira fazem consistem em:
 * Cadastro/identificação de Clientes
 * CRUD e listagem de Produtos
 * CRUD de Pedidos (com pagamento Fake)
 * Listagem da Fila de Pedidos

O projeto utilizou os conceitos de arquitetura hexagonal (portas e adaptadores), criando interfaces que são usadas pelas camadas de controladores e persistência para 
desacoplar ao máximo a implementação das abstração.
Nessa arquitetura, a camada de domínio, que contém o core da nossa regra de negócio, está separada e independente da escolha de implementação de tecnologias.

 -------- # --------------- #---------------- #-------------------# ---------------------------#-----------------#

Para utilizar a API, é muito simples. Após clonar o projeto, acessar o terminar na pasta raiz e digitar o comando:

 docker compose up -d

 Dessa maneira, o docker compose já realizará todo o trabalho para subir o ambiente de desenvolvimento.

 É possível acessar a documentação do projeto no link:
 http://localhost:8080/swagger-ui/index.html#/

 Para testar a API de uma maneira facilitada, acessar o seguinte Postman (necessáro utilizar o Desktop):
 https://www.postman.com/felipe-carvalho-de-souza/workspace/techchallenge-fiap-ps-tech/overview

 Para acessar os reports relacionados a qualidade de código e testabilidade, acessar o SonarQube acoplado ao projeto:
 http://localhost:9000/projects

 O projeto foi desenvolvido pensando nos pilares da arquitetura de um software, considerando escalabilidade, manutenção, testabilidade, monitoramento.
 O projeto possui actuator acoplado no link: #TODO de interface gráfica
 http://localhost:8080/actuator
 http://localhost:8080/actuator/health
