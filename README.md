<h1 align="center" style="font-weight: bold;"> Resumo Gestão do Ciclo de Vida da Aplicação 🔄 </h1>

<p align="center">
Resumo para P1  
</p>

<p align="center">
Data 08/11  
</p>

## 📝 Métodos Ágeis (Scrum)

### Scrum
Prioriza Pessoas, software funcionando, mudanças e relacionamento com o cliente, temos diversos exemplos, como: Scrum, XP, etc... todos baseados nos métodos ágeis, visando manter um rítimo sustentável)

Porcos -> Equipe (doa tudo de sí) | galinha -> usuário (doa só o que é necessário).

Temos algumas classes de pessoas que trabalham no projeto como o PO, o SM e os demais integrantes do time. São utilizados alguns conceitos, como:

- Product Backlog (é incremental e emergente, guarda o que precisa ser implementado)
- Sprint (período de tempo que determinadas tarefas devem ser concluidas)
- Reunião de Revisão (reunião para averiguar como foi o desenvolvimento da Sprint)
- Reunião de Planejamento (reunião para o planejamento da próxima sprint)
- Sprint Dayli (reunião rapida para se averiguar o que foi feito naquele dia, com perguntas como: O que fiz, o que pretendo fazer e se tenho algum impedimento
- Release Plan (conjuntos de Sprints que terão um produto viável)
- MVP (mínimo produto para atender as necessidades do cliente)

### Histórias de usuário
É uma história curta na perspectiva do usuário, tem que ser possível de implementar em pouco tempo e tem que ser na linguagem do cliente. (independe de tecnologia), com o seguinte template:

FRENTE:

ENQUANTO (as) usuário

EU QUERO (i want) alguma coisa

PARA (in order to) objetivo

VERSO:

Critérios de aceitação (verificar se o que foi pretendido está ok, serão usados na hora de testar). Detalhar algum elemento

- CCC - Card, conversa e confirma
- TUS - Histórias de usuário técnicas, normalmente descrevem requisitos não funcionais.

Caso de uso (longo, objetivo, documentação) X Historia de usuário (curto, objetivo, direto, na conversa)

! Pagliares recomenda caso de uso em coisas específicas demais (como jogo) ou muito complexas

A US é um lembrete para a conversa com o cliente.
Nunca use "usuário", coloque enquanto gerente, enquanto comprador casual, específique seus usuários
e crie personas.

#### Como saber se uma história de usuário é boa?

INVEST

- I -> independente (ela, sozinha, trás valor ao projeto, não que não possa depender de outra)
- N -> Negociável (é possível se trabalhar em cima, negociando sua dificuldade)
- V -> Valor (tem que trazer valor ao cliente, usuário e desenvolvedor)
- E -> Estimavel (tem que ser possível estimar ela, caso não for, a alternativa é um SPIKE: testar algo para poder estimar)
- S -> Smaal [pequena] (uma história de usuário tem que ser pequena, simples, não pode ser grande (ou devemos quebra-la)
- T -> Testável (você tem que conseguir testar a história de usuário)

####  Como dividir em tarefas? (não passa de 16 horas cada)

SMART

- S -> Específica
- M -> Estimavel
- A -> Arquitetura
- R -> Relevante
- T -> Tempo Definido

#### Se eu tenho uma história de usuário muito grande, como posso quebra-la?

SPIDR 

- S -> SPIKE (faça um spike para entender melhor a história)
- PATH -> CAMINHOS (ver os caminhos possíveis que se pode tomar naquela história e dividir neles)
- INTERFACE -> (quebra se pensando nas interfases, sistemas operacionais, computadores diferentes, divide pra cada um)
- DATA -> (quebra pelo número de informações que o usuário ira passar, por exemplo, se tem um formulario com 10 questionamento, melhor quebrar em 5 em cada US)
- RULES -> (quebra de acordo com as regras daquele negócio em específico)

#### Como saber se o PB está ok?

DEEP

- D -> Detalhado (de forma propriamente, um PB completamente detalhado vira mais algo cascata)
- E -> Estimado 
- E -> Emergente (vai mudando e emergindo a cada checada)
- P -> Priorizado (o que tem maior prioridade fica no topo, com maior detatlhamento [granulidade]

Definição de PRONTO -> Sempre bom ser no estilo (slice the cake -> pega a interface, o código e o banco de dados)

Temas -> Epicos -> Histórias de Usuário

Mapa de história de usuário -> prioriza as histórias de usuário em um mapa, encontrando o MPV (mínimo produto viável) 

User stories Workshop -> Um dia só pra escrever histórias de usuário

### Product Owner 

Crucial para o Scrum, normalmente, é ele que fica responsável por ver se o produto vai suprir as necessidades, sempre presente nas reuniões de Scrum, guia o projeto, aceitando e negando trabalhos, tem que ser visionário, lider, comunicativo e bom de negociação, comprometido e disponível, conflitos são resolvidos por ELE, tem que ser lider e PARTE DA EQUIPE, com autoridade.

PO - "O que" (gere como o produto tem que ser) | Sm - "Como" (gere o método scrum pro time funcionar)

O que o PO fica responsável:
- Criar a visão do produto
- Cuidar do grumming do PB
-  Criar um plano de liberação
-  Envolver clientes, usuários
-  Gerenciar o orçamento
-  Preparar a apresentação do Produto.

Ele deve fazer: Visão do produto, product backlog, diagrama da arquitetura para modelagem, fazer o sprint burndown (dentro do sprint) e o release burndown (dentro das releases, fora do sprint) 

Erros comuns: Sem poder, sobrecarregado, parcial, distante, fica mandando substituto.

#### Product Backlog
Lista priorizada para o trabalho no projeto, em cima do PB ficam o que é mais granulizado, como histórias de usuário, enquanto embaixo ficam os épicos, e temas, respctivamente.

Cada US, épico... tem pontos de história, são pontos que serão levados para estimar sua dificuldade, normalmente, utiliza-se fibonacci e de 1 até 13 são US, 13 até 20/30 são Epic
os e maior que isso são Temas.

Deve-se basear a priorização em CONHECIMENTO, INCERTEZA E RISCO, esses devem ser feitos primeiro, assim como os que geram depêndencias (criar um sistema de transação antes da conta do cliente é inviável, por exemplo) 

PB -> Pontos de história | Spring Backlog -> Horas

PB não é lista de presente do papai noel, não tem que ter TUDO detalhado já de primeira, se não parece mais um documento de requisitos. 

#### Visão do Produto 
Captura a ESSÊNCIA do produto
-> todos devem gostar da visão do produto pra se comprometer com o projeto.
Perguntas como: Quem compraria, quem usaria, com será e o que faŕa. CURTA

MPV -> minimamente o que deverá ser feito para suprir as necessidades do cliente.
Protítipos e moch ups de baixo custo são bem vindos

Road Map -> mostra como o produto deve evoluir com o  tempo, foque de 6 a 12 meses, nada mais que isso -> data, cliente alvo e top 5 funcionalidades 

INCORPORE O CLIENTE na criação da visão
Quando o produto amadurece, ele pode ter variantes (como o java SE e o java EE)

#### Plano de Liberação
Como o software sera liberado, normalmente, entre n sprints.
O PO deve assegurar entre Tempo, custo e funcionnalidade, normalmente um fica fixo e ele deve escolher qual modificar se algo não estiver indo como planejado.

Lei de brooks: "por um novo  integrante pode ser ruim quando o projeto está atrasado"

As liberações devem ser rápidas e frequentes, para que o cliente de feedback o mais rápido possível

#### Burndown de Liberações 
Sprint burndown -> grafico que mostra a queima de PUS por dia.

Erros comuns de PO -> fazer um show na release plan, sendo que não é isso
PO turista, passivo, insustentavel.

O Scrum dos Scrums é uma reunião que conecta várias equipes Scrum que estão trabalhando juntas em um projeto maior. Ele é usado para coordenar atividades e resolver dependências entre equipes, garantindo que todos estejam alinhados e que obstáculos interequipes sejam resolvidos rapidamente.

## 🗄️ MySQL

Para persistir os dados que utilizamos em uma aplicação, utilizamos banco de dados, esses que são baseados na linguagem SQL.

Ele deve ser instalado tanto em linha de comando e pode ter sua parte visual, como o MySQL Workbench.

Alguns Comandos básicos:
- ``` mysqladmin -u root password 'senha' ``` OU ``` mysql -u root -p ``` -> Entra no MySql com o usuário Root
- ``` Show databses ``` -> mostra os banco de dados disponíveis 
- ```create database NOME;``` -> cria um banco de dados com o nome correspondente
- ```mysql -u root -p nomeBanco < nomeArquivo.sql``` -> importar arquivo que contém os comandos para dentro do SQL
- ```use NOME;``` -> Seleciona o banco de dados
- ```show tables;``` -> Mostra as tabelas do banco de dados
- ```describle NOMETABELA``` -> Mostra a tabela em sí e suas colunas
- ```Select * from NOMETABELA``` -> Mostra todas as tabelas em sí do banco de dados
- ```Select NOMECOLUNA from NOMETABELA```  -> Para mostrar colunas em escecífico
- ```quit;``` -> sair

Como criar um user novo:
- ```CREATEUSER 'nome'@'%' IDENTIFY BY 'senha';``` -> cria um usuário com o nome e a senha
- ```GRANT ALL PRIVILEGES ON nomedobanco.nometabela(ou* para todas) TO 'nome'@'%';```

Códigos para se criar uma tabela:

```bash
CREATE DATABASE nomeBanco;
USE nomeBanco;
DROP TABLE IF EXISTS Tabela;

CREATE TABLE Tabela(
                         PRODUTOS_ID BIGINT AUTO_INCREMENT PRIMARY KEY,
                         NOME VARCHAR(225),
);

INSERT INTO  Tabela (NOME)
VALUES ('Inserir');

Quando eu quero fazer uma tabela pra uma relação:

CREATE TABLE tabela01_tabela02(
       table01_ID BIGINT,
       table02_ID BIGINT,
       PRIMARY KEY (table01_ID, table02_ID),
       FOREIGN KEY (table01_ID) REFERENCES USER(table01_ID) ON DELETE CASCADE,
       FOREIGN KEY (table02_ID) REFERENCES ROLE(table02_ID) ON DELETE CASCADE
);

Uma maneira fácil de inserir é:
INSERT INTO tabela01_tabela02 (table01_ID, table02_ID)
VALUES (oValorDoPrimeiro, oValorDoSegundo);
```

## 🌱 Git 

O git é utilizado para fazer o controle de versões de um código. Controle de versão é de arquivos, á exemplo, se uma pasta sem nada for criada, se ela nao tiver um arquivo, ela não é reconhecida.

surgiu em 2005.

Não é recomendado para aquivos binaríos, músicas, imagens, pdf (não tem tecnologia específica)

Controle de versão distribuido -> não tem um repositório central, cada desenvolvedor tem seu próprio repositorio em sua máquina

GitWorkflow: Diretorio -> Stage Area -> Repositório

### CONCEITOS:
- HEAD -> Ponteiro que aponta pro commit atual daquele repositório (mais recente)
- git status -> verifica o que está na stage area, o que está no repositoprio ou no diretorio
- .gitignore -> arquivos que o git deve ignorar nos commits e adds

## 🔌 JDBC

## 🏷️ JPA

## 🚀 Spring Boot 


