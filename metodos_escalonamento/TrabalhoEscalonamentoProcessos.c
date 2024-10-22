/*+-------------------------------------------------------------+
 | UNIFAL – Universidade Federal de Alfenas.                    |
 | BACHARELADO EM CIENCIA DA COMPUTACAO.                        |
 | Trabalho..: Metodos de Escalonamento                         |
 | Disciplina: Algoritmos e Estrutura de Dados II – Pratica     |
 | Professor.: Fellipe Rey                                      |
 | Aluno(s)..: Maicon Almeida Mian 2023.1.08.013                |
 |             Caio Henrique Costa de Matos 2023.1.08.002       |
 |             Nycole Paulino dos Santos 2023.1.08.044          |
 | Data......: 13/12/2023                                       |
 +-------------------------------------------------------------+*/

#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <unistd.h>
#include "TrabalhoEscalonamentoProcessos.h"

/*
    Cria o tamanho do processo indo de 1 a 20
    @return: o tamanho inteiro do novo processo
*/
int Cria_Tamanho_Processo (){
    return (int) (rand() % 20) + 1;
}

/*
    Confere a probabilidade de um novo processo ser criado
    @return 1: para criar processo;  
    @return 0: para não criar.
*/
int Probabilidade (){
    int probabilidade = (rand() % 100); //gera um numero de 0 a 100
    if (probabilidade < 30){ //caso o numero gerado seja menor que 30, ele cria o processo
        return 1; //30% de chance de criar
    } else {
        return 0; //70% de chance de não criar
    }
}

/*
    Função para iniciar um novo nó
    @paramêtro tamanho: traz o tamanho do novo nó
    @return novo: nó alocado
*/
No * Inicia_Novo_No (int tamanho){
  No * novo = (No *) malloc(sizeof(No));
  (*novo).tamanho = tamanho;
  (*novo).proximo = NULL;
  return novo;
}

/*
    Inicia a fila 
    @param fila - traz a fila a ser inciada
*/
void Inicia_Fila (Fila * fila){
    (*fila).primeiro = NULL;
    (*fila).ultimo = NULL;
    (*fila).Qant_Processos = 0;
}

/*
    Remove um item da fila 
    @param fila - traz a fila a ter um item removido
*/
void Remove_Fila (Fila * fila){
    if ((*fila).primeiro != NULL){ //caso a fila tenha algum nó
        No * aux = (*fila).primeiro; //guarda o nó a ser removido
        if ((*fila).primeiro == (*fila).ultimo){ //se os dois apontavam pro mesmo nó, agora a fila vai estar vazia
          (*fila).primeiro = (*fila).ultimo = NULL;
        } else {
          (*fila).primeiro = (*(*fila).primeiro).proximo; //caso contrário e só o primeiro apontar pro proximo
        }
        free(aux); //libera memoria
    }
}

/*
    Insere um novo elemento na lista
    @param fila - traz a fila a ter um item adicionado
    @param tamanho - tamanho do processo criado
    @return - retorna o novo nó que foi alocado
*/
No * Insere_Fila (Fila * fila, int tamanho){
    (*fila).Qant_Processos++; //adiciona um na quantidade de processos já criados
    No * novo = Inicia_Novo_No(tamanho); 
    (*novo).Processo = (*fila).Qant_Processos; //faz o novo no receber o seu numero
    if ((*fila).primeiro == NULL){ //se o primeiro for nulo, basta fazer primeiro e ultimo apontarem pra ele
        (*fila).primeiro = novo;
        (*fila).ultimo =  novo;
    } else { //caso contrário, faz o ultimo apontar pro novo no
        (*(*fila).ultimo).proximo = novo;
        (*fila).ultimo =  novo;
    }
    return novo; //retorna o novo nó
}

/*
    Faz o método de escalonamento FCFS
    @param fila - traz a fila 
    @return - retorna o nó que foi executado
*/
No * FCFS (Fila * fila){
    if ((*fila).primeiro != NULL){ //se existir elemento a ser executado
        (*(*fila).primeiro).tamanho--; 
        if ((*(*fila).primeiro).tamanho < 0){ //caso já tenha sido completamente executado
            Remove_Fila(fila); 
            if ((*fila).primeiro != NULL){ //caso tenha elemento a ser executado
                (*(*fila).primeiro).tamanho--; 
            }
            
        }
    }
    return (*fila).primeiro; //retorna o nó executado
}

/*
    Inicia a Lista
    @param lista - traz a lista a  ser alocada
*/
void Inicia_Lista(Lista * lista){
    (*lista).nos = NULL;
    (*lista).executando = NULL; //urilizado no Shortest Job First
    (*lista).Qant_Processos = 0;
    (*lista).Quantum = 0; //utilizado no Roud-Robin
}

/*
    Insere em uma lista de forma ordenada
    @param lista - traz a lista a ser inserida
    @param tamanho - tamanho do processo a ser adicionado
    @return retorna o novo nó adicionado
*/
No * Insere_Lista_Ordenada (Lista * lista, int tamanho){
    (*lista).Qant_Processos++; //adiciona um na quantidade de processos
    No * novo = Inicia_Novo_No(tamanho); 
    (*novo).Processo = (*lista).Qant_Processos; //faz o processo receber seu numero

    No * aux = (*lista).nos; 
    No * ant = NULL; 

    while (aux != NULL && (*aux).tamanho <= tamanho){ //enquanto nao chegar ao fim ou encontrar um valor maior
        ant = aux;
        aux = (*aux).proximo;
    }

    if (aux == (*lista).nos){ //caso tenha que adicionar no inicio
        (*novo).proximo = aux; 
        (*lista).nos = novo; 
    } else { //se a lista conta com mais de um no
        (*ant).proximo = novo; 
        (*novo).proximo = aux;  
    }
    return novo; //retorna o novo nó
}

/*
    Remove tanto da lista ordenada quanto da normal
    @param lista - traz a lista a  ser alocada
    @return retorna o nó removido
*/
No * Remove_Lista (Lista * lista){
    if ((*lista).nos != NULL){ //se existir nó a remover
        No * aux = (*lista).nos; 
        (*lista).nos = (*(*lista).nos).proximo; 
        return aux; //retorna o no removido
    }
}

/*
    Executa um processo na forma SJF (o de menor tamanho primeiro)
    @param lista - traz a lista a ter o nó executado
    @return retorna o nó executado
*/
No * SJF (Lista * lista){ 
    if ((*lista).executando != NULL){ //caso exista algum nó a ser exeutado
      (*(*lista).executando).tamanho--; 
      if ((*(*lista).executando).tamanho < 0){ //se ele foi executado
        free((*lista).executando); 
        (*lista).executando = NULL;
        if ((*lista).nos != NULL){ // caso existam mais processo a serem executados
          (*lista).executando = Remove_Lista(lista); 
          (*(*lista).executando).tamanho--; 
        }
      }
    } else if ((*lista).nos != NULL){ //caso não tenha nada executando mas a lista tem algum nó
        (*lista).executando = Remove_Lista(lista); 
        (*(*lista).executando).tamanho--; 
    }
    return (*lista).executando; //retorna o nó executado
}

/*
    Busca o Último elemento de uma lista
    @param lista - traz a lista a ter o nó buscado
    @return retorna o ultimo nó
*/
No * Buscar_Ultimo_Lista (Lista * lista){
  No * aux = (*lista).nos;
  if ((*lista).nos != NULL){ //caso exista algum nó 
      while ((*aux).proximo != NULL){
        aux = (*aux).proximo; //roda a lista até chegar no ultimo
      }
  }
  return aux; //retorna o ultimo
}

/*
    Coloca o nó na ultima posicao 
    @param lista - traz a lista a ter o nó inserido
    @param novo - traz o nó a ser colocado na ultima posicao
*/
void Transposicao_Lista(Lista * lista, No * novo){
    No * Ultimo = Buscar_Ultimo_Lista(lista); //busca a ultima posicao
    if (Ultimo == NULL){ 
      (*lista).nos = novo;
    } else { 
      (*novo).proximo = (*Ultimo).proximo; 
      (*Ultimo).proximo = novo; 
    }
}
/*
    Insere na lista de forma nao ordenada
    @param lista - traz a lista a ter o nó inserido
    @param tamanho - tras o tamanho do processo a ser inserido
*/
No * Insere_Lista (Lista * lista, int tamanho){
    (*lista).Qant_Processos++; //soma um na quantidade de processos da lista
    No * novo = Inicia_Novo_No(tamanho); 
    (*novo).Processo = (*lista).Qant_Processos; 
    Transposicao_Lista(lista, novo); //coloca o novo nó no fim
    return novo; //retorna o nó alocado
}

/*
    Executa no Formato RR com 6 Quantums
    @param lista - traz a lista a ter o processo executado
    @param retorna o processo executado
*/
No * RR (Lista * lista){
    //se existir elemento a ser executado e ele ja completou seu quantum ou já finalizou
    if ((*lista).nos != NULL && ((*lista).Quantum == 6 || (*(*lista).nos).tamanho == 0)){  
        (*lista).Quantum = 0; 
        No * removido = Remove_Lista(lista);
        if ((*removido).tamanho == 0){ //se ele já foi executado
            free(removido);
        } else {
            Transposicao_Lista(lista, removido); //se ele não acabou, é necessario voltar a lista
        }
    } 
    if((*lista).nos != NULL) { //caso o nó a ser executado exista
       (*lista).Quantum++; 
       (*(*lista).nos).tamanho--; 
    }
    return (*lista).nos; //retorna o nó executado
}

int main(){
    //fazendo com que a semente do rand seja baseada no tempo do computador 
    srand(time(NULL));
    //iniciando as estruturas de dados fila e lista
    Fila * fila = (Fila *) malloc(sizeof(Fila));
    Lista * lista = (Lista *) malloc(sizeof(Lista));
    //iniciando variáveis inteiras 
    int escolha, contador_iteracoes = 1, n_iteracoes;
    No * saida; // iniciando uma variavel do tipo no para guardar o retorno das funções

    //imprimindo na tela as opções de escolha
    printf("\n\tTrabalho de AEDS 2 | Escalonamento de Processos\n\t\t    Maicon, Caio e Nycole\n\n");
    printf("\tDigite qual das formas de escalonamento você deseja:\n\t\t1. FCFS (First Come, First Served)\n\t\t2. SJF (Shortest Job First)\n\t\t3. RR (Roud-Robin)> ");
    scanf("%d", &escolha);
    //conferindo se e uma escolha válida
    while (escolha < 1 || escolha > 3){
        printf("\tOpcao Inválida! Digite novamente:");
        scanf("%d", &escolha);
    }

    //inicia a estrutura de dados correspondente a forma de escalonamento
    if (escolha == 1){
        Inicia_Fila(fila);
    } else if (escolha == 2 || escolha == 3){
        Inicia_Lista(lista);
    }

    //solicitacao da entrada para o numero de iteracoes
    printf("\n\tDigite o numero de iteracoes que voce deseja\n\tPS: para fins de teste, valores menores ou iguais a 0 levarao a um numero INFINITO de iteracoes> ");
    scanf("%d", &n_iteracoes);

    printf("\n");

    if (n_iteracoes <= 0){
        n_iteracoes = -1;
    }

    //cabeçalho da tabela
    printf("\n\t==============================================================\n");
    printf("\t|   Iteracao   |    Processo Executado    |  Processo Criado |\n");
    printf("\t|              |      n      |  Restante  |      n      |Tam.|\n");
    printf("\t==============================================================\n");

    //enquanto iteracoes for diferente de 0
    while(n_iteracoes != 0){
        printf("\t|%14d|", contador_iteracoes); //mostra na tabela a iteração atual

        switch(escolha){ //a depender da escolha da forma de escalonamento
            case 1:
                saida = FCFS(fila); 
                break;
            case 2:
                saida = SJF(lista);
                break;
          case 3:
                saida = RR(lista);
                break;
        }
        //se algum processo foi efetivamente executado, mostra em tela
        if (saida != NULL){
            printf("%13d|%12d|", (*saida).Processo, (*saida).tamanho);
        } else {
            printf("             |            |"); //apenas preenche caso nada tenha sido executado
        }

        //confere a probabilidade de 30% para criação de novo processo
        if (Probabilidade() == 1){
            switch(escolha){
                case 1:
                    saida = Insere_Fila(fila, Cria_Tamanho_Processo()); //insere novo processo na fila
                    break;
                case 2:
                    saida = Insere_Lista_Ordenada(lista, Cria_Tamanho_Processo()); //insere de forma ordenada o novo processo
                    break;
                case 3:
                   saida = Insere_Lista(lista, Cria_Tamanho_Processo()); //insere na lista 
                   break;
            }
            printf("%13d|%4d|\n", (*saida).Processo, (*saida).tamanho); //mostra o processo criado em tela
        } else {
            printf("             |    |\n"); //preenche a tabela caso nenhum processo tenha sido criado
        }

        //se o numero de iterações for maior que 0, decresce o valor 
        if (n_iteracoes >= 0){
            n_iteracoes--;
        }    
        contador_iteracoes++; //aumenta um no número de iterações
        sleep(1); //atrasa a execução para melhor visualização
    }

    //fecha a tabela
    printf("\t======================================================================\n");

    //limpa a memória
    free(lista);
    free(fila);

    return 0;
}