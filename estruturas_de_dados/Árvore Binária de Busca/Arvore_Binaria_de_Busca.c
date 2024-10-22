#include <stdio.h>
#include <stdlib.h>

typedef struct no_{
    int valor;
    struct no_ * direita;
    struct no_ * esquerda;
}no;

no * busca (no * raiz, int valor){
    if (raiz == NULL || (*raiz).valor == valor){
        return raiz;
    }
    if ((*raiz).valor > valor){
        return busca((*raiz).esquerda, valor);
    } else {
        return busca((*raiz).direita, valor);
    }
}

no * insere (no * raiz, no * novo){
    if (raiz == NULL)
        return novo;
    if ((*raiz).valor > (*novo).valor){
        (*raiz).esquerda = insere((*raiz).esquerda, novo);
    } else {
        (*raiz).direita = insere((*raiz).direita, novo);
    }
    return raiz;
}

void visita(no * raiz){
    printf("%d\t", (*raiz).valor);
}

void emordem (no * raiz){
    if ((*raiz).esquerda != NULL){
        emordem((*raiz).esquerda);
    }
    visita(raiz);
    if ((*raiz).direita != NULL){
        emordem((*raiz).direita);
    }
}

int main (){
    no * raiz = NULL;
    int continua, valor;
    continua = 1;
    while (continua != 0){
        printf("Digite o que deseja fazer: 1.inserir 2.mostrar 0.sair");
        scanf("%d", &continua);
        if (continua == 1){
            printf("Digite o valor que deseja adicionar:");
            scanf("%d", &valor);
            no * novo = malloc(sizeof(no));
            (*novo).direita = (*novo).esquerda = NULL;
            (*novo).valor = valor;
            raiz = insere(raiz, novo);
        } else if (continua == 2){
            if (raiz != NULL){
                emordem(raiz);
                printf("\n");
            } else {
                printf("Arvore Vazia!\n");
            }
            
        }
    }
}