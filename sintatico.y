/*+=============================================================
  |           UNIFAL - Universidade Federal de Alfenas.
  |             BACHARELADO EM CIENCIA DA COMPUTACAO.
  |  Trabalho: Geracao de codigo MIPS
  |  Disciplina: Compiladores
  |  Professor: Luiz Eduardo da Silva
  |  Aluno: Maicon Almeida Mian
  |  Data: 04/12/2024
  +=============================================================*/  

%{
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "lexico.c"
#include "utils.c"

int contaVar = 0;
int tipo;
int rotulo = 0;
%}

%token T_PROGRAMA
%token T_INICIO
%token T_FIMPROG
%token T_LEIA
%token T_ESCREVA
%token T_SE 
%token T_ENTAO
%token T_SENAO
%token T_FIMSE 
%token T_ENQUANTO
%token T_FACA
%token T_FIMENQTO
%token T_MAIS
%token T_MENOS
%token T_VEZES
%token T_MAIOR
%token T_MENOR
%token T_DIV
%token T_IGUAL
%token T_E
%token T_OU 
%token T_NAO 
%token T_ATRIB 
%token T_ABRE
%token T_FECHA
%token T_INTEIRO
%token T_LOGICO
%token T_V 
%token T_F 
%token T_IDENTIF
%token T_NUMERO
%token T_CONSTLITERAL

%start programa 

%left T_MAIS T_MENOS
%left T_VEZES T_DIV
%left T_MAIOR T_MENOR T_IGUAL
%left T_E T_OU 

%%
programa 
    : cabecalho 
            {
                // inicia cabecalho do programa
                fprintf(yyout, ".text\n");
                fprintf(yyout, "\t.globl main\n");
                fprintf(yyout, "main:\tnop\n");
                // inicia a tabela de constantes com os valores de espaço e pula de linha
                iniciaTabelaConstantes();
                contaVar = 0; // inicia o numero de variaveis
            }
        variaveis 
            { 
                empilha(contaVar); // empilha o número de variaveis
            }
        T_INICIO lista_comandos T_FIMPROG
            {   

                int tmp = desempilha();

                // colocando no lugar do FIMP 
                fprintf(yyout, "fim:\tnop\n");
                fprintf(yyout, "\tli $v0, 10\n");
                fprintf(yyout, "\tli $a0, 0\n");
                fprintf(yyout, "\tsyscall\n");
                
                // iniciando .data
                fprintf(yyout, ".data\n");
                // colocando valores da tabela de simbolos
                for(int i = 0; i < posTab; i++){
                    fprintf(yyout, "\t%s: .word 1\n", tabSimb[i].id);
                }
                // colocando os constantes literais
                for(int i = 0; i < tamanhoAtualTabelaConsts; i++){
                    fprintf(yyout, "\t%s: .asciiz %s\n", constTab[i].id, constTab[i].conteudo);
                }

            }
    ;

cabecalho 
    : T_PROGRAMA T_IDENTIF
    ;

variaveis
    : 
    | declaracao_variaveis
    ;

declaracao_variaveis 
    : tipo lista_variaveis declaracao_variaveis
    | tipo lista_variaveis
    ;

tipo 
    : T_INTEIRO { tipo = INT; }
    | T_LOGICO  { tipo = LOG; }
    ;

lista_variaveis
    : lista_variaveis T_IDENTIF
        { 
            strcpy(elemTab.id, atomo); 
            elemTab.end = contaVar;
            elemTab.tip = tipo;
            insereSimbolo(elemTab);
            contaVar++; 
        }
    | T_IDENTIF
        { 
            strcpy(elemTab.id, atomo);
            elemTab.end = contaVar;
            elemTab.tip = tipo;
            insereSimbolo(elemTab);
            contaVar++; 
        }
    ;

lista_comandos
    : lista_comandos comando 
    | 
    ;

comando
    : leitura
    | escrita
    | repeticao
    | selecao
    | atribuicao
    ;

leitura 
    
    : T_LEIA 

        {   
            // chamada de sistema para leitura
            fprintf(yyout,"\tli $v0, 5\n");
            fprintf(yyout,"\tsyscall\n");
        } 
        
        T_IDENTIF
        
        {   
            // armazena o valor lido no registrador para a variável
            int posicao = buscaSimbolo(atomo);
            fprintf(yyout,"\tsw $v0, %s\n", tabSimb[posicao].id);
        }
    ;

escrita 
    : T_ESCREVA 
    expressao
    { 
        // desempilhando o tipo
        int tipo = desempilha();
        // verificando o tipo de chamada de sistema que deverá ser feita 
        if(tipo == INT || tipo == LOG){
            // chamada de sistema para escrever valor 
            fprintf(yyout,"\tli $v0, 1\n");
            fprintf(yyout,"\tsyscall\n");
            // printando proxima linha 
            fprintf(yyout,"\tla $a0 %s\n", constTab[1].id);
            fprintf(yyout,"\tli $v0, 4\n");
            fprintf(yyout,"\tsyscall\n");
        } else if(tipo == CONST){
            // chamada de sistema para escrever string 
            fprintf(yyout,"\tli $v0, 4\n");
            fprintf(yyout,"\tsyscall\n");
        }
    }
    ;

repeticao   
    : T_ENQUANTO
        { 
            // iniciando posição para o retorno
            rotulo++;
            fprintf(yyout,"L%d:\tnop\n", rotulo);
            empilha(rotulo);
        }
        expressao T_FACA 
        { 
            int tipo = desempilha();
            if (tipo != LOG)
                yyerror("Incompatibilidade de tipo na repetição!");

            // comparando condicao
            rotulo++;
            fprintf(yyout,"\tbeqz $a0, L%d\n", rotulo);
            empilha(rotulo);
        }
        lista_comandos T_FIMENQTO
        { 
            // desempilhando valores para o fim do laço
            int fimDoLaco = desempilha(); 
            int desvioSempre = desempilha(); 

            fprintf(yyout,"\tj L%d\n", desvioSempre);
            fprintf(yyout,"L%d:\tnop\n", fimDoLaco);
        }
    ;

selecao 
    : T_SE expressao 
    T_ENTAO
    {
        int tipo = desempilha();

        if (tipo != LOG)
            yyerror("Incompatibilidade de tipo na seleção!");

        // fazendo a comparacao necessaria e o desvio
        ++rotulo;
        fprintf(yyout,"\tbeqz $a0, L%d\n", rotulo);
        empilha(rotulo);
    } 
    lista_comandos 
    T_SENAO 
    {
        int entradaSenao = desempilha();

        ++rotulo;
        // desvio para o fim do entao
        fprintf(yyout,"\tj L%d\n", rotulo);
        empilha(rotulo);
        // entrada do senao
        fprintf(yyout,"L%d:\tnop\n", entradaSenao);
    }
    lista_comandos 
    T_FIMSE
    {
        // fim do se 
        int saidaEntao = desempilha();
        fprintf(yyout,"L%d:\tnop\n", saidaEntao);
    }
    ;

atribuicao
    : T_IDENTIF 
    { 
        int pos = buscaSimbolo(atomo);
        empilha(pos);
    }
    T_ATRIB expressao
    {
        int tipo = desempilha(); 
        int pos = desempilha(); 

        if (tipo != tabSimb[pos].tip)
            yyerror("Incompatibilidade de tipos");
        // colocando na variavel o valor do registrador a0
        fprintf(yyout,"\tsw $a0, %s\n", tabSimb[pos].id);
    }
    ;

expressao
    : expressao T_MAIS 
        {   // empilhando
            fprintf(yyout,"\tsw $a0 0($sp)\n"); 
            fprintf(yyout,"\taddiu $sp $sp -4\n"); 
        } 
    expressao    
        {
            testaTipo(INT, INT, INT);
            // desempilhando o valor, os operandos estao em a0 e t1
            fprintf(yyout,"\tlw $t1 4($sp)\n"); 
            fprintf(yyout,"\taddiu $sp $sp 4\n"); 
            // somo o valor de t1 + a0 e coloco na pilha
            fprintf(yyout,"\tadd $a0, $t1, $a0\n");
        }

    | expressao T_MENOS
        {
            // empilho
            fprintf(yyout,"\tsw $a0 0($sp)\n"); 
            fprintf(yyout,"\taddiu $sp $sp -4\n"); 
        } 
    expressao   
        {
            testaTipo(INT, INT, INT);
            // desempilhando o valor, os operando estao em a0 e a1
            fprintf(yyout,"\tlw $t1 4($sp)\n"); 
            fprintf(yyout,"\taddiu $sp $sp 4\n"); 
            // subtraio o valor de t1 + a0 e coloco na pilha
            fprintf(yyout,"\tsub $a0, $t1, $a0\n");
        }
    | expressao T_VEZES 
        {
            // empilho
            fprintf(yyout,"\tsw $a0 0($sp)\n"); 
            fprintf(yyout,"\taddiu $sp $sp -4\n"); 
        } 
    expressao   
        { 
            testaTipo(INT, INT, INT);
            // desempilhando o valor, os operando estao em a0 e a1
            fprintf(yyout,"\tlw $t1 4($sp)\n"); 
            fprintf(yyout,"\taddiu $sp $sp 4\n"); 

            // multiplicando e colocando o valor em a0 da parte baixa
            fprintf(yyout,"\tmult $t1, $a0\n");
            fprintf(yyout,"\tmflo $a0\n");
        }
    | expressao T_DIV 
        {
            // empilhando
            fprintf(yyout,"\tsw $a0 0($sp)\n"); 
            fprintf(yyout,"\taddiu $sp $sp -4\n");
        } 
    expressao     
        {
            testaTipo(INT, INT, INT);
            // desempilhando
            fprintf(yyout,"\tlw $t1 4($sp)\n"); 
            fprintf(yyout,"\taddiu $sp $sp 4\n"); 
            // dividindo e colocando o valor em a0 do quociente
            fprintf(yyout,"\tdiv $t1, $a0\n");
            fprintf(yyout,"\tmflo $a0\n");
        }
    | expressao T_MAIOR 
        {
            // empilhando
            fprintf(yyout,"\tsw $a0 0($sp)\n"); 
            fprintf(yyout,"\taddiu $sp $sp -4\n");
        } 
    expressao   
        {
            testaTipo(INT, INT, LOG);
            // desempilhando
            fprintf(yyout,"\tlw $t1 4($sp)\n"); 
            fprintf(yyout,"\taddiu $sp $sp 4\n"); 
            // colocando em a0 1 se a0 fr maior
            fprintf(yyout,"\tslt $a0, $a0, $t1\n"); 
        }
    | expressao T_MENOR 
        {
            // empilhando 
            fprintf(yyout,"\tsw $a0 0($sp)\n"); 
            fprintf(yyout,"\taddiu $sp $sp -4\n");
        } 
    expressao   
        {
            testaTipo(INT, INT, LOG);
            // desempilhando
            fprintf(yyout,"\tlw $t1 4($sp)\n"); 
            fprintf(yyout,"\taddiu $sp $sp 4\n"); 
            // colocando em a0 1 se a0 for menor
            fprintf(yyout,"\tslt $a0, $t1, $a0\n");  
        }
    | expressao T_IGUAL 
        {
            // empilhando
            fprintf(yyout,"\tsw $a0 0($sp)\n"); 
            fprintf(yyout,"\taddiu $sp $sp -4\n");
        } 
    expressao   
        {
            testaTipo(INT, INT, LOG);
            // desempilhando
            fprintf(yyout,"\tlw $t1 4($sp)\n"); 
            fprintf(yyout,"\taddiu $sp $sp 4\n");

            rotulo++; 
            int verificaIguais = rotulo;
            // verifica se os dois sao iguais, se for salta pro fim
            fprintf(yyout, "\tbeq $a0, $t1, L%d\n", verificaIguais);
            empilha(verificaIguais); 

            rotulo++;
            int saidaDoIgual = rotulo;
            // se nao forem iguais, coloca 0 no a
            fprintf(yyout, "\tli $a0, 0\n"); 
            fprintf(yyout, "\tj L%d\n", saidaDoIgual);
            empilha(saidaDoIgual);

            int fimIgual = desempilha();
            int saltoSeIgual = desempilha();

            // lugar que deve ser saltado
            fprintf(yyout, "L%d:\tli $a0, 1\n", saltoSeIgual); 
            fprintf(yyout, "L%d:\tnop\n", fimIgual); 
        }
    | expressao T_E 
        {
            // empilha
            fprintf(yyout,"\tsw $a0 0($sp)\n"); 
            fprintf(yyout,"\taddiu $sp $sp -4\n");
        }
    expressao       
        {
            testaTipo(LOG, LOG, LOG);

            // desempilha
            fprintf(yyout,"\tlw $t1 4($sp)\n"); 
            fprintf(yyout,"\taddiu $sp $sp 4\n");


            rotulo++;
            int verificaVerdadeiro = rotulo;
            // verifica se algum dos dois e falso e salta para o fim se for
            fprintf(yyout, "\tbeqz $a0, L%d\n", verificaVerdadeiro);
            fprintf(yyout, "\tbeqz $t1, L%d\n", verificaVerdadeiro);
            empilha(verificaVerdadeiro); 

            rotulo++;
            int verdadeiro = rotulo;
            // se nao saltou e a0 recebe true
            fprintf(yyout, "\tli $a0, 1\n"); 
            fprintf(yyout, "\tj L%d\n", verdadeiro);
            empilha(verdadeiro);

            int fimFalso = desempilha();
            int fimE = desempilha();

            fprintf(yyout, "L%d:\tli $a0, 0\n", fimE); 
            fprintf(yyout, "L%d:\tnop\n", fimFalso); 
        }
    | expressao T_OU 
        {
            // empilha
            fprintf(yyout,"\tsw $a0 0($sp)\n"); 
            fprintf(yyout,"\taddiu $sp $sp -4\n");
        } 
    expressao      
        { 
            // desempilha
            testaTipo(LOG, LOG, LOG);
            fprintf(yyout,"\tlw $t1 4($sp)\n"); 
            fprintf(yyout,"\taddiu $sp $sp 4\n");

            rotulo++;
            int desvioSeVerdadeiro = rotulo;
            // verifica se qualquer um dos dois e verdadeiro
            fprintf(yyout, "\tbnez $a0, L%d\n", desvioSeVerdadeiro);
            fprintf(yyout, "\tbnez $t1, L%d\n", desvioSeVerdadeiro);
            empilha(desvioSeVerdadeiro); 


            rotulo++;
            int fimFalso = rotulo;
            // se passou pelos anteriores e porque e falso
            fprintf(yyout, "\tli $a0, 0\n"); 
            fprintf(yyout, "\tj L%d\n", fimFalso);
            empilha(fimFalso);

            int fimOU = desempilha();
            int desvioVerdadiero = desempilha();

            fprintf(yyout, "L%d:\tli $a0, 1\n", desvioVerdadiero); 
            fprintf(yyout, "L%d:\tnop\n", fimOU);  
        }
    | termo
    ;

termo
    : T_NUMERO      
    {
        // empilhando valor inteiro
        fprintf(yyout,"\tli $a0 %s\n", atomo);
        empilha(INT); 
    }
    | T_IDENTIF    
    { 
        int pos = buscaSimbolo(atomo); 
        // coloca em a0 o valor da variavel
        fprintf(yyout, "\tlw $a0 %s\n", tabSimb[pos].id); 
        empilha(tabSimb[pos].tip);
    }
    | T_V 
    {
        // coloca em a0 o valor verdadeiro
        fprintf(yyout,"\tli $a0 1\n"); 
        empilha(LOG);
    }
    | T_F          
    { 
        // coloca em a0 o valor falso
        fprintf(yyout,"\tli $a0 0\n"); 
        empilha(LOG);
    }
    | T_NAO termo   
    {
        // desempilha o ultimo permo 
        int tipo = desempilha(); 
        if(tipo != LOG)
            yyerror("Incompatibilidade de tipos");
        empilha(LOG);

        rotulo++;
        int desvioSeFalso = rotulo;
        // se for falso, desvia 
        fprintf(yyout, "\tbeqz $a0, L%d\n", desvioSeFalso); 
        empilha(desvioSeFalso);
        // se for verdadeiro, a0 recebe 0 (negando)
        fprintf(yyout, "\tli $a0, 0\n"); 

        rotulo++;
        int saiAposNegar = rotulo;
        // de qualquer forma pula para o fim
        fprintf(yyout, "\tj L%d\n", saiAposNegar);
        empilha(saiAposNegar);

        int saidaNao = desempilha();
        int verdadeiro = desempilha();

        // se for verdadieor, a0 recebe 1
        fprintf(yyout, "L%d:\tli $a0, 1\n", verdadeiro); 
        fprintf(yyout, "L%d:\tnop\n", saidaNao);

    }
    | T_ABRE expressao T_FECHA
    | T_CONSTLITERAL
    {
        // empilha a constante
        empilha(CONST); 
        // cria o id da constante
        char id[100];
        sprintf(id, "_const%d", tamanhoAtualTabelaConsts-2);
        // coloca o conteudo nela
        char conteudo[100];
        strcpy(conteudo, atomo);
        // insere na tabela
        insereConstante(id, conteudo);
        // coloca em a0 o id da constante
        fprintf(yyout,"\tla $a0 %s\n", id);
    }
    ;

%%

int main(int argc, char *argv[]) {

    char nameIn[30], nameOut[30], *p;

    if(argc < 2){
        printf("Uso:\n\t%s <nomefonte>[.simples]\n", argv[0]);
        return 10;
    }

    p = strstr(argv[1], ".simples");
    if (p) *p = 0;

    strcpy(nameIn, argv[1]);
    strcat(nameIn, ".simples");

    strcpy(nameOut, argv[1]);
    strcat(nameOut, ".asm");
    

    yyin = fopen(nameIn, "rt");
    
    // verificando se o arquivo existe
    if(yyin == NULL){
        puts("Arquivo com o nome especificado nao encontrado\n");
        return 1;
    }

    yyout = fopen(nameOut, "wt");

    yyparse();
    puts("Programa compilado com sucesso!\n");

    fclose(yyin);
    fclose(yyout);
    return 0;

}
