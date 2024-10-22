#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

typedef struct fila {
    int *primeiro, *ultimo;
    int tamanhoatual;
    int *itens;
} Fila;

void criar (Fila *fila){
    fila->tamanhoatual = 0;
    fila->itens = NULL;
    fila->primeiro = NULL;
    fila->ultimo = NULL;
}

bool vazia(Fila *fila){
    return(fila->tamanhoatual == 0);
}

void inserir (Fila *fila, int valor){
    int *novovalor = malloc(sizeof(int));
    *novovalor = valor;
    if (vazia(fila)){
        fila->itens = novovalor;
        fila->primeiro = novovalor;
        fila->ultimo = novovalor;
    } else {
        fila->ultimo++;
        *fila->ultimo = valor;
    }

    fila->tamanhoatual++;
}

void remover (Fila *fila){
    fila->tamanhoatual--;
    fila->primeiro++;
}

void mostrar(Fila *fila){
    int mostra = 0;
    int indice = 0;
    while (mostra < fila->tamanhoatual){
        printf("%d ", fila->itens[indice]);
        indice++;
        mostra++;
    }
    printf("\n");
}

int main (){
    Fila fila;
    int valor, continua = 0;
    criar(&fila);

    printf("Digite o que voce deseja fazer: \n 1. Inserir \n 2. Remover \n 3. Mostrar \n 0. Sair \n");
    scanf("%d", &continua);

    while ( continua != 0 ){
        if (continua == 1){
            printf("Digite o valor que voce deseja adicionar:");
            scanf("%d", &valor);
            inserir (&fila, valor);
        } else if (continua == 2) {
            remover(&fila);
            printf("Valor removido com sucesso!");
        } else if (continua == 3){
            mostrar(&fila);
        }

        printf("Digite o que voce deseja fazer: \n 1. Inserir \n 2. Remover \n 3. Mostrar \n 0. Sair \n");
        scanf("%d", &continua);
        
    }

    free(fila.itens);

    return 0;

}