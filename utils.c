/*+=============================================================
  |           UNIFAL - Universidade Federal de Alfenas.
  |             BACHARELADO EM CIENCIA DA COMPUTACAO.
  |  Trabalho: Geracao de codigo MIPS
  |  Disciplina: Compiladores
  |  Professor: Luiz Eduardo da Silva
  |  Aluno: Maicon Almeida Mian
  |  Data: 04/12/2024
  +=============================================================*/  
  
enum 
{
    INT,
    LOG,
    CONST
};

#define TAM_TAB 100

struct elemTabSimbolos
{
    char id[100]; 
    int end;      
    int tip;      
} tabSimb[TAM_TAB], elemTab;

// tabela de constantes 
struct contsTabela
{
    char id[100];
    char conteudo[1000];
} constTab[TAM_TAB]; 

int tamanhoAtualTabelaConsts = 0;

// insere uma constante 
int insereConstante(char * id, char * conteudo){
    if(tamanhoAtualTabelaConsts == TAM_TAB){
        yyerror("Tabela de constantes cheia!");
        return -1;
    } else {
        strcpy(constTab[tamanhoAtualTabelaConsts].id, id);
        strcpy(constTab[tamanhoAtualTabelaConsts].conteudo, conteudo);
        tamanhoAtualTabelaConsts++;
        return (tamanhoAtualTabelaConsts-1);
    }
}

// inicia a tabela de constantes com os valores para espaço e pula de linha
void iniciaTabelaConstantes()
{
    insereConstante("_esp", "\" \"");
    insereConstante("_ent", "\"\\n\"");
}

int posTab = 0;

int buscaSimbolo(char *s)
{
    int i;
    for (i = posTab - 1; strcmp(tabSimb[i].id, s) && i >= 0; i--) 
        ;
    if (i == -1) 
    {
        char msg[200];
        sprintf(msg, "Identificador [%s] não encontrado!", s);
        yyerror(msg);
    }
    return i; 
}

void criaData(){
    if(posTab == 0) {
        return;
    } else {
        for(int i = 0; i < posTab; i++){
            tabSimb[i].id;
        }
    }
}

void insereSimbolo(struct elemTabSimbolos elem)
{
    int i;
    if (posTab == TAM_TAB)
        yyerror("Tabela de Simbolos cheia!");
    for (i = posTab - 1; strcmp(tabSimb[i].id, elem.id) && i >= 0; i--)
        ;
    if(i != -1)
    {
        char msg[200];
        sprintf(msg, "Idenficador [%s] duplicado!", elem.id);
        yyerror(msg);
    }
    tabSimb[posTab++] = elem;
}

#define TAM_PIL 100

int pilha[TAM_PIL];
int topo = -1;

void empilha (int valor)
{
    if (topo == TAM_PIL)
        yyerror("Pilha semantica cheia!");
    pilha[++topo] = valor;
}

int desempilha(void)
{
    if (topo == -1)
        yyerror("Pilha semantica vazia!");
    return pilha[topo--];
}

void testaTipo (int tipo1, int tipo2, int ret)
{
    int t1 = desempilha();
    int t2 = desempilha();
    if (t1 != tipo1 || t2 != tipo2)
        yyerror("Incompatibilidade de tipo");
    empilha(ret);
}