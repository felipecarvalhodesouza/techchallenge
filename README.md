# Esse projeto é o artefato do segundo tech challenge da Pós Tech da FIAP para o curso de Software Archtecture #

O objetivo é a criaçao de uma API para um sistema de auto atendimento de uma lanchonete.
Os fluxos da API consistem em:
 * Cadastro/identificação de Clientes
 * CRUD e listagem de Produtos
 * CRUD de Pedidos (sem integração com métodos de pagamentos, por hora)
 * Consulta do status de pagamento de um pedido
 * Listagem da Fila de Pedidos de acordo com o status, e avanço de status de preparação.
   
![image](https://github.com/felipecarvalhodesouza/techchallenge/assets/36648569/bd8b1d68-4428-4ff1-880c-649fb2cfa91a)

O projeto foi pensado para execução em um cluster kubernetes, considerando a escalabilidade de acordo com o processamento de CPU.
De acordo com a atual implementação, os deployments onde a aplicação está rodando são escaláveis, e possuem um load balancer para organização das requisições entre instâncias.
Dentro do cluster, a aplicação se comunica com um deployment responsável pelo repositório de dados, que não possui implementação de escalabidade.

O projeto foi implementado considerando os funcionamentos da arquitetura limpa, onde a regra de negócio, isto é, os casos de usos e entidades estão desacoplados da implementação de frameworks e linguagem, bem como entidades externas, como banco de dados.
É possível realizar manutenção sem risco de quebrar a aplicação, pois o desenvolvimento é voltado a interfaces, seguindo todos os princípios de SOLID.

Como a origem do projeto era um Spring Boot, que usa muito dos conceitos MVC, o desafio foi desacoplar o projeto do framework, principalmente nas questões relacionadas a entidades, pois o Spring Data acopla muito as entidades do domínio com as entidades do banco de dados.

Passos para execução do projeto:
* 1 - Realizar o clone do projeto, para que os arquivos manifesto estejam disponíveis (pasta kubernetes).
* 2 - Dentro da raiz do projeto, abrir um terminal e executar os comandos na seguinte ordem:
   * kubectl apply -f .\kubernetes\mysql-db-config.yaml
   * kubectl apply -f .\kubernetes\mysql-db-secret.yaml
   * kubectl apply -f .\kubernetes\mysql-db.yaml
   * kubectl apply -f .\kubernetes\components.yaml (opcional se já houver um deployment de metrics-server no kube-system)
   * kubectl apply -f .\kubernetes\goodburguer-hpa.yaml
   * kubectl apply -f .\kubernetes\goodburguer.yaml
* 3 - Com os deployments devidamente em funcionamento, será possível acessar a API localmente. É possível acessar a documentação do projeto no link:
 http://localhost:8080/swagger-ui/index.html#/
* 4 - Caso a utilização do Postman seja preferível, é possível baixar as Collections necessárias no link abaixo (necessário utilização do Postman Desktop por se tratar de chamadas locais) :
 https://www.postman.com/felipe-carvalho-de-souza/workspace/techchallenge-fiap-ps-tech/overview
