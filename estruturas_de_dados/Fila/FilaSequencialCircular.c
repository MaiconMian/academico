#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

typedef struct fila {
    int tamanhoatual, tamanhomax, primeiro, ultimo;
    int *item;
} fila;

bool cheia(fila *Fila) {
    return (Fila->tamanhoatual == Fila->tamanhomax);
}

bool vazia(fila *Fila) {
    return (Fila->tamanhoatual == 0);
}

void criar(fila *Fila, int tamanho) {
    Fila->tamanhomax = tamanho;
    Fila->tamanhoatual = 0;
    Fila->primeiro = 0;
    Fila->ultimo = -1; 
    Fila->item = (int *)malloc(tamanho * sizeof(int));
}

void inserir(fila *Fila, int valor) {
    if (!cheia(Fila)) {
        Fila->ultimo = (Fila->ultimo + 1) % Fila->tamanhomax;
        Fila->item[Fila->ultimo] = valor;
        Fila->tamanhoatual++;
    } else {
        printf("Fila Cheia!\n");
    }
}

void remover (fila *Fila){
    if (vazia(Fila)){
        printf("Nao há nada para remover! A lista está vazia.");
    } else {
        Fila->primeiro = (Fila->primeiro + 1) % Fila->tamanhomax;
        Fila->tamanhoatual--;
    }
}

void mostrar(fila *Fila) {
    int rodar = 0, indice = Fila->primeiro;
    while (rodar < Fila->tamanhoatual) {
        printf("%d ", Fila->item[indice]);
        indice = (indice + 1) % Fila->tamanhomax;
        rodar++;
    }
    printf("\n");
}

int main() {
    int tamanho, valor;
    fila Fila;

    printf("Tamanho da fila:");
    scanf("%d", &tamanho);
    criar(&Fila, tamanho);

    int continua = 10;
    printf("Digite o que desenja fazer: \n 1 - adicionar \n 2 - remover \n 3 - mostrar \n 0 - sair.\n");
    scanf("%d", &continua);

    while (continua != 0) {

        if (continua == 1){
            if (cheia(&Fila)){
                printf("Sua fila esta cheia\n");
            } else {
                printf("Digite o valor que deseja adicionar: ");
                scanf("%d", &valor);
                inserir(&Fila, valor);
            }
        } else if (continua == 2){
            printf("Digite o valor que deseja remover: ");
            remover(&Fila);
        } else if( continua == 3){
            mostrar(&Fila);
        }

        printf("Digite o que desenja fazer: \n 1 - adicionar \n 2 - remover \n 3 - mostrar \n 0 - sair.\n");
        scanf("%d", &continua);
        
    }

    free(Fila.item);
    return 0;
}
