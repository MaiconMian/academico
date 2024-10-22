#include <stdio.h>
#include <stdlib.h>

typedef struct PILHA 
{
    int valor;
    struct PILHA * prox;
}pilha;

void inserir (pilha ** topo, int valor)
{
    pilha * novo = malloc(sizeof(pilha));
    (*novo).valor = valor;
    (*novo).prox = (*topo);
    (*topo) = novo;
}

pilha * remover (pilha ** topo)
{
    pilha * guarda = (*topo);
    (*topo) = (*(*topo)).prox;
    return guarda;
}

void mostrar (pilha ** topo)
{
    pilha * tmp = (*topo);
    while (tmp != NULL){
        printf("%d\t", (*tmp).valor);
        tmp = (*tmp).prox;
    }
    printf("\n");
}

int main()
  {
    int valor;
    pilha * topo = NULL;
    int continua;
    printf("Digite o que deseja fazer: \n1.empilhar\n2.desempilhar\n3.mostrar\n0.sair\n");
    scanf("%d", &continua);
    while (continua != 0){
        if (continua == 1){
            printf("Digite o valor que deseja empilhar:");
            scanf("%d", &valor);
            inserir(&topo, valor);
        } else if (continua == 2){
           if(topo == NULL){
            printf("pilha vazia!\n");
           } else {
                pilha * desempilhado = remover(&topo);
                printf("O valor removido foi %d\n", (*desempilhado).valor);
                free(desempilhado);
           }
        } else if (continua == 3){
            mostrar(&topo);
        }
        printf("Digite o que deseja fazer: \n1.empilhar\n2.desempilhar\n3.mostrar\n0.sair\n");
        scanf("%d", &continua);
    }
    return 0;
}