<h1 align="center" style="font-weight: bold;">  🔄 Resumo Gestão do Ciclo de Vida da Aplicação 🔄 </h1>

Resumo para P1 | Data 08/11 | Maicon Mian

### CONTEÚDO: 
- [Métodos Ágeis (Scrum)](#-métodos-ágeis-scrum)
- [MySQL](#mysql)
- [Git](#-git)
- [JDBC](#-jdbc)
- [JPA](#jpa)
- [Spring Boot](#-spring-boot)

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

<a name="mysql"></a>
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

<a name="jpa"></a>
## 🏷️ JPA
Jpa encapsula o JDBC para que você o utilize mais facilmente, ele é convensão em cima de configuração, ou seja, é melhor já ter um projeto que já vem facilmente pré configurado, mesmo tendo que alterar coisas especídificas do que configurar tudo.

JAVA EE -> JAKARTA EE (no java 8)

Importante: informaçãoes do banco de dados para conexão ficam num arquivo XML chamado persistence, la vc coloca o nome do banco, o id, a senha, etc…

### CRIANDO CONEXÃO 

- ```private static EntityManeger entitymaneger```
- ``` private statics EntityManegerFactory entitymanegerfactory```
- ```statitc (cria) ```
- ```para fechar, chama o atributo e da .close```

### CRIANDO OBJETOS
No topo da classe que será uma tabela:
- ``` @Entity ```
-``` @Table (name=“nome”) ```-> apenas caso queira mudar o nome

Setando colunas:
```
@Id
@Generated.Value(IDENTITY)
```
NORMAL:
- se nao fizer nada, ele vai mapear cada um dos ateibutos com o nome original, vc pode mudar no get para:``` @Column(name=“”, nulltable=false)```

### TRANSIÇÕES 

No JPA, toda transicao (remover, modificar) tem que ter o:
- ```this.entitymagener.gettransition().begin();```
- ```código```
- ```this.entitymagener.gettransition().commit();```
  
this.getEntityManager() -> na frente dos demais:
- ```.merge(Objeto) ```-> tal que o Objeto foi modificado
- ```.find(Classe.class, id)``` -> Encontrar um objeto pelo ID
- ```.remove(Objeto) ```-> remover um objeto
  
- ```@Transient  ```-> para não rastrear algum atributo
- ```@Temporal(Temporal.Type.TIMESTAMP) ```-> ANTES JAVA 8 voce tinha que colcoar isso pra definir se o Date era hora, dia, etc…, após o JAVA 8 ele mapeia automaticamente com o LocaDate, LocalDatetime, etc…
- ```@Enumered(EnumType.String```) ou Ordinal Tipo Enumerado

Para mapear uma classe dentro da tabela de outra (como endereço em cliente) basta por isso na classe filha:
- ``` @Enbeddable```
Na pai, em cima do atributo:
- ``` @Enbedded```
- ``` @AtributesOverrides({ @Attribute Override (name=“name), @Columns=“NAME_ENDERECO”});```

## 🚀 Spring Boot 

- Spring é um framework java focado em fornecer ferramentas para codagens de aplicações robustas e escaláveis, que desejam comunica com banco de dados, web, tudo de sí.
- Spring Boot é um complemento do Spring que visa facilitar ainda mais a inicialização de aplicações Spring focados em Restful.

Para rodar uma aplicação Spring com Maven, basta: ``` spring-boot:run```

GET é mais adequado para recuperar informações, enquanto POST é mais indicado para enviar ou criar novos dados no servidor.

### PROGRAMA SIMPLES
[Programa Simples](https://spring.io/quickstart)
Para iniciar seu projeto com Spring, basta ir ao site, colocar a versão, dependencias necessárias, etc.

Na assionatura da classe do seu programa para web, deve-se ter:
- ```@SpringBootApplication ```-> classe principal do Spring
- ``` @RestControlle```r -> controlador REST (reponsavel por tratar GET, POST, PUT, DELETE)
- SpringApplication.run(DemoApplication.class, args); -> dentro da main, para iniciar a aplicação.

```
@GetMapping("/hello") -> mapeia que quando receber um GET com hello, /vai pra esse metodo
    @RequestParam -> algo devera vir junto com a resuisição, o valor é name e se nada vier, fica world
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
      return String.format("Hello %s!", name);
    }
```
- http://localhost:8080/hello -> ira rodar a explicação

### PROGRAMA COM JDBC 
[Programa Com JDBC](https://spring.io/guides/gs/relational-data-access/)
Deve-se criar um projeto com a dependencia do JDBC e utlizar 
- ``` import org.springframework.jdbc.core.JdbcTemplate;``` 
- ```JdbcTemplate jdbcTemplate;```
  
Ele encapsula diversas operações do JDBC, tais como:
- ```jdbcTemplate.execute("DROP TABLE customers IF EXISTS");``` -> executar comando
- ```jdbcTemplate.batchUpdate("INSERT INTO customers(first_name, last_name) VALUES (?,?)", splitUpNames); ```-> para inserir na tabela

```java

@SpringBootApplication // setando a classe principal do spring
// Implements CommandLineRunner -> diz que a aplicação spring vai continuar rodando após iniciar
public class RelationalDataAccessApplication implements CommandLineRunner{

	// seta um logger para que conseguimos observar erros e acompanhar o processamento do sistema
	private static final Logger log = LoggerFactory.getLogger(RelationalDataAccessApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(RelationalDataAccessApplication.class, args);
	}

	@Autowired
	JdbcTemplate jdbcTemplate; // injetando a dependencia do jdbc template (que facilita a codagem)

	@Override // sobrescreve o método run (String... String é quando não se tem o número de Strings)
	public void run(String... strings) throws Exception {

		log.info("Creating tables"); //para mostrar no log que estamos criando tabelas

		// .execute -> executando querys do JDBC (sem precisar setar o preparedStatment, como podemos ver)
		jdbcTemplate.execute("DROP TABLE customers IF EXISTS");
		jdbcTemplate.execute("CREATE TABLE customers(" +
				"id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");

		// aqui estamos criando a lista de objetos que serão usados para adicionar ao banco de dados
		List<Object[]> splitUpNames = Arrays.asList("John Woo", "Jeff Dean", "Josh Bloch", "Josh Long").stream()
				.map(name -> name.split(" "))
				.collect(Collectors.toList());

		// MOstrando na tela todos os customers que setão inseridos
		splitUpNames.forEach(name -> log.info(String.format("Inserting customer record for %s %s", name[0], name[1])));

		// usado para adicionar coisas no banco de dados em massa
		jdbcTemplate.batchUpdate("INSERT INTO customers(first_name, last_name) VALUES (?,?)", splitUpNames);

		// mostrando em tela tudo que foi criado:
		log.info("Querying for customer records where first_name = 'Josh':");
		jdbcTemplate.query(
						// utilizando o RESULTSET aqui, como podemos ver
						"SELECT id, first_name, last_name FROM customers WHERE first_name = ?",
						(rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name")), "Josh")
				.forEach(customer -> log.info(customer.toString()));
	}

}
```

### PROGRAMA UTILIZANDO RESTFUL 
[Programa Com Restful](https://spring.io/guides/gs/rest-service)
Quando queremos comunicar uma aplicação com paginas web, utilizamos o sistema Restful, esse que pode ser incorporado em dois modos: Restful e MVC (no exemplo, com Thimeleaf) o restful retorna um Json e o front e responsavel por o incorporar enquanto o MVC leva uma pagina HTML, o que os diferencia e que o MVC tem uma tcnologia e fica preso a ela, enquanto o restful e o JSON e vc pode utilizar onde quiser.

basicamente, teremos solicitacoes nesse estilo:

/greetint?name=Maicon, onde o que vem depois do ? sao parametros

para criar o controlador Rest, basta:
- colocar @RestController antes do nome da classe
- criar um metodo com @GetMapping(/greetint) pra mapear que recebe como parametro (@requestParam(value=“name”, defaultValue=“World) String name
- retorna = return new Greeting(counter.incrementAndGet(), String.format(template, name))

pronto, para chamar a aplicacao basta executar
- @SpringBootAplication na classe com o main
- SpringAplication.run(nomeClasse, argumentos); -> na main

### PROGRAMA UTILIZANDO O THIMELEAF 
[Programa Com Thimeleaf](https://spring.io/guides/gs/handling-form-submission)
Basicamente, o que tem de diferente e que iremos resolver as solicitacoes enviando um HTML pronto em vez de um Json, basta voce ter um controlador que vai mapear o POST e o GET:
- ```@Controller```
- ```@GetMapping(/greeting)```
- ```@PostMappging(/greeting)```
voce deve mapear eles em cada classe e recebe um model e retorna a pagina HTML, que deve ter um modelo (classe com os atributos) para retorno:
```model.addAtribute(“greeting”, new greeting)```

e nas paginas HTML devem ter o mesmo nome das solicitacoes e sempre terao:
```xmls:th=line``` no read e durante todo o HTML terao referencias a pontos que serao mudados dinamicamente pelo thimeleaf, como:
- ```th:catian=@{/greeting}```
- ```th.objet=${classe}```
- ```th.fiel=“*{id}”```


