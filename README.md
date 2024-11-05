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
- .gitignore -> arquivos que o git deve ignorar nos commits e adds

### CONFIGURAÇÃO e COMANDOS BASICOS:
- ``` git status ```
- ``` git config --global user.name "Maicon Mian"```
- ```git config --global user.email "maicon.."```
- ```git config --global core.editor "vscode"``` 
- ```git config --list``` (lista todas as configurações, essas que estão em um aquivo (.gitconfig), mas não é recomendado alterar por ele)

### CRIAÇÃO DE REPOSITORIO
- ```git init ``` -> inicia o repositório no diretorio atual, criando uma pasta chamada .git com as configurações do mesmo
- ```git add *``` -> o * pode ser um . para tudo ou o nome do arquivo que deve ser mandado pra stage area
- ```git commit -m "mensagem" ``` -> manda da stage area pro repositorio
-``` git log``` -> mostra todos os commits como também os autores.

### MANIPULAÇÃO DE ARQUIVOS
- ```git diff``` -> mostra todas as diferenças entre o arquivo do diretorio e do repositorio
- ```git diff --staged``` -> mostra as diferenças entre o arquivo do diretorio e o que esta na stage area (-- repo, ++ diretorio)
-``` git rm nomeArquivo``` -> Remove tanto do seu diretorio quanto do repositorio (joga pra stage area)
pode remover manualmente e comittar depois
- ``` git mv nomeArquivo LocalAtual/Novo Nome``` -> novamente, faz no repositorio e no diretorio (vai pra stage area). Ao renomear um arquivo, o git entende que você removeu o anterior e tá adicionando um arquivo com o nome novo.
- ```git clean``` -> remove todos os arquivos do diretorio que nao tao no repositorio (-n lista eles antes)

### REMOVER MODIFICAÇÕES

- Sem mandar pra lugar nenhum
```git checkout -- nomeArquivo``` -> restaura o arquivo do diretorio pro arquivo do repositório

- Na stage Area
```git reset HEAD nome arquivo ```-> retira da satage area 

- Já commitado:
```git commit —amend -m mensagem```(desfaz o ultimo commit e refaz ele)

### RECUPERAR COMMITS ANTERIORES
```git checkout CODIGODOCOMMIT``` -- NomeArquivo.

```git reset --FUNCIONALIDADE codigoDoCommit ```-> transfere o HEAD pro commit que vc quer e faz, de acordo com a funcionalidade:
- -- soft -> manda os arquivos que estava no commit pra stage area
- -- mixed -> manda os arquivos que estavam no commit pro diretorio
- -- hard -> não manda pra lugar nenhum

### BRANCHES
Formas de se trabalhar paralelamente, forma de assegurar que so mandara para producao algo testado.

- ```git -d branch NomeBranch ```-> Criar
- ```git -d checkout branch NomeBranxh``` -> Mudar para ela
- ```git branch —d nome ```(nao da pra remover estando nela) -> remover

### MERGE EM BRANCHES
Após feito um merge (com o comando git merge BranchMergeada), podem acontecer 3 tipos de merge: 

- Fast-Forwald (a brendh original vai ate o commit 3 e a branch nova vai mais 2 commits, ou seja, nao tem como ter conflito, so tras o que e tem em uma pra outra)
- Recursivo (quando duas branches tem arquivos diferentes alterados, ai apenas une as diferenças)
- Conflict(cada branch alterou o mesmo arquivo da mesma forma, ai voce deve abrir o editor de texto e resolver o conflito)

## 🔌 JDBC
Biblioteca Java responsável por ligar o banco de dados ao seu código, pelo Maven, basta adicionar a dependência: mysql.connector.java

### CONEXÃO

são necessárias os imports:
-``` import java.sql.Connection;```
- ```import java.sql.DriverManager;```
- ```import java.sql.SQLException;```

Para conectar com o banco, é necessário criar uma classe (como a DataBaseUtility). Nela, é necessário definir:

- ```private static final String USERNAME = "florentino";```
-``` private static final String PASSWORD = "123456";```
- ```private static final String CONNECTION_STRING ="jdbc:mysql://localhost:3306/hostelapp_jdbc";```

Após fazer isso, você pode criar um método na sua classe que retona a conexão para você a usar em diversas partes do seu código:
- ```return DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD); ```

### CONNECTION
Váriável responsável por criar a conexão em sí com o banco de dados, da seguinte forma:
- ``` conn = DatabaseUtility.getConnection(RDBMS.MYSQL);```
  
### STATEMENT
Usado para Querys com o JDBC (adiciona, remover, modificar)

- ```Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); ```-> pode ser percorrido (boa pratica) 
- ``` Statement stmt = conn.createStatement();``` -> somente de avanço (só pode ler pra frente)

Prepare Statment → verificação de segurança, já vem pré compilado, impede SqlINjection.
- ``` PreparedStatement preStmt = conn.prepareStatement(SQL, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);```
  
Para usar, você coloca o ? no lugar da consulta e retorna em outra parte do código, á exemplo:
- ```PreparedStatement stmt = conn.prepareStatement("SELECT * FROM ADMIN WHERE ADMIN_ID = ?");```
= ```stmt.setInt(1, adminId);``` -> substitui a posição do primeiro ?


### RESULTSET
Retorna o resultado da consulta (normalmente, vindo de SELECT).

- ```rs = stmt.executeQuery("SELECT * FROM admin");```
- ```rs.next()``` -> sempre vai pegar o proximo elemento do resultado, pode se usado num while por exemplo para mostrar todos os cliente
- ```rs.getString(NOMECOLUNA) ou rs.getInt(NOMECOLUNA) ```, pode ser usado: rs.getObject("GUEST_ID", Integer.class) 
- ```rs.last()``` -> vai pra ultima linha
- ```rs.first()``` -> vai pra primeira
- ```rs.absoluted(int row)``` -> move a uma linha específica
- ```rs.getRow()``` -> retorna a linha que estamos atualmente


Todas essas variáveis podem ser fechadas colocando o nome delas .close

Como usar o StringBuffer:

- ```tringBuffer buffer = new StringBuffer();```
- ```buffer.append("Guest ID......: " + rs.getInt("GUEST_ID") + "\n");```

para setar o numero maximo de linhas, podemos usar o setMaxRows, o que é uma PESSIMA pratica, pos é necessário por o try e finally além de ter um desempenho ruim, o recomendado é ResultSet rs = stmt.executeQuery("SELECT * FROM GUEST LIMIT 5, 3");

### INSERIR:
- ```String sql = "INSERT into admin (userName, password) " + "VALUES (?, ?)"```
- ```stmt.setString(1, bean.getUserName());```
- ```stmt.setString(2, bean.getPassword());```

### UPDATE:
- ```String sql = "UPDATE ADMIN SET " +"USERNAME = ?, PASSWORD = ? " + "WHERE ADMIN_ID = ?";```
### DELETAR:
- ```String sql = "DELETE FROM ADMIN WHERE ADMIN_ID = ?";```

ao fim de todos: int affected = stmt.executeUpdate();

Como criar conexões é TRABALHOSO, é importante implementar e reutilizar, como um sigleton
- InnoDB oferece suporte a transações, chaves estrangeiras, etc.
- MyISAM é o mecanismo padrão, mas não oferece suporte a transações, por exemplo.

```
Metadados:
   DatabaseMetaData metadata = conn.getMetaData();
   String[] tableTypes = {"TABLE"};
   rsTables = metadata.getTables("hostelapp_jdbc", "%", "%", tableTypes);
   while (rsTables.next()) {
   	System.out.println(rsTables.getString("TABLE_NAME"));
   }
```
   
JavaBens -> boas práticas, crie uma classe padrão para seu objeto com os atributos, gets e sets, e um controller para fazer modificações no banco

## 🏷️ JPA

## 🚀 Spring Boot 


